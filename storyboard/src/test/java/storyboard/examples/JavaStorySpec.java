package storyboard.examples;

import static org.junit.Assert.assertTrue;
import static storyboard.Feature.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import storyboard.Feature;
import storyboard.FeatureRunner;
import storyboard.Specification;

@RunWith (FeatureRunner.class) public class JavaStorySpec implements Specification {
    private final ThreadLocal<String> fixture = new ThreadLocal<> ();

    @Override public Feature define () {
        return feature (
            "A Great Feature",

            "In order to <achieve something>",
            "As a <role>",
            "I want to <do something>", f -> {

            f.tags ("main");

            f.background (b -> {
                b.given ("a fixture", () -> fixture.set ("test"));
                b.when ("an action is done", this::when_an_action_is_done);
                b.then ("something happens");
                b.and ("other things too");

                b.parameter ("bg.precondition", true, false);
                b.parameter ("bg.strings",      "v1",    1);
                b.parameter ("bg.integers",     2,    1);
            });

            f.scenario ("A Scenario", s -> {
                s.tags ("important", "ok");

                s.given ("a <precondition>", this::given_a_$precondition);
                s.when ("an action is done", this::when_an_action_is_done);
                s.then ("something happens", () -> assertTrue (fixture.get ().equals ("test")));
                s.and ("other things too");

                s.parameter ("precondition", true, false);
                s.parameter ("strings",      "v1", "v2");
                s.parameter ("integers",     1,    2);
            });

            f.scenario ("Another Scenario", s -> {
                s.given ("a <precondition>", this::given_a_$precondition);
                s.when ("an action is done", this::when_an_action_is_done);
                s.then ("something happens", () -> assertTrue (fixture.get ().equals ("test")));
                s.and ("other things too");
            });
        });
    }

    Feature define1 () {
        return feature (
            "A Great Feature",

            "In order to <achieve something>",
            "As a <role>",
            "I want to <do something>", f -> f

            .background (b -> b
                .given ("a fixture", () -> fixture.set ("test"))
                .when ("an action is done", this::when_an_action_is_done)
                .then ("something happens")
                .and ("other things too")

                .parameter ("bg.precondition", true, false)
                .parameter ("bg.strings",      "v1",    1)
                .parameter ("bg.integers",     2,    1)
            )

            .scenario ("A Scenario", s -> s
                .tags ("important", "happy path")

                .given ("a <precondition>", this::given_a_$precondition)
                .when ("an action is done", this::when_an_action_is_done)
                .then ("something happens", () -> assertTrue (fixture.get ().equals ("test")))
                .and ("other things too")

                .parameter ("precondition", true, false)
                .parameter ("strings",      "v1", "v2")
                .parameter ("integers",     1,    2)
            )

            .scenario ("Another Scenario", s -> s
                .given ("a <precondition>", this::given_a_$precondition)
                .when ("an action is done", this::when_an_action_is_done)
                .then ("something happens", () -> assertTrue (fixture.get ().equals ("test")))
                .and ("other things too")
            )
        );
    }

    Feature define2 () {
        return feature (
            "A Great Feature",

            "In order to <achieve something>",
            "As a <role>",
            "I want to <do something>", f -> f.

            background (b -> b.
                    given ("a fixture", () -> fixture.set ("test")).
                    when ("an action is done", this::when_an_action_is_done).
                    then ("something happens").
                    and ("other things too").

                    parameter ("bg.precondition", true, false).
                    parameter ("bg.strings", "v1", 1).
                    parameter ("bg.integers", 2, 1)
            ).

            scenario ("A Scenario", s -> s.
                    tags ("important", "happy path").

                    given ("a <precondition>", this::given_a_$precondition).
                    when ("an action is done", this::when_an_action_is_done).
                    then ("something happens", () -> assertTrue (fixture.get ().equals ("test"))).
                    and ("other things too").

                    parameter ("precondition", true, false).
                    parameter ("strings", "v1", "v2").
                    parameter ("integers", 1, 2)
            )
        );
    }

    @Test public void define3 () {
        feature (
            "A Great Feature",

            "In order to <achieve something>",
            "As a <role>",
            "I want to <do something>", f -> f.

            background (b -> b.
                    given ("a fixture", () -> fixture.set ("test")).
                    when ("an action is done", this::when_an_action_is_done).
                    then ("something happens").
                    and ("other things too").

                    parameter ("bg.precondition", true, false).
                    parameter ("bg.strings", "v1", 1).
                    parameter ("bg.integers", 2, 1)
            ).

            scenario ("A Scenario", s -> s.
                    tags ("important", "happy path").

                    given ("a <precondition>", this::given_a_$precondition).
                    when ("an action is done", this::when_an_action_is_done).
                    then ("something happens", () -> assertTrue (fixture.get ().equals ("test"))).
                    and ("other things too").

                    parameter ("precondition", true, false).
                    parameter ("strings", "v1", "v2").
                    parameter ("integers", 1, 2)
            )
        );
    }

    private void when_an_action_is_done () {}
    private void given_a_$precondition () {}
}
