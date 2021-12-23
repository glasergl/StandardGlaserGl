package eventListener;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.util.Optional;
import eventListener.emptyImplementation.MyFocusListener;

/**
 * This FocusListener changes the background and foreground of the component
 * this is added to while it has focus and changes it back if the component
 * loses focus.
 *
 * @author Gabriel Glaser
 * @version 23.12.2021
 */
public class ColorChangerOnFocus extends MyFocusListener {

    private final Color backgroundWhileFocused;
    private final Color foregroundWhileFocused;

    private Optional<Color> normalBackground = Optional.empty();
    private Optional<Color> normalForeground = Optional.empty();

    public ColorChangerOnFocus(final Color backgroundWhileFocused, final Color foregroundWhileFocused) {
	super();
	this.backgroundWhileFocused = backgroundWhileFocused;
	this.foregroundWhileFocused = foregroundWhileFocused;
    }

    @Override
    public void focusGained(final FocusEvent focusEvent) {
	final Component whoGotHoveredOn = focusEvent.getComponent();
	normalBackground = Optional.of(whoGotHoveredOn.getBackground());
	normalForeground = Optional.of(whoGotHoveredOn.getForeground());
	whoGotHoveredOn.setBackground(backgroundWhileFocused);
	whoGotHoveredOn.setForeground(foregroundWhileFocused);
    }

    @Override
    public void focusLost(final FocusEvent focusEvent) {
	final Component whoLostHover = focusEvent.getComponent();
	whoLostHover.setBackground(normalBackground.get());
	whoLostHover.setForeground(normalForeground.get());
    }

    public ColorChangerOnFocus getInstanceWithDifferentBackground(final Color newBackground) {
	return new ColorChangerOnFocus(newBackground, foregroundWhileFocused);
    }

    public ColorChangerOnFocus getInstanceWithDifferentForeground(final Color newForeground) {
	return new ColorChangerOnFocus(backgroundWhileFocused, newForeground);
    }

}
