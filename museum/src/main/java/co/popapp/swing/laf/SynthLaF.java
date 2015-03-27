// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.swing.laf;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import static java.awt.EventQueue.invokeLater;
import static java.lang.System.currentTimeMillis;
import static javax.swing.SwingUtilities.updateComponentTreeUI;
import static javax.swing.UIManager.setLookAndFeel;

import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.synth.SynthLookAndFeel;

// T Y P E S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 *
 * @author jamming
 */
public class SynthLaF extends SynthLookAndFeel {
    private static final long serialVersionUID = 1L;

    private static final int MONITOR_DELAY = 500;

    public static void setupSynth (String aFile)
        throws MalformedURLException, UnsupportedLookAndFeelException, ParseException, IOException {
        setupSynth (aFile, false);
    }

    public static void setupSynth (String aFile, boolean aWatch)
        throws MalformedURLException, UnsupportedLookAndFeelException, ParseException, IOException {
        setLookAndFeel(new SynthLaF(aFile, aWatch));
    }

    private final File mFile;

    public SynthLaF(String aFile) throws MalformedURLException, ParseException, IOException {
        this (aFile, false);
    }

    public SynthLaF(String aFile, boolean aWatch)
        throws MalformedURLException, ParseException, IOException {

        mFile = new File (aFile);
        load(mFile.toURI().toURL());

        // Monitor file for changes
        if (aWatch) {
            new Thread () {
                long lastChange = currentTimeMillis ();

                @Override public void run() {
                    while (!currentThread().isInterrupted()) {
                        long lastModified = mFile.lastModified();
                        if (lastModified > lastChange) {
                            lastChange = lastModified;
                            try {
                                reload ();
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        try {
                            sleep(MONITOR_DELAY);
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start ();
        }
    }

    public void reload () throws MalformedURLException, ParseException, IOException {
        load(mFile.toURI().toURL());
        invokeLater(new Runnable () {
            @Override public void run() {
                for (Window wnd : Window.getWindows()) {
                    updateComponentTreeUI(wnd);
                }
            }
        });
    }

    /**
     * TODO .
     *
     * @param args .
     * @throws java.io.IOException e.
     * @throws java.text.ParseException e.
     * @throws javax.swing.UnsupportedLookAndFeelException e.
     * @throws java.net.MalformedURLException e.
     */
    public static void main(String[] args)
        throws MalformedURLException, UnsupportedLookAndFeelException, ParseException, IOException {
        setupSynth("src/main/resources/synthskin.xml", true);

        invokeLater (new Runnable () {
            @Override public void run() {
                JFrame frm = new JFrame ();
                frm.add (new JButton ("Hello Synth"));
                frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frm.pack ();
                frm.setVisible(true);
            }
        });
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
