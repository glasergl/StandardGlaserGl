package standardJComponents.implementations;

import javax.swing.JTextArea;

import standardJComponents.helper.HintController;
import standardJComponents.helper.StandardFocusListener;
import standardJComponents.settings.Colors;
import standardJComponents.settings.Fonts;

public class MyTextArea extends JTextArea {

    public MyTextArea(final String hint, final String initialText, final int rows, final int columns) {
	super(initialText, rows, columns);
	setStandardProperties();
	addFocusListener(new StandardFocusListener(MyTextField.backgroundWhileFocused, MyTextField.background));
	addFocusListener(new HintController(hint, initialText.equals(""), this));
    }

    private void setStandardProperties() {
	setBackground(MyTextField.background);
	setForeground(Colors.ofText());
	setFont(Fonts.standard);
	setBorder(MyTextField.border);
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
