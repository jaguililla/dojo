/*
 * Binary.java
 * Creado en: 02-jun-2005 23:32:03 por: juanjo
 */

// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.util;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * .
 * TODO Pendiente de documentar
 * @author jamming
 */
public class Util {
    /**
     * Constructor.
     * TODO Pendiente de documentar
     */
    private Util () {
        throw new IllegalStateException ("Esta clase no debe ser instanciada");
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @param value
     * @param size
     * @return Valor
     */
    public static String binString (long value, int size) {
        StringBuffer result = new StringBuffer ();
        for (int ii = size - 1; ii > -1; ii--) {
            long mask = 1 << ii;
            result.append ((value & mask) >> ii);
        }
        return result.toString ();
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @param value
     * @return Valor
     */
    public static String binString (byte value) {
        long val = 0 | value;
        return binString (val, Byte.SIZE);
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @param aFile
     * @throws java.io.IOException
     */
    public static void fileInfo (java.io.File aFile) throws java.io.IOException {
        System.out.println ("\n" + String.valueOf (aFile) + " ---------------------------");
        System.out.println ("Fichero [getAbsolutePath]: " + aFile.getAbsolutePath());
        System.out.println ("Fichero [getCanonicalPath]: " + aFile.getCanonicalPath());
        System.out.println ("Fichero [getName]: " + aFile.getName());
        System.out.println ("Fichero [getParent]: " + aFile.getParent());
        System.out.println ("Fichero [getPath]: " + aFile.getPath());

        System.out.println ("Fichero [getAbsoluteFile]: " + aFile.getAbsoluteFile().getAbsolutePath());
        System.out.println ("Fichero [getCanonicalFile]: " + aFile.getCanonicalFile().getAbsolutePath());
        java.io.File pf = aFile.getParentFile();
        System.out.println ("Fichero [getParentFile]: " + (pf == null? "'null'" : pf.getAbsolutePath()));
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @param aFile
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public static void print (java.io.File aFile) throws java.io.FileNotFoundException, java.io.IOException{
        java.io.InputStream stream = null;
        StringBuffer result = new StringBuffer ();
        byte [] buffer = new byte [256];
        String part = null;

        try {
            stream = new java.io.FileInputStream (aFile);
            int bytes = stream.read (buffer);
            while (bytes > 0) {
                part = new String (buffer, 0, bytes);
                result.append (part);
                bytes = stream.read (buffer);
            }
        }
        finally {
            if (stream != null) {
                stream.close ();
            }
        }
        System.out.println (result.toString ());
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
