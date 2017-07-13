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

package co.popapp;

import static co.popapp.Utils.treeItem;
import static java.lang.System.exit;
import static java.util.Arrays.asList;
import static javafx.fxml.FXMLLoader.load;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

class Utils {
    @SafeVarargs static <Z> TreeItem<Z> treeItem (Z value, TreeItem<Z>... children) {
        return treeItem (value, asList (children));
    }

    static <Z> TreeItem<Z> treeItem (Z value, List<TreeItem<Z>> children) {
        TreeItem<Z> item = new TreeItem<> (value);
        item.getChildren ().addAll (children);
        return item;
    }
}

/**
 * TODO Splash screen, undecorated window, app icon, font icons
 */
public class JfxDemo extends Application implements Initializable {
    public static void main (String[] args) { launch (args); }

    @FXML TreeView<String> treeDemo;

    @FXML public void mnuQuitOnAction (ActionEvent aActionEvent) { exit (0); }

    @Override public void initialize (URL location, ResourceBundle resources) {
        treeDemo.setRoot (treeItem ("ROOT",
            treeItem ("Item 1"),
            treeItem ("Item 2"),
            treeItem ("Item 3"),
            treeItem ("Item 4")
        ));
    }

    @Override public void start (Stage primaryStage) throws Exception {
        primaryStage.setScene (new Scene (load (getClass ().getResource ("/jfxdemo.fxml"))));
        primaryStage.setTitle ("JavaFX Demo");
        primaryStage.show ();
    }
}
