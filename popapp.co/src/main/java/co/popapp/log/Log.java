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
import static java.util.logging.Level.FINE;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * TODO Check the types in the getters
 * @author jamming
 */
public class Log extends Logger {
    /** TODO . */
    private static final String LOGGING_SETTINGS_RESOURCE = "/log.properties";
    /** TODO . */
    private static final String LOGGING_MANAGER = "java.util.logging.manager";

    /**
     * The logging manager property is set here, because this class is loaded before 'LogManager'
     */
    static {
        System.setProperty (LOGGING_MANAGER, LogSystem.class.getName ());
        try {
            LogManager.getLogManager ().readConfiguration (
                Class.class.getResourceAsStream (LOGGING_SETTINGS_RESOURCE));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Log getAnonymousLog () {
        return (Log)Logger.getAnonymousLogger ();
    }

    public static Log getAnonymousLog (String aResourceBundleName) {
        return (Log)Logger.getAnonymousLogger (aResourceBundleName);
    }

    public static Log getLog (Class<?> aClass) {
        return getLog (aClass.getName ());
    }

    public static Log getLog (Class<?> aClass, String aResourceBundleName) {
        return getLog (aClass.getName (), aResourceBundleName);
    }

    public static Log getLog (String aName) {
        // Don't allow to retrieve the root logger
        if (aName == null || aName.length () == 0)
            throw new IllegalArgumentException ();
        return (Log)LogManager.getLogManager ().getLogger (aName);
    }

    public static Log getLog (String aName, String aResourceBundleName) {
        // Don't allow to retrieve the root logger
        if (aName == null || aName.length () == 0)
            throw new IllegalArgumentException ();
        return (Log)Logger.getLogger (aName, aResourceBundleName);
    }

    public Log (String aName, String aResourceBundleName) {
        super (aName, aResourceBundleName);
    }

    /**
     * TODO .
     * Method to be able to change message formatting quickly (i.e. use MessageFormat)
     * @param aPattern
     * @param aParameters
     * @return
     */
    private String format (String aPattern, Object... aParameters) {
//        return String.format (aPattern, aParameters);
        return MessageFormat.format (aPattern, aParameters);
    }

    public void caller () {
        if (isLoggable (FINE)) {
            StackTraceElement stack = Thread.currentThread ().getStackTrace () [3];
            log (FINE,
                stack.getClassName () + '.' + stack.getMethodName ()
                + " (" + stack.getLineNumber () + ')');
        }
    }

    public void debug (String aMessage, Object... aParameters) {
        log (Level.FINE, format (aMessage, aParameters));
    }

    public void error (String aMessage, Throwable aException) {
        log (Level.SEVERE, aMessage);
    }

    public void info (String aMessage, Object... aParameters) {
        log (Level.INFO, format (aMessage, aParameters));
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
