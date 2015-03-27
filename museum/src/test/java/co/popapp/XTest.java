/*
 * XTest.java
 * Creado en: 18/09/2007 17:56:23 por: juanjose.aguililla
 */

// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import co.popapp.swing.widget.tree.XNode;
import co.popapp.swing.widget.tree.XTree;
import co.popapp.swing.widget.tree.XTreeModel;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * .
 * TODO Pendiente de documentar
 * @author jamming
 */
public class XTest {
    /** TODO Pendiente de documentar */
    private static final int LAF = 9;

    /**
     * Ventana.
     * TODO Pendiente de documentar
     */
    private static void initForm () {
        Toolkit toolkit = Toolkit.getDefaultToolkit ();
        Dimension screenSize = toolkit.getScreenSize ();
        double screenWidth = screenSize.getWidth ();
        double screenHeight = screenSize.getHeight ();

        XTree tree = new XTree ();
        JScrollPane pane = new JScrollPane (tree);
        populateTree (tree);

        JButton btn1 = new JButton ("b1");
        btn1.setMargin (new Insets (5, 10, 5, 10));
        JButton btn2 = new JButton ("b2");
        btn2.setMargin (new Insets (5, 10, 5, 10));

        JToolBar toolbar = new JToolBar ();
        toolbar.setMargin (new Insets (5, 10, 5, 10));
        toolbar.setFloatable (false);
        toolbar.setRollover (true);
        toolbar.add (btn1);
        toolbar.add (btn2);

        JFrame form = new JFrame ("XTest");
        Container contentPane = form.getContentPane ();
        contentPane.setLayout (new BorderLayout ());
        contentPane.add (toolbar, BorderLayout.NORTH);
        contentPane.add (pane);
        form.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        form.pack ();
        form.setSize (500, 400);
        form.setLocation (
            (int)(screenWidth - form.getWidth ()) / 2,
            (int)(screenHeight - form.getHeight ()) / 2);
        form.setVisible (true);
    }

    /**
     * L&F.
     * TODO Pendiente de documentar
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws javax.swing.UnsupportedLookAndFeelException
     */
    private static void initLookAndFeel () throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        switch (LAF) {
            case 0:
                UIManager.setLookAndFeel("com.jgoodies.looks.windows.WindowsLookAndFeel");
                break;
            case 1:
                UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticXPLookAndFeel");
                break;
            default:
                UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName ());
        }
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @param aTree
     */
    private static void populateTree (XTree aTree) {
        XTreeModel model = (XTreeModel)aTree.getModel ();
        XNode r = (XNode)model.getRoot ();

        r.setUserObject ("R");
        XNode n1 = new XNode ("N 1");
        XNode n2 = new XNode ("N 2");
        XNode n3 = new XNode ("N 3");
        r.add (n1);
        r.add (n2);
        r.add (n3);

        XNode n11 = new XNode ("N 1.1");
        XNode n12 = new XNode ("N 1.2");
        XNode n13 = new XNode ("N 1.3");
        n1.add (n11);
        n1.add (n12);
        n1.add (n13);

        XNode n21 = new XNode ("N 2.1");
        XNode n22 = new XNode ("N 2.2");
        XNode n23 = new XNode ("N 2.3");
        n2.add (n21);
        n2.add (n22);
        n2.add (n23);

        XNode n31 = new XNode ("N 3.1");
        XNode n32 = new XNode ("N 3.2");
        XNode n33 = new XNode ("N 3.3");
        n3.add (n31);
        n3.add (n32);
        n3.add (n33);

        model.nodeStructureChanged (r);
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @param args
     */
    public static void main (String [] args) {
        try {
            initLookAndFeel ();
            initForm ();
        }
        catch (Throwable t) {
            t.printStackTrace();
            System.exit (-1);
        }
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
