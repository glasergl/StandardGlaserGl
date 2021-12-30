package myComponent;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import general.SwingFunctions;
import settings.Colors;
import settings.Fonts;

/**
 * Implementation of JLabel with additional constructors and default attributes.
 *
 * @author Gabriel Glaser
 * @version 30.12.2021
 */
public class MyLabel extends JLabel {

    public MyLabel(final String text, final Color background, final Color foreground, final Font fontOfText) {
	super(text);
	setOpaque(true);
	setBackground(background);
	setForeground(foreground);
	setFont(fontOfText);
    }

    public MyLabel(final String text, final Color background) {
	this(text, background, Colors.ofText(), Fonts.standard());
    }

    public MyLabel(final String text) {
	this(text, Colors.getGray(1));
    }

    public MyLabel(final Color background, final Color foreground, final Font fontOfText) {
	this("", background, foreground, fontOfText);
    }

    public MyLabel(final Color background) {
	this("", background);
    }

    public MyLabel() {
	this("");
    }

    public MyLabel(final ImageIcon icon, final int width, final int height) {
	super(SwingFunctions.scale(icon, width, height));
    }

    public MyLabel(final ImageIcon icon) {
	this(icon, icon.getIconWidth(), icon.getIconHeight());
    }

}
