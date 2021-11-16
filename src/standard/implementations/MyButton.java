package standard.implementations;

import java.awt.Cursor;
import javax.swing.Icon;
import javax.swing.JButton;
import standard.helper.CursorChangerOnHover;
import standard.settings.Colors;
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
    }

    public MyButton() {
	this("");
    }

    protected void setStandardProperties() {
	addMouseListener(new CursorChangerOnHover(new Cursor(Cursor.HAND_CURSOR)));
	setForeground(Colors.ofText());
	setFocusPainted(false);
	setFont(Fonts.standard());
    }

}
