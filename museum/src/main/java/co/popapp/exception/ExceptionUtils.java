// P A C K A G E //////////////////////////////////////////////////////////////////////////////
package co.popapp.exception;

// I M P O R T ////////////////////////////////////////////////////////////////////////////////

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;

import co.popapp.Validate;

// C L A S S //////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * @author jamming
 */
public class ExceptionUtils {
    /** Formato en el que se imprimirá el timestamp de la excepción en los mensajes. */
    protected static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss,SSS");

    /** Constante que indica que se mostrarán todos los paquetes en las trazas. No se excluirá ninguna línea en la pila de llamadas. */
    public static final String ALL_PACKAGES = "";
    /** Nombre del paquete (y sus hijos) que será mostrado en las trazas. El resto de los paquetes será ignorado. */
    private static String visiblePackage = ALL_PACKAGES;

    /**
     * @return Returns the visiblePackage.
     */
    public static String getVisiblePackage () {
        return visiblePackage;
    }

    /**
     * .
     * @param t
     * @param theVisiblePackage
     * @return nueva traza.
     */
    /*
    public static StackTraceElement [] filterStack (Throwable t, String theVisiblePackage) {
        StackTraceElement [] stackFrames = t.getStackTrace();
        List result = new ArrayList (stackFrames.length);
        for (int ii = 0; ii < stackFrames.length; ii++) {
            StackTraceElement currentFrame = stackFrames[ii];
            if (currentFrame.getClassName().startsWith(theVisiblePackage)) {
                result.add(currentFrame);
            }
        }
        StackTraceElement [] resultArray = new StackTraceElement [result.size()];
        return (StackTraceElement [])result.toArray(resultArray);
    }
    */

    /**
     * Indica si se están filtrando líneas en la pila de llamadas.
     * @return Si se están filtrando líneas en la pila de llamadas.
     */
    public static boolean isFiltered () {
        return !visiblePackage.equals(ALL_PACKAGES);
    }

    /**
     * @param theVisiblePackage The visiblePackage to set.
     */
    public static void setVisiblePackage (String theVisiblePackage) {
        Validate.notNull (
            theVisiblePackage, "El paquete mostrado por las trazas no puede ser 'null'");
        visiblePackage = theVisiblePackage;
    }

    /**
     * .
     * @param exception
     * @return String
     */
    public static final String stackTrace (Throwable exception) {
        Validate.notNull(exception, "La excepción no puede ser null.");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        exception.printStackTrace(writer);
        return stringWriter.toString();
    }

    /**
     * .
     * @param exception
     * @return Class
     */
    /*
    public static Class callerClass (Throwable exception) {
        return exception.getStackTrace()[0].getClass();
    }
    */
    /**
     * .
     * @param t
     */
    /* This only works in JDK 1.4.2 or better
    public static void filterStack (Throwable t) {
        t.setStackTrace(filterStack(t, visiblePackage));
    }
    */

    private ExceptionUtils () {
        throw new IllegalStateException ();
    }
}
// E O F //////////////////////////////////////////////////////////////////////////////////////
