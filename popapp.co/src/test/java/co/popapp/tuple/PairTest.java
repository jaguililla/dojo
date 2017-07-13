/*
 * POPAPP Copyright © 2011 Juan José Aguililla. All rights reserved.
 *
 * This program comes WITHOUT ANY WARRANTY; It is free software, you can redistribute it and/or
 * modify it under the terms of the GNU General Public License, Version 3.0.
 * You may obtain a copy of the License at: http://www.gnu.org/licenses/gpl.html
 */

// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.tuple;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import static co.popapp.tuple.Tuple.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * Test method for the {@link Pair} class.
 *
 * @author jamming
 */
public class PairTest {
    /*
     * Values to be tested 'Val' is for a value in a position and 'Null' for
     * null in a tuple element.
     */
    private Pair<Integer, String> tValVal;
    private Pair<Integer, String> tNullVal;
    private Pair<Integer, String> tValNull;
    private Pair<Integer, String> tNull;
    private Pair<Integer, String> tEmpty;
    private Pair<Integer, String> tCopy;

    private List<Pair<Integer, String>> fixtures = new ArrayList<> ();

    /**
     * Test method for {@link Pair#Pair()}, {@link Pair#Pair(Object, Object)} and
     * {@link Pair#Pair(Pair)}.
     */
    @Test public void constructors () {
        assertTrue (tValVal.one.equals (0) && tValVal.two.equals (""));
        assertTrue (tNullVal.one == null && tNullVal.two.equals (""));
        assertTrue (tValNull.one.equals (0) && tValNull.two == null);
        assertTrue (tNull.one == null && tNull.two == null);
        assertTrue (tEmpty.one == null && tEmpty.two == null);
        assertTrue (tCopy.one.equals (tValVal.one) && tCopy.two.equals (tValVal.two));
    }

    /** Test method for {@link Pair#copy(Pair)}. */
    @Test public void copy () {
        for (Pair<Integer, String> t : fixtures) {
            Pair<Integer, String> t1 = new Pair<> ();

            assertSame (t.copy (t1), t1);
            assertEquals (t, t1);
        }
    }

    /** Test method for {@link Pair#copy(Pair)} with an invalid argument. */
    @Test (expected = IllegalArgumentException.class)
    public void copyInvalidArgument () {
        for (Pair<Integer, String> t : fixtures)
            t.copy (null);
    }

    /** Test method for {@link Pair#equals(Object)}. */
    @Test public void pairEquals () {
        for (Pair<Integer, String> t : fixtures) {
            Pair<Integer, String> t1 = new Pair<> (t);
            Pair<Integer, String> t2 = new Pair<> (t1);
            Pair<Integer, String> t3 = new Pair<> (1, "");
            Pair<Integer, String> t4 = new Pair<> (0, "@");

            // Simmetric
            assertEquals (t, t);
            // Reflexive
            assertEquals (t, t1);
            assertEquals (t1, t);
            // Transitive
            assertEquals (t1, t2);
            assertEquals (t2, t);
            // Null safe
            assertFalse (t.equals (null));
            // Consistent
            t1.one = 1;
            assertFalse (t.equals (t1));
            t1.one = t.one;
            assertEquals (t, t1);
            // Take care of incompatible type
            assertFalse (t.equals (""));
            // Check different values
            assertFalse (t.equals (t3));
            assertFalse (t.equals (t4));
        }
    }

    /** Test method for {@link Pair#hashCode()}. */
    @Test public void pairHashCode () {
        for (Pair<Integer,String> t : fixtures) {
            Pair<Integer, String> t1 = new Pair<> (t);
            Pair<Integer, String> t2 = new Pair<> (1, "");
            Pair<Integer, String> t3 = new Pair<> (0, "@");

            assertTrue (t.hashCode () == t1.hashCode ());
            t1.one = 1;
            assertTrue (t.hashCode () != t1.hashCode ());
            // These are not strictly required, but they are desirable
            assertTrue (t.hashCode () != t2.hashCode ());
            assertTrue (t.hashCode () != t3.hashCode ());
        }
    }

    /** Test method for {@link Pair#toString()}. */
    @Test public void pairToString () {
        for (Pair<Integer,String> t : fixtures)
            assertEquals (t.toString (), String.valueOf (START) + t.one + SEPARATOR + t.two + END);
    }

    @Before public void setUp () {
        tValVal = new Pair<> (0, "");
        tNullVal = new Pair<> (null, "");
        tValNull = new Pair<> (0, null);
        tNull = new Pair<> (null, null);
        tEmpty = new Pair<> ();
        tCopy = new Pair<> (tValVal);

        fixtures.add (tValVal);
        fixtures.add (tNullVal);
        fixtures.add (tValNull);
        fixtures.add (tNull);
        fixtures.add (tEmpty);
        fixtures.add (tCopy);
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
