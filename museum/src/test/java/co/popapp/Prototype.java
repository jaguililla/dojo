// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import static co.popapp.swing.builders.ComponentBuilders.*;
import static co.popapp.swing.builders.ContainerBuilders.toolBar;
import static co.popapp.swing.builders.MenuBuilders.menu;
import static co.popapp.swing.builders.MenuBuilders.menuBar;
import static co.popapp.swing.builders.WindowBuilders.frame;
import static java.awt.BorderLayout.*;
import static java.awt.EventQueue.invokeLater;
import static java.lang.System.out;
import static java.sql.DriverManager.getConnection;
import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.RowSet;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.menu.WebMenuBarUI;
import com.alee.laf.toolbar.WebToolBarUI;
import com.sun.rowset.JdbcRowSetImpl;

/**
 * Test bench for UI Changes.
 *
 * @author jamming
 */
public class Prototype {

    @FunctionalInterface interface ExceptionConsumer<T, E extends Exception> {
        void accept (T aT) throws E;
    }

    @FunctionalInterface interface JdbcConsumer
        extends ExceptionConsumer<ResultSet, SQLException> {}

    @FunctionalInterface interface RowSetConsumer
        extends ExceptionConsumer<RowSet, SQLException> {}

    @FunctionalInterface interface ConnConsumer
        extends ExceptionConsumer<Connection, SQLException> {}

    static void with (
        String aUrl, String aUser, String aPass, ConnConsumer aLambda) throws SQLException {

        try (Connection con = getConnection (aUrl, aUser, aPass)) {
            aLambda.accept (con);
        }
    }

    static void select (
        Connection aCon, String aQuery, RowSetConsumer aLambda) throws SQLException {

        aLambda.accept (new JdbcRowSetImpl (aCon.createStatement ().executeQuery (aQuery)));
    }

    public static final String EOL = System.getProperty ("line.separator");

    public static String $ (String... aLines) {
        if (aLines == null || aLines.length == 0)
            return "";

        StringBuffer buffer = new StringBuffer (aLines [0]);
        for (int ii = 1; ii < aLines.length; ii++)
            buffer.append (EOL).append (aLines[ii]);

        return buffer.toString ();
    }

    /**
     * Application entry point.
     *
     * @param args Command line arguments (provided by the system).
     * @throws javax.swing.UnsupportedLookAndFeelException TODO .
     */
    public static void main(String[] args)
        throws UnsupportedLookAndFeelException, SQLException {

        // DB Access
        List<Integer> ids = new ArrayList<> ();
        with ("jdbc:oracle:thin:@10.2.72.101:1521:STDBDMS", "LOADOWNER", "LOADOWNER", c ->
            select (c, "select * from usr_user", rs -> {
                while (rs.next ()) {
                    final int anInt = rs.getInt (1);
                    ids.add (anInt);
                    out.println (anInt);
                }
            })
        );

        // Start main frame
        invokeLater (() -> {
            WebLookAndFeel.install ();
            WebLookAndFeel.setDecorateAllWindows (true);

            // Main frame
            frame (frmMain -> {
                frmMain.setTitle ("test");
                frmMain.setDefaultCloseOperation (EXIT_ON_CLOSE);

                frmMain.setJMenuBar (menuBar (mbarApp -> {
                    ((WebMenuBarUI)mbarApp.getUI ()).setUndecorated (true);

                    mbarApp.setBorderPainted (false);
                    mbarApp.add (menu (mnuFile -> {
                        mnuFile.setText ("File");
                        mnuFile.add (new JMenuItem ("Exit"));
                    }));
                }));

                frmMain.add (NORTH, toolBar (tbar -> {
                    ((WebToolBarUI)tbar.getUI ()).setUndecorated (true);

                    tbar.setBorderPainted (false);
                    tbar.setRollover (true);
                    tbar.setFloatable (false);
                    tbar.add (button (bExit -> {
                        bExit.setText ("Exit");
                        bExit.setRolloverEnabled (true);
                        bExit.setFocusable (false);
                        bExit.addActionListener (aE -> showMessageDialog (frmMain, "Hi"));
                    }));
                }));

                frmMain.add (SOUTH, label (lbl -> {
                    lbl.setText ("Status Bar");
                    lbl.setBorder (new EmptyBorder (new Insets (2, 2, 2, 2)));
                }));

                frmMain.add (CENTER, new TextEditor ());

                frmMain.setSize (500, 200);
                frmMain.setVisible (true);
            });
        });
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
