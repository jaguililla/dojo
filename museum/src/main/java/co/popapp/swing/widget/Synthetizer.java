// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.swing.widget;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import static java.awt.BorderLayout.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
class AppFrame extends JFrame {
    /** TODO . */
    private static final long serialVersionUID = -3251170969358255886L;

    { setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); }

    /**
     * TODO .
     */
    public void open () {
        pack ();
        setVisible (true);
    }
}

//@formatOff
@SuppressWarnings ("serial")
class MainFrame extends AppFrame {{
    final JFrame f = this;
    final String [] props = {
        "control", "info", "nimbusAlertYellow", "nimbusBase", "nimbusDisabledText", "nimbusFocus",
        "nimbusGreen", "nimbusInfoBlue", "nimbusLightBackground", "nimbusOrange", "nimbusRed",
        "nimbusSelectedText", "nimbusSelectionBackground", "text"
    };

    class ColorButton extends JButton {
        public ColorButton (String aText) {
            super (aText);
            setOpaque (true);
            setBackground (UIManager.getColor (aText));
            if (getBackground ().equals (Color.BLACK)) setForeground (Color.WHITE);
            addActionListener (new ActionListener() {
                @Override public void actionPerformed (ActionEvent aE) {
                    UIManager.put (getText(), JColorChooser.showDialog (f, getText(), getBackground ()));
                    setBackground (UIManager.getColor (getText()));
                    if (getBackground ().equals (Color.BLACK)) setForeground (Color.WHITE);
                    SwingUtilities.updateComponentTreeUI (f);
                }
            });
        }
    }

    final Action actExit = new AbstractAction("Exit") {
        @Override
        public void actionPerformed (ActionEvent aE) { f.dispose (); }
    };

    setTitle ("Synthetizer");
    setLayout (new BorderLayout ());
//    setUndecorated(true);
//    getRootPane().setWindowDecorationStyle(JRootPane.FRAME);

    setJMenuBar (new JMenuBar () {{
        add (new JMenu ("Menu") {{
            add (new JMenuItem (actExit));
        }});
    }});

    add (new JToolBar () {{
        setName ("tbrMain");
        setFloatable (false);
        add (new JButton (actExit) {{
            setBorderPainted (false);
            setFocusable (false);
        }});
    }}, NORTH);

    final JPanel pnlColors = new JPanel (new FlowLayout (FlowLayout.CENTER, 3, 3));
    for (String key : props)
        pnlColors.add (new ColorButton (key));

    add (new JPanel () {{
        setLayout (new BorderLayout ());
        final JScrollPane pnl = new JScrollPane (new JTree ());
        add (new JSplitPane (JSplitPane.HORIZONTAL_SPLIT, pnl, pnlColors), CENTER);
    }}, CENTER);

    add (new JLabel ("Status bar"), BorderLayout.SOUTH);
}}
//@formatOn

/**
 * TODO .
 * @author jamming
 */
public class Synthetizer {
    /**
     * TODO .
     * @throws Exception
     */
    private static void laf () throws Exception {
        try {
//            UIManager.put ("control", Color.WHITE);
//            UIManager.put ("info", Color.GRAY);
//            UIManager.put ("nimbusAlertYellow", Color.YELLOW);
//            UIManager.put ("nimbusBase", Color.GRAY);
//            UIManager.put ("nimbusDisabledText", Color.BLACK);
//            UIManager.put ("nimbusFocus", Color.WHITE);
//            UIManager.put ("nimbusGreen", Color.WHITE);
//            UIManager.put ("nimbusInfoBlue", Color.WHITE);
//            UIManager.put ("nimbusLightBackground", Color.WHITE);
//            UIManager.put ("nimbusOrange", Color.WHITE);
//            UIManager.put ("nimbusRed", Color.WHITE);
//            UIManager.put ("nimbusSelectedText", Color.BLACK);
//            UIManager.put ("nimbusSelectionBackground", Color.WHITE);
//            UIManager.put ("text", Color.WHITE);
            UIManager.setLookAndFeel ("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            JFrame.setDefaultLookAndFeelDecorated (true);
            System.out.println (">>> " + UIManager.getLookAndFeel ().getSupportsWindowDecorations ());
        }
        catch (Exception e) {
            try {
                UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName ());
            }
            catch (Exception e1) {
                UIManager.setLookAndFeel (UIManager.getCrossPlatformLookAndFeelClassName ());
            }
        }
    }

    /**
     * TODO .
     * @param args
     */
    public static void main (String[] args) {
        try {
            laf ();
            SwingUtilities.invokeAndWait (new Runnable () {
                @Override
                public void run () {
                    new MainFrame ().open ();
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace ();
        }
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
