package standardJComponents.implementations;

import javax.swing.JTextArea;
import standardJComponents.helper.StandardFocusListener;
import standardJComponents.settings.Colors;
import standardJComponents.settings.Fonts;

public class MyTextArea extends JTextArea {

	public MyTextArea(final String hint, final String initialText, final int rows, final int columns) {
		super(initialText, rows, columns);
		setStandardProperties();
	}

	private void setStandardProperties() {
		setForeground(Colors.ofText());
		setFont(Fonts.standard);
		setLineWrap(true);
	}

	public MyTextArea(final String hint, final String initialText) {
		this(hint, initialText, 0, 0);
	}

	public MyTextArea(final String hint, final int rows, final int columns) {
		this(hint, "", rows, columns);
	}

	public MyTextArea(int rows, int columns) {
		this("", rows, columns);
	}

	public MyTextArea(final String hint) {
		this(hint, "");
	}

	public MyTextArea() {
		this("");
	}

}
