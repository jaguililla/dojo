// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.pushingpixels.substance.api.DecorationAreaType.*;
import static org.pushingpixels.substance.api.SubstanceLookAndFeel.*;

import javax.swing.*;

import org.pushingpixels.substance.api.SubstanceColorScheme;
import org.pushingpixels.substance.api.SubstanceColorSchemeBundle;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.SubstanceSkin;
import org.pushingpixels.substance.api.painter.border.ClassicBorderPainter;
import org.pushingpixels.substance.api.painter.border.FlatBorderPainter;
import org.pushingpixels.substance.api.painter.decoration.ArcDecorationPainter;
import org.pushingpixels.substance.api.painter.decoration.BrushedMetalDecorationPainter;
import org.pushingpixels.substance.api.painter.fill.ClassicFillPainter;
import org.pushingpixels.substance.api.painter.highlight.ClassicHighlightPainter;
import org.pushingpixels.substance.api.shaper.ClassicButtonShaper;
import org.pushingpixels.substance.api.skin.SubstanceBusinessBlueSteelLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceCremeCoffeeLookAndFeel;
import org.pushingpixels.substance.api.trait.SubstanceTrait;

// C L A S S ///////////////////////////////////////////////////////////////////////////////////////
/**
 * TODO Fonts, cursors, icons, active highlight and default color scheme (default buttons)
 * @author JAguililla
 */
public class InsubstantialColorSkin extends SubstanceSkin {
	/**
	 * TODO .
	 */
	public static void setSkin () {
		JFrame.setDefaultLookAndFeelDecorated (true);
		JDialog.setDefaultLookAndFeelDecorated (true);

		UIManager.put (SHOW_EXTRA_WIDGETS, FALSE);
		UIManager.put (WATERMARK_VISIBLE, TRUE);
        UIManager.put (WINDOW_ROUNDED_CORNERS, FALSE);

		SubstanceLookAndFeel.setSkin (new InsubstantialColorSkin ());
	}

	public static void setBusinessLaF () {
		setSubstanceLaF (new SubstanceBusinessBlueSteelLookAndFeel());
	}

    public static void setCremeLaF () {
        setSubstanceLaF (new SubstanceCremeCoffeeLookAndFeel ());
    }

    public static void setSubstanceLaF (SubstanceLookAndFeel laf) {
        JFrame.setDefaultLookAndFeelDecorated (true);
        JDialog.setDefaultLookAndFeelDecorated (true);

        UIManager.put (SHOW_EXTRA_WIDGETS, FALSE);
        UIManager.put (WATERMARK_VISIBLE, FALSE);
        UIManager.put (WINDOW_ROUNDED_CORNERS, FALSE);

        try {
            UIManager.setLookAndFeel (laf);
        }
        catch (Exception e) {
            e.printStackTrace ();
        }
    }

	/**
	 * TODO .
	 */
	public InsubstantialColorSkin () {

		// COLORS
        ColorSchemes schemes = getColorSchemes (Class.class.getResource ("/color.skin"));

        SubstanceColorScheme activeHeader = schemes.get ("Active Header");
        SubstanceColorScheme defaultHeader = schemes.get ("Active Header");
        SubstanceColorScheme activeScheme = schemes.get ("Active");
        SubstanceColorScheme defaultScheme = schemes.get ("Default");
        SubstanceColorScheme disabledScheme = schemes.get ("Disabled");

		SubstanceColorSchemeBundle defaultSchemeBundle =
			new SubstanceColorSchemeBundle (activeScheme, defaultScheme, disabledScheme);

		registerDecorationAreaSchemeBundle (defaultSchemeBundle, NONE);
		registerDecorationAreaSchemeBundle (
			new SubstanceColorSchemeBundle (activeHeader, defaultHeader, disabledScheme),
			PRIMARY_TITLE_PANE, SECONDARY_TITLE_PANE, HEADER, FOOTER, TOOLBAR, GENERAL);

		// PAINTERS
		fillPainter = new ClassicFillPainter ();
		borderPainter = new FlatBorderPainter ();
		highlightPainter = new ClassicHighlightPainter ();
		highlightBorderPainter = new ClassicBorderPainter ();
		decorationPainter = new BrushedMetalDecorationPainter ();

        BrushedMetalDecorationPainter decoPainter =
            (BrushedMetalDecorationPainter)decorationPainter;
		decoPainter.setBaseDecorationPainter (new ArcDecorationPainter ());
		decoPainter.setTextureAlpha (0.2F);

		// OTHERS
		buttonShaper = new ClassicButtonShaper ();

//		InputStream watermarkInput = getClass ().getResourceAsStream("/watermark.jpg");
//      SubstanceImageWatermark wm = new SubstanceImageWatermark(watermarkInput);
//		wm.setKind(APP_TILE);
//		wm.setOpacity(0.1F);
//		watermark = wm;
	}

	/**
	 * @see SubstanceTrait#getDisplayName()
	 */
    @Override public String getDisplayName () { return "Insubstantial Color Skin"; }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
