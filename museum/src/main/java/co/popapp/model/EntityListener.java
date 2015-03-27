/*
 * POPAPP Copyright © 2011 Juan José Aguililla. All rights reserved.
 *
 * This program comes WITHOUT ANY WARRANTY; It is free software, you can redistribute it and/or
 * modify it under the terms of the GNU General Public License, Version 3.0.
 * You may obtain a copy of the License at: http://www.gnu.org/licenses/gpl.html
 */

// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.model;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * <p>Entity events handler. There are events for field modification and for adding, modifying
 * and removal of an object inside any list field inside an Entity.</p>
 *
 * <p>Any class implementing this interface can be provided to entity's instances to handle entity
 * events.</p>
 *
 * @author jamming
 */
public interface EntityListener {
    /**
     * Called when an element is added to an entity's list field.
     *
     * @param <T> Type of the element added to the list field.
     * @param aField List field which has received the new element.
     * @param aValue The new element added to the list field.
     */
    <T> void entityAdded (ListField<T> aField, T aValue);

    /**
     * Invoked when an item from a list field is changed.
     *
     * @param <T> Type of the element modified in the list field.
     * @param aField List field in which the element changed.
     * @param aOldValue List element prior to the modification.
     * @param aValue Modified list element.
     */
    <T> void entityChanged (ListField<T> aField, T aOldValue, T aValue);

    /**
     * This method is called when an object is removed from an Entity's list field.
     *
     * @param <T> Type of the element removed in the list field.
     * @param aField List field in which held the deleted element.
     * @param aValue Element removed from the list field.
     */
    <T> void entityRemoved (ListField<T> aField, T aValue);

    /**
     * Callback for the field modified event.
     *
     * @param <T> Type of the modified field.
     * @param aField Entity's fild which was changed.
     * @param aOldValue Field's value prior to its change.
     * @param aValue New value assigned to the field.
     */
    <T> void fieldChanged (Field<T> aField, T aOldValue, T aValue);
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
