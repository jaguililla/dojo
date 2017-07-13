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
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * Test method for the {@link Quadruple} class.
 *
 * @author jamming
 */
public class QuadrupleTest {
    /*
     * Values to be tested 'Val' is for a value in a position and 'Null' for
     * null in a tuple element.
     */
    private Quadruple<Integer, String, Date, Character> tValVal;
    private Quadruple<Integer, String, Date, Character> tNullVal;
    private Quadruple<Integer, String, Date, Character> tValNull;
    private Quadruple<Integer, String, Date, Character> tNull;
    private Quadruple<Integer, String, Date, Character> tEmpty;
    private Quadruple<Integer, String, Date, Character> tCopy;

    private List<Quadruple<Integer, String, Date, Character>> fixtures = new ArrayList<> ();

    private Date d = new Date (0);

    /**
     * Test method for {@link Quadruple#Quadruple()},
     * {@link Quadruple#Quadruple(Object, Object, Object, Object)} and
     * {@link Quadruple#Quadruple(Quadruple)}.
     */
    @Test public void constructors () {
        assertTrue (
            tValVal.one.equals (0) && tValVal.two.equals ("")
            && tValVal.three.equals (d) && tValVal.four.equals (' '));
        assertTrue (
            tNullVal.one == null && tNullVal.two.equals ("")
            && tNullVal.three.equals (d) && tNullVal.four.equals (' '));
        assertTrue (
            tValNull.one.equals (0) && tValNull.two == null
            && tValNull.three.equals (d) && tValNull.four.equals (' '));
        assertTrue (
            tNull.one == null && tNull.two == null
            && tNull.three == null && tNull.four == null);
        assertTrue (
            tEmpty.one == null && tEmpty.two == null
            && tEmpty.three == null && tEmpty.four == null);
        assertTrue (
            tCopy.one.equals (tValVal.one) && tCopy.two.equals (tValVal.two)
            && tCopy.three.equals (tValVal.three) && tValVal.four.equals (tValVal.four));
    }

    /** Test method for {@link Quadruple#copy(Quadruple)}. */
    @Test public void copy () {
        for (Quadruple<Integer, String, Date, Character> t : fixtures) {
            Quadruple<Integer, String, Date, Character> t1 = new Quadruple<> ();

            assertSame (t.copy (t1), t1);
            assertEquals (t, t1);
        }
    }

    /** Test method for {@link Quadruple#copy(Quadruple)} with an invalid argument. */
    @Test (expected = IllegalArgumentException.class)
    public void copyInvalidArgument () {
        for (Quadruple<Integer, String, Date, Character> t : fixtures)
            t.copy (null);
    }

    /** Test method for {@link Quadruple#equals(Object)}. */
    @Test public void quadrupleEquals () {
        for (Quadruple<Integer, String, Date, Character> t : fixtures) {
            Quadruple<Integer, String, Date, Character> t1 = new Quadruple<> (t);
            Quadruple<Integer, String, Date, Character> t2 = new Quadruple<> (t1);
            Quadruple<Integer, String, Date, Character> t3 = new Quadruple<> (1, "", d, '@');
            Quadruple<Integer, String, Date, Character> t4 = new Quadruple<> (0, "@", d, '@');

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

    /** Test method for {@link Quadruple#hashCode()}. */
    @Test public void quadrupleHashCode () {
        for (Quadruple<Integer, String, Date, Character> t : fixtures) {
            Quadruple<Integer, String, Date, Character> t1 = new Quadruple<> (t);
            Quadruple<Integer, String, Date, Character> t2 = new Quadruple<> (1, "", d, '@');
            Quadruple<Integer, String, Date, Character> t3 = new Quadruple<> (0, "@", d, '@');

            assertTrue (t.hashCode () == t1.hashCode ());
            t1.one = 1;
            assertTrue (t.hashCode () != t1.hashCode ());
            // These are not strictly required, but they are desirable
            assertTrue (t.hashCode () != t2.hashCode ());
            assertTrue (t.hashCode () != t3.hashCode ());
        }
    }

    /** Test method for {@link Quadruple#toString()}. */
    @Test public void quadrupleToString () {
        for (Quadruple<Integer, String, Date, Character> t : fixtures) {
            String s = String.valueOf (START) + t.one + SEPARATOR + t.two + SEPARATOR
                + t.three + SEPARATOR + t.four + END;
            assertEquals (t.toString (), s);
        }
    }

    @Before public void setUp () {
        tValVal = new Quadruple<> (0, "", d, ' ');
        tNullVal = new Quadruple<> (null, "", d, ' ');
        tValNull = new Quadruple<> (0, null, d, ' ');
        tNull = new Quadruple<> (null, null, null, null);
        tEmpty = new Quadruple<> ();
        tCopy = new Quadruple<> (tValVal);

        fixtures.add (tValVal);
        fixtures.add (tNullVal);
        fixtures.add (tValNull);
        fixtures.add (tNull);
        fixtures.add (tEmpty);
        fixtures.add (tCopy);
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
