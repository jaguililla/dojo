/*
 * POPAPP Copyright © 2011 Juan José Aguililla. All rights reserved.
 *
 * This program comes WITHOUT ANY WARRANTY; It is free software, you can redistribute it and/or
 * modify it under the terms of the GNU General Public License, Version 3.0.
 * You may obtain a copy of the License at: http://www.gnu.org/licenses/gpl.html
 */

// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.bus;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * @author jamming
 */
public class EventBus {
    public static class SyncEventBus extends EventBus {
        /** @see co.popapp.bus.EventBus#addHandler(Class, Handler) */
        @Override public synchronized <X extends Event<?>> void addHandler (
            Class<X> aEvent, Handler<X> aHandler) {
            super.addHandler (aEvent, aHandler);
        }

        /** @see co.popapp.bus.EventBus#fire(Event, Object) */
        @Override public synchronized <Z> void fire(co.popapp.bus.Event<Z> aEvent, Z aSource) {
            super.fire (aEvent, aSource);
        }

        /** @see co.popapp.bus.EventBus#removeHandler(Class, Handler) */
        @Override public synchronized <X extends Event<?>> void removeHandler (
            Class<X> aEvent, Handler<X> aHandler) {
            super.removeHandler (aEvent, aHandler);
        }
    }

    private final Map<Class <? extends Event<?>>, Object> mmap =
        new HashMap<> ();

    public <X extends Event<?>> void addHandler (Class<X> aEvent, Handler<X> aHandler) {
        if (aEvent == null || aHandler == null)
            throw new IllegalArgumentException ();

        @SuppressWarnings ("unchecked")
        List<Handler<X>> handlers = (List<Handler<X>>)mmap.get (aEvent);
        if (handlers == null)
            mmap.put (aEvent, handlers = new ArrayList<> ());
        handlers.add (aHandler);
    }

    Map<Class<? extends Event<?>>, Object> getEventHandlers () {
        return mmap;
    }

    public void fire (Event<?> aEvent) {
        fire (aEvent, null);
    }

    public <Z> void fire (Event<Z> aEvent, Z aSource) {
        try {
            @SuppressWarnings ("unchecked")
            List<Handler<Event<Z>>> handlers =
                (List<Handler<Event<Z>>>)mmap.get (aEvent.getClass ());
            if (handlers != null)
                for (Handler<Event<Z>> h : handlers)
                    if (aSource == null || aSource == aEvent.source)
                        h.process (aEvent);
        }
        catch (CancelledException e) {
            // Stop event processing
        }
    }

    public <X extends Event<?>> void removeHandler (Class<X> aEvent, Handler<X> aHandler) {
        @SuppressWarnings ("unchecked")
        List<Handler<X>> handlers = (List<Handler<X>>)mmap.get (aEvent);
        if (handlers != null)
            handlers.remove (aHandler);
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
