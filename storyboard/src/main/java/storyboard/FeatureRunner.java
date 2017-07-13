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

import static org.junit.runner.Description.createSuiteDescription;
import static org.junit.runner.Description.createTestDescription;
import static storyboard.Result.Type.*;
import static storyboard.Result.Type.IGNORED;
import static storyboard.Result.Type.OK;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

/**
 * .
 *
 * @author jam
 */
public final class FeatureRunner extends Runner {
    private final Class<?> clazz;
    private final Feature feature;
    private final Report report;

    public FeatureRunner (final Class<?> clazz)
        throws IllegalAccessException, InstantiationException {

        this.clazz = clazz;
        feature = ((Specification)clazz.newInstance ()).define ();
        report = new TextReport (feature, new File ("build"));
    }

    @Override public Description getDescription () {
        return createSuiteDescription (feature.text, clazz);
    }

    @Override public void run (RunNotifier notifier) {
        Background background = feature.background;
        List<Map<String, Object>> examples = background.examples ();

        if (examples.isEmpty ()) {
            background.steps.stream ().forEach (step -> runStep (null, (Step)step));
            feature.scenarios.stream ()
                .forEach (scenario -> runScenario (notifier, null, scenario));
        }
        else {
            examples.forEach (example -> {
                background.steps.stream ().forEach (step -> runStep (example, (Step)step));
                feature.scenarios.stream ()
                    .forEach (scenario -> runScenario (notifier, example, scenario));
            });
        }

        report.generate ();
    }

    private void runStep (Map<String, Object> example, Step step) {
        try {
            if (step.state == State.ENABLED) {
                Consumer<Map<String, ?>> lambda = step.lambda;
                if (lambda != null)
                    lambda.accept (example);
                report.results.add (new Result (step, example, OK));
            }
            else
                report.results.add (new Result (step, example, IGNORED));
        }
        catch (AssertionError e) {
            report.results.add (new Result (step, example, FAIL));
        }
        catch (Exception e) {
            report.results.add (new Result (step, example, ERROR));
        }
    }

    private void runScenario (RunNotifier notifier, Map<String, Object> example, Scenario scenario) {
        Description description = createTestDescription (clazz, new Result (scenario, example,
            OK).toString ());
        notifier.fireTestStarted (description);

        try {
            if (scenario.state == State.ENABLED)
                scenario.examples ().forEach (stepExample ->
                    scenario.steps.stream ().forEach (step -> runStep (stepExample, (Step)step))
                );
            else
                notifier.fireTestIgnored (description);
        }
        catch (AssertionError e) {
            notifier.fireTestFailure (null); // TODO
        }

        notifier.fireTestFinished (description);
    }
}
