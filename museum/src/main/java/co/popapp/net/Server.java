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
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import co.popapp.log.Log;


// T Y P E S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * @author jamming
 */
public class Server implements Closeable, Runnable {
    private static final Log log = getLog (Server.class);

    private final ServerSocket mSocket;
    private final ExecutorService mService;
    private final ServerHandlerFactory mHandlerFactory;
    private List<Connection> mClients;

    private volatile boolean mRun;

    /**
     * TODO .
     * @param aPort
     * @param aFactory
     * @throws java.io.IOException
     */
    public Server (int aPort, ServerHandlerFactory aFactory) throws IOException {
        this ("Port:" + aPort, aPort, aFactory);
    }

    /**
     * TODO .
     * @param aPort
     * @param aFactory
     * @throws java.io.IOException
     */
    public Server (final String aId, int aPort, ServerHandlerFactory aFactory) throws IOException {
        if (aId == null || aId.length () == 0)
            throw new IllegalArgumentException ("Server ID can't be empty");
        if (aPort < 0)
            throw new IllegalArgumentException ("The port can't be negative: " + aPort);
        if (aFactory == null)
            throw new IllegalArgumentException ("Handler factory can't be 'null'");

        mSocket = new ServerSocket (aPort);
        mService = Executors.newCachedThreadPool (new ThreadFactory() {
            @Override public Thread newThread (Runnable aR) {
                return new Thread (aR, aId + "-" + (mClients == null? 0 : mClients.size ()));
            }
        });
        mHandlerFactory = aFactory;
//        log.info (
//            "Server created in port {} using {}", aPort, aFactory.getClass ().getSimpleName ());
    }

    /**
     * TODO .
     * @param aData
     */
    public synchronized void broadcast (byte [] aData) {
        try {
            log.debug ("Broadcasting: {0}", new String (aData));
            for (Connection c : clients ())
                c.write (aData);
        }
        catch (IOException e) {
            log.warning ("Error broadcasting a message. Client removed");
        }
    }

    /**
     * TODO .
     * @return
     */
    public synchronized List<Connection> clients () {
        return mClients == null? mClients = new ArrayList<Connection> () : mClients;
    }

    /** @see java.io.Closeable#close() */
    @Override public synchronized void close () throws IOException {
        mRun = false;
        int ii = 0;
        for (Connection c : clients ()) {
            try {
                c.close ();
                log.debug ("Client [{0}] closed", ii++);
            }
            catch (Exception e) {
                e.printStackTrace ();
            }
        }
        mService.shutdown ();
        try {
            if (mService.awaitTermination (500, MILLISECONDS))
                mService.shutdownNow ();
        }
        catch (InterruptedException e) {
            log.warning ("Service shutdown interrupted. Forcing it");
            if (!mService.isShutdown ())
                mService.shutdownNow ();
        }
        mSocket.close ();
    }

    /**
     * TODO .
     * @return
     */
    public boolean isRunning () {
        return mRun;
    }

    /** @see Runnable#run() */
    @Override public final void run () {
        mRun = true;

        log.info ("Starting server loop");
        while (!Thread.interrupted () && mRun) {
            try {
                final Connection s = new Connection (mSocket.accept ());
                mService.execute (mHandlerFactory.createHandler (Server.this, s));
            }
            catch (SocketException e1) {
                log.warning ("Closing server");
            }
            catch (IOException e1) {
                log.error ("", e1);
            }
        }
    }

    /** @see Object#toString() */
    @Override public String toString () {
        return String.format ("Server listening in port: %1$s using '%2$s' factory",
            mSocket.getLocalPort (), mHandlerFactory.getClass ().getSimpleName ());
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
