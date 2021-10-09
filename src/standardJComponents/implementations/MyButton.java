package standardJComponents.implementations;

import java.awt.Color;
import javax.swing.Icon;
import javax.swing.JButton;
import standardJComponents.helper.CursorClickController;
import standardJComponents.settings.Fonts;

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
	addMouseListener(new CursorClickController());
	setForeground(new Color(30, 30, 30));
	setFocusPainted(false);
	setFont(Fonts.standard);
    }

}
