// P A C K A G E ///////////////////////////////////////////////////////////////
package co.popapp.swing.widget;

// I M P O R T /////////////////////////////////////////////////////////////////
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.Date;

import javax.swing.JComponent;

// C L A S S ///////////////////////////////////////////////////////////////////
public class JClock extends JComponent implements Runnable
{
    protected Color scanLinesColor = null;
    protected Color scanLinesShadowColor = null;
    protected byte scanLinesWidth = 0;
    protected byte scanLinesSeparation = 0;
    protected Color textShadowColor = null;
    protected byte textShadowXShift = 0;
    protected byte textShadowYShift = 0;

    public JClock ()
    {
        super ();
        // Asigna los valores por defecto
        scanLinesColor = new Color (0, 20, 0, 75);
        scanLinesShadowColor = new Color (0, 20, 0, 30);
        scanLinesWidth = (byte)1;
        scanLinesSeparation = (byte)1;
        textShadowColor = new Color (0, 100, 0, 64);
        textShadowXShift = (byte)3;
        textShadowYShift = (byte)3;
        // Arranca el Thread que actualiza la hora
        /*
        try
        {
         */
            Thread timer = new Thread (this);
            timer.setPriority(1);
            timer.start();
        /*
        }
        catch (InterruptedException exception)
        {
            exception.printStackTrace ();
        }
        catch (InvocationTargetException exception)
        {
            exception.printStackTrace ();
        }
         */
    }

    public void createBackgroundPattern (Graphics gfx)
    {
        Graphics2D backgroundPattern = (Graphics2D)gfx;
        Line2D.Float scanLine = new Line2D.Float ();
        int i = scanLinesSeparation;
        while (i < getHeight())
        {
            backgroundPattern.setColor (scanLinesColor);
            scanLine.setLine (getX(), i, getWidth(), i);
            backgroundPattern.draw (scanLine);
            i++;
            backgroundPattern.setColor (scanLinesShadowColor);
            scanLine.setLine (getX(), i, getWidth(), i);
            backgroundPattern.draw (scanLine);
            i += scanLinesSeparation + 1;
        }
    }


    public void drawText (Graphics g, int xPos, int yPos, String text)
    {
        Graphics2D gfx = (Graphics2D)g;
        // Dibuja la sombra
        gfx.setFont(new Font ("Dialog", 1, 16));
        gfx.setColor(textShadowColor);
        gfx.drawString(text, xPos + textShadowXShift, yPos + textShadowYShift);
        // Dibuja el texto
        gfx.setColor(new Color (0,100,0));
        gfx.drawString(text, xPos, yPos);
    }

    @Override
    public void paint(Graphics g)
    {
        createBackgroundPattern(g);
        drawText(g, 20, 20, (new Date()).toString());
    }

    // PENDIENTE: poner condición de salida al finalizar el bean, porque sino el
    // thread permanece ejecutandose "zombi"
    // NOTA: hacer que parpadeen los dos puntos ":"
    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        while (true)
        {
            if (System.currentTimeMillis() - lastTime > 950)
            {
                lastTime = System.currentTimeMillis();
                repaint();
            }
        }
    }

    public void setScanLinesColor (Color aColor)
    {
        scanLinesColor = aColor;
        repaint ();
    }

}
// E O F ///////////////////////////////////////////////////////////////////////
