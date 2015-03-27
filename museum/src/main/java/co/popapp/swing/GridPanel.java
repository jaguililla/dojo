package co.popapp.swing;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class GridPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public static GridPanel columnsGridPanel (int aColumns, Component... aComponents) {
        return new GridPanel (0, aColumns, aComponents);
    }

    public static GridPanel rowsGridPanel (int aRows, Component... aComponents) {
        return new GridPanel (aRows, 0, aComponents);
    }

    public static GridPanel gridPanel (int aRows, int aColumns, Component... aComponents) {
        return new GridPanel (aRows, aColumns, aComponents);
    }

    public static GridPanel columnsGridPanel (
        int aColumns, int aHGap, int aVGap, Component... aComponents) {
        return new GridPanel (0, aColumns, aHGap, aVGap, aComponents);
    }

    public static GridPanel rowsGridPanel (
        int aRows, int aHGap, int aVGap, Component... aComponents) {
        return new GridPanel (aRows, 0, aHGap, aVGap, aComponents);
    }

    public static GridPanel gridPanel (
        int aRows, int aColumns, int aHGap, int aVGap, Component... aComponents) {
        return new GridPanel (aRows, aColumns, aHGap, aVGap, aComponents);
    }

    public GridPanel (Component... aComponents) {
        super (new GridLayout ());
        for (Component c : aComponents) {
            add (c);
        }
    }

    public GridPanel (int aRows, int aColumns, Component... aComponents) {
        super (new GridLayout (aRows, aColumns));
        for (Component c : aComponents) {
            add (c);
        }
    }

    public GridPanel (int aRows, int aColumns, int aHGap, int aVGap, Component... aComponents) {
        super (new GridLayout (aRows, aColumns, aHGap, aVGap));
        for (Component c : aComponents) {
            add (c);
        }
    }
}
