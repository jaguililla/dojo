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
import static co.popapp.log.Log.getLog;
import static co.popapp.swing.UI.loadNimbusLaF;
import static java.lang.System.exit;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import co.popapp.log.Log;
import co.popapp.net.Connection;
import co.popapp.net.Handler;
import co.popapp.net.Server;
import co.popapp.net.ServerHandler;
import co.popapp.net.ServerHandlerFactory;


// T Y P E S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * @author jam
 */
public class NetDemo {
    static final Log log = getLog (NetDemo.class);

    /**
     * TODO .
     * @throws java.io.IOException
     */
    private static void runClient () throws Exception {
        log.info ("Running the client...");

        final JFrame frm = new JFrame ("Client");
        final JTextArea txta = new JTextArea ();
        final JScrollPane scr = new JScrollPane (txta);

        final Connection c = new Connection ("localhost", 9023);

        final JTextField txt = new JTextField ();
        txt.addActionListener (new ActionListener () {
            @Override public void actionPerformed (ActionEvent aE) {
                try {
                    c.write (txt.getText ().getBytes ());
                }
                catch (IOException e) {
                    log.error ("Error sending a message", e);
                }
            }
        });

        Container cp = frm.getContentPane ();
        cp.setLayout (new BorderLayout (2, 2));
        cp.add (txt, BorderLayout.NORTH);
        cp.add (scr, BorderLayout.CENTER);

        frm.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frm.pack ();
        frm.setSize (150, 200);
        frm.setLocation (0, Toolkit.getDefaultToolkit ().getScreenSize ().height - 200);
        frm.setVisible (true);

        Thread client = new Thread (new Handler (c) {
            @Override public void dataReceived (byte[] aData) {
                txta.append (new String (aData) + "\n");
            }
        });
        client.setDaemon (true);
        client.start ();
    }

    /**
     * TODO .
     * @throws java.io.IOException
     */
    private static void runServer () throws Exception {
        log.info ("Starting the server...");
        final Server srv = new Server (9023, new ServerHandlerFactory () {
            @Override
            protected ServerHandler createHandler (Server aServer, Connection aConnection) {
                aServer.clients ().add (aConnection);
                return new ServerHandler (aServer, aConnection) {
                    @Override public void connectionClosed () {
                        log.warning ("The connection was closed and will be released");
                        getServer ().clients ().remove (getConnection ());
                        try {
                            close ();
                        }
                        catch (IOException e) {
                            log.error ("Error closing the handler", e);
                        }
                    }

                    @Override public void dataReceived (byte[] aData) {
                        getServer ().broadcast (aData);
                    }
                };
            }
        });

        final JFrame frm = new JFrame ("Server");
        final JTextField txt = new JTextField ();
        txt.addActionListener (new ActionListener () {
            @Override public void actionPerformed (ActionEvent aE) {
                srv.broadcast (txt.getText ().getBytes ());
            }
        });

        Container cp = frm.getContentPane ();
        cp.setLayout (new BorderLayout (2, 2));
        cp.add (txt, BorderLayout.NORTH);

        frm.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frm.pack ();
        frm.setSize (150, frm.getHeight ());
        frm.setLocation (0, 0);
        frm.setVisible (true);

        Thread server = new Thread (srv);
        server.setDaemon (true);
        server.start ();
    }

    /**
     * TODO .
     * @param args
     */
    public static void main (String[] args) {
        try {
            loadNimbusLaF ();

            if (args.length > 0 && args[0].equals ("server"))
                runServer ();
            else if (args.length > 0 && args[0].equals ("client"))
                runClient ();
            else {
                runServer ();
                Thread.sleep (50);
                runClient ();
                Thread.sleep (50);
                runClient ();
            }
        }
        catch (Exception e) {
            log.error ("Unhandled error", e);
            e.printStackTrace ();
            exit (-1);
        }
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
