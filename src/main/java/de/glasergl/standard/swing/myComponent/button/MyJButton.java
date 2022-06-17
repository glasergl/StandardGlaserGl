package de.glasergl.standard.swing.myComponent.button;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;

import de.glasergl.standard.swing.eventListener.CursorChangerOnHover;
import de.glasergl.standard.swing.settings.Colors;
import de.glasergl.standard.swing.settings.Fonts;

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
