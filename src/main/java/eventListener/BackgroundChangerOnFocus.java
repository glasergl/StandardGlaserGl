package eventListener;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.util.Optional;

import eventListener.emptyImplementation.MyFocusListener;

/**
 * This FocusListener changes the background of a component when it gets focused
 * and changes it back if the component lost the focus.
 *
 * @author Gabriel Glaser
 * @version 20.12.2021
 */
public class BackgroundChangerOnFocus extends MyFocusListener {

    private final Color whileFocused;
    private Optional<Color> whileNotFocused = Optional.empty();

    public BackgroundChangerOnFocus(final Color whileFocused) {
	super();
	this.whileFocused = whileFocused;
    }

    @Override
    public void focusGained(final FocusEvent focusEvent) {
	final Component whoGainedFocus = focusEvent.getComponent();
	whileNotFocused = Optional.of(whoGainedFocus.getBackground());
	whoGainedFocus.setBackground(whileFocused);
    }

    @Override
    public void focusLost(final FocusEvent focusEvent) {
	final Component whoLostFocus = focusEvent.getComponent();
	whoLostFocus.setBackground(whileNotFocused.get());
    }

}
