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
import static java.lang.System.arraycopy;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

import co.popapp.Validate;
import co.popapp.log.Log;


// T Y P E S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * @author jamming
 */
public final class Connection implements Closeable {
    private static final Log log = getLog (Connection.class);

    private static final String EOL = System.getProperty("line.separator");

    private final Socket mSocket;
    private final InputStream mInput;
    private final OutputStream mOutput;

    private final byte[] mBuffer = new byte[8];

    /**
     * TODO .
     * @param aHost
     * @param aPort
     * @throws java.io.IOException
     */
    public Connection (InetAddress aHost, int aPort) throws IOException {
        this (new Socket (aHost, aPort));
    }

    /**
     * TODO .
     * @param aSocket
     * @throws java.io.IOException
     */
    public Connection (Socket aSocket) throws IOException {
        Validate.notNull (aSocket, "The connection socket can not be 'null'");
        mSocket = aSocket;
        mInput = mSocket.getInputStream ();
        mOutput = mSocket.getOutputStream ();
        log.info ("Connection opened to {0}:{1}", mSocket.getInetAddress (), mSocket.getPort ());
    }

    /**
     * TODO .
     * @param aHost
     * @param aPort
     * @throws java.io.IOException
     */
    public Connection (String aHost, int aPort) throws IOException {
        this (new Socket (aHost, aPort));
    }

    private byte[] concat (byte[] a1, byte[] a2) {
        return concat (a1, a2, a2 == null? 0 : a2.length);
    }

    private byte[] concat (byte[] a1, byte[] a2, int a2Bytes) {
        a1 = a1 == null? new byte[0] : a1;
        a2 = a2 == null? new byte[0] : a2;
        byte[] result = new byte[a1.length + a2Bytes];
        arraycopy (a1, 0, result, 0, a1.length);
        arraycopy (a2, 0, result, a1.length, a2Bytes);
        return result;
    }

    private String stripTrailingEOL (String aString) {
        return aString != null && aString.endsWith (EOL)?
            aString.substring (0, aString.length () - EOL.length ()) :
            aString;
    }

    /** @see java.io.Closeable#close() */
    @Override public void close () throws IOException {
        mSocket.close (); // Automatically closes input and output
        log.info ("Connection closed. Can not be used anymore");
    }

    /**
     * TODO .
     * @return
     */
    public boolean isClosed () {
        return mSocket.isClosed ();
    }

    /**
     * TODO .
     * @return
     */
    public boolean isConnected () {
        return mSocket.isConnected ();
    }

    /**
     * TODO .
     * @param aText
     * @return
     * @throws java.io.IOException
     */
    public String prompt (String aText) throws IOException {
        write (aText.getBytes ());
        return readString ();
    }

    /**
     * TODO .
     * @param aText
     * @return
     * @throws java.io.IOException
     */
    public String promptLine (String aText) throws IOException {
        writeLine (aText);
        return readString ();
    }

    /**
     * TODO .
     * null if connection exception or end of connection, empty if no data
     * @return
     */
    public byte[] read () throws IOException {
        byte[] result = null;
        int readCount = 0;

        try {
            while ((readCount = mInput.read (mBuffer)) == mBuffer.length)
                result = concat (result, mBuffer);

            if (readCount > 0)
                result = concat (result, mBuffer, readCount);
            else if (readCount == 0)
                result = new byte[0];
            else
                close ();
        }
        catch (SocketException e) {
            close ();
        }

        log.debug ("READ: {0}", result == null? "" : stripTrailingEOL (new String (result)));
        return result;
    }

    /**
     * TODO .
     * @return
     * @throws java.io.IOException
     */
    public String readString () throws IOException {
        byte[] bytes = read ();
        return bytes == null? "" : new String (bytes);
    }

    /** @see Object#toString() */
    @Override public String toString () {
        return String.format ("%1$s connection to %2$s:%3$s",
            isClosed ()? "Open" : "Closed", mSocket.getInetAddress (), mSocket.getPort ());
    }

    /**
     * TODO .
     * TODO Handle errors (closed, stream 'null'...)
     * @param aData
     * @throws java.io.IOException
     */
    public void write (byte[] aData) throws IOException {
        Validate.notNull (aData, "Can't write 'null' data");
        try {
            mOutput.write (aData);
            mOutput.flush ();
            log.debug ("SENT: {0}", stripTrailingEOL (new String (aData)));
        }
        catch (SocketException e) {
            close ();
        }
    }

    /**
     * TODO .
     * @param aLine
     * @throws java.io.IOException
     */
    public void writeLine (byte[] aData) throws IOException {
        write (concat (aData, EOL.getBytes ()));
    }

    /**
     * TODO .
     * @param aLine
     * @throws java.io.IOException
     */
    public void writeLine (String aLine) throws IOException {
        writeLine (aLine.getBytes ());
    }

    /**
     * TODO .
     * TODO Handle errors (closed, stream 'null'...)
     * @param aData
     * @throws java.io.IOException
     */
    public void writeString (String aData) throws IOException {
        write (aData.getBytes ());
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
