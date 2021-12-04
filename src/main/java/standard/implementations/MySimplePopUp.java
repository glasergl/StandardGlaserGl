package standard.implementations;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Optional;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.border.Border;
import standard.MyFrame;
import standard.helper.enums.CelestialDirection;

/**
 * This class allows to display a component relative to an sibling, MyFrame or
 * the whole screen.
 * 
 * If there is no owner given at constructor time, the disposal of this has to
 * be done manually.
 * 
 * @author Gabriel Glaser
 * @version 04.12.2021
 */
public class MySimplePopUp extends JDialog {

    private static final CelestialDirection STANDARD_RELATIVE_DIRECTION_OF_POP_UP = CelestialDirection.SOUTH;
    private static final int STANDARD_X_OFFSET_WITH_SIBLING = 0;
    private static final int STANDARD_Y_OFFSET_WITH_SIBLING = -3;
    private static final int STANDARD_X_OFFSET_WITHOUT_SIBLING = 0;
    private static final int STANDARD_Y_OFFSET_WITHOUT_SIBLING = 0;

    private final Optional<CelestialDirection> relativeDirectionOfPopUp;
    private final int xOffset;
    private final int yOffset;

    private final JComponent toShowAsPopUp;
    private final Optional<MyFrame> owner;
    private final Optional<JComponent> sibling;

    private MySimplePopUp(final JComponent toShowAsPopUp, final Optional<CelestialDirection> relativeDirectionOfPopUp, final int xOffset,
	    final int yOffset, final Optional<MyFrame> owner, final Optional<JComponent> sibling) {
	super();
	this.toShowAsPopUp = toShowAsPopUp;
	this.relativeDirectionOfPopUp = relativeDirectionOfPopUp;
	this.xOffset = xOffset;
	this.yOffset = yOffset;
	this.owner = owner;
	this.sibling = sibling;
	setup();
    }

    public MySimplePopUp(final JComponent toShowAsPopUp, final MyFrame owner, final JComponent sibling) {
	this(toShowAsPopUp, Optional.of(STANDARD_RELATIVE_DIRECTION_OF_POP_UP), STANDARD_X_OFFSET_WITH_SIBLING, STANDARD_Y_OFFSET_WITH_SIBLING,
		Optional.of(owner), Optional.of(sibling));
    }

    public MySimplePopUp(final JComponent toShowAsPopUp, final MyFrame owner) {
	this(toShowAsPopUp, Optional.empty(), STANDARD_X_OFFSET_WITHOUT_SIBLING, STANDARD_Y_OFFSET_WITHOUT_SIBLING, Optional.of(owner),
		Optional.empty());
    }

    public MySimplePopUp(final JComponent toShowAsPopUp) {
	this(toShowAsPopUp, Optional.empty(), STANDARD_X_OFFSET_WITHOUT_SIBLING, STANDARD_Y_OFFSET_WITHOUT_SIBLING, Optional.empty(),
		Optional.empty());
    }

    public void setBorderOfSubComponent(final Border toSetAroundThePopUpComponent) {
	toShowAsPopUp.setBorder(toSetAroundThePopUpComponent);
    }

    public Point getLocationOfPopUp() {
	if (sibling.isPresent()) {
	    return getLocationRelativeToSibling();
	} else {
	    return getCenter();
	}
    }

    public void updateLocation() {
	setLocation(getLocationOfPopUp());
    }

    @Override
    public void setVisible(final boolean shouldBeVisible) {
	if (shouldBeVisible) {
	    updateLocation();
	}
	if (owner.isPresent()) {
	    final MyFrame owner = this.owner.get();
	    owner.setFocusable(!shouldBeVisible);
	}
	super.setVisible(shouldBeVisible);
	if (shouldBeVisible) {
	    requestFocusInWindow();
	} else if (owner.isPresent()) {
	    final MyFrame owner = this.owner.get();
	    owner.requestFocusInWindow();
	}
    }

    private void setup() {
	setUndecorated(true);
	setBackground(new Color(0, 0, 0, 0));
	setSize((int) toShowAsPopUp.getPreferredSize().getWidth(), (int) toShowAsPopUp.getPreferredSize().getHeight());
	add(toShowAsPopUp);
	if (owner.isPresent()) {
	    final MyFrame owner = this.owner.get();
	    owner.addWindowListener(new DisposerOfThisWhenWindowIsDisposed());
	}
    }

    /**
     * @return The location relative to the sibling by considering the relative
     *         direction to sibling and the offsets.
     */
    private Point getLocationRelativeToSibling() {
	final JComponent sibling = this.sibling.get();
	final CelestialDirection relativeDirectionOfPopUp = this.relativeDirectionOfPopUp.get();
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

    /**
     * @return The center of the owner or if there is now owner, the center of the
     *         whole screen.
     */
    private Point getCenter() {
	final int centerX;
	final int centerY;
	if (owner.isPresent()) {
	    final MyFrame owner = this.owner.get();
	    final Point locationOnScreen = owner.getLocationOnScreen();
	    centerX = (int) (locationOnScreen.getX() + owner.getWidth() / 2);
	    centerY = (int) (locationOnScreen.getY() + owner.getHeight() / 2);
	} else {
	    final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    centerX = (int) (screenSize.getWidth() / 2);
	    centerY = (int) (screenSize.getHeight() / 2);
	}
	return new Point(centerX, centerY);
    }

    public void test() {

    }

    /**
     * Adds a Listener to the owner of this PopUp which disposes this, when the
     * MyFrame is disposed.
     *
     * @author Gabriel Glaser
     * @version 04.12.2021
     */
    private class DisposerOfThisWhenWindowIsDisposed extends WindowAdapter {
	@Override
	public void windowClosed(WindowEvent e) {
	    dispose();
	}
	
	@Override
	public void windowGainedFocus(WindowEvent e) {
	    setVisible(false);
	}
    }

}
