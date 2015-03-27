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
import static co.popapp.tuple.Tuple._;
import static org.junit.Assert.*;

import org.junit.Test;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * Test method for the {@link Tuple} class.
 *
 * @author jamming
 */
public class TupleTest {
    /**
     * Test method for:
     * <ul>
     *  <li>{@link Tuple#_(Object, Object)}.</li>
     *  <li>{@link Tuple#_(Object, Object, Object)}.</li>
     *  <li>{@link Tuple#_(Object, Object, Objec, Objectt)}.</li>
     * </ul>
     */
    @Test public void _test () {
        assertEquals (new Pair<String, Integer> ("a", 1), _("a", 1));
        assertEquals (new Pair<String, Integer> (null, 1), _(null, 1));
        assertEquals (new Pair<String, Integer> ("a", null), _("a", null));
        assertEquals (new Pair<String, Integer> (null, null), _(null, null));

        assertEquals (new Triple<String, Integer, Character> ("a", 1, 'b'), _("a", 1, 'b'));
        assertEquals (new Triple<String, Integer, Character> (null, 1, 'b'), _(null, 1, 'b'));
        assertEquals (new Triple<String, Integer, Character> ("a", null, 'b'), _("a", null, 'b'));
        assertEquals (new Triple<String, Integer, Character> (null, null, 'b'), _(null, null, 'b'));

        assertEquals (
            new Quadruple<String, Integer, Character, Double> ("a", 1, 'b', 0.0),
            _("a", 1, 'b', 0.0));
        assertEquals (
            new Quadruple<String, Integer, Character, Double> (null, 1, 'b', 0.0),
            _(null, 1, 'b', 0.0));
        assertEquals (
            new Quadruple<String, Integer, Character, Double> ("a", null, 'b', 0.0),
            _("a", null, 'b', 0.0));
        assertEquals (
            new Quadruple<String, Integer, Character, Double> (null, null, 'b', 0.0),
            _(null, null, 'b', 0.0));
    }

    /** Test method for {@link Tuple#code(int, Object)}. */
    @Test @SuppressWarnings ("unused") public void code () {
        new Tuple () {
            {
                String parameter = "##";
                assertEquals (code (1, parameter), PRIME + parameter.hashCode ());
                assertEquals (code (1, null), PRIME);
                assertEquals (code (2, parameter), 2 * PRIME + parameter.hashCode ());
                assertEquals (code (2, null), 2 * PRIME);
            }
        };
    }

    /** Test method for {@link Tuple#equal(Object, Object)}. */
    @Test @SuppressWarnings ("unused") public void equal () {
        new Tuple () {
            {
                assertTrue (equal (null, null));
                assertTrue (equal ("", ""));
                assertTrue (equal ("##", "##")); // Test with a string not optimized by the compiler
                assertFalse (equal ("", "#"));
                assertFalse (equal (null, ""));
                assertFalse (equal ("", null));
            }
        };
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
