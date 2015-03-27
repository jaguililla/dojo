// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.swing.laf;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import static javax.swing.BorderFactory.createBevelBorder;
import static javax.swing.JFrame.setDefaultLookAndFeelDecorated;
import static javax.swing.UIManager.setLookAndFeel;
import static javax.swing.border.BevelBorder.RAISED;
import static javax.swing.plaf.metal.MetalLookAndFeel.setCurrentTheme;

import static co.popapp.log.Log.*;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

import co.popapp.log.Log;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO .
 * @author jamming
 */
public final class LaF {
    private static final Log log = getLog (LaF.class); // NOSONAR

    private LaF () {
        throw new IllegalStateException ("This class is not meant to be instantiated");
    }

    public static void setLaFProperties (Object... aElement) {
        if (aElement.length % 2 != 0) {
            throw new IllegalArgumentException ();
        }

        for (int ii = 0; ii < aElement.length; ii += 2) {
            setLaFProperty (aElement[ii], aElement[ii + 1]);
        }
    }

    public static void setLaFProperty (Object aKey, Object aValue) {
        Object k = UIManager.get (aKey);
        if (k == null) {
            throw new IllegalArgumentException ();
        }

        UIManager.put (aKey, aValue);
    }

    public static void setupOceanLaF() throws UnsupportedLookAndFeelException {
        UIManager.put ("RootPane.frameBorder", createBevelBorder (RAISED));
        setCurrentTheme (new OceanTheme () {
            @Override public ColorUIResource getWindowTitleBackground () {
                return new ColorUIResource (new Color (0xe2f4fd));
            }
        });
        setLookAndFeel (new MetalLookAndFeel ());
        setDefaultLookAndFeelDecorated (true);
    }

    public static void setupPlatformLaf () {
        try {
            setLookAndFeel (UIManager.getSystemLookAndFeelClassName ());
        }
        catch (Exception e) {
            log.info("Platform look and feel could not be loaded, falling back to default", e);
        }
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
