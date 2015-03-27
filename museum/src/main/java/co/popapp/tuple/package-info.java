/*
 * POPAPP Copyright © 2011 Juan José Aguililla. All rights reserved.
 *
 * This program comes WITHOUT ANY WARRANTY; It is free software, you can redistribute it and/or
 * modify it under the terms of the GNU General Public License, Version 3.0.
 * You may obtain a copy of the License at: http://www.gnu.org/licenses/gpl.html
 */

/**
 * <p>Holds classes for N elements tuples. The tuples will be mutable and their elements
 * will allow the <code>null</code> value. Tuples don't support primitive types.</p>
 *
 * <p>A concrete class will exist for each tuple size. Tuples of diferent sizes won't be complatible
 * (an N tuple can't be assigned to a N - 1 tuple).</p>
 *
 * <p>The abstract type Tuple won't be accesible outside the package and it's only used to group
 * functionality common to all tuple sizes.</p>
 *
 * <p>TODO Add support for comparison and serialization.</p>
 */
package co.popapp.tuple;