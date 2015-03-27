
// P A C K A G E ///////////////////////////////////////////////////////////////////////////////////
package co.popapp.swing;

// I M P O R T /////////////////////////////////////////////////////////////////////////////////////
import static java.awt.Font.TRUETYPE_FONT;
import static java.awt.Font.createFont;
import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.KEY_TEXT_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;
import static java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_ON;
import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import static java.lang.String.format;

import static co.popapp.log.Log.*;

import java.awt.*;
import java.awt.font.LineMetrics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.Icon;

import co.popapp.log.Log;

// T Y P E S ///////////////////////////////////////////////////////////////////////////////////////
public class FontIcon implements Icon {
    private static final Log log = getLog (FontIcon.class); // NOSONAR
    private static final boolean DEBUG = false;

    private Font mFont;
    private Color mColor;
    private char mChar;
    private int mSideLength;

    private BufferedImage mImage;

    public FontIcon (String aFontPath, char aChar, int aSideLength) {
        this (aFontPath, null, aChar, aSideLength);
    }

    public FontIcon (String aFontPath, Color aColor, char aChar, int aSideLength) {
        loadFont(aFontPath, aSideLength);

        mColor = aColor;
        mChar = aChar;
        mSideLength = aSideLength;

        render();
    }

    private void render() {
        // Draw in an image
        char[] chars = new char [] { mChar }; // Used by J2D API
        mImage = new BufferedImage(mSideLength, mSideLength, TYPE_INT_ARGB);
        Graphics2D gfx = (Graphics2D)mImage.getGraphics();
        LineMetrics lm = mFont.getLineMetrics(
            chars, 0, 1, gfx.getFontMetrics().getFontRenderContext());

        // Debugging
        if (DEBUG) {
            gfx.setColor(Color.RED);
            gfx.drawRect(0, 0, mSideLength - 1, mSideLength - 1);
            log.debug (format (
                ">>> Chars %d Ascend %f Descent %f (%d) Height %f Leading %f",
                lm.getNumChars(),
                lm.getAscent(),
                lm.getDescent(),
                (int)lm.getDescent(),
                lm.getHeight(),
                lm.getLeading()));
        }

        // Setup rendering quality
        gfx.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        gfx.setRenderingHint(KEY_TEXT_ANTIALIASING, VALUE_TEXT_ANTIALIAS_ON);

        // Fonts and colors
        gfx.setFont(mFont);
        gfx.setColor(mColor == null? Color.BLACK : mColor);

        // Calculate text size and position
        byte bl = mFont.getBaselineFor(mChar);
        int w = gfx.getFontMetrics(mFont).stringWidth(new String (chars));
        int x = (mSideLength - w) / 2;
        int y = mSideLength - (int)lm.getDescent() - bl - 1; // The last '- 1' is to 'center'

        gfx.drawChars(chars, 0, 1, x, y);
    }

    private void loadFont(String aFontPath, int aSize) {
        InputStream fontStream = null;
        try {
//            mFont = createFont(TRUETYPE_FONT, new File (aFontPath)).deriveFont((float)aSize);
            fontStream = getClass ().getResourceAsStream(aFontPath);
            mFont = createFont(TRUETYPE_FONT, fontStream).deriveFont((float)aSize);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Error loading font: " + aFontPath, e);
        }
        finally {
            if (fontStream != null) {
                try {
                    fontStream.close ();
                }
                catch (IOException e) {
                    log.error ("Error closing font stream", e);
                }
            }
        }
    }

    @Override public void paintIcon(Component aC, Graphics aG, int aX, int aY) {
        aG.drawImage(mImage, aX, aY, null);
    }

    @Override public int getIconWidth() {
        return mSideLength;
    }

    @Override public int getIconHeight() {
        return mSideLength;
    }
}
// E O F ///////////////////////////////////////////////////////////////////////////////////////////
