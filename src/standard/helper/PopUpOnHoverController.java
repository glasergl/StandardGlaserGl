package standard.helper;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import standard.helper.enums.CelestialDirection;
import standard.helper.enums.StartMouseEventName;
import standard.implementations.MyPopUpComponent;

/**
 * MouseListener which sets the PopUp visible when the mouse hovers over the
 * component this MouseListener is added to.
 * 
 * @author Gabriel Glaser
 * @version 7.11.2021
 */
public class PopUpOnHoverController implements MouseListener {

    private static final CelestialDirection STANDARD_DIRECTION = CelestialDirection.SOUTH;
    private static final int STANDARD_X_OFFSET = 0;
    private static final int STANDARD_Y_OFFSET = -3;
    private static final StartMouseEventName STANDARD_MOUSE_EVENT_TO_SHOW_POPUP = StartMouseEventName.ENTER;

    private final MyPopUpComponent popUpToDisplay;
    private final CelestialDirection directionOfPopUp;
    private final int xOffset;
    private final int yOffset;
    private final StartMouseEventName mouseEventToShowPopUp;

    public PopUpOnHoverController(final MyPopUpComponent popUpToDisplay, final CelestialDirection directionOfPopUp, final int xOffset,
	    final int yOffset, final StartMouseEventName mouseEventToShowPopUp) {
	super();
	this.popUpToDisplay = popUpToDisplay;
	this.directionOfPopUp = directionOfPopUp;
	this.xOffset = xOffset;
	this.yOffset = yOffset;
	this.mouseEventToShowPopUp = mouseEventToShowPopUp;
    }

    public PopUpOnHoverController(final MyPopUpComponent popUpToDisplay, final int xOffset, final int yOffset,
	    final StartMouseEventName mouseEventToShowPopUp) {
	this(popUpToDisplay, STANDARD_DIRECTION, xOffset, yOffset, mouseEventToShowPopUp);
    }

    public PopUpOnHoverController(final MyPopUpComponent popUpToDisplay, final CelestialDirection directionOfPopUp,
	    final StartMouseEventName mouseEventToShowPopUp) {
	this(popUpToDisplay, directionOfPopUp, STANDARD_X_OFFSET, STANDARD_Y_OFFSET, mouseEventToShowPopUp);
    }

    public PopUpOnHoverController(final MyPopUpComponent popUpToDisplay) {
	this(popUpToDisplay, STANDARD_X_OFFSET, STANDARD_Y_OFFSET, STANDARD_MOUSE_EVENT_TO_SHOW_POPUP);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
	if (mouseEventToShowPopUp == StartMouseEventName.ENTER) {
	    popUpToDisplay.setVisible(true);
	    popUpToDisplay.setLocation(getLocationOfPopUp(e.getComponent()));
	}
    }

    @Override
    public void mouseExited(MouseEvent e) {
	if (mouseEventToShowPopUp == StartMouseEventName.ENTER) {
	    popUpToDisplay.setVisible(false);
	}
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	if (mouseEventToShowPopUp == StartMouseEventName.CLICK) {
	    popUpToDisplay.setVisible(true);
	    popUpToDisplay.setLocation(getLocationOfPopUp(e.getComponent()));
	}
    }

    @Override
    public void mousePressed(MouseEvent e) {
	if (mouseEventToShowPopUp == StartMouseEventName.PRESS) {
	    popUpToDisplay.setVisible(true);
	    popUpToDisplay.setLocation(getLocationOfPopUp(e.getComponent()));
	}
    }

    private Point getLocationOfPopUp(final Component toListenForHovers) {
	final Point locationOfToListenForHovers = toListenForHovers.getLocationOnScreen();
	final int xOfToListenForHovers = (int) locationOfToListenForHovers.getX();
	final int yOfToListenForHovers = (int) locationOfToListenForHovers.getY();
	final int widthDifference = popUpToDisplay.getWidth() - toListenForHovers.getWidth();
	final int heightDifference = popUpToDisplay.getHeight() - toListenForHovers.getHeight();
	int x = xOfToListenForHovers;
	int y = yOfToListenForHovers;
	if (directionOfPopUp == CelestialDirection.NORTH) {
	    x -= widthDifference / 2;
	    y -= popUpToDisplay.getHeight();
	} else if (directionOfPopUp == CelestialDirection.WEST) {
	    x -= popUpToDisplay.getWidth();
	    y -= heightDifference / 2;
	} else if (directionOfPopUp == CelestialDirection.SOUTH) {
	    x -= widthDifference / 2;
	    y += toListenForHovers.getHeight();
	} else {
	    x += toListenForHovers.getWidth();
	    y -= heightDifference / 2;
	}
	return new Point(x + xOffset, y + yOffset);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

}
