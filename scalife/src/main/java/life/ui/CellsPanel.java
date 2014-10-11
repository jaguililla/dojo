// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package life.ui;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import life.LifeGame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * @author jamming
 */
public class CellsPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private static final int CELL_DIMENSION = 10;
    private static final String CELL_TEXT = "";
    private static final Color LINE_COLOR = Color.LIGHT_GRAY;

    static final Color BACKGROUND_COLOR = Color.GRAY;
    static final Color SELECTED_COLOR = Color.ORANGE;

    private static boolean sRecursive = true;

    public static boolean isRecursive () { return sRecursive; }

    public static void setRecursive (boolean aRecursive) { sRecursive = aRecursive; }

    private GridLayout mLayout = new GridLayout ();
    private LineBorder mBorder = new LineBorder (LINE_COLOR, 1);
    private LifeGame mLife = null;

    public CellsPanel () { this (1, 1); }

    public CellsPanel (int aColumns, int aRows) {
        super ();
        mLife = new LifeGame (aColumns, aRows);
        setLayout (mLayout);
        setBorder (mBorder);
        setColumns (aColumns);
        setRows (aRows);
    }

    private void build () {
        invalidate ();
        clear ();
        removeAll (); // Sustituir por un control mas fino de las celdas a insertar/borrar
        int newSize = getColumns () * getRows ();
        for (int ii = 0; ii < newSize; ii++) {
            JToggleButton cell = new JToggleButton (CELL_TEXT);
            cell.setPreferredSize (new Dimension (CELL_DIMENSION, CELL_DIMENSION));
            cell.setFocusable (false);
            cell.setBorder (mBorder);
            cell.setBackground (BACKGROUND_COLOR);
            cell.addChangeListener (new ChangeListener () {
                public void stateChanged (ChangeEvent aE) {
                    JToggleButton source = (JToggleButton)aE.getSource ();
                    source.setBackground (source.isSelected ()? SELECTED_COLOR : BACKGROUND_COLOR);
                }
            });
            add (cell);
        }
    }

    public void clear () {
        for (Component c : getComponents ())
            ((JToggleButton)c).setSelected (false);
    }

    public void generateNext () {
        setValues (isRecursive ()? mLife.gen (getValues ()) : mLife.genit (getValues ()));
    }

    public int getColumns () { return mLayout.getColumns (); }

    public int getRows () { return mLayout.getRows (); }

    public boolean [] getValues () {
        boolean [] results = new boolean [getColumns () * getRows ()];
        for (int ii = 0; ii < getColumns () * getRows (); ii++) {
            results[ii] = ((JToggleButton)getComponent (ii)).isSelected ();
        }
        return results;
    }

    public void setColumns (int aCols) {
        mLayout.setColumns (aCols);
        mLife.setColumns (aCols);
        build ();
    }

    @Override public void setEnabled (boolean aEnabled) {
        for (Component c : getComponents ())
            c.setEnabled (aEnabled);
        super.setEnabled (aEnabled);
    }

    public void setRows (int aRows) {
        mLayout.setRows (aRows);
        mLife.setRows (aRows);
        build ();
    }

    public void setValues (boolean [] aValues) {
        for (int ii = 0; ii < aValues.length; ii++)
            ((JToggleButton)getComponent (ii)).setSelected (aValues[ii]);
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
