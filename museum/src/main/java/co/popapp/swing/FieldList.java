// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.swing;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import static javax.swing.BoxLayout.PAGE_AXIS;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;

import co.popapp.swing.ComponentUtils.AncestorAdapter;


// T Y P E S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 *
 * @author jamming
 */
public class FieldList extends JScrollPane {
    private static final long serialVersionUID = 1L;

    private final JPanel pnl = (JPanel)getViewport().getView();

    public FieldList() {
        super (new JPanel ());
        EmptyBorder border = new EmptyBorder(0, 0, 0, 0);
        setBorder(border);
        pnl.setBorder(border);
        pnl.setLayout (new BoxLayout (pnl, PAGE_AXIS));
    }

    public void add (final Field<?> aField) {
        pnl.add (aField);

        aField.addAncestorListener(new AncestorAdapter () {
            @Override public void ancestorAdded(AncestorEvent aEvent) {
                // Get widest lblStatus
                int w = 0;
                for (Component c : pnl.getComponents()) {
                    if (c instanceof Field) {
                        Field<?> f = (Field<?>)c;
                        JLabel lbl = f.getLabel();
                        if (lbl.getWidth() > w) {
                            w = lbl.getWidth ();
                        }
                    }
                }

                // Set same width
                for (Component c : pnl.getComponents()) {
                    if (c instanceof Field) {
                        Field<?> f = (Field<?>)c;
                        JLabel lbl = f.getLabel();
                        Dimension d =
                            new Dimension (w, f.getLabel().getHeight());
                        lbl.setMinimumSize(d);
                        lbl.setMaximumSize(d);
                        lbl.setPreferredSize(d);
                        lbl.setSize(w, f.getLabel().getHeight());
                    }
                }
                aField.removeAncestorListener(this);
            }
        });
    }

    /**
     * TODO .
     *
     * @param args .
     * @throws Exception TODO .
     */
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel ("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

        JFrame frm = new JFrame ("FieldList");

        FieldList pnl = new FieldList ();

        pnl.add(new Field<> (true, "RO Bool (True)", Boolean.TRUE));
        pnl.add(new Field<> (true, "RO Bool (False)", Boolean.FALSE));
        pnl.add(new Field<> (false, "Bool (True)", Boolean.TRUE));
        pnl.add(new Field<> (false, "Bool (False)", Boolean.FALSE));

        pnl.add(new Field<> (true, "RO Integer (0)", 0));
        pnl.add(new Field<> (true, "RO Integer (1)", 1));
        pnl.add(new Field<> (false, "Integer (0)", 0));
        pnl.add(new Field<> (false, "Integer (1)", 1));

        frm.add (pnl);
        frm.setDefaultCloseOperation (EXIT_ON_CLOSE);
        frm.pack ();
        frm.setVisible (true);
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
