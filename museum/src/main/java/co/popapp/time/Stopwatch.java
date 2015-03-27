/*
 * POPAPP Copyright © 2011 Juan José Aguililla. All rights reserved.
 *
 * This program comes WITHOUT ANY WARRANTY; It is free software, you can redistribute it and/or
 * modify it under the terms of the GNU General Public License, Version 3.0.
 * You may obtain a copy of the License at: http://www.gnu.org/licenses/gpl.html
 */

// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.time;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;
import java.util.List;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * TODO Especificar formatos de tiempo (sensible a la cantidad de tiempo, ej. un formato para días, y otro para segundos)
 * TODO Especificar atributos del campo de texto en función del tiempo (mascara de bits con 2bits por campo -0: no, 1: siempre, 2: Si mayor q cero)
 * TODO Se puede optimizar teniendo un unico 'Handler' para todos los posibles cronómetros.
 * TODO Se puede formatear solo cuando el texto vaya a cambiar.
 * TODO Se debe poner el texto en el campo solo si ha cambiado.
 * TODO Controlar el evento de cada segundo pasado
 * TODO Pendiente de documentar
 * @author jamming
 */
public class Stopwatch {
    /** Logger de la clase Stopwatch. */
    //private static final String TAG = Stopwatch.class.getName ();

    /** TODO . */
    private final StringBuilder mBuffer = new StringBuilder ();

    /** Milisegundos iniciales (desde que se puso en marcha) */
    private long mStartMillis = 0;

    /** Tiempo transcurrido */
    private long mElapsedMillis = 0;
    /** TODO . */
    private int mPrecision = 10;
    /** TODO . */
    private List<Long> mRecords = new ArrayList<Long> (16); // TODO Adjust the initial capacity

    /** TODO . */
    private boolean mShowEmptyFields = true;

    /**
     * TODO .
     */
    public Stopwatch () {
        super ();
    }

    /**
     * TODO .
     * @param aPrecision .
     */
    public Stopwatch (int aPrecision) {
        this ();
        setPrecision (aPrecision);
    }

    /**
     * Devuelve el valor del campo 'precision'. Ver {@link #mPrecision precision}.
     * @return Valor del campo 'precision'.
     */
    public int getPrecision () {
        return mPrecision;
    }

    /**
     * TODO .
     * @return List<Long>
     */
    public List<Long> getRecords () {
        return mRecords;
    }

    /**
     * TODO .
     * @return int
     */
    public int getRecordsCount () {
        return mRecords.size ();
    }

    /**
     * TODO .
     * @return List
     */
    public List<Long> getRecordsDiffs () {
        return null;
    }

    /**
     * TODO .
     * @return long
     */
    public long getTime () {
        return isRunning()?
            mElapsedMillis + System.currentTimeMillis () - mStartMillis :
            mElapsedMillis;
    }

    /**
     * TODO .
     * @return boolean
     */
    public boolean isReset () {
        return mElapsedMillis == 0;
    }

    /**
     * TODO .
     * @return boolean
     */
    public boolean isRunning () {
        return mStartMillis != 0;
    }

    /**
     * Devuelve el valor del campo 'showEmptyFields'. Ver {@link #mShowEmptyFields showEmptyFields}.
     * @return Valor del campo 'showEmptyFields'.
     */
    public boolean isShowEmptyFields () {
        return mShowEmptyFields;
    }

    /**
     * TODO .
     */
    public void record () {
        mRecords.add (getTime ());
    }

    /**
     * TODO .
     */
    public void reset () {
        if (mStartMillis != 0){
            mStartMillis = System.currentTimeMillis ();
        }
        if (!mRecords.isEmpty ()) {
            mRecords.clear ();
        }
        mElapsedMillis = 0;
    }

    /**
     * Asigna un valor al campo 'precision'. Ver {@link #mPrecision precision}.
     * @param aPrecision Nuevo valor del campo 'precision'.
     */
    public void setPrecision (int aPrecision) {
        if (aPrecision < 5) {
            throw new IllegalArgumentException ("La precision no puede ser menor de 5 ms");
        }
        mPrecision = aPrecision;
    }

    /**
     * Asigna un valor al campo 'showEmptyFields'. Ver {@link #mShowEmptyFields showEmptyFields}.
     * @param aShowEmptyFields Nuevo valor del campo 'showEmptyFields'.
     */
    public void setShowEmptyFields (boolean aShowEmptyFields) {
        mShowEmptyFields = aShowEmptyFields;
    }

    /**
     * TODO .
     */
    public void start () {
        if (!isRunning ()) {
            mStartMillis = System.currentTimeMillis();
        }
    }

    /**
     * TODO .
     */
    public void startStop () {
        if (isRunning ()) {
            stop ();
        }
        else {
            start ();
        }
    }

    /**
     * TODO .
     */
    public void stop () {
        if (isRunning ()) {
            mElapsedMillis += System.currentTimeMillis () - mStartMillis;
            mStartMillis = 0;
        }
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
