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

import static storyboard.State.ENABLED;
import static storyboard.util.Strings.capitalize;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Base class for actions executed by tests (features). Following the Gherkin specification
 * they can be: features, scenarios or steps (given, when, then, and or but).
 *
 * @author jam
 * @see <a href="http://docs.behat.org/en/latest/guides/1.gherkin.html">Gherkin reference</a>
 */
abstract class Action {
    /** Action types (they match Gherkin's elements). */
    enum Type { FEATURE, BACKGROUND, SCENARIO, GIVEN, WHEN, THEN, AND, BUT }

    /** This action's type. Can not be 'null'. */
    final Type type;
    /** The action's text. Can be 'null', ie: for features's background scenario. */
    final String text;
    /** Whether this action is enabled or not. It can not be 'null'. */
    final State state;
    /** List of tags applied to this action. */
    final List<String> tags = new ArrayList<> ();

    /**
     * Constructor with all fields.
     *
     * @param type Action's {@link #type type}.
     * @param text Action's {@link #text text}.
     * @param state Action's {@link #state state}.
     */
    Action (final Type type, final String text, final State state) {
        if (type == null || state == null)
            throw new IllegalArgumentException ();

        this.type = type;
        this.text = text;
        this.state = state;
    }

    /**
     * Constructor for an enabled action.
     *
     * @see #Action(Action.Type, String, State)
     */
    Action (final Type type, final String text) {
        this (type, text, ENABLED);
    }

    /**
     * Utility method with ':' delimiter.
     *
     * @see #describe(String)
     */
    protected final String describe () {
        return describe (":");
    }

    /**
     * Returns the textual representation of this action. This is a default implementation with this
     * format:
     *
     * <pre>
     *     \@tag
     *     Type: text
     * </pre>
     *
     * @return Gherkin representation of this action.
     */
    protected final String describe (final String delimiter) {
        String theText = text == null || text.isEmpty ()? delimiter : delimiter + " " + text;
        String theTags = describeTags (" ", "\n");
        return theTags + capitalize (type) + theText;
    }

    /**
     *
     * @param delimiter
     * @param suffix
     * @return
     */
    protected final String describeTags (final String delimiter, final String suffix) {
        StringJoiner joiner = new StringJoiner (delimiter, "", suffix).setEmptyValue ("");
        tags.stream ().map (t -> "@" + t).forEach (joiner::add);
        return joiner.toString ();
    }

    /**
     * Adds a group of tags to this action. Strings are filtered and empty ones will not be added.
     *
     * @param tags List of tags to add. Empty ones are filtered.
     */
    protected final void addTags (final String... tags) {
        for (String tag : tags)
            if (tag == null || tag.isEmpty () || tag.contains (" "))
                throw new IllegalArgumentException ();
            else
                this.tags.add (tag);
    }

    /**
     * @see Object#toString()
     */
    @Override public String toString () {
        return describe ();
    }
}
