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
import static co.popapp.model.Model.*;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;
import java.util.List;


// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * <p>Represents an entity, each instance will hold an array with all the fields in the class
 * (references to an entity's field instances), this array's order is determined by the
 * metadata () method.</p>
 *
 * <p>Entities has support for keys. Multiple keys can be achieved using tuples. Keys are stored
 * in the key field, but others fields can be created referencing the keys (using the same
 * instance). TODO Key support for entities lists.</p>
 *
 * <p>Extends <code>Module</code> just to be able to hold entities within entities (is handy
 * to define entity hierachies in a single file).</p>
 *
 * <p>Gets/Sets</p>
 *
 * Entity Lists (bidirectional fields)
 *
 * Automatic implementations
 * - toString
 * - clone
 * - hashcode
 * - equals
 * - serialization
 *
 * Events
 * - field modification events
 * - list addition/deletion events
 *
 * Performance:
 * - Memory
 * - CPU
 *
 * Metadata
 * -Stored in an static collection in Model. Performance and memory can be improved
 *
 * <pre>
 * METADATA STRATEGIES
 *
 * 1) All Java
 *      - Static initializers in each entity for metadata
 *      - Initializers with all fields in each entity
 *      - No need for annotations
 *      - Can be coded with errors
 * 2) Generate static metadata using Java compiler annotation processors
 *      - Problems with actual annotation processors support
 *      - Annotations only compile time
 *      -
 * 3) Using reflection
 *      - Problem defining model entities. Maybe calling: Entity.init (Module1, Module2...) can work
 *      - No need to do nothing
 *      - No fails
 *      - Annotations runtime
 *      - Can change initializer by getFields when mFields is accessed
 * 4) Mixed
 *      - Simplified file (easy to write by hand) with entities, but not metadata
 *      - Metadata have to be runtime
 *      - Can be initialized early
 *
 * We have to pass the owner entity to the field constructor to avoid an instance initializer in
 * all entities
 *
 * TODO Names of nested classes can be changed: EntityListener -> Entity.Listerner,
 * PairKeyEntity -> Entity.PairKey
 *
 * TODO Add support to compare entities (if fields are comparable, compare each one in the
 * definition order)
 *
 * TODO Review entity inheritance to distribute features across subclasses. Problem: hard to
 *      use only some of the features. Options: use a mixin like structure.
 *      Entity
 *          ExternalizableEntity
 *          ComparableEntity
 *          KeyedEntity
 *
 * TODO Support for bidirectional fields using 'closures' (interfaces).
 * </pre>
 *
 * @author jamming
 */
public abstract class Entity implements Externalizable {
    /**
     * TODO .
     * @author jamming
     */
    public interface Observable { /* Marker interface */ }

    /** TODO . */
    ReadField<?>[] mFields = new ReadField<?>[load (getClass ()).fields.size ()];
    /** TODO . */
    protected transient byte mIndex;
    /** TODO Can be changed to use lazy loading (using an accessor). */
    transient EntityListenerList listeners;

    /**
     * TODO .
     * @return .
     */
    protected <T> Field<T> field () {
        return new Field<T> (this);
    }

    /**
     * TODO .
     * @param aValue
     * @return .
     */
    protected <T> Field<T> field (T aValue) {
        return new Field<T> (this, aValue);
    }

    /**
     * TODO .
     * @return .
     */
    protected <T> ListField<T> listField () {
        return new ListField<T> (this);
    }

    /**
     * TODO .
     * TODO This group of methods can be 'builder' methods and get rid of a lot (if not all) of
     *      the annotations. Ie: field ().notNull ().compareAt (1).build ();
     * @param aValue
     * @return .
     */
    protected <T> ListField<T> listField (List<T> aValue) {
        return new ListField<T> (this, aValue);
    }

    /**
     * TODO .
     * @return .
     */
    protected <T> ReadField<T> readField () {
        return new ReadField<T> (this);
    }

    /**
     * TODO .
     * @param aValue
     * @return .
     */
    protected <T> ReadField<T> readField (T aValue) {
        return new ReadField<T> (this, aValue);
    }

    /**
     * TODO .
     * To initialize R/O fields in constructors.
     * @param <T> .
     * @param aField .
     * @param aValue .
     */
    protected <T> void set (ReadField<T> aField, T aValue) {
        FieldFilter f = structure (aField).filter;
        if (f == null || f.filter (aValue))
            aField.mValue = aValue;
    }

    /**
     * TODO .
     * @param <T> .
     * @param aDest .
     * @return .
     */
    @SuppressWarnings ({"unchecked", "rawtypes"})
    public <T extends Entity> T copy (T aDest) {
        if (aDest == null || !aDest.getClass ().equals (getClass ()))
            throw new IllegalArgumentException ("");

        ReadField[] destFields = aDest.mFields;
        ReadField[] fields = mFields;

        for (int ii = 0; ii < fields.length; ii++)
            destFields[ii].mValue = fields[ii].mValue;

        return aDest;
    }

    /**
     * TODO
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals (Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass () != obj.getClass ()) return false;

        Entity other = (Entity)obj;
        if (!Arrays.equals (mFields, other.mFields))
            return false;

        return true;
    }

    /**
     * TODO
     * @see Object#hashCode()
     */
    @Override public int hashCode () {
        return Arrays.hashCode (mFields);
    }

    /**
     * TODO .
     * @return
     */
    public EntityListenerList listeners () {
        if (listeners == null) {
            if ( !(this instanceof Observable) )
                throw new IllegalStateException ();
            return listeners = new EntityListenerList ();
        }
        return listeners;
    }

    /**
     * TODO .
     * @param aBuffer .
     * @return .
     */
    public StringBuilder print (StringBuilder aBuffer) {
        return print (aBuffer, null, 0);
    }

    /**
     * TODO .
     * @param aBuffer .
     * @param aParent .
     * @param aLevel .
     * @return .
     */
    public StringBuilder print (StringBuilder aBuffer, Entity aParent, int aLevel) {
        ReadField<?>[] fields = mFields;
        if (fields == null || fields.length == 0)
            return aBuffer;

        fields[0].print (aBuffer, aParent, aLevel);
        for (int ii = 1; ii < fields.length; ii++)
            fields[ii].print (aBuffer.append ("\n"), aParent, aLevel);

        return aBuffer;
    }

    /** @see java.io.Externalizable#readExternal(java.io.ObjectInput) */
    @Override
    public void readExternal (ObjectInput aIn) throws IOException, ClassNotFoundException {
        if (aIn.readLong () != Model.structure (this).suid)
            throw new IllegalStateException ();

        int fs = aIn.readInt ();
        for (int ii = 0; ii < fs; ii++) {
            mFields[ii] = (ReadField<?>)aIn.readObject ();
            mFields[ii].mEntity = this;
            mFields[ii].mIndex = (byte)ii;
        }
    }

    /** @see Object#toString() */
    @Override public String toString () {
        return print (new StringBuilder (128)).toString ();
    }

    /** @see java.io.Externalizable#writeExternal(java.io.ObjectOutput) */
    @Override public void writeExternal (ObjectOutput aOut) throws IOException {
        aOut.writeLong (Model.structure (this).suid);
        aOut.writeInt (mFields.length);
        for (ReadField<?> f : mFields)
            aOut.writeObject (f);
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
