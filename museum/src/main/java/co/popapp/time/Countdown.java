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

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * TODO Lanzar evento al llegar a cero
 * TODO Pendiente de documentar
 * @author jamming
 */
public class Countdown extends Stopwatch {
    /** TODO . */
    private long mStartTime = 0;

    /**
     * TODO .
     */
    public Countdown () { super (); }

    /**
     * TODO .
     * @param aPrecision .
     */
    public Countdown (int aPrecision) { super (aPrecision); }

    /**
     * Devuelve el valor del campo 'startTime'. Ver {@link #mStartTime time}.
     * @return Valor del campo 'startTime'.
     */
    public long getStartTime () { return mStartTime; }

    /**
     * TODO .
     */
    @Override
    public long getTime () { return getStartTime () - super.getTime (); }

    /**
     * Asigna un valor al campo 'startTime'. Ver {@link #mStartTime time}.
     * @param aStartTime Nuevo valor del campo 'startTime'.
     */
    public void setStartTime (long aStartTime) {
        mStartTime = aStartTime;
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
