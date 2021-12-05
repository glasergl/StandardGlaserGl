package standard.helper.listeners;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.util.Optional;
import standard.helper.emptyListenerImplementations.MyFocusListener;

/**
 * This FocusListener changes the background of a component when it gets focused
 * and changes it back if the component lost the focus.
 *
 * @author Gabriel Glaser
 * @version 17.11.2021
 */
public class BackgroundChangerOnFocus extends MyFocusListener {

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
