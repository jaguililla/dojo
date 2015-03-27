package co.popapp.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import co.popapp.Validate;

/**
 * @author jamming
 */
public class MultipleException extends Exception {
    /** . */
    private final List errors = new ArrayList ();

    /**
     *
     */
    public MultipleException () {
        super();
    }

    /**
     * @param message
     */
    public MultipleException (String message) {
        super(message);
    }

    /**
     * @param message
     * @param theCause
     */
    public MultipleException (String message, Throwable theCause) {
        super(message, theCause);
    }

    /**
     * @param theCause
     */
    public MultipleException (Throwable theCause) {
        super(theCause);
    }

    /**
     * .
     * @param anException
     */
    public void addExcepcion (Throwable anException) {
        Validate.notNull (anException, "No se puede añadir una excepcion que sea null."); // TODO Cambiar textos
        errors.add(anException);
    }

    /**
     * .
     * @return Throwable[]
     */
    public Throwable [] getExceptions () {
        Throwable [] resultado = new Throwable [errors.size()];
        errors.toArray(resultado);
        return resultado;
    }

    /**
     * @see Throwable#printStackTrace(java.io.PrintStream)
     */
    @Override
    public void printStackTrace (PrintStream s) {
        super.printStackTrace(s);
        s.println("EXCEPCIONES:\n");
        Throwable [] excepciones = getExceptions();
        for (int ii = 0; ii < excepciones.length; ii++) {
            s.println(excepciones[ii].toString());
        }
    }

    /**
     * @see Throwable#printStackTrace(java.io.PrintWriter)
     */
    @Override
    public void printStackTrace (PrintWriter s) {
        super.printStackTrace(s);
        s.println("EXCEPCIONES:\n");
        Throwable [] excepciones = getExceptions();
        for (int ii = 0; ii < excepciones.length; ii++) {
            s.println(excepciones[ii].toString());
        }
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString () {
        StringBuffer resultado = new StringBuffer(super.toString());
        StringBuffer conjuntoErrores = new StringBuffer();
        resultado.append("EXCEPCIONES:\n");
        Throwable [] excepciones = getExceptions();
        for (int ii = 0; ii < excepciones.length; ii++) {
            conjuntoErrores.append(excepciones[ii].toString());
        }
        String cadenaErrores = ""/*Cadenas.indentar (conjuntoErrores.toString (), 4)*/;
        String cadenaResultado = resultado.append(cadenaErrores).toString();
        return cadenaResultado;
    }

}
