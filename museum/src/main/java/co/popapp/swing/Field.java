// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.swing;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import static co.popapp.swing.ComponentUtils.setExpandable;
import static co.popapp.swing.ComponentUtils.setMargin;
import static co.popapp.swing.ComponentUtils.setShrinkable;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

// T Y P E S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * UI to edit a single variable.
 *
 * @param <T> .
 * @author jamming
 */
public class Field<T> extends JPanel {
    private static final long serialVersionUID = 1L;

    private JLabel mLabel = new JLabel();

//    private JButton mAction;
    private JComponent mEditComponent;

    public Field(boolean aReadOnly, String aLabel, T aValue) {
        this(aReadOnly, aLabel, aValue, null);
    }

    public Field(boolean aReadOnly, String aLabel, T aValue, final ChangeListener aListener) {
        super(new BorderLayout(5, 5));

        setMargin(this, 5, 5, 5, 5);
        setShrinkable(this, false, false);
        setExpandable(this, true, false);

        mLabel.setText(aLabel);
        createJComponent(aValue, aListener);
        mEditComponent.setEnabled(!aReadOnly);

        add(mLabel, BorderLayout.WEST);
        add(mEditComponent, BorderLayout.CENTER);
    }

    /**
     * @param aValue
     * @param aListener
     */
    private void createJComponent(T aValue, final ChangeListener aListener) {

        if (aValue instanceof Number) {
            createJSpinner(aValue, aListener);
        }
        else if (aValue instanceof Boolean) {
            createJToggleButton(aValue, aListener);
        }
        else if (aValue.getClass().isEnum()) {
            createJComboBox(aValue, aListener);
        }
        else {
            throw new IllegalArgumentException(
                            "Unsupported data type: " + aValue.getClass().getSimpleName());
        }

    }

    /**
     * @param aValue
     * @param aListener
     */
    private void createJComboBox(T aValue, final ChangeListener aListener) {

        if (aValue.getClass().isEnum()) {
            mEditComponent = new JComboBox(aValue.getClass().getEnumConstants());
            if (aListener != null) {
                JComboBox cmbEdit = (JComboBox) mEditComponent;
                cmbEdit.addItemListener(aE -> {
                    ChangeEvent e = new ChangeEvent(mEditComponent);
                    aListener.stateChanged(e);
                });
            }
        }
        else {
            throw new IllegalArgumentException(
                "Unsupported data type: " + aValue.getClass().getSimpleName());
        }

    }

    /**
     * @param aValue
     * @param aListener
     */
    private void createJToggleButton(T aValue, final ChangeListener aListener) {

        if (aValue.getClass().equals(Boolean.class)) {
            mEditComponent = new JToggleButton((Boolean) aValue ? "ON" : "OFF");
            if (aListener != null) {
                ((JToggleButton) mEditComponent).addItemListener(aE -> {
                    ChangeEvent e = new ChangeEvent(mEditComponent);
                    aListener.stateChanged(e);
                });
            }
        }
        else {
            throw new IllegalArgumentException(
                            "Unsupported data type: " + aValue.getClass().getSimpleName());
        }

    }

    private void createJSpinner(T aValue, final ChangeListener aListener) {

        if (aValue.getClass().equals(Byte.class)
                        || aValue.getClass().equals(Short.class)
                        || aValue.getClass().equals(Integer.class)) {
            mEditComponent = new JSpinner();
            if (aListener != null) {
                ((JSpinner) mEditComponent).addChangeListener(aListener);
            }
        }
        else if (aValue.getClass().equals(Float.class)) {
            mEditComponent = new JSpinner(new SpinnerNumberModel(0.0f, -999.0f, 999.0f, 0.1f));
            if (aListener != null) {
                ((JSpinner) mEditComponent).addChangeListener(aListener);
            }
        }
        else {
            throw new IllegalArgumentException(
                            "Unsupported data type: " + aValue.getClass().getSimpleName());
        }

    }

    public <R> void setValue(R aValue) {
        if (aValue.getClass().equals(Boolean.class)) {
            JToggleButton edc = (JToggleButton) getEditComponent();
            edc.setText((Boolean) aValue ? "ON" : "OFF");
            edc.setSelected((Boolean) aValue);
        }
        else if (aValue.getClass().equals(Byte.class)
                        || aValue.getClass().equals(Short.class)
                        || aValue.getClass().equals(Integer.class)
                        || aValue.getClass().equals(Float.class)) {
            JSpinner edc = (JSpinner) getEditComponent();
            edc.setValue(aValue);
        }
        else if (aValue.getClass().isEnum()) {
            JComboBox edc = (JComboBox)getEditComponent();
            edc.setSelectedItem(aValue);
        }
        else {
            throw new IllegalArgumentException(
                "Invalid value type: " + aValue.getClass().getSimpleName());
        }
    }

    JLabel getLabel() {
        return mLabel;
    }

    JComponent getEditComponent() {
        return mEditComponent;
    }

    @SuppressWarnings("unchecked") <R extends JComponent> R getEditComponent(Class<R> aType) {
        if (mEditComponent.getClass().equals(aType)) {
            return (R) mEditComponent;
        }
        else {
            throw new IllegalStateException();
        }
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
