/*
 * POPAPP Copyright © 2011 Juan José Aguililla. All rights reserved.
 *
 * This program comes WITHOUT ANY WARRANTY; It is free software, you can redistribute it and/or
 * modify it under the terms of the GNU General Public License, Version 3.0.
 * You may obtain a copy of the License at: http://www.gnu.org/licenses/gpl.html
 */

// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import java.util.Map;

import co.popapp.tuple.Pair;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * @author jamming
 * @param <S> .
 * @param <A> .
 */
public class StateMachine<S extends Enum<?>, A extends Enum<?>> {
    /** TODO Current state. */
    private S mState;
    private Map<Pair<S, A>, S> mStateActions;

    public StateMachine (S aInitialState) {
        super ();
    }

    public StateMachine<S, A> add (S aState, A aAction, S aFinalState) {
        return this;
    }

    public S perform (A aAction) {
        return mState;
    }

    public void reset () { mState = null; }

    public S state () { return mState; }

    @Override public String toString() {return super.toString (); }
}

class StateMachineExample {
    enum Status { PENDING, ENQUEUED, CONFIRMED, FAILED }
    enum Action { ACCEPT, PROCCESSED_OK, ERROR_THROWN, UPDATE }

    public static void main (String... args) {
        StateMachine<Status, Action> sm = new StateMachine<>(Status.PENDING);
        sm.add (Status.PENDING, Action.ACCEPT, Status.ENQUEUED);
        sm.perform (Action.ERROR_THROWN);
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
