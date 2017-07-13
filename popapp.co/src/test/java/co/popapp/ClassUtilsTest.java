/*
 * ClassUtilsTest.java
 * Creado en: 19/05/2007 0:03:46 por: juanjose.aguililla
 */

// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import java.net.URL;

import org.junit.Ignore;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////

/**
 * .
 * TODO Pendiente de documentar
 * @author jamming
 */
public class ClassUtilsTest {
    /**
     * Test for <code>getLocation (String): URL</code>.
     */
    @Ignore public void testGetLocation_String () {
        // <<< Ok tests >>>
        try {
            String javaHome = System.getProperty("java.home");
            /*
            URL jreStringUrl = new URL ();
            URL jreMessageFormatUrl = new URL ();
            */

            // jar:file:/${java.home}/jre/lib/rt.jar!/java/util/String.class
            URL stringUrl = ClassUtils.getLocation ("java.lang.String");

            // jar:file:/${java.home}/jre/lib/rt.jar!/java/text/MessageFormat.class
            URL messageFormatUrl = ClassUtils.getLocation ("java.text.MessageFormat$Field");
        }
        catch (Throwable t) {
            t.printStackTrace();
            assertTrue(false);
        }

        // <<< Error tests >>>
        try {
            // ClassNotFoundException
            ClassUtils.getLocation ("java.text.MessageFormat.Field");
            assertTrue(false);
        }
        catch (ClassNotFoundException t) {
            assertTrue(true);
        }

        try {
            // ClassNotFoundException
            ClassUtils.getLocation ("String");
            assertTrue(false);
        }
        catch (ClassNotFoundException e) {
            assertTrue(true);
        }

        try {
            // ClassNotFoundException
            ClassUtils.getLocation ("my.package.Foo");
            fail ();
        }
        catch (ClassNotFoundException e) {
            assertTrue(true);
        }

        try {
            ClassUtils.getLocation ("");
            fail ();
        }
        catch (ClassNotFoundException e) {
            assertTrue(true);
        } // IllegalArgumentException

        try {
            ClassUtils.getLocation ((Class)null);
            assertTrue(false);
        }
        catch (Throwable e) {
            assertTrue(true);
        } // IllegalArgumentException
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
