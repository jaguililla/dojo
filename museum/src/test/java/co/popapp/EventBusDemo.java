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
import static co.popapp.swing.UI.*;
import static java.awt.BorderLayout.*;
import static java.awt.Color.*;
import static java.awt.event.ItemEvent.SELECTED;
import static java.lang.System.*;
import static java.text.MessageFormat.format;
import static javax.swing.BorderFactory.createEmptyBorder;
import static javax.swing.BoxLayout.PAGE_AXIS;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import co.popapp.bus.Event;
import co.popapp.bus.EventBus;
import co.popapp.bus.Handler;
import co.popapp.swing.AppFrame;


// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
abstract class ColorEvent extends Event<EventFrame> {
    Color mColor;

    public ColorEvent (EventFrame aSource) {
        super (aSource);
        mColor = BLACK;
    }
}

class EventBlue extends ColorEvent {
    public EventBlue (EventFrame aSource) {
        super (aSource);
        mColor = BLUE.darker ();
    }
}

@SuppressWarnings ("serial")
abstract class EventControl<Z extends ColorEvent> extends JPanel {
    boolean veto;
    String mName;
    final EventFrame mContainer;
    Handler<Z> handler = new Handler<Z> () {
        @Override
        protected void process (Z aEvent) {
            handle (aEvent);
            if (veto)
                aEvent.cancel ();
        }
    };

    public EventControl (String aEventName, EventFrame aContainer) {
        mName = aEventName;
        mContainer = aContainer;
        setAlignmentX (LEFT_ALIGNMENT);
        setLayout (new GridLayout (1, 0, 10, 5));

        JButton btnFire = new JButton ("Fire");
        btnFire.setToolTipText (
            "Launch a new " + mName + " that will be handled by all registered frames");
        btnFire.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed (ActionEvent aE) {
                long nano = nanoTime();
                long millis = currentTimeMillis();
                EventBusDemo.bus.fire (createEvent ());
                out.println((currentTimeMillis() - millis) + "ms " + (nanoTime() - nano) + "ns");
            }
        });

        JCheckBox cbHandle = new JCheckBox ("Handle");
        cbHandle.setToolTipText ("Switch the " + mName + " processing for this frame");
        cbHandle.addItemListener (new ItemListener() {
            @Override
            public void itemStateChanged (ItemEvent aE) {
                if (aE.getStateChange () == SELECTED)
                    EventBusDemo.bus.addHandler (eventType (), handler);
                else
                    EventBusDemo.bus.removeHandler (eventType (), handler);
            }
        });

        JCheckBox cbVeto = new JCheckBox ("Veto");
        cbVeto.setToolTipText (
            "Checking this option will cause the event processing to stop here. " +
            "The event won't be processed by any remaining handler");
        cbVeto.addItemListener (new ItemListener() {
            @Override
            public void itemStateChanged (ItemEvent aE) {
                veto = aE.getStateChange () == SELECTED;
            }
        });

        add (new JLabel (aEventName));
        add (btnFire);
        add (cbHandle);
        add (cbVeto);
    }

    public abstract Z createEvent ();
    public abstract Class<Z> eventType ();

    public void handle (ColorEvent aEvent) {
        mContainer.lbl.setForeground (aEvent.mColor);
        mContainer.lbl.setText (
            format ("<html><b>{0}</> at <b>{1,time,HH:mm:ss.SSS}</b> from <b>{2}</b></html>",
                mName, new Date (aEvent.time), aEvent.source.getTitle ()));
    }
}

@SuppressWarnings ("serial")
class EventFrame extends JInternalFrame {
    static int count = 0;

    JLabel lbl = new JLabel (" ");

    public EventFrame () {
        super ("Frame " + count++);
        getRootPane ().setBorder (createEmptyBorder(5, 5, 5, 5));

        Container cp = getContentPane ();
        cp.setLayout (new BoxLayout (cp, PAGE_AXIS));

        cp.add (new EventControl<EventBlue> ("Blue Event", this) {
            @Override public EventBlue createEvent () {
                return new EventBlue (EventFrame.this);
            }

            @Override public Class<EventBlue> eventType () {
                return EventBlue.class;
            }
        });
        cp.add (new EventControl<EventOrange> ("Orange Event", this) {
            @Override public EventOrange createEvent () {
                return new EventOrange (EventFrame.this);
            }

            @Override public Class<EventOrange> eventType () {
                return EventOrange.class;
            }
        });
        cp.add (new EventControl<EventGreen> ("Green Event", this) {
            @Override public EventGreen createEvent () {
                return new EventGreen (EventFrame.this);
            }

            @Override public Class<EventGreen> eventType () {
                return EventGreen.class;
            }
        });

        lbl.setBorder (createEmptyBorder (5, 0, 0, 0));
        lbl.setAlignmentX (LEFT_ALIGNMENT);
        cp.add (lbl);
    }
}

class EventGreen extends ColorEvent {
    public EventGreen (EventFrame aSource) {
        super (aSource);
        mColor = GREEN.darker ();
    }
}

class EventOrange extends ColorEvent {
    public EventOrange (EventFrame aSource) {
        super (aSource);
        mColor = ORANGE.darker ();
    }
}

@SuppressWarnings ("serial")
public class EventBusDemo extends AppFrame {
    static EventBus bus = new EventBus ();

    public static void main (String [] aArgs) {
        try {
            loadNimbusLaF ();
            launch (new EventBusDemo ());
        }
        catch (Exception e) {
            e.printStackTrace();
            exit (-1);
        }
    }

    public EventBusDemo () {
        super ("Event Bus Demo");
        setDefaultCloseOperation (EXIT_ON_CLOSE);

        final JDesktopPane dpane = new JDesktopPane ();
        dpane.setPreferredSize (new Dimension (800, 600));

        JButton newEventBox = new JButton ("Add Event box");
        newEventBox.setFocusable (false);
        newEventBox.setToolTipText ("Adds a new component capable of firing and receiving events");
        newEventBox.addActionListener (new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent aE) {
                EventFrame ef = new EventFrame ();
                dpane.add (ef);
                ef.pack ();
                ef.setLocation (EventFrame.count * 20, EventFrame.count * 20);
                ef.setVisible (true);
            }
        });

        JToolBar tbr = new JToolBar ();
        tbr.setFloatable (false);
        tbr.setRollover (true);
        tbr.setFocusable (false);
        tbr.add (newEventBox);

        Container cp = getContentPane ();
        cp.add (tbr, NORTH);
        cp.add (dpane, CENTER);

        pack ();
        Dimension scr = Toolkit.getDefaultToolkit ().getScreenSize ();
        setLocation ((scr.width - getWidth ()) / 2, (scr.height - getHeight ()) / 2);
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
