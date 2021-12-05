package standard.helper.listeners;

import java.awt.event.MouseEvent;
import standard.helper.emptyListenerImplementations.MyMouseListener;
import standard.implementations.MySiblingPopUp;

/**
 * MouseListener which sets the PopUp visible when the mouse hovers over the
 * component this MouseListener is added to.
 * 
 * @author Gabriel Glaser
 * @version 7.11.2021
 */
public class PopUpDisplayerOnHover extends MyMouseListener {

    private final MySiblingPopUp toControl;

    public PopUpDisplayerOnHover(final MySiblingPopUp toControl) {
	super();
	this.toControl = toControl;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
	toControl.setVisible(true);

    }

    @Override
    public void mouseExited(MouseEvent e) {
	toControl.setVisible(false);
    }

}
