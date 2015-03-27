package co.popapp.bus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import co.popapp.bus.Event;
import co.popapp.bus.EventBus;
import co.popapp.bus.Handler;

public class EventBusTest {
    class StringEvent extends Event<String> {
        public StringEvent () {
            this ("");
        }

        public StringEvent (String aSource) {
            super (aSource);
        }
    }

    class IntegerEvent extends Event<Integer> {
        public IntegerEvent (Integer aSource) {
            super (aSource);
        }
    }

    class StringEventHandler extends Handler<StringEvent> {
        @Override protected void process (StringEvent aEvent) {
            System.out.println ("Handling StringEvent");
        }
    }

    private EventBus mFixture;

    @Before public void setUp () throws Exception {
        mFixture = new EventBus ();
    }

    @Test public void testAddHandler () {
        /*
         * HELPERS
         */
        StringEventHandler strHandler1 = new StringEventHandler ();
        StringEventHandler strHandler2 = new StringEventHandler ();

        /*
         * OK CASES
         */
        // Adding a handler
        mFixture.addHandler (StringEvent.class, strHandler1);
        Map<Class<? extends Event<?>>, Object> eventHandlers = mFixture.getEventHandlers ();
        List<?> stringEventsHandlers = (List<?>)eventHandlers.get (StringEvent.class);

        assertEquals (1, stringEventsHandlers.size ());
        assertSame (strHandler1, stringEventsHandlers.get (0));

        // Adding a duplicate handler
        mFixture.addHandler (StringEvent.class, strHandler1);

        // Adding another handler
        mFixture.addHandler (StringEvent.class, strHandler2);

        // Adding other handler for a different event

        // TODO Complete test (assertions)

        /*
         * ERROR CASES
         */
        try {
            mFixture.addHandler (StringEvent.class, null);
            fail ("IllegalArgumentException expected trying to add 'null' handler");
        }
        catch (IllegalArgumentException e) {
            assertTrue (e.getMessage (), e instanceof IllegalArgumentException);
        }
        try {
            mFixture.addHandler (null, strHandler1);
            fail ("IllegalArgumentException expected trying to add handler to 'null' event");
        }
        catch (IllegalArgumentException e) {
            assertTrue (e.getMessage (), e instanceof IllegalArgumentException);
        }
        try {
            mFixture.addHandler (null, null);
            fail ("IllegalArgumentException expected trying to add 'null' handler to 'null' event");
        }
        catch (IllegalArgumentException e) {
            assertTrue (e.getMessage (), e instanceof IllegalArgumentException);
        }
    }

    @Test public void testFireEventOfW () {
        // TODO
//        String sourceA = "A", sourceB = "B";

        mFixture.addHandler(StringEvent.class, new StringEventHandler());

        // Fire an event without source with a handler
        mFixture.fire(new StringEvent());

        // Fire an event without source with two handlers

        // Fire an event without source with subclasses (subevents), handling that event

        // Fire an event without source with subclasses (subevents), handling subclasses

        /*
         * ERROR CASES
         */

        // Fire null event
    }

    @Test public void testFireEventOfZZ () {
        // TODO
//        String sourceA = "A", sourceB = "B";

        mFixture.addHandler(StringEvent.class, new StringEventHandler());


        // Fire an event with a handler

        // Fire an event with two handlers

        // Fire an event with subclasses (subevents), handling that event

        // Fire an event with subclasses (subevents), handling subclasses
    }

    @Test public void testRemoveHandler () {
        // TODO
    }
}
