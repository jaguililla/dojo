/*
 * POPAPP Copyright © 2011 Juan José Aguililla. All rights reserved.
 *
 * This program comes WITHOUT ANY WARRANTY; It is free software, you can redistribute it and/or
 * modify it under the terms of the GNU General Public License, Version 3.0.
 * You may obtain a copy of the License at: http://www.gnu.org/licenses/gpl.html
 */

// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.tuple;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * <p>A three elements tuple.</p>
 *
 * <p>This class could inherit from 'Pair' to reuse code, but it doesn't to avoid a
 * <code>Triple</code> to be compatible (assignable to) with a pair.</p>
 *
 * @author jamming
 * @param <T> First element type.
 * @param <R> Second element type.
 * @param <S> Third element type.
 */
public final class Triple<T, R, S> extends Tuple {
    /** First element value. Can be <code>null</code>. */
    public T one;
    /** Second element value. Can be <code>null</code>. */
    public R two;
    /** Third element value. Can be <code>null</code>. */
    public S three;

    /**
     * Creates a <code>Triple</code> with all its values being <code>null</code>.
     */
    public Triple () {
        super ();
    }

    /**
     * Instantiates a <code>Triple</code> with all of its fields assigned.
     *
     * @param aOne First element value.
     * @param aTwo Second element value.
     * @param aThree Third element value.
     */
    public Triple (T aOne, R aTwo, S aThree) {
        one = aOne;
        two = aTwo;
        three = aThree;
    }

    /**
     * TODO .
     * @param aSource
     */
    public Triple (Triple<T, R, S> aSource) {
        aSource.copy (this);
    }

    /**
     * TODO .
     * @param aTarget
     * @return
     */
    public Triple<T, R, S> copy (Triple<T, R, S> aTarget) {
        if (aTarget == null)
            throw new IllegalArgumentException ();

        aTarget.one = one;
        aTarget.two = two;
        aTarget.three = three;

        return aTarget;
    }

    @Override public boolean equals (Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;

        Triple<?, ?, ?> other = null;
        try {
            other = Triple.class.cast (obj);
        }
        catch (ClassCastException cce) {
            // If the object isn't assignable to a 'Triple', is different, so we return 'false'
            return false;
        }

        return equal (one, other.one) && equal (two, other.two) && equal (three, other.three);
    }

    @Override public int hashCode () {
        return code (code (code (INITIAL_CODE, one), two), three);
    }

    @Override public String toString () {
        return new StringBuilder (48)
            .append (START).append (one)
            .append (SEPARATOR).append (two)
            .append (SEPARATOR).append (three)
            .append (END).toString ();
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
