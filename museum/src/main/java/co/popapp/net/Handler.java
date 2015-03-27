/*
 * POPAPP Copyright © 2011 Juan José Aguililla. All rights reserved.
 *
 * This program comes WITHOUT ANY WARRANTY; It is free software, you can redistribute it and/or
 * modify it under the terms of the GNU General Public License, Version 3.0.
 * You may obtain a copy of the License at: http://www.gnu.org/licenses/gpl.html
 */

// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.net;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import static co.popapp.log.Log.getLog;

import java.io.Closeable;
import java.io.IOException;

import co.popapp.Validate;
import co.popapp.log.Log;


// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * @author jamming
 */
public abstract class Handler implements Runnable, Closeable {
    private static final Log log = getLog (Handler.class);

    /** TODO . */
    protected final Connection mConnection;

    /**
     * TODO .
     * @param aSocket
     */
    public Handler (Connection aConnection) {
        Validate.notNull (aConnection, "Can't create a handler for a 'null' connection");
        mConnection = aConnection;
        log.info ("{0} handler created", mConnection.toString ());
    }

    /**
     * TODO .
     */
    protected void connectionBroken () { /* Empty implementation */ }

    /**
     * TODO .
     */
    protected void connectionClosed () { /* Empty implementation */ }

    /**
     * TODO .
     */
    protected void connectionOpened () { /* Empty implementation */ }

    /**
     * TODO .
     * @param aData
     */
    protected void dataReceived (@SuppressWarnings ("unused") byte[] aData) {
        /* Empty implementation */
    }

    /**
     * TODO .
     * @return
     */
    protected Connection getConnection () {
        return mConnection;
    }

    /**
     * TODO .
     * @throws java.io.IOException
     */
    protected void handleConnection () throws IOException {
        while (!Thread.interrupted () && !mConnection.isClosed ()) {
            byte[] read = mConnection.read ();
            if (!mConnection.isClosed ())
                dataReceived (read);
        }
    }

    /** @see java.io.Closeable#close() */
    @Override public void close () throws IOException {
        mConnection.close ();
        log.info ("{0} handler closed", mConnection.toString ());
    }

    /** @see Runnable#run() */
    @Override public final void run () {
        try {
            connectionOpened ();
            log.info ("{0} handler opened (running loop)", mConnection.toString ());
            handleConnection ();
            log.info ("{0} handler loop finished (closing handler)", mConnection.toString ());
            connectionClosed ();
        }
        catch (IOException e) {
            log.error ("Exception thrown in handler. This handler process will end", e);
            connectionBroken ();
        }
    }

    /** @see Object#toString() */
    @Override public String toString () {
        return String.format ("%1$s handler", mConnection);
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
