package co.popapp.swing.widget;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class ColorTableEditor extends JButton implements TableCellEditor {
    private static final long serialVersionUID = 8645749362259594219L;

    public ColorTableEditor () {
        setBorderPainted (false);
        setOpaque (true);
    }

    @Override public void addCellEditorListener (javax.swing.event.CellEditorListener cellEditorListener) {
        // TODO Pendiente de implementar
    }

    @Override public void cancelCellEditing () {
        // TODO Pendiente de implementar
    }

    @Override public Object getCellEditorValue () {
        return getBackground ();
    }

    @Override public java.awt.Component getTableCellEditorComponent (
        JTable table, Object value, boolean isSelected, int row, int column) {
        return null;
    }

    @Override public boolean isCellEditable (java.util.EventObject eventObject) {
        return true;
    }

    @Override public void removeCellEditorListener (javax.swing.event.CellEditorListener cellEditorListener) {
        // TODO Pendiente de implementar
    }

    @Override public boolean shouldSelectCell (java.util.EventObject eventObject) {
        return false;
    }

    @Override public boolean stopCellEditing () {
        return true;
    }
}
