/*
 * POPAPP Copyright © 2011 Juan José Aguililla. All rights reserved.
 *
 * This program comes WITHOUT ANY WARRANTY; It is free software, you can redistribute it and/or
 * modify it under the terms of the GNU General Public License, Version 3.0.
 * You may obtain a copy of the License at: http://www.gnu.org/licenses/gpl.html
 */

// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////

import java.io.File;

/**
 * TODO .
 * @author jam
 */
public final class Validate {
    /** Nombre de la propiedad del sistema que inhabilita los chequeos. */
    private static final String DISABLE_CHECKS = "DISABLE_CHECKS";

    /** TODO Pendiente de documentar. */
    private static boolean dontValidate = System.getProperty(DISABLE_CHECKS) != null;

    public static void isTrue (boolean aCondition, String aMessage) {
        if (!aCondition) throw new IllegalArgumentException (aMessage);
    }

    public static void notEmpty (String aString) {
        notEmpty (aString, "");
    }

    public static void notEmpty (String aString, String aMessage) {

    }

    public static void notNull (Object aObject) {
        notNull (aObject, "");
    }

    public static void notNull (Object aObject, String aMessage) {
        if (aObject == null) throw new IllegalArgumentException (aMessage);
    }

    /**
     * .
     * @return boolean
     */
    public static boolean isChecksEnabled () {
        return System.getProperty(DISABLE_CHECKS) == null;
    }

    /**
     * .
     * @param activeCheck
     */
    public static void enableChecks (boolean activeCheck) {
        System.setProperty(DISABLE_CHECKS, activeCheck? DISABLE_CHECKS : null);
    }

    /**
     * .
     * @param anObject
     * @param type
     * @param message
     */
    public static void isOfType (final Object anObject, Class type, final String message) {
        notNull(anObject, "");
        if (!anObject.getClass().equals(type)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * .
     * @param anObject
     * @param type
     */
    public static void isOfType (final Object anObject, Class type) {
        notNull(anObject, "");
        if (!anObject.getClass().equals(type)) {
            throw new IllegalArgumentException("");
        }
    }

    /**
     * .
     * @param value
     * @param maxValue
     * @param message
     */
    public static void lesserThan (int value, int maxValue, String message) {
        if (value > maxValue) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * .
     * @param value
     * @param maxValue
     */
    public static void lesserThan (int value, int maxValue) {
        if (value > maxValue) {
            throw new IllegalArgumentException("");
        }
    }

    /**
     * .
     * @param value
     * @param minValue
     * @param message
     */
    public static void greaterThan (int value, int minValue, String message) {
        if (value < minValue) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * .
     * @param value
     * @param minValue
     */
    public static void greaterThan (int value, int minValue) {
        if (value < minValue) {
            throw new IllegalArgumentException("");
        }
    }

    /**
     * .
     * @param value
     * @param maxValue
     * @param minValue
     * @param message
     */
    public static void inRange (int value, int maxValue, int minValue, String message) {
        if (value > maxValue || value < minValue) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * .
     * @param value
     * @param maxValue
     * @param minValue
     */
    public static void inRange (int value, int maxValue, int minValue) {
        if (value > maxValue || value < minValue) {
            throw new IllegalArgumentException("");
        }
    }

    /**
     * .
     * @param value
     * @param maxValue
     * @param message
     */
    public static void lesserThan (long value, long maxValue, String message) {
        if (value > maxValue) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * .
     * @param value
     * @param maxValue
     */
    public static void lesserThan (long value, long maxValue) {
        if (value > maxValue) {
            throw new IllegalArgumentException("");
        }
    }

    /**
     * .
     * @param value
     * @param minValue
     * @param message
     */
    public static void greaterThan (long value, long minValue, String message) {
        if (value < minValue) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * .
     * @param value
     * @param minValue
     */
    public static void greaterThan (long value, long minValue) {
        if (value < minValue) {
            throw new IllegalArgumentException("");
        }
    }

    /**
     * .
     * @param value
     * @param maxValue
     * @param minValue
     * @param message
     */
    public static void inRange (long value, long maxValue, long minValue, String message) {
        if (value > maxValue || value < minValue) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * .
     * @param value
     * @param maxValue
     * @param minValue
     */
    public static void inRange (long value, long maxValue, long minValue) {
        if (value > maxValue || value < minValue) {
            throw new IllegalArgumentException("");
        }
    }

    /**
     * .
     * @param value
     * @param message
     */
    public static void fileExist (File value, String message) {
        notNull(value, "El fichero no puede ser 'null'");
        if (!value.exists()) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * .
     * @param value
     */
    public static void fileExist (File value) {
        notNull(value, "El fichero no puede ser 'null'");
        if (!value.exists()) {
            throw new IllegalArgumentException("");
        }
    }

    /**
     * TODO .
     */
    private Validate () {
        throw new IllegalStateException ("This class should not be instantiated");
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
