package standard.helper;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import standard.implementations.MySimplePopUp;

/**
 * MouseListener which sets the PopUp visible when the mouse hovers over the
 * component this MouseListener is added to.
 * 
 * @author Gabriel Glaser
 * @version 7.11.2021
 */
public class PopUpOnHoverController implements MouseListener {

    private final MySimplePopUp toControl;

    public PopUpOnHoverController(final MySimplePopUp toControl) {
	super();
	this.toControl = toControl;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
	toControl.setVisible(true);
	toControl.updateLocation();

    }

    @Override
    public void mouseExited(MouseEvent e) {
	toControl.setVisible(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

}
