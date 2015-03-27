package co.popapp.swing.widget;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.colorchooser.ColorChooserComponentFactory;

class ColorChooserDemo {
    public static void main (String[] args) {
        ActionListener listener = new ActionListener () {
            @Override public void actionPerformed (ActionEvent event) { System.exit (0); }
        };

        JColorChooser chooser = new JColorChooser ();
        AbstractColorChooserPanel[] thePanels
        = ColorChooserComponentFactory.getDefaultChooserPanels();
        AbstractColorChooserPanel[] thePanels1 = new AbstractColorChooserPanel[2];
        System.arraycopy(thePanels, 1, thePanels1, 0, 2);
        chooser.setChooserPanels(thePanels1);
        chooser.setPreviewPanel(new JPanel());
        JDialog colorChooser =
            JColorChooser.createDialog (null, "Color Chooser", false, chooser, listener, listener);

        for (AbstractColorChooserPanel cp : chooser.getChooserPanels ()) {
            System.out.println (cp.getClass ().getName ());
            System.out.println (cp.getDisplayName ());
        }

        colorChooser.setVisible (true);
    }
}
