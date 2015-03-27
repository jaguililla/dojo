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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * @author jamming
 */
public class KeyedListField<K, T extends KeyedEntity<K>> extends ListField<T> {
    /** TODO . */
    private static final long serialVersionUID = 728850682167711201L;
    /** TODO . */
    private final Map<K, T> mKeys = new HashMap<K, T> ();

    /**
     * TODO .
     * @param aEntity
     */
    public KeyedListField (Entity aEntity) {
        super (aEntity);
    }

    /**
     * TODO .
     * @param aEntity
     * @param aValue
     */
    public KeyedListField (Entity aEntity, List<T> aValue) {
        super (aEntity, aValue);
    }

    /**
     * @param aKey
     * @return
     * @see java.util.Map#containsKey(Object)
     */
    public boolean containsKey (Object aKey) {
        return mKeys.containsKey (aKey);
    }

    /**
     * @param aKey
     * @return
     * @see java.util.Map#get(Object)
     */
    public T get (Object aKey) {
        return mKeys.get (aKey);
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
