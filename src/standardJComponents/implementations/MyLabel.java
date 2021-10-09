package standardJComponents.implementations;

import java.awt.Color;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.JLabel;
import standardJComponents.settings.Colors;
import standardJComponents.settings.Fonts;

public class MyLabel extends JLabel {

    public static Color standardBackground = Colors.getBackground(2);

    public MyLabel(final String text, final Color background, final Color colorOfText, final Font fontOfText) {
	super(text);
	setOpaque(true);
	setBackground(background);
	setForeground(colorOfText);
	setFont(fontOfText);
    }

    public MyLabel(final String text, final Color background) {
	this(text, background, Colors.ofText(), Fonts.standard);
    }

    public MyLabel(final Icon icon) {
	this("", new Color(0, true));
	setIcon(icon);
    }

    public MyLabel(final String text) {
	this(text, standardBackground);
    }

    public MyLabel() {
	this("");
    }

}
