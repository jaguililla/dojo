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
import org.junit.Before;
import org.junit.Test;

import co.popapp.movier.Movier.Actor;

import co.popapp.log.Log;


// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * @author jamming
 */
public class EntityTest {
    private static final Log log = Log.getLog (EntityTest.class);

    private Actor empty;
    private Actor keanu;
    private Actor morgan;

    /** Test method for {@link Entity#copy(Entity)}. */
    @Test public void copy () {
//        fail ("Not yet implemented");
    }

    /**
     * Test method for {@link Entity#toString()}.
     */
    @Test public void entityToString () {
//        fail ("Not yet implemented");
    }

    /**
     * Test method for {@link Entity#fields()}.
     */
    @Test public void fields () {
//        fail ("Not yet implemented");
    }

    /**
     * Test method for {@link Entity#print(StringBuilder)}.
     */
    @Test public void printStringBuilder () {
//        fail ("Not yet implemented");
    }

    /**
     * Test method for {@link Entity#print(StringBuilder, Entity, int)}.
     */
    @Test public void printStringBuilderEntityInt () {
//        fail ("Not yet implemented");
    }

    /**
     * Test method for {@link Entity#readExternal(ObjectInput)}.
     */
    @Test public void readExternal () {
//        fail ("Not yet implemented");
    }

    /**
     * TODO .
     */
    @Before
    public void setUp () {
        log.debug ("Setting up test");
        empty = new Actor ();
        keanu = new Actor ("Keanu");
        morgan = new Actor ("Morgan");
    }

    /**
     * Test method for {@link Entity#writeExternal(ObjectOutput)}.
     */
    @Test public void writeExternal () {
//        fail ("Not yet implemented");
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
