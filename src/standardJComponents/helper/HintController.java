package standardJComponents.helper;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.text.JTextComponent;
import standardJComponents.implementations.MyTextField;
import standardJComponents.settings.Colors;

/**
 * Class to control a displayed String (hint) on a JTextComponent.
 *
 * @author Gabriel Glaser
 * @version 27.09.2021
 */
public class HintController implements FocusListener {

    private final String hint;
    private final JTextComponent toControl;

    private boolean showingHint;

    public HintController(final String hint, final boolean showHintAtStart, final JTextComponent toControl) {
	super();
	this.hint = hint;
	this.showingHint = showHintAtStart;
	this.toControl = toControl;
	if (showHintAtStart) {
	    showHint();
	}
    }

    @Override
    public void focusGained(final FocusEvent event) {
	if (showingHint) {
	    hideHint();
	}
    }

    @Override
    public void focusLost(final FocusEvent event) {
	final String currentText = toControl.getText();
	if (currentText.length() == 0) {
	    showHint();
	}
    }

    public boolean showingHint() {
	return showingHint;
    }

    private void showHint() {
	toControl.setForeground(MyTextField.ofHintText);
	toControl.setText(hint);
	showingHint = true;
    }

    private void hideHint() {
	toControl.setForeground(Colors.ofText());
	toControl.setText("");
	showingHint = false;
    }
}
