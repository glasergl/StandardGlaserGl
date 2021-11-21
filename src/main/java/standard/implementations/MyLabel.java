package standard.implementations;

import java.awt.Color;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.JLabel;
import standard.settings.Colors;
import standard.settings.Fonts;

public class MyLabel extends JLabel {

	public MyLabel(final String text, final Color background, final Color colorOfText, final Font fontOfText) {
		super(text);
		setOpaque(true);
		setBackground(background);
		setForeground(colorOfText);
		setFont(fontOfText);
	}

	public MyLabel(final String text, final Color background) {
		this(text, background, Colors.ofText(), Fonts.standard());
	}

	public MyLabel(final Icon icon) {
		this("", Color.BLACK);
		setIcon(icon);
	}

	public MyLabel(final String text) {
		this(text, Colors.getBackground(2));
	}

	public MyLabel() {
		this("");
	}

}
