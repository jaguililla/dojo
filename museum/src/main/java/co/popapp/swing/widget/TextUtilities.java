// P A C K A G E ///////////////////////////////////////////////////////////////
package com.popapp.swing;

// I M P O R T /////////////////////////////////////////////////////////////////
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;

// C L A S S ///////////////////////////////////////////////////////////////////
/**
 *
 * @author jamming
 */
public class TextUtilities
{
    private static final String TRUETYPE_EXTENSION = ".ttf";

    public static void drawOutlinedText (Graphics gfx, int x, int y, String text, int outlineOffset, Color outlineColor)
    {
        // PENDIENTE: hacer que la posición en la que se dibuja sea la del borde,
        // y no la del texto dibujado dentro de los bordes
        if (gfx instanceof Graphics2D)
        {
            Graphics2D gfx2D = (Graphics2D)gfx;
            gfx2D.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            gfx2D = null;
        }
        Color initialColor = gfx.getColor ();
        gfx.setColor (outlineColor);
        gfx.drawString (text, x - outlineOffset, y - outlineOffset);
        gfx.drawString (text, x + outlineOffset, y - outlineOffset);
        gfx.drawString (text, x - outlineOffset, y + outlineOffset);
        gfx.drawString (text, x + outlineOffset, y + outlineOffset);
        gfx.setColor (initialColor);
        gfx.drawString (text, x, y);
    }

    public static void drawShadowedText (Graphics gfx, int x, int y, String text, int shadowXOffset, int shadowYOffset, Color shadowColor)
    {
        if (gfx == null || x < 0 || y < 0 || text == null || shadowColor == null)
        {
            throw new IllegalArgumentException ("");
        }
        // Esto mejor debe estar en el componente que muestre el texto
        if (gfx instanceof Graphics2D)
        {
            Graphics2D gfx2D = (Graphics2D)gfx;
            gfx2D.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            gfx2D = null;
        }
        Color initialColor = gfx.getColor ();
        gfx.setColor (shadowColor);
        gfx.drawString (text, x + shadowXOffset, y + shadowYOffset);
        gfx.setColor (initialColor);
        gfx.drawString (text, x, y);
    }

    public static void drawStripedText (Graphics gfx, int x, int y, String text, int separtion, Color stripesColor)
    {
    }

    /**
     * @deprecated
     * @param fontsDir
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     * @throws java.awt.FontFormatException
     */
    @Deprecated
    public static void loadFonts (File fontsDir) throws FileNotFoundException, IOException, FontFormatException
    {
        if (!fontsDir.isDirectory ())
        {
            throw new IllegalArgumentException ("");
        }
        File [] fonts = fontsDir.listFiles(new FilenameFilter ()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                return name.toLowerCase ().endsWith (TRUETYPE_EXTENSION);
            }
        });
        FileInputStream currentFontFile = null;
        for (int ii = 0; ii < fonts.length; ii++)
        {
            currentFontFile = new FileInputStream (fonts [ii]);
            Font.createFont (Font.TRUETYPE_FONT, currentFontFile);
            currentFontFile.close ();
        }
    }
}
// E O F ///////////////////////////////////////////////////////////////////////
