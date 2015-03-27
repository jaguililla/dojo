/*
 * XTreeModel.java
 * Creado en: 18/09/2007 16:39:57 por: juanjose.aguililla
 */

// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.swing.widget.tree;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * .
 * TODO Pendiente de documentar
 * @author jamming
 */
public class XTreeModel extends DefaultTreeModel {
    /**
     * Constructor.
     * TODO Pendiente de documentar
     */
    public XTreeModel () {
        this (new XNode ("root"));
    }

    /**
     * Constructor.
     * TODO Pendiente de documentar
     * @param aRoot
     */
    public XTreeModel (TreeNode aRoot) {
        super (aRoot);
    }

    /**
     * Constructor.
     * TODO Pendiente de documentar
     * @param aRoot
     * @param aAsksAllowsChildren
     */
    public XTreeModel (TreeNode aRoot, boolean aAsksAllowsChildren) {
        super (aRoot, aAsksAllowsChildren);
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString () {
        // TODO Bloque generado automáticamente
        return super.toString ();
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
