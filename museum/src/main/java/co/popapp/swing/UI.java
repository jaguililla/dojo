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
import static javax.swing.JFrame.setDefaultLookAndFeelDecorated;
import static javax.swing.SwingUtilities.invokeAndWait;
import static javax.swing.UIManager.*;

import java.io.File;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * @author jam
 */
public class UI {
    /**
     * TODO .
     * @param args
     */
    public static void launch (final AppFrame aFrame) throws Exception {
        invokeAndWait (new Runnable () {
            @Override
            public void run () {
                aFrame.open ();
            }
        });
    }

    /**
     * TODO .
     * @throws Exception
     */
    public static void loadCrossPlatformLaF () throws Exception {
        setLookAndFeel (getCrossPlatformLookAndFeelClassName ());
        setDefaultLookAndFeelDecorated (true);
    }

    /**
     * TODO .
     * TODO Option to support native colors.
     * @throws Exception
     */
    public static void loadNimbusLaF () throws Exception {
        try {
            setLookAndFeel ("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            setDefaultLookAndFeelDecorated (true);
        }
        catch (Exception e) {
            loadCrossPlatformLaF ();
        }
    }

    /**
     * TODO .
     * @param aSynthFile
     */
    public static void loadSynthLaF (File aSynthFile) {
        // TODO
    }

    /**
     * TODO .
     * @throws Exception
     */
    public static void loadSystemLaF () throws Exception {
        try {
            setLookAndFeel (getSystemLookAndFeelClassName ());
            setDefaultLookAndFeelDecorated (true);
        }
        catch (Exception e) {
            loadNimbusLaF ();
        }
    }

    /**
     * TODO .
     */
    private UI () {
        throw new IllegalStateException ("This class should not be instantiated");
    }

    // TODO Style JComponent

    // TODO Layout helpers

    // TODO Look before in SwingUtilities!
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
