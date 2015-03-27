// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.swing;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
public class BoxPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public static BoxPanel horizontalBoxPanel (int aPadding, Component... aComponents) {
        return new BoxPanel (BoxLayout.LINE_AXIS, aPadding, aComponents);
    }

    public static BoxPanel verticalBoxPanel (int aPadding, Component... aComponents) {
        return new BoxPanel (BoxLayout.PAGE_AXIS, aPadding, aComponents);
    }

    public static BoxPanel horizontalBoxPanel (Component... aComponents) {
        return new BoxPanel (BoxLayout.LINE_AXIS, aComponents);
    }

    public static BoxPanel verticalBoxPanel (Component... aComponents) {
        return new BoxPanel (BoxLayout.PAGE_AXIS, aComponents);
    }

    public BoxPanel (int aAxis, Component... aComponents) {
        setLayout (new BoxLayout (this, aAxis));

        for (Component c : aComponents) {
            add (c);
        }
    }

    public BoxPanel (int aAxis, int aPadding, Component... aComponents) {
        this (aAxis, aComponents);
        setBorder (new EmptyBorder (aPadding, aPadding, aPadding, aPadding));
    }

    @Override public BoxPanel add (Component aComponent) {
        super.add (aComponent);
        return this;
    }

    public BoxPanel addAlignedX (JComponent aComponent, float aAlignmentX) {
        aComponent.setAlignmentX (aAlignmentX);
        return add (aComponent);
    }

    public BoxPanel addAlignedRight (JComponent aComponent) {
        return addAlignedX (aComponent, RIGHT_ALIGNMENT);
    }

    public BoxPanel addHorizontalStrut (int aWidth) {
        return add (Box.createHorizontalStrut (aWidth));
    }

    public BoxPanel addGlue () {
        return add (Box.createGlue ());
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
