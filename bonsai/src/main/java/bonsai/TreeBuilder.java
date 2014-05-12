package bonsai;

import java.util.Comparator;

/**
 * Supplies the needed behaviour to buildTree a table from a list of entities. Essentially this is
 * providing a list with an Entity's parents and sorting them taking care of the parents.
 *
 * @param <E> Type of the entities from which the builder will arrange a tree (tree leaves).
 */
public interface TreeBuilder<E> extends Comparator<E>, TreeLeaf<E> {}
