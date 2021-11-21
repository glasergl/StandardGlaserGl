package standard.helper;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import standard.helper.enums.StartMouseEvent;
import standard.implementations.MySimplePopUp;

/**
 * MouseListener which sets the PopUp visible when the mouse hovers over the
 * component this MouseListener is added to.
 * 
 * @author Gabriel Glaser
 * @version 7.11.2021
 */
public class PopUpOnHoverController implements MouseListener {

	private static final StartMouseEvent STANDARD_MOUSE_EVENT_TO_SHOW_POPUP = StartMouseEvent.ENTER;

	private final MySimplePopUp toControl;
	private final StartMouseEvent mouseEventToShowPopUp;

	public PopUpOnHoverController(final MySimplePopUp toControl, final StartMouseEvent mouseEventToShowPopUp) {
		super();
		this.toControl = toControl;
		this.mouseEventToShowPopUp = mouseEventToShowPopUp;
	}

	public PopUpOnHoverController(final MySimplePopUp toControl) {
		this(toControl, STANDARD_MOUSE_EVENT_TO_SHOW_POPUP);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (mouseEventToShowPopUp == StartMouseEvent.ENTER) {
			toControl.setVisible(true);
			toControl.updateLocation((JComponent) e.getComponent());
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (mouseEventToShowPopUp == StartMouseEvent.ENTER) {
			toControl.setVisible(false);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (mouseEventToShowPopUp == StartMouseEvent.CLICK) {
			toControl.setVisible(!toControl.isVisible());
			toControl.updateLocation((JComponent) e.getComponent());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (mouseEventToShowPopUp == StartMouseEvent.PRESS) {
			toControl.setVisible(!toControl.isVisible());
			toControl.updateLocation((JComponent) e.getComponent());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
