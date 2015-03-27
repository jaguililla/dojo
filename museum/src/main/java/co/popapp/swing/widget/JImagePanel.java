// P A C K A G E ///////////////////////////////////////////////////////////////
package co.popapp.swing.widget;

// I M P O R T /////////////////////////////////////////////////////////////////
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JPanel;

// C L A S S ///////////////////////////////////////////////////////////////////
/**
 *
 * @author jamming
 */
public class JImagePanel extends JPanel
{
    /**  */
    public static final int TILE = 0;
    public static final int SCALE = 1;
    public static final int SCALE_MAXFACTOR = 2;
    public static final int TOP_LEFT = 3;
    public static final int TOP_CENTER = 4;
    public static final int TOP_RIGHT = 5;
    public static final int MIDDLE_LEFT = 6;
    public static final int MIDDLE_CENTER = 7;
    public static final int MIDDLE_RIGHT = 8;
    public static final int BOTTOM_LEFT = 9;
    public static final int BOTTOM_CENTER = 10;
    public static final int BOTTOM_RIGHT = 11;

    private int imageX = 0;
    private int imageY = 0;
    /** Holds value of property image. */
    private Icon image = null;
    /** Holds value of property paintMethod. */
    private int paintMethod = TILE;

    // PENDIENTE: almacenar la imagen internamente como un objeto Graphics2D
    // que es más flexible y da mejor rendimiento
    // PENDIENTE: copiar todos los constructores
    public JImagePanel (int thePaintMethod, Icon theImage)
    {
        super ();
        setPaintMethod (thePaintMethod);
        setImage (theImage);
    }

    /**
     * Getter for property image.
     * @return Value of property image.
     */
    public Icon getImage ()
    {
        return this.image;
    }

    /**
     * Getter for property paintMethod.
     * @return Value of property paintMethod.
     */
    public int getPaintMethod ()
    {
        return this.paintMethod;
    }

    @Override
    public void paint (Graphics gfx)
    {
        if (image != null && (!this.isOpaque ()))
        {
            image.paintIcon (this, gfx, imageX, imageY);
        }
        super.paint (gfx);
    }

    /**
     * Setter for property image.
     * @param image New value of property image.
     */
    public void setImage (Icon image)
    {
        if (image == null)
        {
            throw new IllegalArgumentException ("Argumento para la propiedad image no valido");
        }
        this.image = image;
        this.repaint ();
    }

    /**
     * Setter for property paintMethod.
     * @param paintMethod New value of property paintMethod.
     */
    public void setPaintMethod (int paintMethod)
    {
        this.paintMethod = paintMethod;
        // PENDIENTE: ajustar las propiedades de la imagen en función del método
        // de dibujo
        switch (paintMethod)
        {
            case TILE:
                break;
            default:
                this.paintMethod = TILE;
                // PENDIENTE: lanzar excepción
                break;
        }
    }
}
// E O F ///////////////////////////////////////////////////////////////////////
