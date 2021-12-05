package standard.implementations;

import java.awt.Color;
import java.awt.Point;
import javax.swing.JComponent;
import javax.swing.JWindow;
import complex.PointingBorder;
import standard.MyFrame;
import standard.helper.enums.CelestialDirection;

/**
 * This class allows to display a component relative to an sibling.
 * 
 * @author Gabriel Glaser
 * @version 05.12.2021
 */
public class MySiblingPopUp extends JWindow {

    private static final CelestialDirection STANDARD_RELATIVE_DIRECTION_OF_POP_UP = CelestialDirection.SOUTH;
    private static final int STANDARD_X_LOCATION_OFFSET = 0;
    private static final int STANDARD_Y_LOCATION_OFFSET = -3;

    private final CelestialDirection relativeDirectionOfPopUp;
    private final int xOffset;
    private final int yOffset;

    private final JComponent toShowAsPopUp;
    private final JComponent sibling;

    private MySiblingPopUp(final JComponent toShowAsPopUp, final CelestialDirection relativeDirectionOfPopUp, final int xOffset, final int yOffset,
	    final MyFrame owner, final JComponent sibling) {
	super(owner);
	this.toShowAsPopUp = toShowAsPopUp;
	this.relativeDirectionOfPopUp = relativeDirectionOfPopUp;
	this.xOffset = xOffset;
	this.yOffset = yOffset;
	this.sibling = sibling;
	setup();
    }

    public MySiblingPopUp(final JComponent toShowAsPopUp, final MyFrame owner, final JComponent sibling) {
	this(toShowAsPopUp, STANDARD_RELATIVE_DIRECTION_OF_POP_UP, STANDARD_X_LOCATION_OFFSET, STANDARD_Y_LOCATION_OFFSET, owner, sibling);
    }

    public Point getLocationOfPopUp() {
	return getLocationRelativeToSibling();
    }

    public void updateLocation() {
	setLocation(getLocationOfPopUp());
    }

    @Override
    public void setVisible(final boolean shouldBeVisible) {
	super.setVisible(shouldBeVisible);
	updateLocation();
    }

    private void setup() {
	setBackground(new Color(0, 0, 0, 0));
	toShowAsPopUp.setBorder(new PointingBorder(relativeDirectionOfPopUp, toShowAsPopUp.getBackground(), toShowAsPopUp.getForeground()));
	setSize((int) toShowAsPopUp.getPreferredSize().getWidth(), (int) toShowAsPopUp.getPreferredSize().getHeight());
	add(toShowAsPopUp);
    }

    /**
     * @return The location relative to the sibling by considering the relative
     *         direction to sibling and the offsets.
     */
    private Point getLocationRelativeToSibling() {
	final Point locationOfSibling = sibling.getLocationOnScreen();
	final int xOfToListenForHovers = (int) locationOfSibling.getX();
	final int yOfToListenForHovers = (int) locationOfSibling.getY();
	final int widthDifference = getWidth() - sibling.getWidth();
	final int heightDifference = getHeight() - sibling.getHeight();
	int x = xOfToListenForHovers;
	int y = yOfToListenForHovers;
	if (relativeDirectionOfPopUp == CelestialDirection.NORTH) {
	    x -= widthDifference / 2;
	    y -= getHeight();
	} else if (relativeDirectionOfPopUp == CelestialDirection.WEST) {
	    x -= getWidth();
	    y -= heightDifference / 2;
	} else if (relativeDirectionOfPopUp == CelestialDirection.SOUTH) {
	    x -= widthDifference / 2;
	    y += sibling.getHeight();
	} else {
	    x += sibling.getWidth();
	    y -= heightDifference / 2;
	}
	return new Point(x + xOffset, y + yOffset);
    }

}
