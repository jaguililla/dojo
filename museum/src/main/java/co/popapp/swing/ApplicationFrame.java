// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.swing;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;

import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.*;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * TODO Support adding actions
 * @author jamming
 */
public class ApplicationFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private JLabel mStatusBar = new JLabel("Status");
    private JMenuBar mMenuBar = new JMenuBar ();
    private JToolBar mToolBar = new JToolBar ();

    /**
     * Create the frame.
     * TODO Change close operation for a boolean confirm exit, allow to pass a close callback
     *
     * @param aTitle .
     * @param aCloseOperation .
     */
    public ApplicationFrame (String aTitle, int aCloseOperation) {
        super (aTitle);
        setDefaultCloseOperation (aCloseOperation);
        setup();
    }

    /**
     * Create the frame.
     * @param aTitle .
     * @param aIcon .
     * @param aCloseOperation .
     */
    public ApplicationFrame (String aTitle, Image aIcon, int aCloseOperation) {
        this (aTitle, aCloseOperation);
        setIconImage (aIcon);
    }

    /**
     * TODO Position, size and state (maximized, minimized).
     * @param aState .
     */
    public void open (int aState) {
        pack ();
        setExtendedState (aState);
        setVisible (true);
    }

    /**
     * TODO Position, size and state (maximized, minimized).
     */
    public void open () {
        pack ();
        setLocation (5, 5); // Upper left corner to avoid overlapping with the app
        setVisible (true);
    }

    /**
     * TODO Position, size and state (maximized, minimized).
     * @param aWidth .
     * @param aHeight .
     */
    public void open (int aWidth, int aHeight) {
        setSize(aWidth, aHeight);
        setLocation (5, 5); // Upper left corner to avoid overlapping with the app
        setVisible (true);
    }

    /**
     * Adds a list of components to this application frame.
     *
     * @param aComponents The list of components to be added. If 'null', no component will be added.
     */
    public void add (List<? extends JComponent> aComponents) {
        if (aComponents == null) {
            return;
        }

        for (JComponent c : aComponents) {
            getContentPane ().add (c);
        }
    }

    public ApplicationFrame setup () {
        final ExitAction exitAction = new ExitAction (this);

        JMenu mnuFile = new JMenu ("File");
        mnuFile.add (new JMenuItem (exitAction));

        mMenuBar.setBorderPainted (false);
        mMenuBar.add (mnuFile);

        mToolBar.setBorderPainted (false);
        mToolBar.setRollover (true);
        mToolBar.setFloatable (false);
        JButton btnExit = new JButton (exitAction);
        btnExit.setRolloverEnabled (true);
        btnExit.setFocusable (false);
        mToolBar.add (btnExit);

        setJMenuBar (mMenuBar);
        add (mToolBar, NORTH);
        add (mStatusBar, SOUTH);
        addWindowListener (new WindowAdapter () {
            @Override public void windowClosing (WindowEvent e) {
                exitAction.actionPerformed (null); // The event isn't used in the action
            }
        });

        // Allows to ask prior to shutdown
        setDefaultCloseOperation (DO_NOTHING_ON_CLOSE);
        return this;
    }

    /**
     * @return The statusBar actual value.
     */
    public JLabel getStatusBar() {
        return mStatusBar;
    }

    /**
     * @return The toolBar actual value.
     */
    public JToolBar getToolBar() {
        return mToolBar;
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
