/*
 * POPAPP Copyright © 2011 Juan José Aguililla. All rights reserved.
 *
 * This program comes WITHOUT ANY WARRANTY; It is free software, you can redistribute it and/or
 * modify it under the terms of the GNU General Public License, Version 3.0.
 * You may obtain a copy of the License at: http://www.gnu.org/licenses/gpl.html
 */

// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import static javax.swing.colorchooser.ColorChooserComponentFactory.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.colorchooser.AbstractColorChooserPanel;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
class ColorChooserDemo {
    public static void main(String[] args) {
        ActionListener listener = new ActionListener() {
            @Override public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        };

        JColorChooser chooser = new JColorChooser();
        AbstractColorChooserPanel[] thePanels = getDefaultChooserPanels();
        AbstractColorChooserPanel[] thePanels1 = new AbstractColorChooserPanel[2];
        System.arraycopy(thePanels, 1, thePanels1, 0, 2);
        chooser.setChooserPanels(thePanels1);
        chooser.setPreviewPanel(new JPanel());
        JDialog colorChooser =
            JColorChooser.createDialog(null, "Color Chooser", false, chooser, listener, listener);

        for (AbstractColorChooserPanel cp : chooser.getChooserPanels()) {
            System.out.println(cp.getClass().getName());
            System.out.println(cp.getDisplayName());
        }

        colorChooser.setVisible(true);
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
