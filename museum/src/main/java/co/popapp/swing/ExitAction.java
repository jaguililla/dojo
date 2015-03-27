// P A C K A G E //////////////////////////////////////////////////////////////////////////////
package co.popapp.swing;

// I M P O R T ////////////////////////////////////////////////////////////////////////////////
import static java.awt.event.InputEvent.CTRL_MASK;
import static java.awt.event.KeyEvent.VK_Q;
import static java.awt.event.KeyEvent.VK_X;
import static java.lang.System.exit;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.KeyStroke.getKeyStroke;

import java.awt.event.ActionEvent;
import javax.swing.*;

public class ExitAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    private final JFrame mFrame;

    public ExitAction (JFrame aFrame) {
        if (aFrame == null)
            throw new IllegalArgumentException ("Exit action needs a non null frame");

        mFrame = aFrame;
        setKeys (VK_X, getKeyStroke (VK_Q, CTRL_MASK));
        setTexts ("Exit \u2026", "Exit the application");
    }

    @Override public void actionPerformed (ActionEvent aE) {
        int dialogAnswer = showConfirmDialog (
            mFrame, "Are you sure you want to exit?", "Exit application", YES_NO_OPTION);

        if (dialogAnswer == JOptionPane.YES_OPTION) {
            mFrame.dispose ();
            exit (0);
        }
    }
}
// E O F //////////////////////////////////////////////////////////////////////////////////////
