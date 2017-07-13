package storyboard;

import static org.junit.Assert.*;
import static storyboard.Action.Type.*;
import static storyboard.Result.Type.OK;
import static storyboard.State.ENABLED;

import java.util.LinkedHashMap;

import org.junit.Test;

public class ActionTest {
    @Test (expected = IllegalArgumentException.class)
    public void dont_allow_action_without_type () {
        new Step (null, "text", ENABLED);
    }

    @Test (expected = IllegalArgumentException.class)
    public void dont_allow_action_without_state () {
        new Step (GIVEN, "text", null);
    }

    @Test public void allow_action_without_text () throws Exception {
        Step step = new Step (GIVEN, null, ENABLED);
        assertEquals ("Given", new Result (step, null, OK).toString ());
    }

    @Test public void action_text_unchanged_with_null_example () throws Exception {
        String givenText = "no examples with <variable>";
        Step step = new Step (GIVEN, givenText, ENABLED);
        assertEquals ("Given " + givenText, new Result (step, null, OK).toString ());
    }

    @Test public void action_text_unchanged_without_example () throws Exception {
        String givenText = "no examples with <variable>";
        Step step = new Step (GIVEN, givenText, ENABLED);
        assertEquals ("Given " + givenText, new Result (step, new LinkedHashMap<> (), OK).toString ());
    }

    @Test public void action_text_filtered_with_one_parameter () throws Exception {
        Step step = new Step (GIVEN, "an example with a <variable> <variable>", ENABLED);
        LinkedHashMap<String, Object> example = new LinkedHashMap<> ();
        example.put ("variable", "value");
        assertEquals ("Given " + "an example with a value value", new Result (step, example, OK).toString ());
    }

    @Test public void action_text_filtered_with_two_parameters () throws Exception {
        Step step = new Step (GIVEN, "an example with a <var> <param> <var>", ENABLED);
        LinkedHashMap<String, Object> example = new LinkedHashMap<> ();
        example.put ("var", "v");
        example.put ("param", "p");
        assertEquals ("Given " + "an example with a v p v", new Result (step, example, OK).toString
            ());
    }

    @Test public void narrative_is_formatted_according_to_pattern () throws Exception {
        String text = "no examples with <variable>";

        Step step = new Step (GIVEN, text, ENABLED);
        assertEquals ("Given " + text, step.toString ());

        LinkedHashMap<String, Object> example = new LinkedHashMap<> ();
        example.put ("variable", "value");
        assertEquals ("Given no examples with value", new Result (step, example, OK).toString ());
    }
}
