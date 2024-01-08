package standardGlaserGl.swing.border;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.border.AbstractBorder;

import standardGlaserGl.swing.entity.CelestialDirection;
import standardGlaserGl.swing.general.SwingFunctions;

/**
 * Border which draws a triangle pointing at the given celestial direction and a
 * line which surrounds everything.
 * 
 * @author Gabriel Glaser
 */
public final class PointingBorder extends AbstractBorder {
    private static final CelestialDirection STANDARD_POINTER_DIRECTION = CelestialDirection.NORTH;
    private static final int STANDARD_POINTER_WIDTH = 25;
    private static final int STANDARD_POINTER_HEIGHT = 15;

    private final CelestialDirection pointerDirection;
    private final int pointerWidth;
    private final int pointerHeight;
    private final Color pointerColor;
    private final Color surroundingLineColor;

    /**
     * @param pointerDirection
     * @param pointerWidth
     * @param pointerHeight
     * @param pointerColor
     * @param surroundingLineColor
     */
    public PointingBorder(final CelestialDirection pointerDirection, final int pointerWidth, final int pointerHeight, final Color pointerColor, final Color surroundingLineColor) {
	super();
	this.pointerDirection = pointerDirection;
	this.pointerWidth = pointerWidth;
	this.pointerHeight = pointerHeight;
	this.pointerColor = pointerColor;
	this.surroundingLineColor = surroundingLineColor;
    }

    /**
     * Constructor with default width of 25 and height of 15.
     * 
     * @param pointerDirection
     * @param pointerColor
     * @param surroundingLineColor
     */
    public PointingBorder(final CelestialDirection pointerDirection, final Color pointerColor, final Color surroundingLineColor) {
	this(pointerDirection, STANDARD_POINTER_WIDTH, STANDARD_POINTER_HEIGHT, pointerColor, surroundingLineColor);
    }

    /**
     * Constructor with the pointer pointing north and with default width of 25 and
     * height of 15.
     * 
     * @param pointerColor
     * @param surroundingLineColor
     */
    public PointingBorder(final Color pointerColor, final Color surroundingLineColor) {
	this(STANDARD_POINTER_DIRECTION, pointerColor, surroundingLineColor);
    }

    /**
     * Constructor with the pointer pointing north and with default width of 25,
     * height of 15.
     * 
     * @param borderColor
     */
    public PointingBorder(final Color borderColor) {
	this(borderColor, borderColor);
    }

    /**
     * Constructor which sets the Color of the pointer to the Color of the
     * background of the given JComponent and the Color of the line to the Color of
     * the foreground of the given JComponent.
     * 
     * @param pointerDirection
     * @param pointerWidth
     * @param pointerHeight
     * @param jComponent
     */
    public PointingBorder(final CelestialDirection pointerDirection, final int pointerWidth, final int pointerHeight, final JComponent jComponent) {
	this(pointerDirection, pointerWidth, pointerHeight, jComponent.getBackground(), jComponent.getForeground());
    }

    /**
     * Constructor which sets the Color of the pointer to the Color of the
     * background of the given JComponent and the Color of the line to the Color of
     * the foreground of the given JComponent. Furthermore, the standard width of 25
     * and height of 15 is used.
     * 
     * @param pointerDirection
     * @param jComponent
     */
    public PointingBorder(final CelestialDirection pointerDirection, final JComponent jComponent) {
	this(pointerDirection, STANDARD_POINTER_WIDTH, STANDARD_POINTER_HEIGHT, jComponent);
    }

    /**
     * Constructor which sets the Color of the pointer to the Color of the
     * background of the given JComponent and the Color of the line to the Color of
     * the foreground of the given JComponent. Furthermore, the standard width of 25
     * and height of 15 is used and the pointer points north.
     * 
     * @param jComponent
     */
    public PointingBorder(final JComponent jComponent) {
	this(STANDARD_POINTER_DIRECTION, jComponent);
    }

    @Override
    public void paintBorder(final Component toPutThisAround, final Graphics context, final int x, final int y, final int width, final int height) {
	SwingFunctions.setAntialiasing(context, true);
	final Rectangle spaceToClear = getSpaceToClear(width, height);
	context.clearRect(spaceToClear.x, spaceToClear.y, spaceToClear.width, spaceToClear.height);
	context.setColor(pointerColor);
	final Polygon pointer = calculatePointer(width, height);
	context.drawPolygon(pointer);
	context.fillPolygon(pointer);
	context.setColor(surroundingLineColor);
	context.drawPolygon(calculateSurroundingLine(width, height));
    }

    /**
     * @param width  of the Component
     * @param height of the Component
     * @return The space that needs to be cleared.
     */
    private Rectangle getSpaceToClear(final int width, final int height) {
	if (pointerDirection == CelestialDirection.NORTH) {
	    return new Rectangle(0, 0, width, pointerHeight);
	} else if (pointerDirection == CelestialDirection.WEST) {
	    return new Rectangle(0, 0, pointerHeight, height);
	} else if (pointerDirection == CelestialDirection.SOUTH) {
	    return new Rectangle(0, height - pointerHeight, width, pointerHeight);
	} else {
	    return new Rectangle(width - pointerHeight, 0, pointerHeight, height);
	}
    }

    /**
     * @param width  of the Component
     * @param height of the Component
     * @return The Polygon of the pointer.
     */
    private Polygon calculatePointer(final int width, final int height) {
	if (pointerDirection == CelestialDirection.NORTH) {
	    return calculateNorthPointer(width, height);
	} else if (pointerDirection == CelestialDirection.WEST) {
	    return calculateWestPointer(width, height);
	} else if (pointerDirection == CelestialDirection.SOUTH) {
	    return calculateSouthPointer(width, height);
	} else {
	    return calculateEastPointer(width, height);
	}
    }

    private Polygon calculateNorthPointer(final int width, final int height) {
	final Polygon northPointer = new Polygon();
	final Point left = new Point(width / 2 - pointerWidth / 2, pointerHeight);
	final Point middle = new Point(width / 2, 0);
	final Point right = new Point(width / 2 + pointerWidth / 2, pointerHeight);
	addPointsToPolygon(northPointer, left, middle, right);
	return northPointer;
    }

    private Polygon calculateWestPointer(final int width, final int height) {
	final Polygon westPointer = new Polygon();
	final Point left = new Point(pointerHeight, height / 2 + pointerWidth / 2);
	final Point middle = new Point(0, height / 2);
	final Point right = new Point(pointerHeight, height / 2 - pointerWidth / 2);
	addPointsToPolygon(westPointer, left, middle, right);
	return westPointer;
    }

    private Polygon calculateSouthPointer(final int width, final int height) {
	final Polygon southPointer = new Polygon();
	final Point left = new Point(width / 2 + pointerWidth / 2, height - pointerHeight);
	final Point middle = new Point(width / 2, height - 1);
	final Point right = new Point(width / 2 - pointerWidth / 2, height - pointerHeight);
	addPointsToPolygon(southPointer, left, middle, right);
	return southPointer;
    }

    private Polygon calculateEastPointer(final int width, final int height) {
	final Polygon eastPointer = new Polygon();
	final Point left = new Point(width - pointerHeight, height / 2 - pointerWidth / 2);
	final Point middle = new Point(width - 1, height / 2);
	final Point right = new Point(width - pointerHeight, height / 2 + pointerWidth / 2);
	addPointsToPolygon(eastPointer, left, middle, right);
	return eastPointer;
    }

    /**
     * @param width  of the Component
     * @param height of the Component
     * @return The Polygon of the line which surrounds everything.
     */
    private Polygon calculateSurroundingLine(final int width, final int height) {
	final Polygon pointer = calculatePointer(width, height);
	if (pointerDirection == CelestialDirection.NORTH) {
	    addSurroundingLinePointsForNorthPointer(pointer, width, height);
	} else if (pointerDirection == CelestialDirection.WEST) {
	    addSurroundingLinePointsForWestPointer(pointer, width, height);
	} else if (pointerDirection == CelestialDirection.SOUTH) {
	    addSurroundingLinePointsForSouthPointer(pointer, width, height);
	} else {
	    addSurroundingLinePointsForEastPointer(pointer, width, height);
	}
	return pointer;
    }

    private void addSurroundingLinePointsForNorthPointer(final Polygon pointer, final int width, final int height) {
	final Point topLeft = new Point(0, pointerHeight);
	final Point topRight = new Point(width - 1, pointerHeight);
	final Point bottomLeft = new Point(0, height - 1);
	final Point bottomRight = new Point(width - 1, height - 1);
	addPointsToPolygon(pointer, topRight, bottomRight, bottomLeft, topLeft);
    }

    private void addSurroundingLinePointsForWestPointer(final Polygon pointer, final int width, final int height) {
	final Point topLeft = new Point(pointerHeight, 0);
	final Point topRight = new Point(width - 1, 0);
	final Point bottomLeft = new Point(pointerHeight, height - 1);
	final Point bottomRight = new Point(width - 1, height - 1);
	addPointsToPolygon(pointer, topLeft, topRight, bottomRight, bottomLeft);
    }

    private void addSurroundingLinePointsForSouthPointer(final Polygon pointer, final int width, final int height) {
	final Point topLeft = new Point(0, 0);
	final Point topRight = new Point(width - 1, 0);
	final Point bottomLeft = new Point(0, height - pointerHeight);
	final Point bottomRight = new Point(width - 1, height - pointerHeight);
	addPointsToPolygon(pointer, bottomLeft, topLeft, topRight, bottomRight);
    }

    private void addSurroundingLinePointsForEastPointer(final Polygon pointer, final int width, final int height) {
	final Point topLeft = new Point(0, 0);
	final Point bottomLeft = new Point(0, height - 1);
	final Point bottomRight = new Point(width - pointerHeight, height - 1);
	final Point topRight = new Point(width - pointerHeight, 0);
	addPointsToPolygon(pointer, bottomRight, bottomLeft, topLeft, topRight);
    }

    @Override
    public Insets getBorderInsets(final Component component) {
	if (pointerDirection == CelestialDirection.NORTH) {
	    return new Insets(pointerHeight + 1, 1, 1, 1);
	} else if (pointerDirection == CelestialDirection.WEST) {
	    return new Insets(1, pointerHeight + 1, 1, 1);
	} else if (pointerDirection == CelestialDirection.SOUTH) {
	    return new Insets(1, 1, pointerHeight + 1, 1);
	} else {
	    return new Insets(1, 1, 1, pointerHeight + 1);
	}
    }

    @Override
    public Insets getBorderInsets(final Component component, final Insets insets) {
	final Insets i = getBorderInsets(component);
	insets.set(i.top, i.left, i.bottom, i.right);
	return insets;
    }

    private static void addPointsToPolygon(final Polygon polygon, final Point... points) {
	for (final Point point : points) {
	    polygon.addPoint(point.x, point.y);
	}
    }
}
