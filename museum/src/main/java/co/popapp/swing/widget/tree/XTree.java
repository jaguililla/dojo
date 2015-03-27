/*
 * XTree.java
 * Creado en: 18/09/2007 16:37:17 por: juanjose.aguililla
 */

// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.swing.widget.tree;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.SystemFlavorMap;
import java.awt.dnd.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.util.TooManyListenersException;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.ToolTipManager;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * .
 * TODO Pendiente de documentar
 * @author jamming
 */
public class XTree extends JTree {
    /** TODO Pendiente de documentar */
    private DropTarget mTarget = new DropTarget ();
    /** TODO Pendiente de documentar */
    private DragSource mSource = DragSource.getDefaultDragSource ();
    /** TODO Pendiente de documentar */
    private TreeModel mModel = new XTreeModel ();

    /** TODO Pendiente de documentar */
    private JPopupMenu mPopup = new JPopupMenu ();

    private Action mCutAction = new AbstractAction () {
        @Override
        public void actionPerformed (ActionEvent aArg0) {
            clipboardCopy ();
        }
    };

    private Action mCopyAction = new AbstractAction () {
        @Override
        public void actionPerformed (ActionEvent aArg0) {
            clipboardCopy ();
        }
    };

    private Action mPasteAction = new AbstractAction () {
        @Override
        public void actionPerformed (ActionEvent aArg0) {
            clipboardCopy ();
        }
    };

    private Action mDeleteAction = new AbstractAction () {
        @Override
        public void actionPerformed (ActionEvent aArg0) {
            clipboardCopy ();
        }
    };

    /**
     * Constructor.
     * TODO Pendiente de documentar
     */
    public XTree () {
        super ();
        try {
            init ();
        }
        catch (TooManyListenersException e) {
            throw new IllegalStateException ("Error al inicializar el árbol", e);
        }
    }

    /**
     * Constructor.
     * TODO Pendiente de documentar
     * @param aValue
     */
    public XTree (Hashtable aValue) {
        super (aValue);
        try {
            init ();
        }
        catch (TooManyListenersException e) {
            throw new IllegalStateException ("Error al inicializar el árbol", e);
        }
    }

    /**
     * Constructor.
     * TODO Pendiente de documentar
     * @param aValue
     */
    public XTree (Object [] aValue) {
        super (aValue);
        try {
            init ();
        }
        catch (TooManyListenersException e) {
            throw new IllegalStateException ("Error al inicializar el árbol", e);
        }
    }

    /**
     * Constructor.
     * TODO Pendiente de documentar
     * @param aNewModel
     */
    public XTree (TreeModel aNewModel) {
        super (aNewModel);
        try {
            init ();
        }
        catch (TooManyListenersException e) {
            throw new IllegalStateException ("Error al inicializar el árbol", e);
        }
    }

    /**
     * Constructor.
     * TODO Pendiente de documentar
     * @param aRoot
     */
    public XTree (TreeNode aRoot) {
        super (aRoot);
        try {
            init ();
        }
        catch (TooManyListenersException e) {
            throw new IllegalStateException ("Error al inicializar el árbol", e);
        }
    }

    /**
     * Constructor.
     * TODO Pendiente de documentar
     * @param aRoot
     * @param aAsksAllowsChildren
     */
    public XTree (TreeNode aRoot, boolean aAsksAllowsChildren) {
        super (aRoot, aAsksAllowsChildren);
        try {
            init ();
        }
        catch (TooManyListenersException e) {
            throw new IllegalStateException ("Error al inicializar el árbol", e);
        }
    }

    /**
     * Constructor.
     * TODO Pendiente de documentar
     * @param aValue
     */
    public XTree (Vector aValue) {
        super (aValue);
        try {
            init ();
        }
        catch (TooManyListenersException e) {
            throw new IllegalStateException ("Error al inicializar el árbol", e);
        }
    }

    private void clipboardCopy () {
        XNode lastPathComponent = (XNode)getSelectionPath ().getLastPathComponent ();
        Toolkit.getDefaultToolkit ().getSystemClipboard ().setContents (lastPathComponent, lastPathComponent);
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @throws java.util.TooManyListenersException
     */
    private void init () throws TooManyListenersException {
        setModel (mModel);

        // Drag & Drop
        mSource.createDefaultDragGestureRecognizer (this, DnDConstants.ACTION_COPY_OR_MOVE, new DragGestureListener () {
            @Override
            public void dragGestureRecognized (DragGestureEvent aDge) {
                sourceDragCheck (aDge);
            }
        });
        mTarget.setDefaultActions (DnDConstants.ACTION_COPY_OR_MOVE);
        mTarget.setFlavorMap (SystemFlavorMap.getDefaultFlavorMap ());
        mTarget.addDropTargetListener (new DropTargetAdapter () {
            @Override
            public void dragOver (DropTargetDragEvent aDtde) {
                targetOver (aDtde);
            }

            @Override
            public void drop (DropTargetDropEvent aDtde) {
                targetDrop (aDtde);
            }
        });
        setDropTarget (mTarget);

        // Provisional
        JMenuItem menuItemCut = new JMenuItem ("Cortar");
        JMenuItem menuItemCopy = new JMenuItem ("Copiar");
        JMenuItem menuItemPaste = new JMenuItem ("Pegar");
        JMenuItem menuItemDelete = new JMenuItem ("Borrar");
        JMenuItem menuItemExpand = new JMenuItem ("Expandir");
        JMenuItem menuItemCollapse = new JMenuItem ("Comprimir");
        JMenuItem menuItemSort = new JMenuItem ("Ordenar");

        mPopup.add (menuItemCut);
        mPopup.add (menuItemCopy);
        mPopup.add (menuItemPaste);
        mPopup.addSeparator ();
        mPopup.add (menuItemDelete);
        mPopup.addSeparator ();
        mPopup.add (menuItemExpand);
        mPopup.add (menuItemCollapse);
        mPopup.add (menuItemSort);
        // -----------------

        // Menu contextual
        addMouseListener (new MouseAdapter () {
            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }
        });

        // Tooltip
        ToolTipManager tooltipManager = ToolTipManager.sharedInstance ();
        tooltipManager.setDismissDelay (5000);
        tooltipManager.registerComponent (this);

        // Teclas
        InputMap theInputMap = getInputMap();
        ActionMap theActionMap = getActionMap();

        theInputMap.put(KeyStroke.getKeyStroke("control x"), "cut");
        theInputMap.put(KeyStroke.getKeyStroke("control c"), "copy");
        theInputMap.put(KeyStroke.getKeyStroke("control v"), "paste");
        theInputMap.put(KeyStroke.getKeyStroke("DELETE"), "delete");
        theInputMap.put(KeyStroke.getKeyStroke("BACK_SPACE"), "delete");

        theActionMap.put("cut", mCutAction);
        theActionMap.put("copy", mCopyAction);
        theActionMap.put("paste", mPasteAction);
        theActionMap.put("delete", mDeleteAction);
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @param e
     */
    private void showPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            TreeSelectionModel selection = getSelectionModel ();
            TreePath path = getClosestPathForLocation (e.getX (), e.getY ());

            if (path != null) {
                if (!selection.isPathSelected (path)) {
                    selection.setSelectionPath (path);
                }
                mPopup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    /**
     * @param aDge
     */
    private void sourceDragCheck (DragGestureEvent aDge) {
        if (getSelectionCount () > 0) {
            TreePath [] selectionPaths = getSelectionModel ().getSelectionPaths ();
            XNode node = (XNode)selectionPaths[0].getLastPathComponent ();
            aDge.startDrag(null, node, new DragSourceAdapter () {
                @Override
                public void dragDropEnd (DragSourceDropEvent aDsde) {
                    sourceDrop (aDsde);
                }

                @Override
                public void dragOver (DragSourceDragEvent aDsde) {
                    sourceOver (aDsde);
                }
            });
        }
    }

    /**
     * @param aDsde
     */
    private void sourceDrop (DragSourceDropEvent aDsde) {
        //System.out.println ("Target Drop. Acción : " + aDsde);
    }

    /**
     * @param aDsde
     */
    private void sourceOver (DragSourceDragEvent aDsde) {
        //System.out.println ("Source Over. Acción : " + aDsde);
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @param aDtde
     */
    private void targetDrop (DropTargetDropEvent aDtde) {
        Point point = aDtde.getLocation ();
        TreePath pathForLocation = getPathForLocation (point.x, point.y);
        int dropAction = aDtde.getDropAction ();
        System.out.println ("Source Drop. Acción : " + aDtde);

        XNode target = (XNode)pathForLocation.getLastPathComponent ();
        TreePath[] sources = getSelectionModel ().getSelectionPaths ();
        XNode firstSource = (XNode)sources[0].getLastPathComponent ();
        XNode sourceParent = (XNode)firstSource.getParent ();

        if (dropAction == DnDConstants.ACTION_COPY) {
            for (int ii = 0; ii < sources.length; ii++) {
                XNode source = (XNode)sources[ii].getLastPathComponent ();
                source.copyTo (target, true);
            }
        }
        if (dropAction == DnDConstants.ACTION_MOVE) {
            for (int ii = 0; ii < sources.length; ii++) {
                XNode source = (XNode)sources[ii].getLastPathComponent ();
                source.moveTo (target);
            }
        }

        ((XTreeModel)getModel ()).nodeStructureChanged (target);
        ((XTreeModel)getModel ()).nodeStructureChanged (sourceParent);
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @param aDtde
     */
    private void targetOver (DropTargetDragEvent aDtde) {
        //System.out.println ("Target Over. Acción : " + aDtde);
    }

    /**
     * .
     * TODO Pendiente de documentar
     */
    public void collapseAll () {
        // TODO Pendiente
    }

    /**
     * .
     * TODO Pendiente de documentar
     */
    public void collapseSelected () {
        // TODO Pendiente
    }

    /**
     * .
     * TODO Pendiente de documentar
     */
    public void expandAll () {
        // TODO Pendiente
    }

    /**
     * .
     * TODO Pendiente de documentar
     */
    public void expandSelected () {
        // TODO Pendiente
    }

    /**
     * @see javax.swing.JTree#getToolTipText(java.awt.event.MouseEvent)
     */
    @Override
    public String getToolTipText (MouseEvent aEvent) {
        Point point = aEvent.getPoint ();
        TreePath pathForLocation = getPathForLocation (point.x, point.y);

        if (pathForLocation == null) {
            return super.getToolTipText (aEvent);
        }
        else {
            XNode node = (XNode)pathForLocation.getLastPathComponent ();
            return "Tooltip : " + node.tooltip ();
        }
    }

    /**
     * @see java.awt.Component#toString()
     */
    @Override
    public String toString () {
        return super.toString ();
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
