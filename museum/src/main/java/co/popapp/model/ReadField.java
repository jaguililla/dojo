/*
 * POPAPP Copyright © 2011 Juan José Aguililla. All rights reserved.
 *
 * This program comes WITHOUT ANY WARRANTY; It is free software, you can redistribute it and/or
 * modify it under the terms of the GNU General Public License, Version 3.0.
 * You may obtain a copy of the License at: http://www.gnu.org/licenses/gpl.html
 */

// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.model;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import static co.popapp.model.Model.structure;

import java.io.Serializable;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * TODO Make primitive fields for performance 'IntReadField', 'IntField' and so on... see how to
 * deal with the entity's field array
 * TODO All the flags can be joined in an int bit field, the entity reference can be changed for
 * an index to a table with the metadata.
 * @author jamming
 * @param <T> Field Type.
 */
public class ReadField<T> implements Comparable<ReadField<T>>, Serializable {
    /** TODO . */
    private static final long serialVersionUID = 1851401714036934674L;
    /** TODO . */
    private static final String KEY_VALUE_SEPARATOR = " : ";
    /** TODO . */
    private static final char INDENT_STRING = '\t';

    /** TODO . */
    protected T mValue;

    /** TODO . */
    protected transient Entity mEntity;
    /** TODO . Make final. */
    protected transient byte mIndex;

    /**
     * TODO .
     */
    public ReadField (Entity aEntity) {
        mEntity = aEntity;
        mIndex = aEntity.mIndex++;
        mEntity.mFields[mIndex] = this;
    }

    /**
     * TODO .
     * @param aValue .
     */
    public ReadField (Entity aEntity, T aValue) {
        this (aEntity);
        mValue = aValue;
    }

    /**
     * TODO .
     * TODO Move this function.
     * @param aBuffer .
     * @param aLevel .
     * @return .
     */
    protected StringBuilder indent (StringBuilder aBuffer, int aLevel) {
        for (int ii = 0; ii < aLevel; ii++)
            aBuffer.append (INDENT_STRING);
        return aBuffer;
    }

    /**
     * TODO .
     * @param aValue .
     * @return .
     */
    protected boolean isValid (@SuppressWarnings ("unused") T aValue) {
        return true;
    }

    /**
     * TODO .
     * @param aBuffer .
     * @return .
     */
    protected StringBuilder print (StringBuilder aBuffer) {
        return print (aBuffer, null, 0);
    }

    /**
     * TODO .
     * @param aBuffer .
     * @param aParent .
     * @param aLevel .
     * @return .
     */
    protected StringBuilder print (StringBuilder aBuffer, Entity aParent, int aLevel) {
        return indent (aBuffer, aLevel)
            .append (name ()).append (KEY_VALUE_SEPARATOR)
            .append (aParent != null && get () == aParent? "PARENT" : mValue);
    }

    /** @see Comparable#compareTo(Object) */
    @Override
    @SuppressWarnings ("unchecked")
    public int compareTo (ReadField<T> aO) {
        if (mValue instanceof Comparable<?>)
            return ((Comparable<T>)mValue).compareTo (aO.mValue);
        throw new IllegalStateException ();
    }

    /**
     * TODO .
     * @return .
     */
    public Entity entity () {
        return mEntity;
    }

    /** @see Object#equals(Object) */
    @Override
    public boolean equals (Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass () != obj.getClass ())
            return false;

        ReadField<?> other = (ReadField<?>)obj;
        if (mValue == null && other.mValue != null)
            return false;
        else if (mValue != null && !mValue.equals (other.mValue))
            return false;

        return true;
    }

    /**
     * TODO .
     * @return .
     */
    public T get () {
        return mValue;
    }

    /** @see Object#hashCode() */
    @Override
    public int hashCode () {
        return 31 + ((mValue == null)? 0 : mValue.hashCode ());
    }

    /**
     * TODO .
     * @return .
     */
    public String name () {
        return structure (this).name;
    }

    /** @see Object#toString() */
    @Override
    public String toString () {
        return print (new StringBuilder (128)).toString ();
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
