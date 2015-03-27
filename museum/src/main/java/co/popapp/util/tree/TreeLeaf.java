package co.popapp.util.tree;

import java.util.List;

public interface TreeLeaf<E> {
    /**
     * Should provide a list with all entity's ascendants.
     *
     * @param aEntity The leave from which the parents will be extracted. Empty or 'null' will
     *  result in a plain list (a tree with one level).
     * @return List of beans from the entity (leaf) to the root of the tree (root first).
     */
    List<?> list (E aEntity);
}
