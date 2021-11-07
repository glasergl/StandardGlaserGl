package standardJComponents.implementations;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import static java.lang.Math.pow;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

import standardJComponents.helper.Direction;

public class MyToggleSwitch extends JComponent {

	public static Color standardToggledBackground = Color.BLACK;
	public static Color standardUntoggledBackground = Color.GREEN;
	public static Color standardToggleCircle = Color.GRAY;
	public static Color standardToggleHover = Color.DARK_GRAY;
	public static Color standardTogglePress = Color.BLACK;

	private final int transitionSteps = 50;
	private final int durationOfToggle = 150;
	private final double relativeBackgroundHeight = 6.0 / 7.0;

	private final ToggleSwitchCircle toggleSwitchCircle = new ToggleSwitchCircle();
	private final ToggleSwitchCircleController toggleButtonController = new ToggleSwitchCircleController();
	private Timer toggleMover;
	private boolean isToggled;

	public MyToggleSwitch(final boolean isToggled) {
		super();
		this.isToggled = isToggled;
		setBackground(standardToggledBackground);
		addMouseListener(toggleButtonController);
		addMouseMotionListener(toggleButtonController);
	}

	public MyToggleSwitch() {
		this(false);
	}

	@Override
	public void paintComponent(final Graphics context) {
		((Graphics2D) context).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		context.setColor(getBackground());
		final int heightOfBackground = (int) (relativeBackgroundHeight * getHeight());
		final int amountToCenterBackground = (int) (((1 - relativeBackgroundHeight) / 2) * getHeight());
		context.fillOval(1, amountToCenterBackground, getWidth() / 2, heightOfBackground);
		context.fillOval(getWidth() / 2 - 1, amountToCenterBackground, getWidth() / 2, heightOfBackground);
		context.fillRect(getWidth() / 4, amountToCenterBackground, getWidth() / 2, heightOfBackground);
		toggleSwitchCircle.paintComponent(context);
	}

	public void toggle() {
		if (isToggled()) {
			setBackground(standardToggledBackground);
			moveToggle(Direction.LEFT);
		} else {
			setBackground(standardUntoggledBackground);
			moveToggle(Direction.RIGHT);
		}
		isToggled = !isToggled;
	}

	public boolean isToggled() {
		return isToggled;
	}

	private void moveToggle(final Direction toMoveTo) {
		toggleMover = new Timer(transitionSteps / durationOfToggle, new MoveToggleCircle(toMoveTo));
		toggleMover.setInitialDelay(0);
		toggleMover.start();
	}

	private class ToggleSwitchCircle {

		private int xCoordinate = 0;
		private Color color = standardToggleCircle;

		public void paintComponent(final Graphics context) {
			context.setColor(color);
			context.fillOval(xCoordinate, 0, getWidth() / 2, getWidth() / 2);
		}

		public boolean contains(final Point toTest) {
			final double centerOfCirlceX = xCoordinate + getWidth() / 4;
			final double centerOfCirlceY = 0 + getHeight() / 2;
			final double radius = getWidth() / 4;
			return pow(toTest.getX() - centerOfCirlceX, 2) + pow(toTest.getY() - centerOfCirlceY, 2) < pow(radius, 2);
		}

		public void move(final double amount) {
			if (xCoordinate + amount < 0) {
				xCoordinate = 0;
			} else if (xCoordinate + amount > getWidth() / 2) {
				xCoordinate = getWidth() / 2;
			} else {
				xCoordinate += amount;
			}
			repaint();
		}

		public void moveAt(final int x) {
			this.xCoordinate = x;
			repaint();
		}

		public void setColor(final Color color) {
			this.color = color;
			repaint();
		}

		public int getX() {
			return xCoordinate;
		}
	}

	private class ToggleSwitchCircleController implements MouseInputListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (toggleSwitchCircle.contains(e.getPoint())) {
				toggle();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (toggleSwitchCircle.contains(e.getPoint())) {
				toggleSwitchCircle.setColor(standardTogglePress);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (isToggled) {
				if (toggleSwitchCircle.getX() < getWidth() / 4) {
					toggle();
				} else {
					toggleSwitchCircle.moveAt(getWidth() / 2);
				}
			} else {
				if (toggleSwitchCircle.getX() > getWidth() / 4) {
					toggle();
				} else {
					toggleSwitchCircle.moveAt(0);
				}
			}
			if (toggleSwitchCircle.contains(e.getPoint())) {
				toggleSwitchCircle.setColor(standardToggleHover);
			} else {
				toggleSwitchCircle.setColor(standardToggleCircle);
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
			toggleSwitchCircle.setColor(standardToggleCircle);
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (toggleSwitchCircle.contains(e.getPoint()) && e.getX() >= getWidth() / 4 && e.getX() <= (3 * getWidth()) / 4) {
				toggleSwitchCircle.moveAt(e.getX() - getWidth() / 4);
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (toggleSwitchCircle.contains(e.getPoint())) {
				toggleSwitchCircle.setColor(standardToggleHover);
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			} else {
				toggleSwitchCircle.setColor(standardToggleCircle);
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}

	}

	private class MoveToggleCircle implements ActionListener {

		private final Direction toMoveTo;
		private final int stepWidth;
		private int currentStep = 1;

		public MoveToggleCircle(final Direction toMoveTo) {
			this.toMoveTo = toMoveTo;
			this.stepWidth = (getWidth() / 2) / transitionSteps;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			toggleSwitchCircle.move((toMoveTo == Direction.RIGHT ? 1 : -1) * stepWidth);
			currentStep++;
			if (currentStep > transitionSteps) {
				toggleMover.stop();
				if (!toggleSwitchCircle.contains(getMousePosition())) {
					toggleSwitchCircle.setColor(standardToggleCircle);
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}
			}
		}

	}

}
