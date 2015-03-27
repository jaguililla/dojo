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
 * <p>A two elements tuple. Named like this to not be confused with double data type.</p>
 *
 * @author jamming
 * @param <T> First element type.
 * @param <R> Second element type.
 */
public final class Pair<T, R> extends Tuple {
    /** First element value. Can be <code>null</code>. */
    public T one;
    /** Second element value. Can be <code>null</code>. */
    public R two;

    /**
     * Creates a <code>Pair</code> with both values being <code>null</code>.
     */
    public Pair () {
        super ();
    }

    /**
     * Creates a <code>Pair</code> with the same values as the passed one.
     *
     * @param aSource Pair whose values will be copied inside the new <code>Pair</code>.
     * @see co.popapp.tuple.Pair#copy(co.popapp.tuple.Pair)
     */
    public Pair (Pair<T, R> aSource) {
        aSource.copy (this);
    }

    /**
     * Instantiates a <code>Pair</code> with all of its fields assigned.
     *
     * @param aOne First element value.
     * @param aTwo Second element value.
     */
    public Pair (T aOne, R aTwo) {
        one = aOne;
        two = aTwo;
    }

    /**
     * <p>Copies this tuple to the passed instance. The parameter instance will be returned.</p>
     *
     * <p>If 'null' is passed, an exception will be thrown.</p>
     *
     * @param aTarget Tuple whose values will be overriden with this instance's ones.
     * @return Passed tuple.
     */
    public Pair<T, R> copy (Pair<T, R> aTarget) {
        if (aTarget == null)
            throw new IllegalArgumentException ();

        aTarget.one = one;
        aTarget.two = two;

        return aTarget;
    }

    @Override public boolean equals (Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;

        Pair<?, ?> other = null;
        try {
            other = Pair.class.cast (obj);
        }
        catch (ClassCastException cce) {
            // If the object isn't assignable to a 'Pair', is different, so we return 'false'
            return false;
        }

        return equal (one, other.one) && equal (two, other.two);
    }

    @Override public int hashCode () {
        return code (code (INITIAL_CODE, one), two);
    }

    @Override public String toString () {
        return new StringBuilder (32)
            .append (START).append (one)
            .append (SEPARATOR).append (two)
            .append (END).toString ();
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
