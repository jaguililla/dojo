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

import static com.google.common.base.Preconditions.checkArgument;

import java.awt.*;
import java.util.function.Consumer;

final class Builders {
    static <T extends Component> T buildComponent (T instance, Consumer<T> builder) {
        return buildComponent (null, null, instance, builder);
    }

    static <T extends Component> T buildComponent (
        Container parent, Object layoutData, T instance) {

        return buildComponent (parent, layoutData, instance, null);
    }

    static <T extends Component> T buildComponent (Container parent, T instance) {
        return buildComponent (parent, null, instance, null);
    }

    static <T extends Component> T buildComponent (
        Container parent, T instance, Consumer<T> builder) {

        return buildComponent (parent, null, instance, builder);
    }

    static <T extends Component> T buildComponent (
        Container parent, Object layoutData, T instance, Consumer<T> builder) {

        checkArgument (instance != null);

        if (builder != null)
            builder.accept (instance);

        if (parent != null)
            if (layoutData == null)
                parent.add (instance);
            else
                parent.add (instance, layoutData);

        return instance;
    }

    private Builders () { throw new IllegalStateException (); }
}
