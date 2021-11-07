package standardJComponents.helper;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * This FocusListener changes the background of a component when it gets
 * focused.
 *
 * @author Gabriel Glaser
 * @version 27.09.2021
 */
public class StandardFocusListener implements FocusListener {

	private final Color whileFocused;
	private final Color whileNotFocused;

	public StandardFocusListener(final Color whileFocused, final Color whileNotFocused) {
		super();
		this.whileFocused = whileFocused;
		this.whileNotFocused = whileNotFocused;
	}

	@Override
	public void focusGained(final FocusEvent event) {
		final Component whoGainedFocus = event.getComponent();
		whoGainedFocus.setBackground(whileFocused);
	}

	@Override
	public void focusLost(final FocusEvent event) {
		final Component whoLostFocus = event.getComponent();
		whoLostFocus.setBackground(whileNotFocused);
	}

}
