package co.popapp.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jamming
 */
public class MultipleRuntimeException extends RuntimeException {
    /** . */
    protected final List errores;

    /**
     *
     */
    public MultipleRuntimeException () {
        super();
        errores = new ArrayList();
    }

    /**
     * @param message
     */
    public MultipleRuntimeException (String message) {
        super(message);
        errores = new ArrayList();
    }

    /**
     * @param message
     * @param laCausa
     */
    public MultipleRuntimeException (String message, Throwable laCausa) {
        super(message, laCausa);
        errores = new ArrayList();
    }

    /**
     * @param laCausa
     */
    public MultipleRuntimeException (Throwable laCausa) {
        super(laCausa);
        errores = new ArrayList();
    }

    /**
     * .
     * @param excepcion
     */
    public void aniadirExcepcion (Throwable excepcion) {
        if (excepcion == null) {
            throw new IllegalArgumentException("No se puede añadir una excepcion que sea null.");
        }
        errores.add(excepcion);
    }

    /**
     * .
     * @return Throwable[]
     */
    public Throwable [] getExcepciones () {
        Throwable [] resultado = new Throwable [errores.size()];
        errores.toArray(resultado);
        return resultado;
    }

    /**
     * @see Throwable#printStackTrace(PrintStream)
     */
    @Override
    public void printStackTrace (PrintStream s) {
        super.printStackTrace(s);
        s.println("EXCEPCIONES:\n");
        Throwable [] excepciones = getExcepciones();
        for (int ii = 0; ii < excepciones.length; ii++) {
            s.println(excepciones[ii].toString());
        }
    }

    /**
     * @see Throwable#printStackTrace(PrintWriter)
     */
    @Override
    public void printStackTrace (PrintWriter s) {
        super.printStackTrace(s);
        s.println("EXCEPCIONES:\n");
        Throwable [] excepciones = getExcepciones();
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
        Throwable [] excepciones = getExcepciones();
        for (int ii = 0; ii < excepciones.length; ii++) {
            conjuntoErrores.append(excepciones[ii].toString());
        }
        String cadenaErrores = ""/*Cadenas.indentar (conjuntoErrores.toString (), 4)*/;
        String cadenaResultado = resultado.append(cadenaErrores).toString();
        return cadenaResultado;
    }

}
