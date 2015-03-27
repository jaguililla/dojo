package co.popapp.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

/**
 * TODO .
 * TODO Create only two, one for enabled and one for disabled and share them.
 * Square icon with a color
 * @author jamming
 */
public final class LightIcon implements Icon {
    public static LightIcon get (Color aColor, int aSideLenght) {
        return new LightIcon (aColor, aSideLenght);
    }

    private final Color mColor;
    private final int mSideLenght;

    private LightIcon (Color aColor, int aSideLenght) {
        mColor = aColor;
        mSideLenght = aSideLenght;
    }

    @Override public void paintIcon (Component aC, Graphics aG, int aX, int aY) {
        aG.setColor (mColor);
        aG.fillRect (aX, aY, mSideLenght, mSideLenght);
    }

    @Override public int getIconWidth () { return mSideLenght; }

    @Override public int getIconHeight () { return mSideLenght; }
}
