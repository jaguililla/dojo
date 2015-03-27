
// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.swing;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

// T Y P E S ///////////////////////////////////////////////////////////////////////////////////////
public class ComponentUtils {
    public static class AncestorAdapter implements AncestorListener {
        @Override public void ancestorAdded(AncestorEvent aEvent) {}
        @Override public void ancestorRemoved(AncestorEvent aEvent) {}
        @Override public void ancestorMoved(AncestorEvent aEvent) {}
    }

    public static void setMargin (
        JComponent aComponent, int aTop, int aRight, int aBottom, int aLeft) {

        Border border = aComponent.getBorder();

        Border marginBorder = new EmptyBorder(new Insets(aTop, aLeft, aBottom, aRight));
        aComponent.setBorder (border == null?
            marginBorder :
            new CompoundBorder(marginBorder, border));
    }

    public static void setShrinkable (
        final JComponent aComponent, final boolean aHorizontal, final boolean aVertical) {

        aComponent.addAncestorListener(new AncestorAdapter () {
            @Override public void ancestorAdded(AncestorEvent aEvent) {

                Dimension preferred = aComponent.getPreferredSize();
                aComponent.setMinimumSize(new Dimension(
                    aHorizontal? 0 : preferred.width,
                    aVertical? 0 : preferred.height));

                aComponent.removeAncestorListener(this);
            }
        });
    }

    public static void setExpandable (
        final JComponent aComponent, final boolean aHorizontal, final boolean aVertical) {

        aComponent.addAncestorListener(new AncestorAdapter () {
            @Override public void ancestorAdded(AncestorEvent aEvent) {

                Dimension preferred = aComponent.getPreferredSize();
                aComponent.setMaximumSize(new Dimension(
                    aHorizontal? Integer.MAX_VALUE : preferred.width,
                    aVertical? Integer.MAX_VALUE : preferred.height));

                aComponent.removeAncestorListener(this);
            }
        });
    }

    private ComponentUtils() {
        throw new IllegalArgumentException("This class is not meant to be instantiated");
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
