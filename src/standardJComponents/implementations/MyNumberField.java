package standardJComponents.implementations;

import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MyNumberField extends MyHintTextField {

    public MyNumberField(final String hint) {
	super(hint);
	textField.getDocument().addDocumentListener(new NumberValidator(textField.getBorder()));
    }

    public int getNumber() {
	return Integer.valueOf(getText());
    }

    /**
     * Document which paints a red border around the Document when it doesn't
     * represent a number, an empty String or a hint.
     *
     * @author Gabriel Glaser
     * @version 27.09.2021
     */
    private class NumberValidator implements DocumentListener {
	private final Border old;

	public NumberValidator(final Border old) {
	    this.old = old;
	}

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
