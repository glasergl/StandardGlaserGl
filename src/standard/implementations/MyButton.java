package standard.implementations;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.Icon;
import javax.swing.JButton;

import standard.helper.CursorHoverController;
import standard.settings.Fonts;

public class MyButton extends JButton {

	public MyButton(final String text, final Icon icon) {
		super(text, icon);
		setStandardProperties();
	}

	public MyButton(final String text) {
		this(text, null);
	}

	public MyButton(final Icon icon) {
		this("", icon);
		setIcon(icon);
	}

	public MyButton() {
		this("", null);
	}

	protected void setStandardProperties() {
		addMouseListener(new CursorHoverController(new Cursor(Cursor.HAND_CURSOR)));
		setForeground(new Color(30, 30, 30));
		setFocusPainted(false);
		setFont(Fonts.standard);
	}

}
