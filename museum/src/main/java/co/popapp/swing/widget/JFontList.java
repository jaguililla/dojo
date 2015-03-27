// P A C K A G E ///////////////////////////////////////////////////////////////
package co.popapp.swing.widget;

// I M P O R T /////////////////////////////////////////////////////////////////
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

// C L A S S ///////////////////////////////////////////////////////////////////
/**
 * Description of the Class
 *
 * @author jamming
 * @created   13 de mayo de 2001
 */
public class JFontList extends JList
{

    /**
     * Description of the Class
     *
     * @author jamming
     * @created   13 de mayo de 2001
     */
    class FontListRenderer extends JLabel implements ListCellRenderer
    {
        protected Border noFocusBorder;

        public FontListRenderer()
        {
            super();
            setOpaque(true);
            noFocusBorder = new EmptyBorder (1, 1, 1, 1);
        }

        /**
         * Gets the ListCellRendererComponent attribute of the
         * FontComboBoxRenderer object
         *
         * @param list          Description of Parameter
         * @param value         Description of Parameter
         * @param index         Description of Parameter
         * @param isSelected    Description of Parameter
         * @param cellHasFocus  Description of Parameter
         * @return              The ListCellRendererComponent value
         * @since
         */
        @Override public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            Font popupFont = list.getFont();
            setBorder((cellHasFocus) ? UIManager.getBorder("List.focusCellHighlightBorder"): noFocusBorder);
            setEnabled(list.isEnabled());
            setComponentOrientation(list.getComponentOrientation());
            setFont(new Font((String)value, popupFont.getStyle(), popupFont.getSize()));
            setText((String)value);
            if (isSelected)
            {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            }
            else
            {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            return this;
        }
    }

    public static void main (String [] aArgs) {
        JFrame f = new JFrame ("Test");
        f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        f.getRootPane ().setLayout (new BorderLayout ());
        f.getRootPane ().add (new JFontList (), BorderLayout.CENTER);
        f.pack ();
        f.setVisible (true);
    }

    /**
     * Constructor for the FontComboBox object
     *
     * @since
     */
    public JFontList ()
    {
        super(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setCellRenderer(new FontListRenderer());
    }
}
// E O F ///////////////////////////////////////////////////////////////////////

