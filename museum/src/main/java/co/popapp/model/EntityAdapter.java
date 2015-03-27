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
 * Empty implementation of {@link EntityListener <code>EntityListener</code>}. Provided for ease of
 * use (avoid implementing all methods).
 *
 * @author jamming
 * @see EntityListener
 */
public abstract class EntityAdapter implements EntityListener {
    /** {@inheritDoc} */
    @Override public <T> void entityAdded (ListField<T> aField, T aValue) {
        // Optional implementation (left to subclasses)
    }

    /** {@inheritDoc} */
    @Override public <T> void entityChanged (ListField<T> aField, T aOldValue, T aValue) {
        // Optional implementation (left to subclasses)
    }

    /** {@inheritDoc} */
    @Override public <T> void entityRemoved (ListField<T> aField, T aValue) {
        // Optional implementation (left to subclasses)
    }

    /** {@inheritDoc} */
    @Override public <T> void fieldChanged (Field<T> aField, T aOldValue, T aValue) {
        // Optional implementation (left to subclasses)
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
