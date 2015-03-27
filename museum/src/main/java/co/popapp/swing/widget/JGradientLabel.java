package co.popapp.swing.widget;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.plaf.metal.MetalLabelUI;

import co.popapp.swing.TextUtilities;

/**
 * Etiqueta que muestra como fondo un gradiente desde un color inicial a otro final.
 * El gradiente puede ser de un punto fijo a otro fijo, o dinámico segun se cambia
 * de tamaño la etiqueta (propiedad gradientType)
 * NOTA: para hacer que mantenga la relación, en vez de puntos, las propiedades
 * pueden ser porcentajes de donde se quiere que este el principio y el final
 * del gradiente, por ejemplo beginGradientX = 50 (50%) beginGradientY = 0 (0%)
 * @author jamming
 *
 * PENDIENTE:
 *  - Se puede mejorar el rendimiento generando una imagen fija del gradiente cuando
 *    se cambia algunos de sus parametros y siendo esta imagen la que se muestra
 *    al pintar "paint" la etiqueta
 */
public class JGradientLabel extends JLabel
{
    /**
     * @author jamming
     */
    private class MetalGradientLabelUI extends MetalLabelUI
    {
        /**
         * Paint clippedText at textX, textY with background.lighter() and then
         * shifted down and to the right by one pixel with background.darker().
         */
        @Override
        protected void paintDisabledText(JLabel l, Graphics g, String s, int textX, int textY)
        {
            TextUtilities.drawOutlinedText (g, textX, textY, s, 1, Color.orange);
        }

        /**
         * Paint clippedText at textX, textY with the labels foreground color.
         */
        @Override
        protected void paintEnabledText(JLabel l, Graphics g, String s, int textX, int textY)
        {
            TextUtilities.drawOutlinedText (g, textX, textY, s, 1, Color.orange);
        }
    }

    // Constantes que especifican el valor de los puntos de inicio y de fin
    public static final int RELATIVE = 0;
    public static final int FIXED = 1;

    public static final int CUSTOM = 0;
    public static final int HORIZONTAL = 1;
    public static final int VERTICAL = 2;
    public static final int DIAGONAL_DOWN = 3;
    public static final int DIAGONAL_UP = 4;

    /** Holds value of property firstGradientColor. */
    private Color firstGradientColor = null;
    /** Holds value of property secondGradientColor. */
    private Color secondGradientColor = null;
    /** Holds value of property gradientStartPoint. */
    private Point gradientStartPoint = null;
    /** Holds value of property gradientEndPoint. */
    private Point gradientEndPoint = null;
    /** Holds value of property gradientType. */
    private int gradientType = CUSTOM;

    // Variables de uso interno
    protected GradientPaint gradient = null;
    protected int savedHeight = 0;
    protected int savedWidth = 0;

    /** Creates a new instance of JGradientLabel */
    public JGradientLabel ()
    {
        super ();
        this.setUI (new MetalGradientLabelUI ());
        firstGradientColor = getBackground ();
        secondGradientColor = getBackground ();
        gradientStartPoint = new Point (0, 0);
        gradientEndPoint = new Point (0, 0);
        savedHeight = getHeight();
        savedWidth = getWidth();
        updateGradient ();
    }

    /**
     * Getter for property firstGradientColor.
     * @return Value of property firstGradientColor.
     */
    public Color getFirstGradientColor ()
    {
        return this.firstGradientColor;
    }

    /**
     * Getter for property gradientEndPoint.
     * @return Value of property gradientEndPoint.
     */
    public Point getGradientEndPoint ()
    {
        return this.gradientEndPoint;
    }

    /**
     * Getter for property gradientStartPoint.
     * @return Value of property gradientStartPoint.
     */
    public Point getGradientStartPoint ()
    {
        return this.gradientStartPoint;
    }

    /** Getter for property gradientType.
     * @return Value of property gradientType.
     */
    public int getGradientType ()
    {
        return this.gradientType;
    }

    /**
     * Getter for property secondGradientColor.
     * @return Value of property secondGradientColor.
     */
    public Color getSecondGradientColor ()
    {
        return this.secondGradientColor;
    }

    /**
     * Insert the method's description here.
     * @param g java.awt.Graphics
     */
    @Override
    public void paint(Graphics g) {
        if (isOpaque ())
        {
            Graphics2D g2d = (Graphics2D)g;
            g2d.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // NOTA: esto se puede poner en el evento resize para mejorar el rendimiento
            if (savedHeight != getHeight() || savedWidth != getWidth())
            {
                savedHeight = getHeight();
                savedWidth = getWidth();
                updateGradient ();
            }
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
        super.paint(g);
    }

    /**
     * Setter for property firstGradientColor.
     * @param firstGradientColor New value of property firstGradientColor.
     */
    public void setFirstGradientColor (Color firstGradientColor)
    {
        if (firstGradientColor == null)
        {
            throw new IllegalArgumentException ("Argumento no valido para la propiedad firstGradientColor");
        }
        this.firstGradientColor = firstGradientColor;
        updateGradient ();
    }

    /**
     * Setter for property gradientEndPoint.
     * @param gradientEndPoint New value of property gradientEndPoint.
     */
    public void setGradientEndPoint (Point gradientEndPoint)
    {
        this.gradientEndPoint = gradientEndPoint;
        updateGradient ();
    }

    /**
     * Setter for property gradientStartPoint.
     * @param gradientStartPoint New value of property gradientStartPoint.
     */
    public void setGradientStartPoint (Point gradientStartPoint)
    {
        if (gradientStartPoint == null)
        {
            throw new IllegalArgumentException ("Argumento no valido para la propiedad gradientStartPoint");
        }
        this.gradientStartPoint = gradientStartPoint;
        updateGradient ();
    }

    /** Setter for property gradientType.
     * @param gradientType New value of property gradientType.
     */
    public void setGradientType (int gradientType)
    {
        this.gradientType = gradientType;
    }

    /**
     * Setter for property secondGradientColor.
     * @param secondGradientColor New value of property secondGradientColor.
     */
    public void setSecondGradientColor (Color secondGradientColor)
    {
        if (secondGradientColor == null)
        {
            throw new IllegalArgumentException ("Argumento no valido para la propiedad secondGradientColor");
        }
        this.secondGradientColor = secondGradientColor;
        updateGradient ();
    }

    public void updateGradient ()
    {
        switch (gradientType)
        {
            case CUSTOM:
                // No hace nada
                break;
            case HORIZONTAL:
                gradientStartPoint.setLocation (0, 0);
                gradientEndPoint.setLocation (this.getWidth (), 0);
                break;
            case VERTICAL:
                gradientStartPoint.setLocation (0, 0);
                gradientEndPoint.setLocation (0, this.getHeight ());
                break;
            case DIAGONAL_DOWN:
                gradientStartPoint.setLocation (0, 0);
                gradientEndPoint.setLocation (this.getWidth (), this.getHeight ());
                break;
            case DIAGONAL_UP:
                gradientStartPoint.setLocation (0, this.getHeight ());
                gradientEndPoint.setLocation (this.getWidth (), 0);
                break;
            default:
                // No debe pasar nunca por aquí, si se comprueban los argumentos
                // en los setter
                break;
        }
        gradient = new GradientPaint(gradientStartPoint, firstGradientColor, gradientEndPoint, secondGradientColor);
    }
}
