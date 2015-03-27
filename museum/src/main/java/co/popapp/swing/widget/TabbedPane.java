// P A C K A G E ///////////////////////////////////////////////////////////////
package co.popapp.swing.widget;

// I M P O R T /////////////////////////////////////////////////////////////////
import java.awt.event.ActionEvent;
import java.util.Hashtable;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

// C L A S S ///////////////////////////////////////////////////////////////////
/**
 * .
 * TODO Pendiente de documentar
 * @author jamming
 */
public class TabbedPane extends JTabbedPane {
    /**
     * .
     * TODO Pendiente de documentar
     * @author jamming
     */
    class ChangeTabbedPaneAction extends AbstractAction {
        /**
         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
         */
        @Override
        public void actionPerformed (ActionEvent event) {
            String mnemonic = event.getActionCommand ();
            int tabIndex = ((Integer)tabMnemonics.get (mnemonic.toUpperCase ())).intValue ();
            ((TabbedPane)event.getSource ()).setSelectedIndex (tabIndex);
        }
    }
    /**
     * .
     * TODO Pendiente de documentar
     * @param args
     */
    public static void main (String [] args) {
        JFrame frame = new JFrame ("");
        TabbedPane tabbedPane = new TabbedPane ();
        tabbedPane.add ("A", new JLabel ("Pulsa Alt + Vocal"));
        tabbedPane.add ("E", new JLabel ("No quiero sus pestañas!!!"));
        tabbedPane.add ("I", new JLabel ("A la mierda!!!"));
        tabbedPane.add ("O", new JLabel ("A Carrasco le gustan \"Los Chungitos\""));
        tabbedPane.add ("U", new JLabel ("Y a Pitican le gusta lamer culos"));
        tabbedPane.setMnemonic (0, "A");
        tabbedPane.setMnemonic (1, "E");
        tabbedPane.setMnemonic (2, "I");
        tabbedPane.setMnemonic (3, "O");
        tabbedPane.setMnemonic (4, "U");
        frame.getContentPane ().add (tabbedPane);
        frame.pack ();
        frame.setVisible (true);
    }
    /** TODO Pendiente de documentar. */
    InputMap tabbedPaneInputMap = null;

    /** TODO Pendiente de documentar. */
    ActionMap tabbedPaneActionMap = null;

    // NOTA: usar array de caracteres en vez de Hashtable
    /** TODO Pendiente de documentar. */
    Hashtable tabMnemonics = new Hashtable ();

    /**
     * Constructor.
     * TODO Pendiente de documentar
     */
    public TabbedPane () {
        super ();
        tabbedPaneInputMap = SwingUtilities.getUIInputMap (this, JComponent.WHEN_IN_FOCUSED_WINDOW);
        if (tabbedPaneInputMap == null) {
            tabbedPaneInputMap = new InputMap ();
            SwingUtilities.replaceUIInputMap (this, JComponent.WHEN_FOCUSED, tabbedPaneInputMap);
        }
        tabbedPaneActionMap = SwingUtilities.getUIActionMap (this);
        if (tabbedPaneActionMap == null) {
            tabbedPaneActionMap = new ActionMap ();
        }
        tabbedPaneActionMap.put ("changeTabbedPane", new ChangeTabbedPaneAction ());
        SwingUtilities.replaceUIActionMap (this, tabbedPaneActionMap);
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @param tabIndex
     * @param tabMnemonic
     */
    public void setMnemonic (int tabIndex, String tabMnemonic) {
        // PENDIENTE: pasar a mayúsculas tabMnemonic (no funciona si se le pasa
        // una minúscula)
        // PENDIENTE: controlar que el indice exista
        // PENDIENTE: controlar que no se repiten teclas aceleradoras
        tabbedPaneInputMap.put (KeyStroke.getKeyStroke (tabMnemonic.charAt (0), ActionEvent.ALT_MASK, false), "changeTabbedPane");
        tabMnemonics.put (tabMnemonic, new Integer (tabIndex));
    }
}
