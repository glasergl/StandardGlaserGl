package standard.helper;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Optional;

/**
 * This MouseListener changes the background of the component this is added to
 * when the mouse entered the component and changes it back if the mosue exits
 * the area of the component.
 *
 * @author Gabriel Glaser
 * @version 17.11.2021
 */
public class BackgroundChangerOnHover implements MouseListener {

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

    @Override
    public void mouseClicked(final MouseEvent event) {
    }

    @Override
    public void mousePressed(final MouseEvent event) {
    }

    @Override
    public void mouseReleased(MouseEvent event) {
    }

}
