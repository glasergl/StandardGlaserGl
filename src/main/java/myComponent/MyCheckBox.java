package myComponent;

import java.awt.Cursor;
import javax.swing.JCheckBox;

import eventListener.CursorChangerOnHover;
import settings.Colors;
import settings.Fonts;

public class MyCheckBox extends JCheckBox {

    public MyCheckBox(final String title, final boolean shouldBeSelected) {
	super(title, shouldBeSelected);
	setForeground(Colors.ofText());
	setBackground(Colors.getGray(2));
	setFocusPainted(false);
	setFont(Fonts.standard());
	addMouseListener(new CursorChangerOnHover(new Cursor(Cursor.HAND_CURSOR)));
    }

    public MyCheckBox(final String title) {
	this(title, false);
    }

}
