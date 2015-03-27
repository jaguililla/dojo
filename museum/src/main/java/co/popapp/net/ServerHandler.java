/*
 * POPAPP Copyright © 2011 Juan José Aguililla. All rights reserved.
 *
 * This program comes WITHOUT ANY WARRANTY; It is free software, you can redistribute it and/or
 * modify it under the terms of the GNU General Public License, Version 3.0.
 * You may obtain a copy of the License at: http://www.gnu.org/licenses/gpl.html
 */

// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.net;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * @author jamming
 */
public abstract class ServerHandler extends Handler {
    protected final Server mServer;

    public ServerHandler (Server aServer, Connection aSocket) {
        super (aSocket);
        mServer = aServer;
    }

    public Server getServer () {
        return mServer;
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
