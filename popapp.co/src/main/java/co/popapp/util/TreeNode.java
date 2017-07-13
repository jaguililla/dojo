/*
 * Copyright (C) 2002 - Juan José Aguililla Martínez. Todos los derechos reservados.
 */

// P A C K A G E ////////////////////////////////////////////////////////////////////////
package co.popapp.util;

// I M P O R T //////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import co.popapp.StringUtils;

// C L A S S ////////////////////////////////////////////////////////////////////////////
/**
 * Nodo de un árbol de cualquier número de elementos. Es por si mismo un árbol. No puede
 * almacenar valores nulos <i>null</i>. Si el padre es <i>null</i> significa que es un
 * nodo raiz.
 * PENDIENTE:
 *  - Implementar "cloneable" y sobreescribir el método clone ().
 *  - Hacer que sea un objeto más del framework "Collections" del JDK.
 *  - Estudiar la posibilidad de que children sea de la clase <code>WeakHashMap</code>.
 *  - Métodos: "generateIndex ()" y "deleteIndex ()"
 *  - Métodos: "load (TreeNode [])" y "store (TreeNode [])"
 *  - Subclase IndexedTreeNode
 *  - Subclase WeakTreeNode
 * @author jamming
 */
public class TreeNode {
    /** Nodo padre. */
    private TreeNode parent = null;
    /** Tabla de los nodos hijos. */
    private Map children = null;
    /** Clave del nodo. */
    private Object id = null;
    /** Valor del nodo. */
    private Object value = null;

    /**
     * Constructor con el identificador (id) y el valor del nodo.
     * @param nodeId Objeto que será la clave de este nodo.
     * @param nodeValue Objeto almacenado en el nodo.
     */
    public TreeNode (Object nodeId, Object nodeValue) {
        super();
        setId(nodeId);
        setValue(nodeValue);
    }

    /**
     * Constructor con el identificador (id), el valor y el nodo padre de este nodo.
     * @param parentNode Nodo padre del que se va a crear.
     * @param nodeId Objeto que será la clave de este nodo.
     * @param nodeValue Objeto almacenado en el nodo.
     */
    public TreeNode (TreeNode parentNode, Object nodeId, Object nodeValue) {
        this(nodeId, nodeValue);
        setParent(parentNode);
    }

    /**
     * Libera los objetos no necesarios al eliminar el nodo.
     * @see java.lang.Object#finalize()
     */
    @Override
    protected void finalize () throws Throwable {
        id = null;
        value = null;
        parent = null;
        children.clear();
        children = null;
        super.finalize();
    }

    /**
     * Method printSubTree.
     * @param level
     * @param treeString
     * @return String
     */
    protected String printSubTree (int level, StringBuffer treeString) {
        treeString.append(StringUtils.repeat (String.valueOf ('\t'), level));
        treeString.append(toString());
        treeString.append("\n");
        if (children != null) {
            Iterator keys = children.keySet().iterator();
            while (keys.hasNext()) {
                TreeNode currentChild = (TreeNode)keys.next();
                treeString.append(currentChild.printSubTree(level + 1, treeString));
            }
        }
        return treeString.toString();
    }

    /**
     * Method addChild.
     * NOTA: no comprueba que el nodo exista ya.
     * @param child
     */
    public void addChild (TreeNode child) {
        if (child == null) {
            throw new IllegalArgumentException("Invalid tree child.");
        }
        if (children == null) {
            children = new HashMap();
        }
        else {
            if (children.containsKey(child.id)) {
                throw new IllegalStateException("Node " + String.valueOf(child.id)
                    + " is already child of " + String.valueOf(id));
            }
        }
        child.parent = this;
        children.put(child.id, child);
    }

    /**
     * .
     * Devuelve el arbol como un mapa. Hace el acceso aleatorio más rápido en caso de no tener
     * que hacer inserciones.
     *
     * Claves:
     * a
     *   aa
     *   ab
     *     aba
     *   ac
     * b
     *   ba
     * c
     * d
     *
     * a
     * a.aa
     * a.ab
     * a.ab.aba
     * a.ac
     * b
     * b.ba
     * c
     * d
     *
     * TODO Pendiente de documentar
     * @return Map
     */
    public Map deflate () {
        return null;
    }

    /**
     * .
     * @param childPath .
     * @return .
     */
    public TreeNode getChild (List childPath) {
        if (childPath == null) {
            throw new IllegalArgumentException("Invalid tree path.");
        }
        TreeNode result = null;
        for (int ii = 0; ii < childPath.size(); ii++) {
            result = result.getChild(childPath.get(ii));
            if (result == null) {
                return null;
            }
        }
        return result;
    }

    /**
     * Devuelve un nodo descendiente directo de este con el id indicado. Si no
     * se encuentra ninguno se devuelve null.
     * @param childId Clave del hijo que buscado.
     * @return El nodo cuyo id es "childId" o null si no existe.
     */
    public TreeNode getChild (Object childId) {
        return (TreeNode)children.get(childId);
    }

    /**
     *
     * @param childPath
     * @return TreeNode
     */
    public TreeNode getChild (Object [] childPath) {
        return getChild(Arrays.asList(childPath));
    }

    /**
     *
     * @return TreeNode[]
     */
    public TreeNode [] getChildren () {
        return null;
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @return Número de hijos.
     */
    public int getChildrenCount () {
        return children.size();
    }

    /**
     * Devuelve el identificador del nodo.
     * @return Identificador del nodo.
     */
    public Object getId () {
        return id;
    }

    /**
     * Devuelve el nivel de este nodo dentro del árbol al que pertenece.
     * @return Nivel de este nodo dentro del árbol al que pertenece.
     */
    public int getLevel () {
        int level = 0;
        TreeNode parentNode = getParent();
        while (parentNode != null) {
            parentNode = parentNode.getParent();
            level++;
        }
        return level;
    }

    /**
     * Devuelve el nodo inmediatamente superior a este en la jerarquía del árbol.
     * @return Padre del nodo actual.
     */
    public TreeNode getParent () {
        return parent;
    }

    /**
     * No incluye el nodo actual
     * @return TreeNode[]
     */
    public TreeNode [] getPathToRoot () {
        List nodes = new ArrayList();
        TreeNode root = this;
        while (root.getParent() != null) {
            root = root.getParent();
            nodes.add(root);
        }
        TreeNode [] result = new TreeNode [nodes.size()];
        nodes.toArray(result);
        return result;
    }

    /**
     * Devuelve la raiz del árbol al que pertenece este nodo.
     * @return Raiz del árbol al que pertenece este nodo.
     */
    public TreeNode getRoot () {
        TreeNode root = this;
        while (root.getParent() != null) {
            root = root.getParent();
        }
        return root;
    }

    /**
     * Method getSubTreeChildren.
     * @return List
     */
    public TreeNode [] getSubTreeChildren () {
        ArrayList allChildren = new ArrayList();
        if (children != null) {
            Iterator keys = children.keySet().iterator();
            while (keys.hasNext()) {
                TreeNode currentChild = (TreeNode)children.get(keys.next());
                allChildren.add(currentChild);
                allChildren.addAll(Arrays.asList(currentChild.getSubTreeChildren()));
            }

        }
        TreeNode [] result = new TreeNode [allChildren.size()];
        allChildren.toArray(result);
        return result;
    }

    /**
     * Devuelve el objeto almacenado por el nodo.
     * @return Objeto contenido en el nodo.
     */
    public Object getValue () {
        return value;
    }

    /**
     * Indica si este nodo es terminal, no tiene hijos.
     * @return True si el nodo no contiene más nodos.
     */
    public boolean isLeaf () {
        return children.isEmpty();
    }

    /**
     * Devuelve si este nodo es raiz (no tiene padre).
     * @return True si este nodo es raiz.
     */
    public boolean isRoot () {
        return parent == null;
    }

    /**
     *
     * @return String
     */
    public String printSubTree () {
        return printSubTree(0, new StringBuffer());
    }

    /**
     * Method removeChild.
     * NOTA: no elimina el subarbol del nodo eliminado.
     * @param childId
     */
    public void removeChild (TreeNode childId) {
        if (childId == null) {
            throw new IllegalArgumentException("Invalid tree child.");
        }
        children.remove(childId);
    }

    /**
     *
     * @param nodeId
     * @return TreeNode[]
     */
    /*
    public TreeNode [] getPathToNode (Object nodeId) {
        // TODO Pendiente de implementar
        return null;
    }
    */

    /**
     *
     * @param childId
     * @return TreeNode[]
     */
    public TreeNode [] searchSubTreeChild (Object childId) {
        ArrayList nodes = new ArrayList();
        if (children != null) {
            TreeNode searchNode = (TreeNode)children.get(childId);
            if (searchNode != null) {
                nodes.add(searchNode);
            }
            Iterator keys = children.keySet().iterator();
            while (keys.hasNext()) {
                TreeNode currentChild = (TreeNode)children.get(keys.next());
                nodes.addAll(Arrays.asList(currentChild.searchSubTreeChild(childId)));
            }

        }
        TreeNode [] result = new TreeNode [nodes.size()];
        nodes.toArray(result);
        return result;
    }

    /**
     * Asigna un nuevo valor al identificador del nodo.
     * @param aId Nuevo identificador de este nodo.
     */
    public void setId (Object aId) {
        if (aId == null) {
            throw new IllegalArgumentException("Invalid node id.");
        }
        this.id = aId;
    }

    /**
     * Añade un hijo al nodo y si ya existe esa id lo sobreescribe
     * @param childId
     * @param newChild
     */
    /*
    public void setChild (Object childId, TreeNode newChild) {
        // TODO Pendiente de implementar
    }
    */

    /**
     * Cambia el padre de este nodo. Se elimina el nodo de la lista de hijos de su
     * anterior padre y se añade al nuevo padre. Los nodos hijos del nodo actual
     * cambian de jerarquia junto con el nodo actual.
     * @param newParent
     */
    public void setParent (TreeNode newParent) {
        if (parent != null) {
            parent.removeChild(this);
        }
        if (newParent != null) {
            newParent.addChild(this);
        }
        else {
            parent = newParent;
        }
    }

    /**
     * Asigna al nodo un objeto.
     * @param aValue que será almacenado en el nodo.
     */
    public void setValue (Object aValue) {
        if (aValue == null) {
            throw new IllegalArgumentException("Invalid node value.");
        }
        this.value = aValue;
    }

    /**
     * Devuelve una cadena representativa de este nodo con el formato: id - valor.
     * PENDIENTE: poner tambien el nivel, padre e hijos del nodo. ej.
     *     padre -> id [1] -> (hijo1, hijo2, hijo3) - valor
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString () {
        StringBuffer nodeInfo = new StringBuffer();
        nodeInfo.append(String.valueOf(id));
        nodeInfo.append(" - ");
        nodeInfo.append(String.valueOf(value));
        return nodeInfo.toString();
    }
}
// E O F ////////////////////////////////////////////////////////////////////////////////
