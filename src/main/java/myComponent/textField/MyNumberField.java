package myComponent.textField;

import java.awt.Color;
import java.util.Optional;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import eventListener.emptyImplementation.MyDocumentListener;

/**
 * TextField which changes the border to a red border when it doesn't contain a
 * number and isn't empty.
 * 
 * @author Gabriel Glaser
 * @version 20.12.2021
 */
public class MyNumberField extends MyTextField {
    // TODO prevent reading non numbers.
    // TODO fix border bug
    public MyNumberField(final int initialValue) {
	super(String.valueOf(initialValue));
	setup();
    }

    public MyNumberField() {
	super();
	setup();
    }

    public int getNumber() {
	return Integer.valueOf(getText());
    }

    public void setNumber(int number) {
	setText(String.valueOf(number));
    }

    private void setup() {
	addDocumentListener(new MyDocumentListener() {
	    private Optional<Border> old = Optional.empty();

	    @Override
	    public void update() {
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
	});
    }

}
