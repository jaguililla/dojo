////////////////////////////////////////////////////////////////////////////////
package co.popapp.swing.widget;

////////////////////////////////////////////////////////////////////////////////
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.basic.BasicTableUI;

////////////////////////////////////////////////////////////////////////////////
/**
 * Description of the Class
 *
 * @author jamming
 * @created   17 de abril de 2001
 */
public class MyTableUI extends BasicTableUI {
    /**
     * Description of the Method
     *
     * @return   Description of the Returned Value
     * @since
     */
    @Override protected MouseInputListener createMouseInputListener () {
        return new MyMouseInputHandler ();
    }

    /**
     * Description of the Class
     *
     * @author jamming
     * @created   17 de abril de 2001
     */
    class MyMouseInputHandler extends MouseInputHandler {
        /**
         * Description of the Method
         *
         * @param e  Description of Parameter
         * @since
         */
        @Override public void mouseClicked (MouseEvent e) {
            JTable theTable = (JTable)e.getComponent ();
            int rowIndex = theTable.rowAtPoint (new Point (e.getX (), e.getY ()));
            if (theTable.isRowSelected (rowIndex)) {
                theTable.removeRowSelectionInterval (rowIndex, rowIndex);
            }
            else {
                theTable.addRowSelectionInterval (rowIndex, rowIndex);
            }
        }

        /**
         * Este metodo es sobreescrito para que no ejecute las acciones por
         * defecto del L&F de la JTable
         *
         * @param e  Description of Parameter
         * @since
         */
        @Override public void mousePressed (MouseEvent e) {
        // TODO Pendiente de implementar
        }

        /**
         * Exactamente igual que el metodo anterior
         *
         * @param e  Description of Parameter
         * @since
         */
        @Override public void mouseReleased (MouseEvent e) {
        // TODO Pendiente de implementar
        }
    }

    /**
     * The main program for the UIDefaultsMain class
     *
     * @param args  The command line arguments
     * @since
     */
    public static void main (String [] args) {
        Object [][] datos = new Object [4] [3];
        datos[0][0] = "Aguililla";
        datos[0][1] = "Martinez";
        datos[0][2] = "Alicia";
        datos[1][0] = "Martinez";
        datos[1][1] = "Perez";
        datos[1][2] = "Luis";
        datos[2][0] = "Gonzalez";
        datos[2][1] = "Rodrigez";
        datos[2][2] = "Manolo";
        datos[3][0] = "Nito";
        datos[3][1] = "Del Bosque";
        datos[3][2] = "Helena";

        String [] columnas = new String [3];
        columnas[0] = "Primer Apellido";
        columnas[1] = "Segundo Apellido";
        columnas[2] = "Nombre";

        JTable tabla = new JTable (datos, columnas);
        tabla.setUI (new MyTableUI ());
        JFrame ventana = new JFrame ("Prueba Tabla");
        ventana.getContentPane ().add (tabla);
        ventana.pack ();
        ventana.setVisible (true);
    }
}
