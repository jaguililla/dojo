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

import static java.util.stream.Collectors.toList;
import static storyboard.Action.Type.FEATURE;
import static storyboard.util.Strings.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * .
 *
 * @author jam
 */
public final class Feature extends Action {
    /**
     *
     * @param title
     * @param narrative
     * @param builder
     * @return
     */
    public static Feature feature (
        final String title, final String narrative, final Consumer<Feature> builder) {

        Feature feature = new Feature (title, narrative);

        if (builder != null)
            builder.accept (feature);

        return feature;
    }

    /**
     *
     * @param title
     * @param asA
     * @param iWant
     * @param inOrder
     * @param builder
     * @return
     */
    public static Feature feature (
        final String title,
        final String asA,
        final String iWant,
        final String inOrder,
        final Consumer<Feature> builder) {

        // If you do not want to set the purpose, call a method without those parameters
        if (asA == null || iWant == null || inOrder == null)
            throw new IllegalArgumentException ();

        return feature (title, asA + EOL + iWant + EOL + inOrder, builder);
    }

    /**
     *
     * @param title
     * @param builder
     * @return
     */
    public static Feature feature (final String title, final Consumer<Feature> builder) {
        return feature (title, null, builder);
    }

    final String purpose;
    final Background background = new Background ();
    final List<Scenario> scenarios = new ArrayList<> ();

    Feature (final String title, final String purpose) {
        super (FEATURE, title);
        this.purpose = unindent (purpose).trim ();
    }

    Feature (final Feature source) {
        this (source.text, source.purpose);
        tags.addAll (source.tags);
    }

    Feature composeBackground () {
        Feature result = new Feature (this);

        result.scenarios.addAll (
            scenarios.stream ()
                .map (s -> (Scenario)s.compose (background))
                .collect (toList ())
        );

        return result;
    }

    /**
     *
     * @param builder .
     */
    public final Feature background (final Consumer<Background> builder) {
        if (builder != null)
            builder.accept (background);
        return this;
    }

    /**
     *
     * @param text
     * @param steps
     */
    public final Feature scenario (final String text, final Consumer<Scenario> steps) {
        Scenario s = new Scenario (text);
        steps.accept (s);
        scenarios.add (s);
        return this;
    }

    public final Feature tags (final String... tags) {
        addTags (tags);
        return this;
    }

    @Override public String toString () {
        boolean noBackground = background.steps.isEmpty () && background.parameters.isEmpty ();
        return
            describe () + EOL + indent (purpose) + EOL +
            (noBackground? "" : indent (hangingIndent (background))) +
            scenarios.stream ()
                .map (s ->
                    EOL + indent (hangingIndent (s, s.describe ().startsWith ("@")? 2 : 1, 2)))
                .reduce ("", (a, b) -> a + b);
    }
}
