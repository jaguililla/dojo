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
import java.util.WeakHashMap;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * TODO Preserve order
 * @author jam
 */
public class EntityListenerList extends WeakHashMap<EntityListener, Void> {
    /** Initial capacity for the listeners list. */
    private static final int INITIAL_LISTENERS = 0;

    /** TODO . */
    public transient boolean notifyEvents = true;

    /**
     * TODO .
     */
    public EntityListenerList () {
        super (INITIAL_LISTENERS);
    }

    /**
     * TODO .
     * @param <T> .
     * @param aListField .
     * @param aAddedEntity .
     */
    <T> void fireEntityAdded (ListField<T> aListField, T aAddedEntity) {
        if (notifyEvents)
            for (EntityListener l : keySet ())
                l.entityAdded (aListField, aAddedEntity);
    }

    /**
     * TODO .
     * @param <T> .
     * @param aListField .
     * @param aOldEntity .
     * @param aNewEntity .
     */
    <T> void fireEntityChanged (ListField<T> aListField, T aOldEntity, T aNewEntity) {
        if (notifyEvents)
            for (EntityListener l : keySet ())
                l.entityChanged (aListField, aOldEntity, aNewEntity);
    }

    /**
     * TODO .
     * @param <T> .
     * @param aListField .
     * @param aRemovedEntity .
     */
    <T> void fireEntityRemoved (ListField<T> aListField, T aRemovedEntity) {
        if (notifyEvents)
            for (EntityListener l : keySet ())
                l.entityRemoved (aListField, aRemovedEntity);
    }

    /**
     * TODO .
     * @param <T> .
     * @param aField .
     * @param aOldValue .
     * @param aNewValue .
     */
    <T> void fireFieldChanged (Field<T> aField, T aOldValue, T aNewValue) {
        if (notifyEvents)
            for (EntityListener l : keySet ())
                l.fieldChanged (aField, aOldValue, aNewValue);
    }

    public void add (EntityListener aNewListener) {
        put (aNewListener, null);
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
