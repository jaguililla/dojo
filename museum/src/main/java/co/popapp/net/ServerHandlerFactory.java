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
import java.io.IOException;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * TODO Clean the simple handler mess!!!!!
 * @author jamming
 */
public abstract class ServerHandlerFactory {
    /**
     * TODO .
     * @author jamming
     */
    public static abstract class SimpleEventHandlerFactory extends ServerHandlerFactory {
        /**
         * @see HandlerFactory#createHandler(Server, Connection)
         */
        @Override protected ServerHandler createHandler (Server aServer, Connection aConnection) {
            return new ServerHandler (aServer, aConnection) {
                @Override public void connectionClosed () {
                    SimpleEventHandlerFactory.this.connectionClosed (getConnection ());
                }

                @Override public void connectionOpened () {
                    SimpleEventHandlerFactory.this.connectionOpened (getConnection ());
                }

                @Override public void dataReceived (byte[] aData) {
                    SimpleEventHandlerFactory.this.dataReceived (getConnection (), aData);
                }
            };
        }

        /**
         * TODO .
         * @param aConnection
         */
        public void connectionClosed (Connection aConnection) { /* Empty */}

        /**
         * TODO .
         * @param aConnection
         */
        public void connectionOpened (Connection aConnection) { /* Empty */}

        /**
         * TODO .
         * @param aConnection
         * @param aData
         */
        public void dataReceived (Connection aConnection, byte[] aData) { /* Empty */}
    }

    /**
     * TODO .
     * @author jamming
     */
    public static abstract class SimpleHandlerFactory extends ServerHandlerFactory {
        /** @see HandlerFactory#createHandler(Server, Connection) */
        @Override protected ServerHandler createHandler (Server aServer, Connection aConnection) {
            return new ServerHandler (aServer, aConnection) {
                @Override public void connectionClosed () {
                    SimpleHandlerFactory.this.connectionClosed (getConnection ());
                }

                @Override public void connectionOpened () {
                    SimpleHandlerFactory.this.connectionOpened (getConnection ());
                }

                @Override public void dataReceived (byte[] aData) {
                    SimpleHandlerFactory.this.dataReceived (getConnection (), aData);
                }

                @Override public void handleConnection () throws IOException {
                    SimpleHandlerFactory.this.handleConnection (getConnection ());
                }
            };
        }

        /**
         * TODO .
         * @param aConnection
         */
        public void connectionClosed (Connection aConnection) { /* Empty */}

        /**
         * TODO .
         * @param aConnection
         */
        public void connectionOpened (Connection aConnection) { /* Empty */}

        /**
         * TODO .
         * @param aConnection
         * @param aData
         */
        public void dataReceived (Connection aConnection, byte[] aData) { /* Empty */}

        /**
         * TODO .
         * @param aConnection
         * @throws java.io.IOException
         */
        public void handleConnection (Connection aConnection) throws IOException { /* Empty */}
    }

    /**
     * TODO .
     * @param aServer
     * @param aConnection
     * @return
     */
    protected abstract ServerHandler createHandler (Server aServer, Connection aConnection);
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
