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

package storyboard;

import static storyboard.Action.Type.*;
import static storyboard.State.ENABLED;

/**
 * An scenario is an action (scenarios can be nested).
 *
 * @author jam
 */
public final class Scenario extends Background {
    Scenario (final String text) {
        super (SCENARIO, text, ENABLED);
    }

    Scenario (final String text, final State state) {
        super (SCENARIO, text, state);
    }

    Scenario (final Scenario background) {
        super (background);
    }

    public final Scenario tags (final String... tags) {
        addTags (tags);
        return this;
    }
}
