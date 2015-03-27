/*
 * UIDefaultsTableModel.java
 *
 * Created on 5 de febrero de 2002, 13:55
 */
package co.popapp.swing.widget;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jamming
 */
public class UIDefaultsTableModel extends AbstractTableModel {
    private Object [][] defaultsArray = null;
    private String [] columnNames = { "Clave", "Valor" };

    /** Creates a new instance of UIDefaultsTableModel */
    public UIDefaultsTableModel () {
        UIDefaults defaults = UIManager.getDefaults ();
        defaultsArray = hashtableToArray (defaults);
    }

    /**
     * .
     * TODO Pendiente de documentar
     * @param aTable
     * @return Array
     */
    public Object [][] hashtableToArray (Hashtable aTable) {
        Object [][] resultArray = new Object [aTable.size ()] [2];
        Enumeration tableKeys = aTable.keys ();
        int ii = 0;
        while (tableKeys.hasMoreElements ()) {
            Object currentKey = tableKeys.nextElement ();
            resultArray[ii][0] = currentKey;
            resultArray[ii][1] = aTable.get (currentKey);
            ii++;
        }
        return resultArray;
    }

    /**
     * Returns the number of columns in the model. A
     * <code>JTable</code> uses this method to determine how many columns it
     * should create and display by default.
     *
     * @return the number of columns in the model
     * @see #getRowCount
     */
    @Override public int getColumnCount () {
        return columnNames.length;
    }

    /**
     * Returns the number of rows in the model. A
     * <code>JTable</code> uses this method to determine how many rows it
     * should display.  This method should be quick, as it
     * is called frequently during rendering.
     *
     * @return the number of rows in the model
     * @see #getColumnCount
     */
    @Override public int getRowCount () {
        return defaultsArray.length;
    }

    /**
     * Returns the value for the cell at <code>columnIndex</code> and
     * <code>rowIndex</code>.
     *
     * @param   rowIndex    the row whose value is to be queried
     * @param   columnIndex     the column whose value is to be queried
     * @return  the value Object at the specified cell
     */
    @Override public Object getValueAt (int rowIndex, int columnIndex) {
        return defaultsArray[rowIndex][columnIndex];
    }

    /**
     * @see javax.swing.table.TableModel#getColumnName(int)
     */
    @Override public String getColumnName (int columnIndex) {
        return columnNames[columnIndex];
    }

    /**
     * @see javax.swing.table.TableModel#getColumnClass(int)
     */
    @Override public Class getColumnClass (int columnIndex) {
        return getValueAt (0, columnIndex).getClass ();
    }
}
