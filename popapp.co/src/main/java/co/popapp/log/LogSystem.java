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
import java.util.logging.LogManager;
import java.util.logging.Logger;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * TODO Explain problem overriding default constructor because of visibility issues.
 * TODO Problems with root logger
 * @author jamming
 */
public class LogSystem extends LogManager {
    /** @see LogManager#getLogger(String) */
    @Override public synchronized Logger getLogger (String aName) {
        Logger log = super.getLogger(aName);

        if (log == null) {
            Logger newLogger = new Log(aName, null);
            do {
                if (addLogger(newLogger))
                    return newLogger;

                log = super.getLogger(aName);
            }
            while (log == null);
        }

        return log;
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
