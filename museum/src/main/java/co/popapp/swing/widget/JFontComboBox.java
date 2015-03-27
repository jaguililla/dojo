// P A C K A G E ///////////////////////////////////////////////////////////////
package co.popapp.swing.widget;

// I M P O R T /////////////////////////////////////////////////////////////////
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.Serializable;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

// C L A S S ///////////////////////////////////////////////////////////////////
/**
 * Description of the Class
 *
 * PENDIENTE: al seleccionar una fuente la opcion seleccionada no se dibuja con
 * la fuente elegida (cambiar la propiedad font del combo al cambiar la
 * seleccion)
 *
 * @author jamming
 * @created   13 de mayo de 2001
 */
public class JFontComboBox extends JComboBox
{

    /**
     * Constructor for the FontComboBox object
     *
     * @since
     */
    public JFontComboBox ()
    {
        super(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
        setRenderer(new FontComboBoxRenderer());
    }

    /**
     * Description of the Class
     *
     * ERROR: al abrir el combo por primera vez el borde inferior de la lista no
     * se pinta
     *
     * @author jamming
     * @created   13 de mayo de 2001
     */
    /*
    class MetalFontComboBoxUI extends MetalComboBoxUI
    {
        public void itemStateChanged(ItemEvent event)
        {
            System.out.println(event.getItem().toString());
            //comboBox.getModel().getSelectedItem();
        }
    }
     */

    class FontComboBoxRenderer extends JLabel implements ListCellRenderer, Serializable
    {
        public FontComboBoxRenderer()
        {
            super();
            setOpaque(true);
            setBorder(new EmptyBorder (1, 1, 1, 1));
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
}
// E O F ///////////////////////////////////////////////////////////////////////

