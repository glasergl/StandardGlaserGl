package standardJComponents.implementations;

import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class MyNumberField extends MyTextField {

    public MyNumberField(final String hint, final int initialValue) {
	super(hint, String.valueOf(initialValue));
    }

    public MyNumberField(final int initialValue) {
	this("", initialValue);
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

    @Override
    protected Document createDefaultModel() {
	return new NumberDocument(MyTextField.border);
    }

    /**
     * Document which paints a red border around the Document when it doesn't
     * represent a number, an empty String or a hint.
     *
     * @author Gabriel Glaser
     * @version 27.09.2021
     */
    private class NumberDocument extends PlainDocument {
	private final Border old;

	public NumberDocument(final Border old) {
	    this.old = old;
	}

	@Override
	public void remove(int a, int b) throws BadLocationException {
	    super.remove(a, b);
	    update();
	}

	@Override
	public void insertString(int a, String b, AttributeSet c) throws BadLocationException {
	    super.insertString(a, b, c);
	    update();
	}

	private void update() {
	    try {
		String currentContent = getText(0, getLength());
		if (!currentContent.equals(hint) && !currentContent.equals("") && !currentContent.matches("[0-9]*")) {
		    setBorder(new LineBorder(Color.RED, 2));
		} else {
		    setBorder(old);
		}
	    } catch (BadLocationException e) {
		e.printStackTrace();
	    }
	}

    }
}
