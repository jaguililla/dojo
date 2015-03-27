/*
 * AlphaChooserPanel.java
 *
 * Created on 5 de septiembre de 2001, 13:49
 */

package co.popapp.swing.widget;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author jamming
 * @version
 */
public class AlphaChooserPanel extends AbstractColorChooserPanel implements ChangeListener
{
    protected JLabel lblAlphaLabel = null;
    protected JSlider sldAlphaSlider = null;
    protected JSpinner spnAlphaSpinner = null;

    public AlphaChooserPanel()
    {
        super();
    }

    @Override protected void buildChooser()
    {
        lblAlphaLabel = new JLabel ("Transparencia");
        sldAlphaSlider = new JSlider ();
    }

    @Override public String getDisplayName()
    {
        return "";
    }

    @Override public Icon getLargeDisplayIcon()
    {
        return null;
    }

    @Override public Icon getSmallDisplayIcon()
    {
        return null;
    }

    @Override public void updateChooser()
    {
    }

    /**
     * Invoked when the target of the listener has changed its state.
     *
     * @param e  a ChangeEvent object
     */
    @Override public void stateChanged(ChangeEvent e) {
    }

}
