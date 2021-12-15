package standard.helper.listeners;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.util.Optional;
import standard.helper.emptyListenerImplementations.MyMouseListener;

/**
 * This MouseListener changes the Cursor while the mouse entered the area of the
 * component this MouseListener is added to and changes it back if the mouse
 * exits the area.
 * 
 * @author Gabriel Glaser
 * @version 17.11.2021
 */
public class CursorChangerOnHover extends MyMouseListener {

	private final Cursor whileMouseEntered;
	private Optional<Cursor> whileMouseExited = Optional.empty();

	public CursorChangerOnHover(final Cursor whileMouseEntered) {
		super();
		this.whileMouseEntered = whileMouseEntered;
	}

	@Override
	public void mouseEntered(final MouseEvent event) {
		final Component whereTheMouseIsOn = event.getComponent();
		whileMouseExited = Optional.of(whereTheMouseIsOn.getCursor());
		whereTheMouseIsOn.setCursor(whileMouseEntered);
	}

	@Override
	public void mouseExited(final MouseEvent event) {
		final Component whichTheMouseLeft = event.getComponent();
		whichTheMouseLeft.setCursor(whileMouseExited.get());
	}

}
