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
 * <p>Contains helper code to assist in the creation of the tuples <code>equals</code> and
 * <code>hashcode</code> methods.</p>
 *
 * <p>This class visibility is package private to avoid declare <code>Tuple</code> variables.</p>
 *
 * @author jamming
 */
public abstract class Tuple {
    /** Prime number used to calculate instance hashcodes. Is protected just to test properly. */
    protected static final int PRIME = 31;
    /** Hashcode initialization value. */
    protected static final int INITIAL_CODE = 1;
    /** Tuple description end character. */
    protected static final char END = ')';
    /** Tuple description start character. */
    protected static final char START = '(';
    /** Separator between values in the tuple description. */
    protected static final String SEPARATOR = ", ";

    /**
     * Utility method to create a pair in a less verbose way. It only calls
     * <code>new Pair (a1, a2)</code>. The best way to use it is to import it statically.
     *
     * @see Pair#Pair(Object, Object)
     */
    public static <T, S> Pair<T, S> _ (T a1, S a2) {
        return new Pair<T, S> (a1, a2);
    }

    /**
     * Utility method to create a triple in a less verbose way. It only calls
     * <code>new Triple (a1, a2, a3)</code>. The best way to use it is to import it statically.
     *
     * @see Triple#Triple(Object, Object, Object)
     */
    public static <T, S, Z> Triple<T, S, Z> _ (T a1, S a2, Z a3) {
        return new Triple<T, S, Z> (a1, a2, a3);
    }

    /**
     * Utility method to create a quadruple in a less verbose way. It only calls
     * <code>new Quadruple (a1, a2, a3, a4)</code>. The best way to use it is to import it
     * statically.
     *
     * @see Quadruple#Quadruple(Object, Object, Object, Object)
     */
    public static <T, S, Z, X> Quadruple<T, S, Z, X> _ (T a1, S a2, Z a3, X a4) {
        return new Quadruple<T, S, Z, X> (a1, a2, a3, a4);
    }

    /**
     * <p>Creates a <code>Tuple</code> without assigning values to its fields.</p>
     *
     * <p>Defined to force subclasses to declare their constructors (preventing programming
     * errors.</p>
     */
    protected Tuple () {
        super ();
    }

    /**
     * Calculate the <code>hashcode</code> of an object and adds it to a previous
     * <code>hashcode</code>. Called to get a tuple <code>hashcode</code> from its elements.
     *
     * @param aCarry Tuple hashcode processed for the previous fields.
     * @param aObject Tuple member whose hashcode will be calculated and added to the carried one.
     * @return The carried hashcode plus the passed instance one.
     * @see Object#hashCode()
     */
    protected int code (int aCarry, Object aObject) {
        return  PRIME * aCarry + ((aObject == null)? 0 : aObject.hashCode ());
    }

    /**
     * Compare two objects of the same type taking care of <code>null</code> references. Used
     * to compare the tuple elements.
     *
     * @param <T> Type of the compared instances.
     * @param aObject1 Left side of the comparison.
     * @param aObject2 Right side of the comparison.
     * @return True if the two instances are equal (determined by their <code>equals</code> method).
     * @see Object#equals(Object)
     */
    protected <T> boolean equal (T aObject1, T aObject2) {
        return aObject1 == null?  aObject2 == null : aObject1.equals (aObject2);
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
