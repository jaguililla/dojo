package co.popapp.swing;

import static co.popapp.swing.BoxPanel.verticalBoxPanel;
import static java.awt.RenderingHints.KEY_TEXT_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_ON;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

import java.awt.*;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import co.popapp.swing.laf.LaF;

class LightBorder extends LineBorder {
    private static final long serialVersionUID = 1L;

    public static LightBorder get (Color aColor, int aSideLenght) {
        return new LightBorder (aColor, aSideLenght, false);
    }

    public LightBorder (Color aColor, int aThickness, boolean aRounded) {
        super (aColor, aThickness, aRounded);
    }
}

/**
 * TODO .
 * @author jamming
 */
public class SwitchLabel extends JLabel {
    private static final long serialVersionUID = 1L;

    private static final int ICON_TEXT_GAP = 20;
    private static final int ICON_SIDE = 16;
    private static final int BORDER_WIDTH = 8;

    // Shared switch properties
    private static Color sOnColor = Color.GREEN, sOffColor = Color.RED;
    private static int sBorderWidth = BORDER_WIDTH, sIconSide = ICON_SIDE;

    private static void switchTest () {
        ApplicationFrame appFrm = new ApplicationFrame ("Switch test", DISPOSE_ON_CLOSE);
        final SwitchLabel def = new SwitchLabel ("Default");
        final SwitchLabel ico = new SwitchLabel ("Icon").showIcon (true);
        final SwitchLabel bor = new SwitchLabel ("Border").showBorder (true);
        final SwitchLabel bg = new SwitchLabel ("Background").lightBackground (true);
        final SwitchLabel fg = new SwitchLabel ("Text").lightText (true);
        final SwitchLabel sha = new SwitchLabel ("Shade").shadeText (true);
        final JButton btn = new JButton ("Change");
        btn.addActionListener (aE -> {
            def.setOn (!def.isOn ());
            ico.setOn (!ico.isOn ());
            bor.setOn (!bor.isOn ());
            bg.setOn (!bg.isOn ());
            fg.setOn (!fg.isOn ());
            sha.setOn (!sha.isOn ());
        });
        appFrm.setContentPane (verticalBoxPanel ( def, ico, bor, bg, fg, sha, btn ) );
        appFrm.open ();
    }

    public static void main (String[] args) {
        Font defaultFont = new Font("Arial", Font.BOLD, 20);
        LaF.setLaFProperties (
            "Label.font", defaultFont,
            "Button.font", defaultFont,
            "TextField.font", defaultFont
        );
        EventQueue.invokeLater (SwitchLabel::switchTest);
    }

    private boolean mShowIcon, mShowBorder, mLightBackground, mLightText, mShadeText;
    private boolean mOn;
    private Color mShade, mFore, mBack;

    private Color shade (Color aColor) {
        return new Color (aColor.getRGB ()).brighter ().brighter ().brighter ();
    }

    public SwitchLabel () {
        updateSwitch ();
        setIconTextGap (ICON_TEXT_GAP); // TODO
    }

    public SwitchLabel (boolean aOn) {
        setOn (aOn);
        updateSwitch ();
        setIconTextGap (ICON_TEXT_GAP); // TODO
    }

    public SwitchLabel (String aText) {
        super (aText);
        updateSwitch ();
        setIconTextGap (ICON_TEXT_GAP); // TODO
    }

    public SwitchLabel (boolean aOn, String aText) {
        super (aText);
        setOn (aOn);
        updateSwitch ();
        setIconTextGap (ICON_TEXT_GAP); // TODO
    }

    public boolean isShowIcon () {
        return mShowIcon;
    }

    public void setShowIcon (boolean aShowIcon) {
        if (mShowIcon != aShowIcon) {
            mShowIcon = aShowIcon;
            Color c = mOn? sOnColor : sOffColor;
            setIcon (mShowIcon? LightIcon.get (c, sIconSide) : null);
        }
    }

    public boolean isShowBorder () {
        return mShowBorder;
    }

    public void setShowBorder (boolean aShowBorder) {
        if (mShowBorder != aShowBorder) {
            mShowBorder = aShowBorder;
            Color c = mOn? sOnColor : sOffColor;
            setBorder (mShowBorder? LightBorder.get (c, sBorderWidth) : null);
        }
    }

    public boolean isLightBackground () {
        return mLightBackground;
    }

    public void setLightBackground (boolean aLightBackground) {
        if (mLightBackground != aLightBackground) {
            mLightBackground = aLightBackground;
            if (mLightBackground && mBack == null) {
                mBack = getBackground ();
            }
            Color c = mOn? sOnColor : sOffColor;
            setBackground (mLightBackground? c : mBack);
            setOpaque (mLightBackground);
        }
    }

    public boolean isLightText () {
        return mLightText;
    }

    public void setLightText (boolean aLightText) {
        if (mLightText != aLightText) {
            mLightText = aLightText;
            Color c = mOn? sOnColor : sOffColor;
            setForeground (mLightText? c : mFore);
        }
    }

    public boolean isShadeText () {
        return mShadeText;
    }

    public void setShadeText (boolean aShadeText) {
        if (mShadeText != aShadeText) {
            mShadeText = aShadeText;
            if (mShadeText && mFore == null) {
                mFore = getForeground ();
                mShade = shade (mFore);
            }
            Color c = mOn? mFore : mShade;
            setForeground (mShadeText? c : mFore);
        }
    }

    public boolean isOn () {
        return mOn;
    }

    public final void setOn (boolean aOn) {
        if (aOn != mOn) {
            mOn = aOn;
            updateSwitch ();
        }
    }

    private void updateSwitch () {
        Color c = mOn? sOnColor : sOffColor;
        if (mShowIcon) {
            setIcon (LightIcon.get (c, sIconSide));
        }
        if (mShowBorder) {
            setBorder (LightBorder.get (c, sBorderWidth));
        }
        if (mLightBackground) {
            setBackground (c);
        }
        if (mLightText) {
            setForeground (c);
        }
        if (mShadeText) {
            setForeground (mOn? mFore : mShade);
        }
    }

    public SwitchLabel showIcon (boolean aShowIcon) {
        setShowIcon (aShowIcon);
        return this;
    }

    public SwitchLabel showBorder (boolean aShowBorder) {
        setShowBorder (aShowBorder);
        return this;
    }

    public SwitchLabel lightBackground (boolean aLightBackground) {
        setLightBackground (aLightBackground);
        return this;
    }

    public SwitchLabel lightText (boolean aLightText) {
        setLightText (aLightText);
        return this;
    }

    public SwitchLabel shadeText (boolean aShadeText) {
        setShadeText (aShadeText);
        return this;
    }

    public SwitchLabel horizontalAlignment (int aPosition) {
        setHorizontalAlignment (aPosition);
        return this;
    }

    public SwitchLabel horizontalTextPosition (int aTextPosition) {
        setHorizontalTextPosition (aTextPosition);
        return this;
    }

    public SwitchLabel font (Font aFont) {
        setFont (aFont);
        return this;
    }

    @Override protected void paintComponent (Graphics aG) {
        // Hack to display the icon aligned to the right
        if (mShowIcon && getHorizontalTextPosition () == LEFT) {
            Graphics2D g2d = (Graphics2D)aG;
            g2d.setRenderingHint (KEY_TEXT_ANTIALIASING, VALUE_TEXT_ANTIALIAS_ON);
            FontMetrics fm = g2d.getFontMetrics ();
            int mid = getHeight () / 2;
//            Rectangle2D stringBounds = fm.getStringBounds (getText(), g2d);
            g2d.drawString (getText (), (int)(getWidth () * 0.2), mid + (fm.getAscent () / 2));
            Icon icon = getIcon ();
            icon.paintIcon (this, g2d, getWidth()- icon.getIconWidth (), mid - (icon.getIconHeight () / 2));
        }
        else {
            super.paintComponent (aG);
        }
    }
}
