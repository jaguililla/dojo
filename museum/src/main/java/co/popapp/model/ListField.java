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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * @author jamming
 * @param <T> .
 */
public class ListField<T> extends ReadField<List<T>> {
    /** TODO . */
    private static final long serialVersionUID = 876350565171107004L;

    /** @see ReadField#ReadField (Entity) */
    public ListField (Entity aEntity) {
        super (aEntity);
    }

    /**
     * TODO .
     * @param aValue .
     */
    public ListField (Entity aEntity, List<T> aValue) {
        super (aEntity);
        mValue = aValue;
    }

    /**
     * TODO .
     * @param aElement .
     */
    protected void entityChanged (
        @SuppressWarnings ("unused") T aElement,
        @SuppressWarnings ("unused") Entity aListEntity) { /* Empty */ }

    /** @see ReadField#print(StringBuilder, Entity, int) */
    @Override
    protected StringBuilder print (StringBuilder aBuffer, Entity aParent, int aLevel) {
        // Print the field name and object count
        indent (aBuffer, aLevel)
            .append (name ()).append (" [").append (get ().size ()).append ("]");

        // Print the elements (indented)
        int ii = 0;
        aLevel++;
        for (T e : get ())
            if (e != null && e instanceof Entity) {
                aBuffer.append ("\n");
                indent (aBuffer, aLevel)
                    .append ("<<<< ").append (ii++).append (" >>>>\n");

                ((Entity)e).print (aBuffer, mEntity, aLevel);
            }
            else
                aBuffer.append ("\n").append (String.valueOf (e));

        return aBuffer;
    }

    /** @see java.util.List#add(int, Object) */
    public void add (int aIndex, T aElement) {
        get ().add (aIndex, aElement);
        entityChanged (aElement, mEntity);
    }

    /** @see java.util.List#add(Object) */
    public boolean add (T aE) {
        boolean result = get ().add (aE);
        entityChanged (aE, mEntity);
        return result;
    }

    /** @see java.util.List#addAll(java.util.Collection) */
//    @SafeVarargs // TODO Uncomment when Travis support JDK 1.7
    @SuppressWarnings ("unchecked") // TODO Remove when Travis support JDK 1.7
    public final <E extends Entity> E addAll (
        @SuppressWarnings ("unused") Class<E> aEntityClass, T... aC) {

        addAll (Arrays.asList (aC));
        return (E)mEntity;
    }

    /** @see java.util.List#addAll(java.util.Collection) */
    public boolean addAll (Collection<? extends T> aC) {
        boolean result = get ().addAll (aC);
        for (T aE : aC)
            entityChanged (aE, mEntity);
        return result;
    }

    /** @see java.util.List#addAll(int, java.util.Collection) */
    public boolean addAll (int aIndex, Collection<? extends T> aC) {
        boolean result = get ().addAll (aIndex, aC);
        for (T aE : aC)
            entityChanged (aE, mEntity);
        return result;
    }

    /** @see java.util.List#addAll(int, java.util.Collection) */
//    @SafeVarargs // TODO Uncomment when Travis support JDK 1.7
    @SuppressWarnings ("unchecked") // TODO Remove when Travis support JDK 1.7
    public final boolean addAll (int aIndex, T... aC) {
        return addAll (aIndex, Arrays.asList (aC));
    }

    /** @see java.util.List#addAll(java.util.Collection) */
//    @SafeVarargs // TODO Uncomment when Travis support JDK 1.7
    @SuppressWarnings ("unchecked") // TODO Remove when Travis support JDK 1.7
    public final boolean addAll (T... aC) {
        return addAll (Arrays.asList (aC));
    }

    /** @see java.util.List#clear() */
    public void clear () {
        for (T aE : get ())
            entityChanged (aE, null);
        get ().clear ();
    }

    /** @see java.util.List#contains(Object) */
    public boolean contains (T aO) {
        return get ().contains (aO);
    }

    /** @see java.util.List#containsAll(java.util.Collection) */
    public boolean containsAll (Collection<? extends T> aC) {
        return get ().containsAll (aC);
    }

    /** @see java.util.List#containsAll(java.util.Collection) */
//    @SafeVarargs // TODO Uncomment when Travis support JDK 1.7
    @SuppressWarnings ("unchecked") // TODO Remove when Travis support JDK 1.7
    public final boolean containsAll (T... aC) {
        return containsAll (Arrays.asList (aC));
    }

    /**
     * TODO .
     * @return .
     */
    @Override
    public List<T> get () {
        return (mValue == null)? mValue = new ArrayList<T> () : mValue;
    }

    /** @see java.util.List#get(int) */
    public T get (int aIndex) {
        return get ().get (aIndex);
    }

    /** @see java.util.List#indexOf(Object) */
    public int indexOf (T aO) {
        return get ().indexOf (aO);
    }

    /** @see java.util.List#isEmpty() */
    public boolean isEmpty () {
        return get ().isEmpty ();
    }

    /**
     * @return .
     */
    public boolean isNull () {
        return mValue == null;
    }

    /** @see java.util.List#iterator() */
    public Iterator<T> iterator () {
        return get ().iterator ();
    }

    /** @see java.util.List#lastIndexOf(Object) */
    public int lastIndexOf (T aO) {
        return get ().lastIndexOf (aO);
    }

    /** @see java.util.List#listIterator() */
    public ListIterator<T> listIterator () {
        return get ().listIterator ();
    }

    /** @see java.util.List#listIterator(int) */
    public ListIterator<T> listIterator (int aIndex) {
        return get ().listIterator (aIndex);
    }

    /** @see java.util.List#remove(int) */
    public T remove (int aIndex) {
        entityChanged (get (aIndex), null);
        return get ().remove (aIndex);
    }

    /** @see java.util.List#remove(Object) */
    public boolean remove (T aO) {
        entityChanged (aO, null);
        return get ().remove (aO);
    }

    /** @see java.util.List#removeAll(java.util.Collection) */
    public boolean removeAll (Collection<? extends T> aC) {
        for (T aO : get ())
            entityChanged (aO, null);
        return get ().removeAll (aC);
    }

    /** @see java.util.List#removeAll(java.util.Collection) */
//    @SafeVarargs // TODO Uncomment when Travis support JDK 1.7
    @SuppressWarnings ("unchecked") // TODO Remove when Travis support JDK 1.7
    public final boolean removeAll (T... aC) {
        return removeAll (Arrays.asList (aC));
    }

    /** @see java.util.List#retainAll(java.util.Collection) */
    public boolean retainAll (Collection<? extends T> aC) {
        // TODO
        return get ().retainAll (aC);
    }

    /** @see java.util.List#retainAll(java.util.Collection) */
//    @SafeVarargs // TODO Uncomment when Travis support JDK 1.7
    @SuppressWarnings ("unchecked") // TODO Remove when Travis support JDK 1.7
    public final boolean retainAll (T... aC) {
        return retainAll (Arrays.asList (aC));
    }

    /** @see java.util.List#set(int, Object) */
    public T set (int aIndex, T aElement) {
        entityChanged (get ().get (aIndex), null);
        entityChanged (aElement, mEntity);
        return get ().set (aIndex, aElement);
    }

    /** @see java.util.List#size() */
    public int size () {
        return get ().size ();
    }

    /** @see java.util.List#subList(int, int) */
    public List<T> subList (int aFromIndex, int aToIndex) {
        return get ().subList (aFromIndex, aToIndex);
    }

    /** @see java.util.List#toArray(Object[]) */
    public T [] toArray (T [] aA) {
        return get ().toArray (aA);
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
