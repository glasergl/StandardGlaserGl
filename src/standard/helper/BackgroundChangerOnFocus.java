package standard.helper;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Optional;

/**
 * This FocusListener changes the background of a component when it gets
 * focused.
 *
 * @author Gabriel Glaser
 * @version 27.09.2021
 */
public class BackgroundChangerOnFocus implements FocusListener {

	private final Color whileFocused;
	private Optional<Color> whileNotFocused = Optional.empty();

	public BackgroundChangerOnFocus(final Color whileFocused) {
		super();
		this.whileFocused = whileFocused;
	}

	@Override
	public void focusGained(final FocusEvent event) {
		final Component whoGainedFocus = event.getComponent();
		whileNotFocused = Optional.of(whoGainedFocus.getBackground());
		whoGainedFocus.setBackground(whileFocused);
	}

	@Override
	public void focusLost(final FocusEvent event) {
		final Component whoLostFocus = event.getComponent();
		whoLostFocus.setBackground(whileNotFocused.get());
	}

}
