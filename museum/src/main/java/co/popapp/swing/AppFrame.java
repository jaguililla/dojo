/*
 * POPAPP Copyright © 2011 Juan José Aguililla. All rights reserved.
 *
 * This program comes WITHOUT ANY WARRANTY; It is free software, you can redistribute it and/or
 * modify it under the terms of the GNU General Public License, Version 3.0.
 * You may obtain a copy of the License at: http://www.gnu.org/licenses/gpl.html
 */

// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.swing;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import java.awt.HeadlessException;

import javax.swing.JFrame;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * @author jam
 */
public class AppFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    // TODO Fullscreen
    // TODO Ask to exit app
    // TODO Maximize only height or width
    // TODO Stack to a border
    // TODO Center
    // TODO Create default structure (menu, toolbar, statusbar) each part will be optional
    // TODO Store load coordinates and state
    // TODO Splashscreen (see java.awt.SplashScreen)
    // TODO Close procedure

    {
        setDefaultCloseOperation (EXIT_ON_CLOSE);
    }

    /**
     * TODO .
     * @throws java.awt.HeadlessException
     */
    public AppFrame () throws HeadlessException {
        super ();
    }

    /**
     * TODO .
     * @param aTitle
     * @throws java.awt.HeadlessException
     */
    public AppFrame (String aTitle) throws HeadlessException {
        super (aTitle);
    }

    /**
     * TODO .
     */
    public void open () {
        pack ();
        setVisible (true);
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
