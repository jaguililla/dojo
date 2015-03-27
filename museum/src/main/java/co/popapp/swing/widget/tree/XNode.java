/*
 * XNode.java
 * Creado en: 18/09/2007 16:53:41 por: juanjose.aguililla
 */

// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.swing.widget.tree;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////

import java.awt.datatransfer.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;

import co.popapp.Validate;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * .
 * TODO Pendiente de documentar
 * @author jamming
 */
public class XNode extends DefaultMutableTreeNode implements Comparable, Transferable, ClipboardOwner {
    /**
     * Constructor.
     * TODO Pendiente de documentar
     */
    public XNode () {
        super ();
    }

    /**
     * Constructor.
     * TODO Pendiente de documentar
     * @param aUserObject
     */
    public XNode (Object aUserObject) {
        super (aUserObject);
    }

    /**
     * Constructor.
     * TODO Pendiente de documentar
     * @param aUserObject
     * @param aAllowsChildren
     */
    public XNode (Object aUserObject, boolean aAllowsChildren) {
        super (aUserObject, aAllowsChildren);
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @param aNode
     * @param aRecursive
     * @return XNode
     */
    private XNode copy (XNode aNode, boolean aRecursive) {
        XNode result = (XNode)aNode.clone ();

        if (aRecursive) {
            Enumeration nodes = aNode.children ();
            while (nodes.hasMoreElements ()) {
                XNode currentNode = (XNode)nodes.nextElement ();
                result.add (copy (currentNode, aRecursive));
            }
        }

        return result;
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @param aDestination
     * @return boolean
     */
    public boolean canMoveTo (XNode aDestination) {
        Validate.notNull (aDestination, "El nodo al que mover no puede ser 'null'");
        return !isRoot () && !isNodeDescendant (aDestination);
    }

    /**
     * @see Comparable#compareTo(Object)
     */
    @Override
    public int compareTo (Object aO) {
        Object user = getUserObject ();

        if (user == this) {
            throw new IllegalStateException ("compareTo recursivo !!!");
        }
        else if (user instanceof Comparable) {
            Comparable comparableUser = (Comparable)user;
            return comparableUser.compareTo (aO);
        }
        return 0;
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @param aRecursive
     * @return XNode
     */
    public XNode copy (boolean aRecursive) {
        return copy (this, aRecursive);
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @param aDestination
     * @param aRecursive
     * @return XNode
     */
    public XNode copyTo (XNode aDestination, boolean aRecursive) {
        Validate.notNull (aDestination, "El nodo al que copiar no puede ser 'null'");

        XNode newNode = copy (this, aRecursive);
        aDestination.add (newNode);
        return newNode;
    }

    /**
     * @see java.awt.datatransfer.Transferable#getTransferData(java.awt.datatransfer.DataFlavor)
     */
    @Override
    public Object getTransferData (DataFlavor aFlavor) throws UnsupportedFlavorException, IOException {
        // TODO Devolver objeto en función del data flavor
        return getUserObject ().toString ();
    }

    /**
     * @see java.awt.datatransfer.Transferable#getTransferDataFlavors()
     */
    @Override
    public DataFlavor [] getTransferDataFlavors () {
        return new DataFlavor [] {
            DataFlavor.stringFlavor,
            new DataFlavor (this.getClass(), "") // Clase Java
        };
    }

    /**
     * @see java.awt.datatransfer.Transferable#isDataFlavorSupported(java.awt.datatransfer.DataFlavor)
     */
    @Override
    public boolean isDataFlavorSupported (DataFlavor aFlavor) {
        getTransferDataFlavors ();
        return true;
    }

    /**
     * @see java.awt.datatransfer.ClipboardOwner#lostOwnership(java.awt.datatransfer.Clipboard, java.awt.datatransfer.Transferable)
     */
    @Override
    public void lostOwnership (Clipboard aClipboard, Transferable aContents) {
        // No implementado
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @param aDestination
     */
    public void moveTo (XNode aDestination) {
        Validate.isTrue (canMoveTo (aDestination), "No se puede mover al nodo seleccionado");
        removeFromParent ();
        aDestination.add (this);
    }

    /**
     * .
     * TODO Pendiente de documentar
     */
    public void sort () {
        List orderedNodes = new ArrayList ();
        Enumeration nodes = children ();

        while (nodes.hasMoreElements ()) {
            XNode currentNode = (XNode)nodes.nextElement ();
            currentNode.removeFromParent ();
            orderedNodes.add (currentNode);
        }

        Collections.sort (orderedNodes);

        for (int ii = 0; ii < orderedNodes.size (); ii++) {
            add ((XNode)orderedNodes.get (ii));
        }
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @return String
     */
    public String tooltip () {
        return "";
    }

    /**
     * @see javax.swing.tree.DefaultMutableTreeNode#toString()
     */
    @Override
    public String toString () {
        return super.toString ();
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
