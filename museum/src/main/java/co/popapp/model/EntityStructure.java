/*
 * POPAPP Copyright © 2011 Juan José Aguililla. All rights reserved.
 *
 * This program comes WITHOUT ANY WARRANTY; It is free software, you can redistribute it and/or
 * modify it under the terms of the GNU General Public License, Version 3.0.
 * You may obtain a copy of the License at: http://www.gnu.org/licenses/gpl.html
 */

// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.model;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * @author jamming
 */
public class EntityStructure {
    static Comparator<Class<?>> sClassComparator = new Comparator<Class<?>> () {
        @Override public int compare (Class<?> aO1, Class<?> aO2) {
            return aO1.getName ().compareTo (aO2.getName ());
        }
    };

    static Comparator<Constructor<?>> sConstructorComparator = new Comparator<Constructor<?>> () {
        @Override public int compare (Constructor<?> aO1, Constructor<?> aO2) {
            // TODO Sort by signature too !!!
            return aO1.getName ().compareTo (aO2.getName ());
        }
    };

    static Comparator<Method> sMethodComparator = new Comparator<Method> () {
        @Override public int compare (Method aO1, Method aO2) {
            // TODO Sort by signature too !!!
            return aO1.getName ().compareTo (aO2.getName ());
        }
    };

    static Comparator<Field> sFieldComparator = new Comparator<Field> () {
        @Override public int compare (Field aO1, Field aO2) {
            return aO1.getName ().compareTo (aO2.getName ());
        }
    };

    public final String name;
    public final long suid;

    public final Class<? extends Entity> clazz;

    public final List<FieldStructure> fields = new ArrayList<FieldStructure> ();

    public EntityStructure (Class<? extends Entity> aClass) {
        name = aClass.getSimpleName ();
        clazz = aClass;
        suid = entityHash ();
    }

    /**
     * @return .
     * @see http://docs.oracle.com/javase/7/docs/platform/serialization/spec/class.html#4100.
     * @see http://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.3
     */
    private long entityHash () {
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;

        try {
            baos = new ByteArrayOutputStream ();
            dos = new DataOutputStream (baos);

            writeClass (dos);
            writeFields (dos);
//            writeInitializer (dos);
//            writeConstructors (dos);
//            writeMethods (dos);

            // 8) SHA-1 algorithm is executed on the stream of bytes produced by DataOutputStream
            byte[] digest = MessageDigest.getInstance("SHA-1").digest (baos.toByteArray ());

            // 9) Hash value assembled from the first and second 32-bit values of the SHA-1 digest
            return
                ((digest[0] >>> 24) & 0xFF) |
                ((digest[0] >>> 16) & 0xFF) << 8 |
                ((digest[0] >>> 8) & 0xFF) << 16 |
                ((digest[0] >>> 0) & 0xFF) << 24 |
                ((digest[1] >>> 24) & 0xFF) << 32 |
                ((digest[1] >>> 16) & 0xFF) << 40 |
                ((digest[1] >>> 8) & 0xFF) << 48 |
                ((digest[1] >>> 0) & 0xFF) << 56;
        }
        catch (Exception e) {
            throw new IllegalStateException (e);
        }
        finally {
            try {
                if (baos != null)
                    baos.close ();
                if (dos != null)
                    dos.close ();
            }
            catch (Exception e) {
                e.printStackTrace ();
            }
        }
    }

    private String getDescriptor (@SuppressWarnings ("unused") Constructor<?> aConstructor) {
        throw new UnsupportedOperationException ();
    }

    private String getDescriptor (Field aField) {
        Class<?> t = aField.getType ().getClass ();
        StringBuilder b = new StringBuilder ();

        if (t.isArray ())
            return t.getName ().replace ('.', '/');

        if (t.isPrimitive ()) {
            if (t.equals (Byte.TYPE))
                b.append ('B');
            else if (t.equals (Character.TYPE))
                b.append ('C');
            else if (t.equals (Double.TYPE))
                b.append ('D');
            else if (t.equals (Float.TYPE))
                b.append ('F');
            else if (t.equals (Integer.TYPE))
                b.append ('I');
            else if (t.equals (Long.TYPE))
                b.append ('J');
            else if (t.equals (Short.TYPE))
                b.append ('S');
            else if (t.equals (Boolean.TYPE))
                b.append ('Z');
        }
        else
            b.append ('L').append (t.getName ().replace ('.', '/')).append (';');

        return b.toString ();
    }

    private String getDescriptor (@SuppressWarnings ("unused") Method aMethod) {
        throw new UnsupportedOperationException ();
    }

    /**
     * TODO .
     * @param dos
     * @throws java.io.IOException
     */
    private void writeClass (DataOutputStream dos) throws IOException {
        // 1) Class name
        dos.writeUTF (clazz.getName ());

        // 2) Class modifiers (32-bit integer)
        dos.write (clazz.getModifiers ());

        // 3) Interfaces names (sorted by name)
        Class<?>[] interfaces = clazz.getInterfaces ();
        Arrays.sort (interfaces, sClassComparator);
        for (Class<?> c : interfaces)
            dos.writeUTF (c.getName ());
    }

    /**
     * TODO .
     * @param aDos
     */
    @SuppressWarnings ("unused")
    private void writeConstructors (DataOutputStream aDos) throws SecurityException, IOException {
        // 6) For each non-private constructor sorted by method name and signature:
        //      a) The name of the method, <init>.
        //      b) The modifiers of the method written as a 32-bit integer.
        //      c) The descriptor of the method.
        Constructor<?>[] constructors = clazz.getConstructors ();
        Arrays.sort (constructors, sConstructorComparator);
        for (Constructor<?> c : constructors)
            if ( (c.getModifiers () & Modifier.PRIVATE) == 0) {
                aDos.writeUTF (c.getName ());
                aDos.writeInt (c.getModifiers ());
                aDos.writeUTF (getDescriptor (c));
            }
    }

    /**
     * TODO .
     */
    private void writeFields (DataOutputStream aDos) throws IOException {
        // 4) Member fields sorted by field (except private static and private transient fields)
        //      a) Name
        //      b) Modifiers (32-bit integer)
        //      c) Field descriptor
        Field[] ff = clazz.getFields ();
        Arrays.sort (ff, sFieldComparator);
        int privateStaticMask = Modifier.PRIVATE | Modifier.STATIC;
        int privateTransientMask = Modifier.PRIVATE | Modifier.TRANSIENT;

        for (Field f : ff) {
            int modifiers = f.getModifiers ();
            if ( ((modifiers & privateStaticMask) != privateStaticMask)
                && ((modifiers & privateTransientMask) != privateTransientMask)) {

                aDos.writeUTF (f.getName ());
                aDos.writeInt (modifiers);
                aDos.writeUTF (getDescriptor (f));
            }
        }
    }

    /**
     * TODO .
     * @param aDos
     */
    @SuppressWarnings ("unused")
    private void writeInitializer (DataOutputStream aDos) throws IOException {
        // 5) Class initializer
        //      a) The name of the method, <clinit>.
        //      b) The modifier of the method, java.lang.reflect.Modifier.STATIC, written as a
        //         32-bit integer.
        //      c) The descriptor of the method, ()V.
        Method[] methods = clazz.getMethods ();
        Arrays.sort (methods, sMethodComparator);
        for (Method m : methods)
            if ( (m.getModifiers () & Modifier.STATIC) != 0) {
                aDos.writeUTF (m.getName ());
                aDos.writeInt (m.getModifiers ());
                aDos.writeUTF (getDescriptor (m));
            }
    }

    /**
     * TODO .
     * @param aDos
     */
    @SuppressWarnings ("unused")
    private void writeMethods (DataOutputStream aDos) throws IOException {
        // 7) For each non-private method sorted by method name and signature:
        //      a) The name of the method.
        //      b) The modifiers of the method written as a 32-bit integer.
        //      c) The descriptor of the method.
        Method[] methods = clazz.getMethods ();
        Arrays.sort (methods, sMethodComparator);
        for (Method m : methods)
            if ( (m.getModifiers () & Modifier.PRIVATE) == 0) {
                aDos.writeUTF (m.getName ());
                aDos.writeInt (m.getModifiers ());
                aDos.writeUTF (getDescriptor (m));
            }
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
