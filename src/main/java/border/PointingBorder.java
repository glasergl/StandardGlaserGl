package border;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import javax.swing.border.AbstractBorder;

import entity.CelestialDirection;
import settings.Colors;

/**
 * Border which draws a triangle pointing at the defined celestial direction and
 * a line which surrounds everything.
 * 
 * @author Gabriel Glaser
 * @version 20.12.2021
 */
public class PointingBorder extends AbstractBorder {

    private static final CelestialDirection STANDARD_DIRECTION_OF_POINTER = CelestialDirection.NORTH;
    private static final int STANDARD_HEIGHT_OF_POINTER = 15;
    private static final int STANDARD_WIDTH_OF_POINTER = 25;

    private final CelestialDirection directionOfPointer;
    private final int heightOfPointer;
    private final int widthOfPointer;
    private final Color colorOfPointer;
    private final Color colorOfLineBorder;

    public PointingBorder(final CelestialDirection directionOfPointer, final int heightOfPointer, final int widthOfPointer, final Color colorOfPointer, final Color colorOfLineBorder) {
	super();
	this.directionOfPointer = directionOfPointer;
	this.heightOfPointer = heightOfPointer;
	this.widthOfPointer = widthOfPointer;
	this.colorOfPointer = colorOfPointer;
	this.colorOfLineBorder = colorOfLineBorder;
    }

    public PointingBorder(final CelestialDirection edgeForPointer, final Color ofPointer, final Color ofLineBorder) {
	this(edgeForPointer, STANDARD_HEIGHT_OF_POINTER, STANDARD_WIDTH_OF_POINTER, ofPointer, ofLineBorder);
    }

    public PointingBorder(final CelestialDirection edgeForPointer, final JComponent toSetThisAround) {
	this(edgeForPointer, toSetThisAround.getBackground(), toSetThisAround.getForeground());
    }

    public PointingBorder(final Color ofPointer, final Color ofLineBorder) {
	this(STANDARD_DIRECTION_OF_POINTER, ofPointer, ofLineBorder);
    }

    public PointingBorder(final JComponent toPutThisAround) {
	this(toPutThisAround.getBackground(), toPutThisAround.getForeground());
    }

    public PointingBorder(final Color color) {
	this(color, color);
    }

    public PointingBorder() {
	this(Colors.getGray(4));
    }

    @Override
    public void paintBorder(final Component toPutThisAround, final Graphics context, final int x, final int y, final int w, final int h) {
	deleteCorrectPlace(context, toPutThisAround);
	((Graphics2D) context).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	context.setColor(colorOfPointer);
	final Polygon pointer = calculatePointer(toPutThisAround);
	context.fillPolygon(pointer);
	context.drawPolygon(pointer);
	context.setColor(colorOfLineBorder);
	context.drawPolygon(calculateLineBorder(toPutThisAround));
    }

    @Override
    public Insets getBorderInsets(final Component component) {
	switch (directionOfPointer) {
	case NORTH:
	    return new Insets(heightOfPointer, 1, 1, 1);
	case WEST:
	    return new Insets(1, heightOfPointer, 1, 1);
	case SOUTH:
	    return new Insets(1, 1, heightOfPointer, 1);
	case EAST:
	    return new Insets(1, 1, 1, heightOfPointer);
	default:
	    throw new RuntimeException();
	}
    }

    @Override
    public Insets getBorderInsets(final Component component, final Insets insets) {
	final Insets i = getBorderInsets(component);
	insets.set(i.top, i.left, i.bottom, i.right);
	return insets;
    }

    /**
     * Clears the place where the pointer needs to be.
     * 
     * @param context
     * @param toPutThisAround
     */
    private void deleteCorrectPlace(final Graphics context, final Component toPutThisAround) {
	if (directionOfPointer == CelestialDirection.NORTH) {
	    context.clearRect(0, 0, toPutThisAround.getWidth(), heightOfPointer);
	} else if (directionOfPointer == CelestialDirection.WEST) {
	    context.clearRect(0, 0, heightOfPointer, toPutThisAround.getWidth());
	} else if (directionOfPointer == CelestialDirection.SOUTH) {
	    context.clearRect(0, toPutThisAround.getHeight() - heightOfPointer, toPutThisAround.getWidth(), heightOfPointer);
	} else {
	    context.clearRect(toPutThisAround.getWidth() - heightOfPointer, 0, heightOfPointer, toPutThisAround.getHeight());
	}
    }

    /**
     * Calculates the Polygon which represents the pointer.
     * 
     * @param toPutThisAround
     * @return The pointer.
     */
    private Polygon calculatePointer(final Component toPutThisAround) {
	final Polygon pointer = new Polygon();
	final int midX = toPutThisAround.getWidth() / 2;
	final int midY = toPutThisAround.getHeight() / 2;
	final int leftX = midX - widthOfPointer / 2;
	final int rightX = midX + widthOfPointer / 2;
	final int upY = midY - widthOfPointer / 2;
	final int downY = midY + widthOfPointer / 2;
	if (directionOfPointer == CelestialDirection.NORTH) {
	    pointer.addPoint(leftX, heightOfPointer - 1);
	    pointer.addPoint(midX, 0);
	    pointer.addPoint(rightX, heightOfPointer - 1);
	} else if (directionOfPointer == CelestialDirection.WEST) {
	    pointer.addPoint(heightOfPointer - 1, upY);
	    pointer.addPoint(0, midY);
	    pointer.addPoint(heightOfPointer - 1, downY);
	} else if (directionOfPointer == CelestialDirection.SOUTH) {
	    pointer.addPoint(leftX, toPutThisAround.getHeight() - heightOfPointer);
	    pointer.addPoint(midX, toPutThisAround.getHeight());
	    pointer.addPoint(rightX, toPutThisAround.getHeight() - heightOfPointer);
	} else {
	    pointer.addPoint(toPutThisAround.getWidth() - heightOfPointer, upY);
	    pointer.addPoint(toPutThisAround.getWidth(), midY);
	    pointer.addPoint(toPutThisAround.getWidth() - heightOfPointer, downY);
	}
	return pointer;
    }

    /**
     * Calculates the Polygon which surrounds everything, depending on the given
     * Component.
     * 
     * @param toPutThisAround
     * @return The Polygon of the line which surrounds everything.
     */
    private Polygon calculateLineBorder(final Component toPutThisAround) {
	final Polygon pointer = calculatePointer(toPutThisAround);
	final int width = toPutThisAround.getWidth();
	final int height = toPutThisAround.getHeight();
	if (directionOfPointer == CelestialDirection.NORTH) {
	    addPointsForNorthPointer(pointer, width, height);
	} else if (directionOfPointer == CelestialDirection.WEST) {
	    addPointsForWestPointer(pointer, width, height);
	} else if (directionOfPointer == CelestialDirection.SOUTH) {
	    addPointsForSouthPointer(pointer, width, height);
	} else {
	    addPointsForEastPointer(pointer, width, height);
	}
	return pointer;
    }

    private void addPointsForEastPointer(final Polygon pointer, final int widthOfComponent, final int heightOfComponent) {
	pointer.addPoint(widthOfComponent - heightOfPointer, heightOfComponent - 1); // down-right
	pointer.addPoint(0, heightOfComponent - 1); // down-left
	pointer.addPoint(0, 0); // up-left
	pointer.addPoint(widthOfComponent - heightOfPointer, 0); // up-right
    }

    private void addPointsForSouthPointer(final Polygon pointer, final int widthOfComponent, final int heightOfComponent) {
	pointer.addPoint(widthOfComponent - 1, heightOfComponent - heightOfPointer); // down-right
	pointer.addPoint(widthOfComponent - 1, 0); // up-right
	pointer.addPoint(0, 0); // up-left
	pointer.addPoint(0, heightOfComponent - heightOfPointer); // down-left
    }

    private void addPointsForWestPointer(final Polygon pointer, final int widthOfComponent, final int heightOfComponent) {
	pointer.addPoint(heightOfPointer, heightOfComponent - 1); // down-left
	pointer.addPoint(widthOfComponent - 1, heightOfComponent - 1); // down-right
	pointer.addPoint(widthOfComponent - 1, 0); // up-right
	pointer.addPoint(heightOfPointer, 0); // up-left
    }

    private void addPointsForNorthPointer(final Polygon pointer, final int widthOfComponent, final int heightOfComponent) {
	pointer.addPoint(widthOfComponent - 1, heightOfPointer - 1); // up-right
	pointer.addPoint(widthOfComponent - 1, heightOfComponent - 1); // down-right
	pointer.addPoint(0, heightOfComponent - 1); // down-left
	pointer.addPoint(0, heightOfPointer - 1); // up-left
    }

}
