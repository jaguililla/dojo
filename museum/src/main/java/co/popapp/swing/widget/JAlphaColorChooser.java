package co.popapp.swing.widget;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.colorchooser.ColorSelectionModel;

/**
 * Description of the Class
 *
 * @author jamming
 * @created   8 de mayo de 2001
 */
class JAlphaColorChooser extends JColorChooser implements ActionListener
{
    /**
     *
     */
    public JAlphaColorChooser()
    {
        super();

        // @todo Auto-generated constructor stub
    }

    /**
     * @param initialColor
     */
    public JAlphaColorChooser(Color initialColor)
    {
        super(initialColor);
        // @todo Auto-generated constructor stub
    }

    /**
     * @param model
     */
    public JAlphaColorChooser(ColorSelectionModel model)
    {
        super(model);
        // @todo Auto-generated constructor stub
    }

    /**
     * Description of the Method
     *
     * @param event  Description of Parameter
     * @since
     */
    @Override public void actionPerformed(ActionEvent event)
    {
        System.exit(0);
    }

    /**
     * The main program for the JAlphaColorChooser class
     *
     * @param args  The command line arguments
     * @since
     */
    public static void main(String[] args)
    {
        JAlphaColorChooser listener = new JAlphaColorChooser();
        JColorChooser chooser = new JColorChooser();
        JFrame frame = new JFrame("Color Chooser");
        frame.show();
        /*
        AbstractColorChooserPanel[] thePanels
                 = ColorChooserComponentFactory.getDefaultChooserPanels();
        AbstractColorChooserPanel[] thePanels1 = new AbstractColorChooserPanel[2];
        System.arraycopy(thePanels, 1, thePanels1, 0, 2);
        chooser.setChooserPanels(thePanels1);
        chooser.setPreviewPanel(new JPanel());
        */
        JDialog colorChooser = JColorChooser.createDialog(frame, "Color Chooser", false, chooser
                , listener, listener);
        AbstractColorChooserPanel[] panels = chooser.getChooserPanels();
        for (int ii = 0; ii < panels.length; ii++)
        {
            System.out.println(panels[ii].getClass().getName());
            System.out.println(panels[ii].getDisplayName());
        }
        colorChooser.show();
    }
}

