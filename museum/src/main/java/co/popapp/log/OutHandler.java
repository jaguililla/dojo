/*
 * POPAPP Copyright © 2011 Juan José Aguililla. All rights reserved.
 *
 * This program comes WITHOUT ANY WARRANTY; It is free software, you can redistribute it and/or
 * modify it under the terms of the GNU General Public License, Version 3.0.
 * You may obtain a copy of the License at: http://www.gnu.org/licenses/gpl.html
 */

// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.log;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import java.util.logging.Handler;
import java.util.logging.LogRecord;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * @author jamming
 */
public class OutHandler extends Handler {
    /**
     * TODO .
     */
    public OutHandler () {
        super ();
        setFormatter (new ShortFormat ());
    }

    /**
     * @see java.util.logging.Handler#close()
     */
    @Override
    public void close () throws SecurityException {
        // TODO
    }

    /**
     * @see java.util.logging.Handler#flush()
     */
    @Override
    public void flush () {
        // TODO
    }

    /**
     * @see java.util.logging.Handler#publish(java.util.logging.LogRecord)
     */
    @Override
    public void publish (LogRecord aRecord) {
        System.out.println (getFormatter ().format (aRecord));
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
