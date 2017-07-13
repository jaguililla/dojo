/*
 * POPAPP Copyright © 2011 Juan José Aguililla. All rights reserved.
 *
 * This program comes WITHOUT ANY WARRANTY; It is free software, you can redistribute it and/or
 * modify it under the terms of the GNU General Public License, Version 3.0.
 * You may obtain a copy of the License at: http://www.gnu.org/licenses/gpl.html
 */

// P A C K A G E //////////////////////////////////////////////////////////////////////////////
package co.popapp;

// I M P O R T ////////////////////////////////////////////////////////////////////////////////
import static co.popapp.Validate.notNull;

import java.net.URL;

// C L A S S //////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * @author jamming
 */
public class ClassUtils {
    /**
     * <p>Java classes file name extension (<code>{@value}</code>).</p>
     */
    public static final String CLASS_EXTENSION = ".class";

    /**
     * <p>Obtains the URL of the specified class. The class must exist.</p>
     *
     * <p>This method may be useful to find out the class origin in the case of complex
     * classpaths that overlaps classes.</p>
     *
     * <p><b>Important:</b> only works with public types.</p>
     *
     * <code>
     * ClassUtils.getLocation ("java.lang.String") =
     *      jar:file:/${java.home}/jre/lib/rt.jar!/java/lang/String.class
     * ClassUtils.getLocation ("java.text.MessageFormat$Field") =
     *      jar:file:/${java.home}/jre/lib/rt.jar!/java/text/MessageFormat.class
     * ClassUtils.getLocation ("java.text.MessageFormat.Field") = ClassNotFoundException
     * ClassUtils.getLocation ("String")                        = ClassNotFoundException
     * ClassUtils.getLocation ("my.package.Foo")                = ClassNotFoundException
     * ClassUtils.getLocation ("")                              = IllegalArgumentException
     * ClassUtils.getLocation (null)                            = IllegalArgumentException
     * </code>
     *
     * @param aClass The class. Can't be <code>null</code>.
     * @return URL of the java binary file loaded for this class.
     */
//    public static URL getLocation (Class aClass) {
//        notNull (aClass, "The class can't be 'null'");
//        isTrue (Modifier.isPublic (aClass.getModifiers ()), "The type is not public");
//
//        String className = isInnerClass(aClass)?
//            getShortClassName(aClass) : // TODO Cambiar para manejar correctamente las clases internas
//            getShortClassName(aClass);
//        StringBuffer classResource = new StringBuffer (className);
//        classResource.append (CLASS_EXTENSION);
//        return aClass.getResource(classResource.toString());
//    }

    /**
     * <p>Obtains the URL of the specified object's class. The object can't be <code>null</code>.</p>
     *
     * <p>Utility method. Same as: <code>getLocation (aObject.getClass())</code>
     *
     * @param aObject The object whose class URL will be returned. Can't be <code>null</code>.
     * @return URL of the java binary file loaded for this class.
     * @see #getLocation(Class)
     */
    public static URL getLocation (Object aObject) {
        notNull (aObject, "The object can't be 'null'");
        return getLocation (aObject.getClass());
    }

    /**
     * <p>Obtains the URL of the specified class name. The class must exist.</p>
     *
     * <p>Utility method. Same as: <code>getLocation (Class.forName(aClassName))</code>
     *
     * @param aClassName The class name, must be fully qualified. Can't be empty and must exist.
     * @return URL of the java binary file loaded for this class.
     * @throws ClassNotFoundException Thrown if the class doesn't exist in the classpath.
     * @see #getLocation(Class)
     */
    public static URL getLocation (String aClassName) throws ClassNotFoundException {
        Validate.notEmpty (aClassName, "The class name can't be empty");
        return getLocation (Class.forName(aClassName));
    }

    private ClassUtils () {
        throw new IllegalStateException ();
    }
}
// E O F //////////////////////////////////////////////////////////////////////////////////////
