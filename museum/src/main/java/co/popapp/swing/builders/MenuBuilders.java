/*
 * Copyright © 2014 Juan José Aguililla. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */

package co.popapp.swing.builders;

import static co.popapp.swing.builders.Builders.buildComponent;
import static com.google.common.base.Preconditions.checkArgument;

import java.awt.*;
import java.util.function.Consumer;
import javax.swing.*;

public final class MenuBuilders {
    public static JMenuItem menuItem (Consumer<JMenuItem> builder) {
        return buildComponent (new JMenuItem (), builder);
    }

    public static void menuItem (Container parent, Consumer<JMenuItem> builder) {
        buildComponent (parent, new JMenuItem (), builder);
    }

    public static void menuItem (Container parent, String text, Consumer<JMenuItem> builder) {
        buildComponent (parent, new JMenuItem (text), builder);
    }

    public static JCheckBoxMenuItem checkBoxMenuItem (Consumer<JCheckBoxMenuItem> builder) {
        return buildComponent (new JCheckBoxMenuItem (), builder);
    }

    public static JRadioButtonMenuItem radioButtonMenuItem (
        Consumer<JRadioButtonMenuItem> builder) {

        return buildComponent (new JRadioButtonMenuItem (), builder);
    }

    public static JMenu menu (Consumer<JMenu> builder) {
        return buildComponent (new JMenu (), builder);
    }

    public static void menu (Container parent, Consumer<JMenu> builder) {
        buildComponent (parent, new JMenu (), builder);
    }

    public static void menu (Container parent, String text, Consumer<JMenu> builder) {
        buildComponent (parent, new JMenu (text), builder);
    }

    public static JMenuBar menuBar (Consumer<JMenuBar> builder) {
        return buildComponent (new JMenuBar (), builder);
    }

    public static void menuBar (JFrame parent, Consumer<JMenuBar> builder) {
        checkArgument (parent != null);
        parent.setJMenuBar (buildComponent (new JMenuBar (), builder));
    }

    public static JPopupMenu popupMenu (Consumer<JPopupMenu> builder) {
        return buildComponent (new JPopupMenu (), builder);
    }

    private MenuBuilders () { throw new IllegalStateException (); }
}
