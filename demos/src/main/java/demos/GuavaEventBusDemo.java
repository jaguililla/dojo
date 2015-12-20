package demos;

import static demos.GuavaEventBusDemo.Handler.handler;

import java.util.function.Consumer;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * TODO .
 *
 * @author jam
 */
public class GuavaEventBusDemo {
    static class Handler<T> {
        static <E> Handler<E> handler (Consumer<E> c) {
            Handler<E> h = new Handler<> ();
            h.callback = c;
            return h;
        }

        Consumer<T> callback;
        @Subscribe public void handle (T event) {
            callback.accept (event);
        }
    }

    static class Event {
        public String data = "data";
    }

    static class OtherEvent {
        public String data = "other data";
    }

    static class Bus extends EventBus {
        public void register (Consumer<?> c) {
            register (handler (c));
        }
    }

    public static void main (String... args) {
        Bus bus = new Bus ();
        bus.register ((Event evt) -> System.out.println ("Hi " + evt.data));
        bus.register ((OtherEvent evt) -> System.out.println ("Ey... " + evt.data));
        bus.post (new Event ());
//        bus.post (new OtherEvent ());
    }
}
