package co.popapp.swing.widget;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ColorTableRenderer extends JLabel implements TableCellRenderer {
    private static final long serialVersionUID = -6575044549440253586L;

    public ColorTableRenderer () { setOpaque (true); }

    @Override public Component getTableCellRendererComponent (
        JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setBackground ((Color)value);
        return this;
    }
}
