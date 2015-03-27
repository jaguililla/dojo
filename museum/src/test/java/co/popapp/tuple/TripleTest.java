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
 * Test method for the {@link Triple} class.
 *
 * @author jamming
 */
public class TripleTest {
    /*
     * Values to be tested 'Val' is for a value in a position and 'Null' for
     * null in a tuple element.
     */
    private Triple<Integer, String, Date> tValVal;
    private Triple<Integer, String, Date> tNullVal;
    private Triple<Integer, String, Date> tValNull;
    private Triple<Integer, String, Date> tNull;
    private Triple<Integer, String, Date> tEmpty;
    private Triple<Integer, String, Date> tCopy;

    private List<Triple<Integer, String, Date>> fixtures = new ArrayList<> ();

    private Date d = new Date (0);

    /**
     * Test method for {@link Triple#Triple()}, {@link Triple#Triple(Object, Object, Object)} and
     * {@link Triple#Triple(Triple)}.
     */
    @Test public void constructors () {
        assertTrue (tValVal.one.equals (0) && tValVal.two.equals ("") && tValVal.three.equals (d));
        assertTrue (tNullVal.one == null && tNullVal.two.equals ("") && tNullVal.three.equals (d));
        assertTrue (tValNull.one.equals (0) && tValNull.two == null && tValNull.three.equals (d));
        assertTrue (tNull.one == null && tNull.two == null && tNull.three == null);
        assertTrue (tEmpty.one == null && tEmpty.two == null && tEmpty.three == null);
        assertTrue (
            tCopy.one.equals (tValVal.one) && tCopy.two.equals (tValVal.two)
            && tCopy.three.equals (tValVal.three));
    }

    /** Test method for {@link Triple#copy(Triple)}. */
    @Test public void copy () {
        for (Triple<Integer, String, Date> t : fixtures) {
            Triple<Integer, String, Date> t1 = new Triple<> ();

            assertSame (t.copy (t1), t1);
            assertEquals (t, t1);
        }
    }

    /** Test method for {@link Triple#copy(Triple)} with an invalid argument. */
    @Test (expected = IllegalArgumentException.class)
    public void copyInvalidArgument () {
        for (Triple<Integer, String, Date> t : fixtures)
            t.copy (null);
    }

    @Before public void setUp () {
        tValVal = new Triple<> (0, "", d);
        tNullVal = new Triple<> (null, "", d);
        tValNull = new Triple<> (0, null, d);
        tNull = new Triple<> (null, null, null);
        tEmpty = new Triple<> ();
        tCopy = new Triple<> (tValVal);

        fixtures.add (tValVal);
        fixtures.add (tNullVal);
        fixtures.add (tValNull);
        fixtures.add (tNull);
        fixtures.add (tEmpty);
        fixtures.add (tCopy);
    }

    /** Test method for {@link Triple#equals(Object)}. */
    @Test public void tripleEquals () {
        for (Triple<Integer, String, Date> t : fixtures) {
            Triple<Integer, String, Date> t1 = new Triple<> (t);
            Triple<Integer, String, Date> t2 = new Triple<> (t1);
            Triple<Integer, String, Date> t3 = new Triple<> (1, "", d);
            Triple<Integer, String, Date> t4 = new Triple<> (0, "@", d);

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

    /** Test method for {@link Triple#hashCode()}. */
    @Test public void tripleHashCode () {
        for (Triple<Integer, String, Date> t : fixtures) {
            Triple<Integer, String, Date> t1 = new Triple<> (t);
            Triple<Integer, String, Date> t2 = new Triple<> (1, "", d);
            Triple<Integer, String, Date> t3 = new Triple<> (0, "@", d);

            assertTrue (t.hashCode () == t1.hashCode ());
            t1.one = 1;
            assertTrue (t.hashCode () != t1.hashCode ());
            // These are not strictly required, but they are desirable
            assertTrue (t.hashCode () != t2.hashCode ());
            assertTrue (t.hashCode () != t3.hashCode ());
        }
    }

    /** Test method for {@link Triple#toString()}. */
    @Test public void tripleToString () {
        for (Triple<Integer, String, Date> t : fixtures) {
            String s =
                String.valueOf (START) + t.one + SEPARATOR + t.two + SEPARATOR + t.three + END;
            assertEquals (t.toString (), s);
        }
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
