package co.popapp;

import static co.popapp.swing.builders.ComponentBuilders.flatNoFocusButton;
import static co.popapp.swing.builders.ComponentBuilders.label;
import static co.popapp.swing.builders.ContainerBuilders.*;
import static co.popapp.swing.builders.MenuBuilders.*;
import static co.popapp.swing.builders.WindowBuilders.runSwingApplication;
import static co.popapp.swing.builders.WindowBuilders.showMainFrame;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import static java.lang.System.exit;
import static javax.swing.JSplitPane.LEFT;
import static javax.swing.JSplitPane.RIGHT;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class BuildersDemo {
    public static void main (String[] aArgs) throws Exception {
        runSwingApplication (InsubstantialColorSkin::setBusinessLaF, () -> {
            showMainFrame ("Test", frm -> {
                menuBar (frm, mbar -> {
                    menu (mbar, "File", mnu ->
                        menuItem (mnu, "Exit", mit ->
                            mit.addActionListener (aE -> exit (0))
                        )
                    );

                    menu (mbar, "Help", mnu ->
                        menuItem (mnu, "About", mit -> {
                        })
                    );
                });

                flatFixedToolBar (frm, NORTH, tbar -> {
                    flatNoFocusButton (tbar, "Exit", btn -> {
                        btn.addActionListener (aE -> exit (0));
                    });
                });

                splitPane (frm, splitp -> {
                    scrollTree (splitp, LEFT, tree ->
                        tree.setModel (
                            new DefaultTreeModel (new DefaultMutableTreeNode ("ROOT")))
                    );

                    tabbedPane (splitp, RIGHT, tabs -> {
                        panel (tabs, "Information", p -> {
                        });
                        panel (tabs, "Record Order", p -> {
                        });
                    });
                });

                label (frm, SOUTH, lbl -> lbl.setText ("Status"));
            });
        });
    }
}
