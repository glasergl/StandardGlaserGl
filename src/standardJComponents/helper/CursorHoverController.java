package standardJComponents.helper;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CursorHoverController implements MouseListener {

	private final Cursor whileMouseEntered;
	private final Cursor whileMouseExited;

	public CursorHoverController(final Cursor whileMouseEntered, final Cursor whileMouseExited) {
		super();
		this.whileMouseEntered = whileMouseEntered;
		this.whileMouseExited = whileMouseExited;
	}

	public CursorHoverController(final Cursor whileMouseEntered) {
		this(whileMouseEntered, new Cursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	public void mouseClicked(final MouseEvent event) {
	}

	@Override
	public void mousePressed(final MouseEvent event) {
	}

	@Override
	public void mouseReleased(final MouseEvent event) {
	}

	@Override
	public void mouseEntered(final MouseEvent event) {
		final Component whereTheMouseIsOn = event.getComponent();
		whereTheMouseIsOn.setCursor(whileMouseEntered);
	}

	@Override
	public void mouseExited(final MouseEvent event) {
		final Component whichTheMouseLeft = event.getComponent();
		whichTheMouseLeft.setCursor(whileMouseExited);
	}

}
