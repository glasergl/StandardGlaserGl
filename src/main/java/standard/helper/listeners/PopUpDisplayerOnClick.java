package standard.helper.listeners;

import java.awt.event.MouseEvent;

import standard.helper.emptyListenerImplementations.MyMouseListener;
import standard.implementations.MySiblingPopUp;

/**
 * MouseListener which changes the visibility of the pop up on click.
 * 
 * @author Gabriel Glaser
 * @version 5.12.2021
 */
public class PopUpDisplayerOnClick extends MyMouseListener {

    private final MySiblingPopUp toControl;

    public PopUpDisplayerOnClick(final MySiblingPopUp toControl) {
	super();
	this.toControl = toControl;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	toControl.setVisible(!toControl.isVisible());

    }

}
