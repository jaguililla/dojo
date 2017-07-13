package storyboard.examples

import org.junit.runner.RunWith
import storyboard.Feature
import storyboard.FeatureRunner
import storyboard.Specification

import static org.junit.Assert.assertTrue
import static Feature.feature

@RunWith (FeatureRunner.class) class GroovyStorySpec implements Specification {

    @Override Feature define () {
        String fixture

        return feature (
            "A Great Feature (in Groovy)",

            """In order to <achieve something>
            As a <role>
            I want to <do something>""") { f ->

            f.tags "main"

            f.background {
                it.given "a fixture", { fixture = "test" }
                it.when "an action is done", { when_an_action_is_done () }
                it.then "something happens"
                it.and "other things too"

                it.parameter "bg.precondition", true, false
                it.parameter "bg.strings",      "v1", "v2"
                it.parameter "bg.integers",     1,    2
            }

            f.scenario "A Scenario", {
                it.tags "important", "happy_path"

                it.given "a <precondition>", { given_a_$precondition () }
                it.when "an action is done", this.&when_an_action_is_done
                it.then "something happens", { assertTrue (true) }
                it.and "other things too"

                it.parameter "precondition", true, false
                it.parameter "strings",      "v1", "v2"
                it.parameter "integers",     1,    2
            }
        }
    }

    Feature define1 () {
        String fixture

        return feature (
            "A Great Feature",

            """In order to <achieve something>
            As a <role>
            I want to <do something>""") { f -> f.with {

            tags "main"

            background { it.with {
                given "a fixture", { fixture = "test" }
                when "an action is done", { when_an_action_is_done () }
                then "something happens"
                and "other things too"

                parameter "bg.precondition", true, false
                parameter "bg.strings",      "v1", "v2"
                parameter "bg.integers",     1,    2
            }}

            scenario "A Scenario", { it.with {
                tags "important", "happy path"

                given "a <precondition>", { given_a_$precondition () }
                when "an action is done", this.&when_an_action_is_done
                then "something happens", { assertTrue (true) }
                and "other things too"

                parameter "precondition", true, false
                parameter "strings",      "v1", "v2"
                parameter "integers",     1,    2
            }}
        }}
    }

    void when_an_action_is_done () {}
    void given_a_$precondition () {}
}
