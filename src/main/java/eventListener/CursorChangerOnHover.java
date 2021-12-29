package eventListener;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.util.Optional;
import eventListener.emptyImplementation.MyMouseListener;

/**
 * This MouseListener changes the Cursor while the mouse entered the area of the
 * component this MouseListener is added to and changes it back if the mouse
 * exits the area.
 * 
 * @author Gabriel Glaser
 * @version 20.12.2021
 */
public class CursorChangerOnHover implements MyMouseListener {

    private final Cursor whileMouseEntered;
    private Optional<Cursor> whileMouseExited = Optional.empty();

    public CursorChangerOnHover(final Cursor whileMouseEntered) {
	super();
	this.whileMouseEntered = whileMouseEntered;
    }

    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
	final Component whereTheMouseIsOn = mouseEvent.getComponent();
	whileMouseExited = Optional.of(whereTheMouseIsOn.getCursor());
	whereTheMouseIsOn.setCursor(whileMouseEntered);
    }

    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
	final Component whichTheMouseLeft = mouseEvent.getComponent();
	whichTheMouseLeft.setCursor(whileMouseExited.get());
    }

}
