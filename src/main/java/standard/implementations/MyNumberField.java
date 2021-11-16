package standard.implementations;

import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * TextField which changes the border when it doesnt contain a number or is
 * empty.
 * 
 * @author Gabriel Glaser
 * @version 7.11.2021
 */
public class MyNumberField extends MyHintTextField {

    public MyNumberField(final String hint, final int initialValue) {
	super(hint);
	textField.getDocument().addDocumentListener(new NumberValidator());
	setText(String.valueOf(initialValue));
    }

    public MyNumberField(final String hint) {
	this(hint, -1);
	setText("");
    }

    public MyNumberField() {
	this("");
    }

    public int getNumber() {
	return Integer.valueOf(getText());
    }

    public void setNumber(int day) {
	setText(String.valueOf(day));
    }

    /**
     * DocumentListener which paints a red border around the Document when it
     * doesn't represent a number or is empty.
     *
     * @author Gabriel Glaser
     * @version 7.11.2021
     */
    private class NumberValidator implements DocumentListener {
	private final Border old = getBorder();

	@Override
	public void insertUpdate(DocumentEvent e) {
	    update();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
	    update();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	    update();
	}

	private void update() {
	    final String currentContent = textField.getText();
	    if (!currentContent.matches("[0-9]*")) {
		setBorder(new LineBorder(Color.RED, 2));
	    } else {
		setBorder(old);
	    }
	}

    }
}
