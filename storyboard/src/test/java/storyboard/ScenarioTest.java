package storyboard;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;

public class ScenarioTest {
    @Test public void no_params_return_empty_examples () {
        Scenario scenario = new Scenario ("test");

        assertTrue (scenario.examples().isEmpty ());
    }

    @Test public void one_param_with_one_val_return_an_example_with_a_param () {
        Scenario scenario = new Scenario ("test");
        scenario.parameter ("param1", 1);

        List<Map<String, Object>> examples = scenario.examples ();
        assertEquals (1, examples.size ());

        Map<String, Object> example = examples.get (0);
        assertEquals (1, example.size ());
        assertEquals (1, example.get ("param1"));
    }

    @Test public void one_param_with_two_vals_return_two_examples_with_one_param () {
        Scenario scenario = new Scenario ("test");
        scenario.parameter ("param1", 1, 2);

        List<Map<String, Object>> examples = scenario.examples ();
        assertEquals (2, examples.size ());

        Map<String, Object> example = examples.get (0);
        assertEquals (1, example.size ());
        assertEquals (1, example.get ("param1"));

        example = examples.get (1);
        assertEquals (1, example.size ());
        assertEquals (2, example.get ("param1"));
    }

    @Test public void two_params_with_one_val_return_an_example_with_two_params () {
        Scenario scenario = new Scenario ("test");
        scenario.parameter ("param1", 1);
        scenario.parameter ("param2", "a");

        List<Map<String, Object>> examples = scenario.examples ();
        assertEquals (1, examples.size ());

        Map<String, Object> example = examples.get (0);
        assertEquals (2, example.size ());
        assertEquals (1, example.get ("param1"));
        assertEquals ("a", example.get ("param2"));
    }
}
