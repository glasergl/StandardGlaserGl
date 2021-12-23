package eventListener;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.Optional;
import eventListener.emptyImplementation.MyMouseListener;

/**
 * This MouseListener changes the background and foreground of the component
 * this is added to while the mouse entered it and changes it back if the mouse
 * exits the area of the component.
 *
 * @author Gabriel Glaser
 * @version 23.12.2021
 */
public class ColorChangerOnHover extends MyMouseListener {

    private final Color backgroundWhileMouseEntered;
    private final Color foregroundWhileMouseEntered;

    private Optional<Color> normalBackground = Optional.empty();
    private Optional<Color> normalForeground = Optional.empty();

    public ColorChangerOnHover(final Color backgroundWhileMouseEntered, final Color foregroundWhileMouseEntered) {
	super();
	this.backgroundWhileMouseEntered = backgroundWhileMouseEntered;
	this.foregroundWhileMouseEntered = foregroundWhileMouseEntered;

    }

    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
	final Component whoGotHoveredOn = mouseEvent.getComponent();
	normalBackground = Optional.of(whoGotHoveredOn.getBackground());
	normalForeground = Optional.of(whoGotHoveredOn.getForeground());
	whoGotHoveredOn.setBackground(backgroundWhileMouseEntered);
	whoGotHoveredOn.setForeground(foregroundWhileMouseEntered);
    }

    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
	final Component whoLostHover = mouseEvent.getComponent();
	whoLostHover.setBackground(normalBackground.get());
	whoLostHover.setForeground(normalForeground.get());
    }

    public ColorChangerOnHover getInstanceWithDifferentBackground(final Color newBackground) {
	return new ColorChangerOnHover(newBackground, foregroundWhileMouseEntered);
    }

    public ColorChangerOnHover getInstanceWithDifferentForeground(final Color newForeground) {
	return new ColorChangerOnHover(backgroundWhileMouseEntered, newForeground);
    }

}
