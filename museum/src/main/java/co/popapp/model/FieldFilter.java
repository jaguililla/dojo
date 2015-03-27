/*
 * POPAPP Copyright © 2011 Juan José Aguililla. All rights reserved.
 *
 * This program comes WITHOUT ANY WARRANTY; It is free software, you can redistribute it and/or
 * modify it under the terms of the GNU General Public License, Version 3.0.
 * You may obtain a copy of the License at: http://www.gnu.org/licenses/gpl.html
 */

// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.model;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * Unused right now, filter and validation can be done with anonymous classes: new Field<>() {}
 * @author jam
 */
public abstract class FieldFilter {
    /**
     * TODO .
     * @param aValue .
     * @return .
     */
    abstract boolean filter (Object aValue);
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
