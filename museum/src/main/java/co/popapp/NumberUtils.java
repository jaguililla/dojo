/*
 * POPAPP Copyright © 2011 Juan José Aguililla. All rights reserved.
 *
 * This program comes WITHOUT ANY WARRANTY; It is free software, you can redistribute it and/or
 * modify it under the terms of the GNU General Public License, Version 3.0.
 * You may obtain a copy of the License at: http://www.gnu.org/licenses/gpl.html
 */

// P A C K A G E //////////////////////////////////////////////////////////////////////////////
package co.popapp;

// C L A S S //////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * @author jamming
 */
public class NumberUtils {
    /**
     * Método auxiliar que devuelve el número de dígitos de un valor en la base especificada.
     * @param number
     * @param base
     * @return Valor.
     */
    public static int digitsOf (int number, int base) {
        int value = (number >= 0)? number : Math.abs(number);
        int ii = 0;
        do {
            value /= base;
            ii++;
        }
        while (value >= base);
        return ii;
    }

    private NumberUtils () {
        throw new IllegalStateException ();
    }
}
// E O F //////////////////////////////////////////////////////////////////////////////////////
