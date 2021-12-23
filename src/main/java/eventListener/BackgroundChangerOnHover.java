package eventListener;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.Optional;
import eventListener.emptyImplementation.MyMouseListener;

/**
 * This MouseListener changes the background of the component this is added to
 * when the mouse entered the component and changes it back if the mouse exits
 * the area of the component.
 *
 * @author Gabriel Glaser
 * @version 23.12.2021
 */
public class BackgroundChangerOnHover extends MyMouseListener {

    private final Color whileMouseEntered;
    private Optional<Color> whileMouseExited = Optional.empty();

    public BackgroundChangerOnHover(final Color whileMouseEntered) {
	super();
	this.whileMouseEntered = whileMouseEntered;
    }

    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
	final Component whoGotHoveredOn = mouseEvent.getComponent();
	whileMouseExited = Optional.of(whoGotHoveredOn.getBackground());
	whoGotHoveredOn.setBackground(whileMouseEntered);
    }

    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
	final Component whoLostHover = mouseEvent.getComponent();
	whoLostHover.setBackground(whileMouseExited.get());
    }

}
