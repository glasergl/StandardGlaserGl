package myComponent.textField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public final class HintAnimationTransitioner implements ActionListener {

    private final Timer timer = new Timer(TextFieldAttributes.LENGTH_OF_STEP_MS, this);
    private final Font startFont;
    private final Font endFont;
    private final float startFontSize;
    private final float endFontSize;

    private int currentTransitionIndex = 1;

    public HintAnimationTransitioner(final boolean fromFocusedToUnfocused) {
	super();
	startFont = hintDisplay.getFont();
	if (fromFocusedToUnfocused) {
	    endFont = textField.getFont().deriveFont(TextFieldAttributes.HINT_SIZE_FACTOR * textField.getFont().getSize2D());
	} else {
	    endFont = textField.getFont();
	}
	startFontSize = startFont.getSize2D();
	endFontSize = endFont.getSize2D();
	timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (currentTransitionIndex <= TextFieldAttributes.NUMBER_OF_HINT_TRANSITION_STEPS) {
	    final float alpha = (float) currentTransitionIndex / TextFieldAttributes.NUMBER_OF_HINT_TRANSITION_STEPS;
	    final float newSize = (1 - alpha) * startFontSize + alpha * endFontSize;
	    final Font newFont = startFont.deriveFont(newSize);
	    hintDisplay.setFont(newFont);
	    currentTransitionIndex++;
	} else {
	    timer.stop();
	}
    }
}
