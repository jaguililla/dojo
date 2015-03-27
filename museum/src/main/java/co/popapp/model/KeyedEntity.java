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
import co.popapp.tuple.Pair;
import co.popapp.tuple.Quadruple;
import co.popapp.tuple.Triple;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * @author jam
 */
public abstract class KeyedEntity<K> extends Entity {
    /**
     * TODO .
     * @author jamming
     */
    public static abstract class AutoKeyEntity
    extends KeyedEntity<Long> { /* Long key */ }

    /**
     * TODO .
     * @author jam
     */
    public static abstract class PairKeyEntity<T, S>
    extends KeyedEntity<Pair<T, S>> { /* Pair key */ }

    /**
     * TODO .
     * @author jam
     */
    public static abstract class QuadrupleKeyEntity<T, S, Z, W>
    extends KeyedEntity<Quadruple<T, S, Z, W>> { /* Quadruple key */ }

    /**
     * TODO .
     * @author jam
     */
    public static abstract class TripleKeyEntity<T, S, Z>
    extends KeyedEntity<Triple<T, S, Z>> { /* Triple key */ }

    /** TODO . */
    public final Field<K> key = new Field<K> (this);
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
