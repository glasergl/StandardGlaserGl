package standardJComponents.helper;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import standardJComponents.implementations.MyPopUpComponent;

public class PopUpOnHoverController implements MouseListener {

    private static final CelestialDirection STANDARD_DIRECTION = CelestialDirection.SOUTH;
    private static final int STANDARD_X_OFFSET = 0;
    private static final int STANDARD_Y_OFFSET = -3;

    private final MyPopUpComponent popUpToDisplay;
    private final CelestialDirection directionOfPopUp;
    private final int xOffset;
    private final int yOffset;

    public PopUpOnHoverController(final MyPopUpComponent popUpToDisplay, final CelestialDirection directionOfPopUp, final int xOffset,
	    final int yOffset) {
	super();
	this.popUpToDisplay = popUpToDisplay;
	this.directionOfPopUp = directionOfPopUp;
	this.xOffset = xOffset;
	this.yOffset = yOffset;
    }

    public PopUpOnHoverController(final MyPopUpComponent popUpToDisplay, final int xOffset, final int yOffset) {
	this(popUpToDisplay, STANDARD_DIRECTION, xOffset, yOffset);
    }

    public PopUpOnHoverController(final MyPopUpComponent popUpToDisplay, final CelestialDirection directionOfPopUp) {
	this(popUpToDisplay, directionOfPopUp, STANDARD_X_OFFSET, STANDARD_Y_OFFSET);
    }

    public PopUpOnHoverController(final MyPopUpComponent popUpToDisplay) {
	this(popUpToDisplay, STANDARD_X_OFFSET, STANDARD_Y_OFFSET);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
	popUpToDisplay.setVisible(true);
	popUpToDisplay.setLocation(getLocationOfPopUp(e.getComponent()));
    }

    @Override
    public void mouseExited(MouseEvent e) {
	popUpToDisplay.setVisible(false);
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
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

}
