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
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * @author jamming
 */
public class ShortFormat extends Formatter {
    /**
     * TODO Pendiente de documentar
     */
    public ShortFormat () {
        super ();
    }

    /**
     * @see Formatter#format(LogRecord)
     */
    @Override
    public String format (LogRecord aRecord) {
        return new StringBuilder ()
            .append (aRecord.getMillis ())
            .append (' ')
            .append (aRecord.getMessage ())
            .toString ();
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
