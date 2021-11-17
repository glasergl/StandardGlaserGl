package standard.implementations;

import java.awt.Cursor;
import javax.swing.JButton;
import standard.helper.CursorChangerOnHover;
import standard.settings.Colors;
import standard.settings.Fonts;

public class MyButton extends JButton {

    public MyButton(final String text) {
	super(text);
	setForeground(Colors.ofText());
	setFocusPainted(false);
	setFont(Fonts.standard());
	addMouseListener(new CursorChangerOnHover(new Cursor(Cursor.HAND_CURSOR)));
    }

    public MyButton() {
	this("");
    }

}
