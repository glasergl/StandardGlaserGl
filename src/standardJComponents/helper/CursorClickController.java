package standardJComponents.helper;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CursorClickController implements MouseListener {

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
	whereTheMouseIsOn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(final MouseEvent event) {
	final Component whichTheMouseLeft = event.getComponent();
	whichTheMouseLeft.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

}
