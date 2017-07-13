package co.popapp.util.tree;

import static java.util.Arrays.asList;
import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import co.popapp.util.tree.TreeLeaf;

/**
 * Mutable node tree, it is heterogeneus, so type is only for the value.
 *
 * @param <T> Type for the value of the node. Parent and children can be of a different type.
 * @author jamming
 */
public class TreeNode<T> {
    /**
     * Builds a tree from a list of entities.
     *
     * <p>Creates a table with the parents and traverses it top/down and left/right creating
     * the nodes if they do not exists in the result tree yet.</p>
     *
     * <pre>
     * Example:
     *
     * ENTITIES: E1, E2
     *
     * ENTITIES PARENTS:
     *   - E1 -> P1, P2, P3
     *   - E2 -> P1, P2, P4
     *
     * TRAVERSED TABLE
     *
     * +----+----+----+----+
     * | P1 | P2 | P3 | E1 |
     * +----+----+----+----+
     * | P1 | P2 | P4 | E2 |
     * +----+----+----+----+
     *
     * TRAVERSED ORDER
     *
     * P1, P2, P3, E1, P1 (EXISTS), P2 (EXISTS), P4, E2
     *
     * RESULTING TREE
     *
     * - ROOT
     *   - P1
     *     - P2
     *       - P3
     *         - E1
     *       - P4
     *         - E2
     * </pre>
     *
     * @param aEntities List with entities. If 'null' or empty only the root node will be returned.
     * @param aBuilder Provides logic to buildTree the traversed table from entities. Can't be
     *  'null'.
     * @param <E> Leaves type.
     * @return The resulting tree.
     */
    public static <E> TreeNode<?> buildTree (List<E> aEntities, TreeBuilder<E> aBuilder) {
        if (aBuilder == null)
            throw new IllegalArgumentException ("tree.TreeBuilder can not be 'null'");

        // Root is empty node, pointer is used to traverse the tree while looping over entities
        TreeNode<?> root = new TreeNode<String> ("ROOT"), pointer = root;

        if (aEntities == null)
            return root;

        sort (aEntities, aBuilder);

        // Loop over entities (leaves), these are the table rows
        for (E e : aEntities) {
            // Loop over entity's parents (levels) starting from the root (columns)
            for (Object p : aBuilder.list (e)) {
                TreeNode<?> node = pointer.getChild (p);
                if (node == null) {
                    // Node not found in tree (will be created and pointed to)
                    TreeNode<?> newNode = new TreeNode<Object> (p, pointer);
                    pointer.children.add (newNode);
                    pointer = newNode;
                }
                else {
                    // Node already exists (won't be created, only the pointer will be updated)
                    pointer = node;
                }
            }

            // If a node with the value already exists, this entity is discarted
            if (pointer.getChild (e) == null)
                pointer.children.add (new TreeNode<E> (e, pointer));

            pointer = root; // Set pointer to the root for the next row (leaf)
        }

        return root;
    }

    public static <E extends Comparable<E> & TreeLeaf<E>>
    TreeNode<?> buildTree (List<E> aEntities) {

        // Root is empty node, pointer is used to traverse the tree while looping over entities
        TreeNode<?> root = new TreeNode<> ("ROOT"), pointer = root;

        if (aEntities == null)
            return root;

        sort (aEntities, null);

        // Loop over entities (leaves), these are the table rows
        for (E e : aEntities) {
            // Loop over each entity's parents (levels) starting from root. Table's columns
            for (Object p : e.list (e)) {
                TreeNode<?> node = pointer.getChild (p);
                if (node == null) {
                    // Node not found in tree (will be created and pointed to)
                    TreeNode<?> newNode = new TreeNode<> (p, pointer);
                    pointer.children.add (newNode);
                    pointer = newNode;
                }
                else {
                    // Node exists (it won't be created, only the pointer will be updated)
                    pointer = node;
                }
            }

            // If a node with the value already exists, this entity is discarted
            if (pointer.getChild (e) == null)
                pointer.children.add (new TreeNode<> (e, pointer));

            pointer = root; // Set pointer to the root for the next row (leaf)
        }

        return root;
    }

    public static <V> TreeNode<V> treeNode (Object... children) {
        return treeNode (null, children);
    }

    public static <V> TreeNode<V> treeNode (V value, Object... children) {
        TreeNode<V> node = new TreeNode<> (value);
        node.add (children);
        return node;
    }

    private T value;
    private TreeNode<?> parent;
    private final List<TreeNode<?>> children;

    public TreeNode () {
        value = null;
        parent = null;
        children = new ArrayList<> ();
    }

    public TreeNode (T aValue, TreeNode<?> aParent, List<TreeNode<?>> aChildren) {
        value = aValue;
        parent = aParent;
        children = aChildren;
    }

    public TreeNode (T aValue, List<TreeNode<?>> aChildren) {
        this (aValue, null, aChildren);

        // Assign the children this node as parent (only for declarative builder)
        for (TreeNode<?> n : children)
            assignParents (n, this);
    }

    public TreeNode (T aValue) {
        this (aValue, null, new ArrayList<TreeNode<?>> ());
    }

    public TreeNode (T aValue, TreeNode<?> aParent) {
        this (aValue, aParent, new ArrayList<TreeNode<?>> ());
    }

    public TreeNode (T aValue, TreeNode<?>... aChildren) {
        this (aValue, asList (aChildren));
    }

    public T getValue () {
        return value;
    }

    public void setValue (T aValue) {
        value = aValue;
    }

    public TreeNode<?> getParent () {
        return parent;
    }

    public void setParent (TreeNode<?> aParent) {
        parent = aParent;
    }

    public List<TreeNode<?>> getChildren () {
        return children;
    }

    public TreeNode<T> add (TreeNode<?>... elements) {
        getChildren ().addAll (asList (elements));
        return this;
    }

    public TreeNode<T> add (Object... elements) {
        getChildren ().addAll (
            Stream.of (elements)
                .map (it -> new TreeNode<> (it))
                .collect (Collectors.toList ())
        );
        return this;
    }

    /**
     * TODO
     * @param aP
     * @return
     */
    public TreeNode<?> getChild (Object aP) {
        for (TreeNode<?> n : children)
            if (n.value.equals (aP))
                return n;

        return null;
    }

    /**
     * TODO
     * @param aIndexes
     * @return
     */
    public TreeNode<?> getDescendent (int... aIndexes) {
        TreeNode<?> result = this;

        for (int index : aIndexes)
            result = result.children.get (index);

        return result;
    }

    public Object getDescendentValue (int... aIndexes) {
        return getDescendent (aIndexes).getValue ();
    }

    private void assignParents (TreeNode<?> aNode, TreeNode<?> aParent) {
        aNode.parent = aParent;
        for (TreeNode<?> n : aNode.children)
            assignParents (n, aNode);
    }

    private String list (int aLevel, StringBuilder aBuilder, TreeNode<?> aNode) {
        aBuilder.append (System.getProperty ("line.separator"));
        for (int ii = 0; ii < aLevel; ii++)
            aBuilder.append ("\t");

        Object parentVal = aNode.parent == null? null : aNode.parent.value;
        aBuilder.append (aNode.value).append (" [").append (parentVal).append ("]");

        final List<TreeNode<?>> children = aNode.children;
        if (children != null)
            for (TreeNode<?> cb : aNode.children)
                list (aLevel + 1, aBuilder, cb);

        return aBuilder.toString ();
    }

    @Override public String toString () {
        return list (0, new StringBuilder (), this);
    }

    @Override public boolean equals (Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TreeNode))
            return false;

        TreeNode treeNode = (TreeNode)o;

        return !(children != null?
            !children.equals (treeNode.children) :
            treeNode.children != null) && !(parent != null?
                !parent.equals (treeNode.parent) :
                treeNode.parent != null) && !(value != null?
                    !value.equals (treeNode.value) :
                    treeNode.value != null);
    }

    @Override public int hashCode () {
        int result = value != null? value.hashCode () : 0;
        result = 31 * result + (parent != null? parent.hashCode () : 0);
        return 31 * result + (children != null? children.hashCode () : 0);
    }
}
