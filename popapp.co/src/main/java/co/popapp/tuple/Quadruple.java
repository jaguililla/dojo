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
 * <p>TODO Add support for a <code>copy</code> method and a copy constructor.</p>
 *
 * @author jamming
 * @param <T> First element type.
 * @param <R> Second element type.
 * @param <S> Third element type.
 * @param <V> Fourth element type.
 */
public final class Quadruple<T, R, S, V> extends Tuple {
    /** First element value. Can be <code>null</code>. */
    public T one;
    /** Second element value. Can be <code>null</code>. */
    public R two;
    /** Third element value. Can be <code>null</code>. */
    public S three;
    /** Fourth element value. Can be <code>null</code>. */
    public V four;

    /**
     * Creates a <code>Quadruple</code> with all its values being <code>null</code>.
     */
    public Quadruple () {
        super ();
    }

    /**
     * TODO .
     * @param aSource
     */
    public Quadruple (Quadruple<T, R, S, V> aSource) {
        aSource.copy (this);
    }

    /**
     * Instantiates a <code>Quadruple</code> with all of its fields assigned.
     *
     * @param aOne First element value.
     * @param aTwo Second element value.
     * @param aThree Third element value.
     * @param aFour Fourth element value.
     */
    public Quadruple (T aOne, R aTwo, S aThree, V aFour) {
        one = aOne;
        two = aTwo;
        three = aThree;
        four = aFour;
    }

    /**
     * TODO .
     * @param aTarget
     * @return
     */
    public Quadruple<T, R, S, V> copy (Quadruple<T, R, S, V> aTarget) {
        if (aTarget == null)
            throw new IllegalArgumentException ();

        aTarget.one = one;
        aTarget.two = two;
        aTarget.three = three;
        aTarget.four = four;

        return aTarget;
    }

    @Override public boolean equals (Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;

        Quadruple<?, ?, ?, ?> other = null;
        try {
            other = Quadruple.class.cast (obj);
        }
        catch (ClassCastException cce) {
            // If the object isn't assignable to a 'Quadruple', is different, so we return 'false'
            return false;
        }

        return
            equal (one, other.one) && equal (two, other.two)
            && equal (three, other.three) && equal (four, other.four);
    }

    @Override public int hashCode () {
        return code (code (code (code (INITIAL_CODE, one), two), three), four);
    }

    @Override public String toString () {
        return new StringBuilder (64)
            .append (START).append (one)
            .append (SEPARATOR).append (two)
            .append (SEPARATOR).append (three)
            .append (SEPARATOR).append (four)
            .append (END).toString ();
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
