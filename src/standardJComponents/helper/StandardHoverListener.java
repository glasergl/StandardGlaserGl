package standardJComponents.helper;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Optional;

public class StandardHoverListener implements MouseListener {

	private final Color whileMouseEntered;
	private Optional<Color> whileMouseExited = Optional.empty();

	public StandardHoverListener(final Color whileMouseEntered) {
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
		if (whileMouseExited.isPresent()) {
			whoLostHover.setBackground(whileMouseExited.get());
		} else {
			throw new RuntimeException("Couldnt set old background color after hover");
		}

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
