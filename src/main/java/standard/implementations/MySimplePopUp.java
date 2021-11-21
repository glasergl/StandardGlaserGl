package standard.implementations;

import java.awt.Color;
import java.awt.Point;
import java.util.Optional;

import javax.swing.JComponent;
import javax.swing.JWindow;
import javax.swing.border.Border;
import standard.SwingFunctions;
import standard.helper.enums.CelestialDirection;

/**
 * This class allows to display a component anywhere on the screen.
 * 
 * @author Gabriel Glaser
 * @version 21.11.2021
 */
public class MySimplePopUp extends JWindow {

	private static final CelestialDirection STANDARD_DIRECTION_OF_POP_UP = CelestialDirection.SOUTH;
	private static final int STANDARD_X_OFFSET = 0;
	private static final int STANDARD_Y_OFFSET = -3;

	private final CelestialDirection directionOfPopUp;
	private final int xOffset;
	private final int yOffset;
	private final JComponent toGetTheFrame;

	private JComponent toShowAsPopUp;
	private boolean addedToFrame = false;
	private Optional<JComponent> sibling = Optional.empty();

	public MySimplePopUp(final JComponent toShowAsPopUp, final CelestialDirection directionOfPopUp, final int xOffset, final int yOffset,
			final JComponent toGetTheFrame) {
		super();
		this.toShowAsPopUp = toShowAsPopUp;
		this.directionOfPopUp = directionOfPopUp;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.toGetTheFrame = toGetTheFrame;
		setBackground(new Color(0, 0, 0, 0));
		add(toShowAsPopUp);
	}

	public MySimplePopUp(final JComponent toShowAsPopUp, final JComponent toGetTheFrame) {
		this(toShowAsPopUp, STANDARD_DIRECTION_OF_POP_UP, STANDARD_X_OFFSET, STANDARD_Y_OFFSET, toGetTheFrame);
	}

	public void setPopUpComponent(final JComponent toShowAsPopUp) {
		this.toShowAsPopUp = toShowAsPopUp;
		add(toShowAsPopUp);
	}

	public void setBorderOfSubComponent(final Border toSetAroundThePopUpComponent) {
		toShowAsPopUp.setBorder(toSetAroundThePopUpComponent);
	}

	public Point getLocationOfPopUp(final JComponent sibling) {
		final Point locationOfSibling = sibling.getLocationOnScreen();
		final int xOfToListenForHovers = (int) locationOfSibling.getX();
		final int yOfToListenForHovers = (int) locationOfSibling.getY();
		final int widthDifference = getWidth() - sibling.getWidth();
		final int heightDifference = getHeight() - sibling.getHeight();
		int x = xOfToListenForHovers;
		int y = yOfToListenForHovers;
		if (directionOfPopUp == CelestialDirection.NORTH) {
			x -= widthDifference / 2;
			y -= getHeight();
		} else if (directionOfPopUp == CelestialDirection.WEST) {
			x -= getWidth();
			y -= heightDifference / 2;
		} else if (directionOfPopUp == CelestialDirection.SOUTH) {
			x -= widthDifference / 2;
			y += sibling.getHeight();
		} else {
			x += sibling.getWidth();
			y -= heightDifference / 2;
		}
		return new Point(x + xOffset, y + yOffset);
	}

	public void updateLocation(final JComponent sibling) {
		setLocation(getLocationOfPopUp(sibling));
		this.sibling = Optional.of(sibling);
	}

	public void setSibling(final JComponent sibling) {
		this.sibling = Optional.of(sibling);
	}

	public JComponent getSibling() {
		if (sibling.isEmpty()) {
			throw new RuntimeException("No Sibling of PopUp");
		} else {
			return sibling.get();
		}
	}

	public void updateLocation() {
		updateLocation(getSibling());
	}

	@Override
	public void setVisible(final boolean shouldBeVisible) {
		super.setVisible(shouldBeVisible);
		if (isVisible()) {
			if (!addedToFrame) {
				SwingFunctions.getMyFrame(toGetTheFrame).controllPopUp(this);
				addedToFrame = true;
			}
			pack();
		}
	}

}
