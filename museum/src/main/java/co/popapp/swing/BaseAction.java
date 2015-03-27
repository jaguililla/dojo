/*
 * POPAPP Copyright © 2011 Juan José Aguililla. All rights reserved.
 *
 * This program comes WITHOUT ANY WARRANTY; It is free software, you can redistribute it and/or
 * modify it under the terms of the GNU General Public License, Version 3.0.
 * You may obtain a copy of the License at: http://www.gnu.org/licenses/gpl.html
 */

// P A C K A G E //////////////////////////////////////////////////////////////////////////////
package co.popapp.swing;

import javax.swing.*;

// C L A S S //////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 *
 * @author jam
 */
public abstract class BaseAction extends AbstractAction {
    private static final long serialVersionUID = 1L;

    public void setTexts (String name, String description) {
        setName (name);
        setDescription (description);
    }

    public void setTexts (String name, String description, String longDescription) {
        setName (name);
        setDescription (description);
        setLongDescription (longDescription);
    }

    private void setName (String name) {
        if (name != null)
            putValue (NAME, name);
    }

    private void setDescription (String description) {
        if (description != null)
            putValue (SHORT_DESCRIPTION, description);
    }

    private void setLongDescription (String longDescription) {
        if (longDescription != null)
            putValue (LONG_DESCRIPTION, longDescription);
    }

    public void setKeys (int mnemonic, KeyStroke accelerator) {
        setMnemonic (mnemonic);
        setAccelerator (accelerator);
    }

    private void setAccelerator (KeyStroke accelerator) {
        if (accelerator != null)
            putValue (ACCELERATOR_KEY, accelerator);
    }

    private void setMnemonic (int mnemonic) {
        if (mnemonic < 0)
            putValue (MNEMONIC_KEY, mnemonic);
    }

    public void setIcons (Icon icon, Icon largeIcon) {
        setIcon (icon);
        setLargeIcon (largeIcon);
    }

    private void setIcon (Icon icon) {
        if (icon != null)
            putValue (SMALL_ICON, icon);
    }

    private void setLargeIcon (Icon icon) {
        if (icon != null)
            putValue (LARGE_ICON_KEY, icon);
    }
}
