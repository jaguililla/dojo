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
import java.lang.annotation.Annotation;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * @author jamming
 */
public class FieldStructure {
    public final String name;
    public final FieldFilter filter;
    public final java.lang.reflect.Field metadata;

    public FieldStructure (java.lang.reflect.Field aMetadata, FieldFilter aFilter) {
        name = aMetadata.getName ();
        filter = aFilter;
        metadata = aMetadata;
    }

    public boolean haveAnnotation (Class<? extends Annotation> aAnnotationClass) {
        return metadata.isAnnotationPresent (aAnnotationClass);
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
