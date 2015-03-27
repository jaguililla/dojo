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
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.Map;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * Groups a set of modules (which groups a set of entities). Provides access to metadata.
 * @author jamming
 */
public abstract class Model {
    /**
     * TODO .
     * Marker class to define a class which only holds entities. The reason why this class is
     * not an interface is because I don't want the modules to inherit from other classes, and
     * because an entity holder 'is a' module. Will be reviewed in the future.
     * @author jamming
     */
    public static abstract class Module { /* Marker class */ }

    /**
     * TODO .
     * @author jamming
     */
    @Target (ElementType.FIELD)
    @Retention (RetentionPolicy.RUNTIME)
    public @interface NotNull { /* Marker annotation */ }

    /** TODO This will hold ALL the metadata (no matter what Model it belongs). */
    private static final Map<Class<? extends Entity>, EntityStructure> STRUCTURE =
        new HashMap<Class<? extends Entity>, EntityStructure> ();

    /**
     * TODO .
     * TODO Order fields!!!
     */
    public static EntityStructure load (Class<? extends Entity> entityClass) {
        EntityStructure entityFields = STRUCTURE.get (entityClass);

        if (entityFields == null) {
            entityFields = new EntityStructure (entityClass);

            Class<?> sc = entityClass.getSuperclass ();
            if (sc != null && Entity.class.isAssignableFrom (sc)) {
                @SuppressWarnings ("unchecked")
                Class<Entity> superclass = (Class<Entity>)sc;
                entityFields.fields.addAll (load (superclass).fields);
            }

            for (java.lang.reflect.Field f : entityClass.getDeclaredFields ())
                if (ReadField.class.isAssignableFrom (f.getType ()))
                    entityFields.fields.add (new FieldStructure (f, null));

            STRUCTURE.put (entityClass, entityFields);
        }

        return entityFields;
    }

    /**
     * TODO .
     */
    @SuppressWarnings ("unchecked")
    public static void load (Class<? extends Module>... aModules) {
        for (Class<? extends Module> m : aModules)
            for (Class<?> e : m.getDeclaredClasses ())
                if (Entity.class.isAssignableFrom (e))
                    load ((Class<? extends Entity>)e);
    }

    /**
     * TODO .
     * TODO Order fields!!!
     */
    @SafeVarargs
    public static void loadAll (Class<? extends Entity>... entityClasses) {
        for (Class<? extends Entity> entityClass : entityClasses)
            load (entityClass);
    }

    /**
     * TODO .
     * @return .
     */
    public static EntityStructure structure (Entity aEntity) {
        return STRUCTURE.get (aEntity.getClass());
    }

    /**
     * TODO .
     * @return .
     */
    public static FieldStructure structure (ReadField<?> aField) {
        return structure (aField.mEntity).fields.get (aField.mIndex);
    }

    /**
     * TODO .
     */
    private Model () {
        throw new IllegalStateException ();
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
