package standard.implementations;

import java.awt.Cursor;
import javax.swing.JCheckBox;

import standard.helper.listeners.CursorChangerOnHover;
import standard.settings.Colors;
import standard.settings.Fonts;

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
