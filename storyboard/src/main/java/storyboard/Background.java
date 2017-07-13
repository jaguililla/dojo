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

import static java.lang.Math.max;
import static java.util.Arrays.asList;
import static java.util.Collections.copy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static storyboard.Action.Type.*;
import static storyboard.State.ENABLED;
import static storyboard.data.Builder.*;
import static storyboard.util.Strings.*;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import storyboard.data.Builder;

/**
 * An scenario is an action (scenarios can be nested).
 *
 * @author jam
 */
public class Background extends Action {
    final List<Action> steps = new ArrayList<> ();
    final Map<String, List<Object>> parameters = new LinkedHashMap<> ();

    Background (final Type type, final String text, final State state) {
        super (type, text, state);
    }

    Background (final State state) {
        super (BACKGROUND, null, state);
    }

    Background () {
        super (BACKGROUND, null);
    }

    Background (final Background background) {
        this (background.type, background.text, background.state);
        tags.addAll (background.tags);
    }

    @SafeVarargs public final <Z> Background parameter (final String name, final Z... variables) {
        if (name == null || name.isEmpty ())
            throw new IllegalArgumentException ();

        parameters.put (name, asList (variables));
        return this;
    }

    Scenario compose (Background background) {
        Scenario result = new Scenario (text, state);

        result.steps.addAll (background.steps);
        result.steps.addAll (steps);
        result.parameters.putAll (parametersProduct (background.parameters));

        return result;
    }

    List<Object> cycle (List<Object> list, int times) {
        return list.stream ()
            .flatMap (l -> range (0, times).mapToObj (i -> list).flatMap (Collection::stream))
            .collect (toList ());
    }

    List<Object> duplicate (List<Object> list, int times) {
        return list.stream ()
            .flatMap (l -> range (0, times).mapToObj (i -> l))
            .collect (toList ());
    }

    Map<String, List<Object>> parametersProduct (Map<String, List<Object>> params) {
        Map<String, List<Object>> result = new LinkedHashMap<> ();

        int rows = 2; // TODO BAAAAAAD
        params.entrySet ().stream ()
            .forEach (e -> result.put (e.getKey (), duplicate (e.getValue (), rows)));
        parameters.entrySet ().stream ()
            .forEach (e -> result.put (e.getKey (), cycle (e.getValue (), rows)));

        return result;
    }

    List<Map<String, Object>> examples () {
        List<Map<String, Object>> result = new ArrayList<> ();

        for (Entry<String, List<Object>> param : parameters.entrySet ()) {
            List<Object> values = param.getValue ();
            for (int ii = 0; ii < values.size (); ii++) {
                if (result.size () <= ii)
                    result.add (new LinkedHashMap<> ());

                result.get (ii).put (param.getKey (), values.get (ii));
            }
        }

        return result;
    }

    protected Background step (
        final Type type, final String text, final Consumer<Map<String, ?>> lambda) {

        steps.add (new Step (type, text, ENABLED, lambda));
        return this;
    }

    protected Background step (final Type type, final String text, final Runnable lambda) {
        steps.add (new Step (type, text, ENABLED, lambda));
        return this;
    }

    protected Background step (final Type type, final String text) {
        steps.add (new Step (type, text, ENABLED));
        return this;
    }

    public final Background given (final String text, final Consumer<Map<String, ?>> lambda) {
        return step (GIVEN, text, lambda);
    }

    public final Background when (final String text, final Consumer<Map<String, ?>> lambda) {
        return step (WHEN, text, lambda);
    }

    public final Background then (final String text, final Consumer<Map<String, ?>> lambda) {
        return step (THEN, text, lambda);
    }

    public final Background and (final String text, final Consumer<Map<String, ?>> lambda) {
        return step (AND, text, lambda);
    }

    public final Background but (final String text, final Consumer<Map<String, ?>> lambda) {
        return step (BUT, text, lambda);
    }

    public final Background given (final String text, final Runnable lambda) {
        return step (GIVEN, text, lambda);
    }

    public final Background when (final String text, final Runnable lambda) {
        return step (WHEN, text, lambda);
    }

    public final Background then (final String text, final Runnable lambda) {
        return step (THEN, text, lambda);
    }

    public final Background and (final String text, final Runnable lambda) {
        return step (AND, text, lambda);
    }

    public final Background but (final String text, final Runnable lambda) {
        return step (BUT, text, lambda);
    }

    public final Background given (final String text) {
        return step (GIVEN, text);
    }

    public final Background when (final String text) {
        return step (WHEN, text);
    }

    public final Background then (final String text) {
        return step (THEN, text);
    }

    public final Background and (final String text) {
        return step (AND, text);
    }

    public final Background but (final String text) {
        return step (BUT, text);
    }

    @Override public String toString () {
        return
            describe () + EOL +
            steps.stream ()
                .map (a -> a + EOL)
                .reduce ("", (a, b) -> a + b) +
            parametersNarrative ();
    }

    private String parametersNarrative () {
        return examples ().isEmpty ()?
            "" :
            "\nExamples:\n" + indent (printExamples (examplesTable (), fieldsWidths ()));
    }

    private List<Integer> fieldsWidths () {
        return parameters.entrySet ()
                .stream ()
                .map (e -> {
                    int max = longest (
                        e.getValue ().stream ().map (String::valueOf).collect (toList ())
                    );
                    return max (max, e.getKey ().length ());
                })
                .collect (toList ());
    }

    private String printExamples (List<String> texts, List<Integer> widths) {
        String examples = "|";
        for (int ii = 0, jj = 0; ii < texts.size (); ii++, jj = ii % widths.size ()) {
            examples += ' ' + pad (texts.get (ii), widths.get (jj)) + ' ';
            examples += jj == widths.size () - 1 && ii < texts.size() - 1? "|\n|" : "|";
        }
        return examples;
    }

    List<String> examplesTable () {
        List<String> result = new ArrayList<> ();
        result.addAll (parameters.keySet ());
        examples ()
            .stream ()
            .map (ex -> ex.values ()
                    .stream ()
                    .map (String::valueOf)
                    .collect (toList ())
            )
            .forEach (result::addAll);

        return result;
    }
}
