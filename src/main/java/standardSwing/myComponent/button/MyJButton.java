package standardSwing.myComponent.button;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;
import standardSwing.eventListener.CursorChangerOnHover;
import standardSwing.settings.Colors;
import standardSwing.settings.Fonts;

public class MyJButton extends JButton {

    private static Color defaultColorOfText = Colors.ofText();
    private static Font defaultFontOfText = Fonts.standard();

    public MyJButton(final String displayedText) {
	super(displayedText);
	setDefaultValues();
	addMouseListener(new CursorChangerOnHover(new Cursor(Cursor.HAND_CURSOR)));
    }

    public MyJButton() {
	this("");
    }

    public void setDefaultValues() {
	setForeground(defaultColorOfText);
	setFont(defaultFontOfText);
	setFocusPainted(false);
    }

    public static Color getTextColor() {
	return defaultColorOfText;
    }

    public static Font getTextFont() {
	return defaultFontOfText;
    }

    public static void setTextColor(final Color newColorOfText) {
	MyJButton.defaultColorOfText = newColorOfText;
    }

    public static void setTextFont(final Font newFontOfText) {
	MyJButton.defaultFontOfText = newFontOfText;
    }

}
