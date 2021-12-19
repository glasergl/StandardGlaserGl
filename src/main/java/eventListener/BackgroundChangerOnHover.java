package eventListener;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.Optional;

import eventListener.emptyImplementation.MyMouseListener;

/**
 * This MouseListener changes the background of the component this is added to
 * when the mouse entered the component and changes it back if the mosue exits
 * the area of the component.
 *
 * @author Gabriel Glaser
 * @version 17.11.2021
 */
public class BackgroundChangerOnHover extends MyMouseListener {

    private final Color whileMouseEntered;
    private Optional<Color> whileMouseExited = Optional.empty();

    public BackgroundChangerOnHover(final Color whileMouseEntered) {
	super();
	this.whileMouseEntered = whileMouseEntered;
    }

    @Override
    public void mouseEntered(final MouseEvent event) {
	final Component whoGotHoveredOn = event.getComponent();
	whileMouseExited = Optional.of(whoGotHoveredOn.getBackground());
	whoGotHoveredOn.setBackground(whileMouseEntered);
    }

    @Override
    public void mouseExited(final MouseEvent event) {
	final Component whoLostHover = event.getComponent();
	whoLostHover.setBackground(whileMouseExited.get());
    }

}
