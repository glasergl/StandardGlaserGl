package standard.implementations;

import java.awt.Color;
import java.util.Optional;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * TextField which changes the border when it doesn't contain a number or is
 * empty.
 * 
 * @author Gabriel Glaser
 * @version 17.11.2021
 */
public class MyNumberField extends MyTextField {

    public MyNumberField(final String hint, final int initialValue) {
	super(hint, String.valueOf(initialValue));
	addDocumentListener(new NumberValidator());
    }

    public MyNumberField(final String hint) {
	super(hint, "");
	addDocumentListener(new NumberValidator());
    }

    public int getNumber() {
	return Integer.valueOf(getText());
    }

    public void setNumber(int number) {
	setText(String.valueOf(number));
    }

    /**
     * DocumentListener which paints a red border around the Document when it
     * doesn't represent a number or is empty.
     *
     * @author Gabriel Glaser
     * @version 7.11.2021
     */
    private class NumberValidator implements DocumentListener {
	private Optional<Border> old = Optional.empty();

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
		old = Optional.of(getBorder());
		setBorder(new LineBorder(Color.RED, 2));
	    } else {
		if (old.isPresent()) {
		    setBorder(old.get());
		    old = Optional.empty();
		}
	    }
	}

    }
}
