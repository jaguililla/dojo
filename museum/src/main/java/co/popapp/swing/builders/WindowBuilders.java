/*
 * POPAPP Copyright © 2011 Juan José Aguililla. All rights reserved.
 *
 * This program comes WITHOUT ANY WARRANTY; It is free software, you can redistribute it and/or
 * modify it under the terms of the GNU General Public License, Version 3.0.
 * You may obtain a copy of the License at: http://www.gnu.org/licenses/gpl.html
 */

package co.popapp.swing.builders;

import static co.popapp.swing.builders.Builders.buildComponent;
import static com.google.common.base.Preconditions.checkArgument;
import static javax.swing.SwingUtilities.invokeLater;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import java.util.function.Consumer;
import javax.swing.*;

public class WindowBuilders {
    public static JWindow window (Consumer<JWindow> builder) {
        return buildComponent (new JWindow (), builder);
    }

    public static JFrame frame (Consumer<JFrame> builder) {
        return buildComponent (new JFrame (), builder);
    }

    public static JFrame frame (String aTitle, Consumer<JFrame> builder) {
        return buildComponent (new JFrame (aTitle), builder);
    }

    public static JDialog dialog (Consumer<JDialog> builder) {
        return buildComponent (new JDialog (), builder);
    }

    // RUN / SHOW /////////////////////////////////////////////////////////////////////////////

    public static void showFrame (Consumer<JFrame> builder) {
        showFrame (frame (builder));
    }

    public static void showFrame (String title, Consumer<JFrame> builder) {
        showFrame (frame (title, builder));
    }

    public static void showMainFrame (String title, Consumer<JFrame> builder) {
        checkArgument (title != null);
        JFrame frame = frame (title, builder);
        frame.setDefaultCloseOperation (EXIT_ON_CLOSE);
        showFrame (frame);
    }

    private static void showFrame (JFrame frame) {
        checkArgument (frame != null);
        frame.pack ();
        frame.setVisible (true);
    }

    public static void runSwingApplication (Runnable initialization, Runnable builder) {
        checkArgument (builder != null);

        if (initialization != null)
            initialization.run ();

        invokeLater (builder::run);
    }

    public static void runSwingApplication (Runnable builder) {
        runSwingApplication (null, builder);
    }

    private WindowBuilders () { throw new IllegalStateException (); }
}
