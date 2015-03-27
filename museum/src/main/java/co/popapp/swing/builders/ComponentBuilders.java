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

public class ComponentBuilders {
    public static JLabel label (Consumer<JLabel> builder) {
        return buildComponent (new JLabel (), builder);
    }

    public static void label (Container parent, Consumer<JLabel> builder) {
        buildComponent (parent, new JLabel (), builder);
    }

    public static void label (Container parent, Object layoutData, Consumer<JLabel> builder) {
        buildComponent (parent, layoutData, new JLabel (), builder);
    }

    public static JLabel label (String aText, Consumer<JLabel> builder) {
        return buildComponent (new JLabel (aText), builder);
    }

    public static JTextArea textArea (Consumer<JTextArea> builder) {
        return buildComponent (new JTextArea (), builder);
    }

    public static void textArea (Container parent, Consumer<JTextArea> builder) {
        buildComponent (parent, new JTextArea (), builder);
    }

    public static JTextField textField (Consumer<JTextField> builder) {
        return buildComponent (new JTextField (), builder);
    }

    public static JPasswordField passwordField (Consumer<JPasswordField> builder) {
        return buildComponent (new JPasswordField (), builder);
    }

    public static JEditorPane editorPane (Consumer<JEditorPane> builder) {
        return buildComponent (new JEditorPane (), builder);
    }

    public static JTextPane textPane (Consumer<JTextPane> builder) {
        return buildComponent (new JTextPane (), builder);
    }

    public static JToggleButton toggleButton (Consumer<JToggleButton> builder) {
        return buildComponent (new JToggleButton (), builder);
    }

    public static JCheckBox checkBox (Consumer<JCheckBox> builder) {
        return buildComponent (new JCheckBox (), builder);
    }

    public static JRadioButton radioButton (Consumer<JRadioButton> builder) {
        return buildComponent (new JRadioButton (), builder);
    }

    public static JButton button (Consumer<JButton> builder) {
        return buildComponent (new JButton (), builder);
    }

    public static void button (Container parent, Consumer<JButton> builder) {
        buildComponent (parent, new JButton (), builder);
    }

    public static void button (Container parent, String text, Consumer<JButton> builder) {
        buildComponent (parent, new JButton (text), builder);
    }

    public static void flatButton (
        Container parent, String text, boolean focusable, Consumer<JButton> builder) {

        JButton button = new JButton (text);
        button.setFocusable (focusable);
        button.setRolloverEnabled (true);

        buildComponent (parent, button, builder);
    }

    public static void flatButton (
        Container parent, String text, Consumer<JButton> builder) {

        flatButton (parent, text, true, builder);
    }

    public static void flatNoFocusButton (
        Container parent, String text, Consumer<JButton> builder) {

        flatButton (parent, text, false, builder);
    }

    public static JSeparator separator (Consumer<JSeparator> builder) {
        return buildComponent (new JSeparator (), builder);
    }

    public static JComboBox comboBox (Consumer<JComboBox> builder) {
        return buildComponent (new JComboBox (), builder);
    }

    public static JScrollBar scrollBar (Consumer<JScrollBar> builder) {
        return buildComponent (new JScrollBar (), builder);
    }

    public static JProgressBar progressBar (Consumer<JProgressBar> builder) {
        return buildComponent (new JProgressBar (), builder);
    }
}
