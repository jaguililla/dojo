/*
 * POPAPP Copyright © 2011 Juan José Aguililla. All rights reserved.
 *
 * This program comes WITHOUT ANY WARRANTY; It is free software, you can redistribute it and/or
 * modify it under the terms of the GNU General Public License, Version 3.0.
 * You may obtain a copy of the License at: http://www.gnu.org/licenses/gpl.html
 */

// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.model;

//I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import static co.popapp.model.Model.structure;

import co.popapp.model.Model.NotNull;


// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * @author jamming
 * @param <T> .
 */
public class Field<T> extends ReadField<T> {
    /** TODO . */
    private static final long serialVersionUID = -2939146970692685074L;

    /**
     * TODO .
     */
    public Field (Entity aEntity) {
        super (aEntity);
    }

    /**
     * TODO .
     * @param aValue .
     */
    public Field (Entity aEntity, T aValue) {
        super (aEntity);
        /*
         * TODO Null is not checked in the constructor (design flaw).
         * Metadata can't be accessed here (This field is not instantiated yet).
         * events are not fire because at this time, the entity can't have listeners.
         * Can be fixed with a Field subclass.
         */
        //if (!structure().filter.filter (aValue))
        if (!isValid (aValue))
            throw new IllegalArgumentException (
                "This field value (" + String.valueOf (aValue) + ") isn't valid");
        mValue = aValue;
    }

    /**
     * TODO .
     * @param aValue .
     */
    @SuppressWarnings ("unchecked")
    public <E extends Entity> E set (
        @SuppressWarnings ("unused") Class<E> aEntityClass, T aValue) {
        set (aValue);
        return (E)mEntity;
    }

    /**
     * TODO .
     * @param aValue .
     */
    public void set (T aValue) {
        if (mValue == aValue) return; // Maybe this case won't be usual...

        if (structure (this).haveAnnotation (NotNull.class) && aValue == null)
            throw new IllegalArgumentException ("This field doesn't allow 'null' values");
        if (!isValid (aValue))
        //if (!structure().filter.filter (aValue))
            throw new IllegalArgumentException (
                "This field value (" + String.valueOf (aValue) + ") isn't valid");

        T oldValue = mValue;
        mValue = aValue;

        if (mEntity.listeners != null
            && (oldValue == null? aValue != null : !oldValue.equals (aValue)))
            mEntity.listeners.fireFieldChanged (this, oldValue, mValue);
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
