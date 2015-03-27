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

import java.awt.*;
import java.util.function.Consumer;
import javax.swing.*;

public class ContainerBuilders {
    public static JList list (Consumer<JList> builder) {
        return buildComponent (new JList (), builder);
    }

    public static JTree tree (Consumer<JTree> builder) {
        return buildComponent (new JTree (), builder);
    }

    public static void tree (Container parent, Consumer<JTree> builder) {
        buildComponent (parent, new JTree (), builder);
    }

    public static void scrollTree (
        Container parent, Object layoutData, Consumer<JTree> builder) {
        buildComponent (parent, layoutData, new JScrollPane (tree (builder)));
    }

    public static JToolBar toolBar (Consumer<JToolBar> builder) {
        return buildComponent (new JToolBar (), builder);
    }

    public static void toolBar (
        Container parent, Object layoutData, Consumer<JToolBar> builder) {

        buildComponent (parent, layoutData, new JToolBar (), builder);
    }

    public static void flatToolBar (
        Container parent, Object layoutData, Consumer<JToolBar> builder) {

        flatToolBar (parent, layoutData, true, builder);
    }

    public static void flatFixedToolBar (
        Container parent, Object layoutData, Consumer<JToolBar> builder) {

        flatToolBar (parent, layoutData, false, builder);
    }

    public static void flatToolBar (
        Container parent, Object layoutData, boolean floatable, Consumer<JToolBar> builder) {

        JToolBar toolBar = new JToolBar ();
        toolBar.setFloatable (floatable);
        toolBar.setRollover (true);
        toolBar.setFocusable (false);

        buildComponent (parent, layoutData, toolBar, builder);
    }

    public static JTable table (Consumer<JTable> builder) {
        return buildComponent (new JTable (), builder);
    }

    public static JPanel panel (Consumer<JPanel> builder) {
        return buildComponent (new JPanel (), builder);
    }

    public static JPanel panel (String name, Consumer<JPanel> builder) {
        JPanel panel = new JPanel ();
        panel.setName (name);
        return buildComponent (panel, builder);
    }

    public static void panel (Container parent, String name, Consumer<JPanel> builder) {
        buildComponent (parent, panel (name, builder));
    }

    public static JViewport viewport (Consumer<JViewport> builder) {
        return buildComponent (new JViewport (), builder);
    }

    public static JScrollPane scrollPane (Consumer<JScrollPane> builder) {
        return buildComponent (new JScrollPane (), builder);
    }

    public static void scrollPane (
        Container parent, Object constraints, Consumer<JScrollPane> builder) {

        buildComponent (parent, constraints, new JScrollPane (), builder);
    }

    public static JSplitPane splitPane (Consumer<JSplitPane> builder) {
        return buildComponent (new JSplitPane (), builder);
    }

    public static void splitPane (Container parent, Consumer<JSplitPane> builder) {
        buildComponent (parent, new JSplitPane (), builder);
    }

    public static JRootPane rootPane (Consumer<JRootPane> builder) {
        return buildComponent (new JRootPane (), builder);
    }

    public static JTabbedPane tabbedPane (Consumer<JTabbedPane> builder) {
        return buildComponent (new JTabbedPane (), builder);
    }

    public static void tabbedPane (
        Container parent, Object constraints, Consumer<JTabbedPane> builder) {

        buildComponent (parent, constraints, new JTabbedPane (), builder);
    }

    public static JLayeredPane layeredPane (Consumer<JLayeredPane> builder) {
        return buildComponent (new JLayeredPane (), builder);
    }

    public static JInternalFrame internalFrame (Consumer<JInternalFrame> builder) {
        return buildComponent (new JInternalFrame (), builder);
    }
}
