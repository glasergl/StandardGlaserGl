package complex;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.border.AbstractBorder;

import standard.helper.enums.CelestialDirection;

/**
 * Border which draws a triangle pointing at the defined celestial direction and
 * a line which surrounds everything (the pointer, too).
 * 
 * @author Gabriel Glaser
 * @version 16.11.2021
 */
public class PointingBorder extends AbstractBorder {

    private static final CelestialDirection STANDARD_DIRECTION = CelestialDirection.NORTH;
    private static final int STANDARD_HEIGHT = 15;
    private static final int STANDARD_WIDTH = 25;

    private final CelestialDirection edgeForPointer;
    private final int height;
    private final int width;
    private final Color ofPointer;
    private final Color ofLineBorder;

    public PointingBorder(final CelestialDirection edgeForPointer, final int height, final int width, final Color ofPointer, final Color ofLineBorder) {
	super();
	this.edgeForPointer = edgeForPointer;
	this.height = height;
	this.width = width;
	this.ofPointer = ofPointer;
	this.ofLineBorder = ofLineBorder;
    }

    public PointingBorder(final CelestialDirection edgeForPointer, final Color ofPointer, final Color ofLineBorder) {
	this(edgeForPointer, STANDARD_HEIGHT, STANDARD_WIDTH, ofPointer, ofLineBorder);
    }

    public PointingBorder(final CelestialDirection edgeForPointer, final JComponent toSetThisAround) {
	this(edgeForPointer, toSetThisAround.getBackground(), toSetThisAround.getForeground());
    }

    public PointingBorder(final Color ofPointer, final Color ofLineBorder) {
	this(STANDARD_DIRECTION, ofPointer, ofLineBorder);
    }

    public PointingBorder(final JComponent toSetThisAround) {
	this(toSetThisAround.getBackground(), toSetThisAround.getForeground());
    }

    public PointingBorder(final Color color) {
	this(color, color);
    }

    @Override
    public void paintBorder(final Component toPutThisAround, final Graphics g, final int x, final int y, final int w, final int h) {
	deleteCorrectPlace(g, toPutThisAround);
	((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	g.setColor(ofPointer);
	final Polygon pointer = calculatePointer(toPutThisAround);
	g.fillPolygon(pointer);
	g.drawPolygon(pointer);
	g.setColor(ofLineBorder);
	g.drawPolygon(calculateLineBorder(toPutThisAround));
    }

    @Override
    public Insets getBorderInsets(Component c) {
	switch (edgeForPointer) {
	case NORTH:
	    return new Insets(height, 1, 1, 1);
	case WEST:
	    return new Insets(1, height, 1, 1);
	case SOUTH:
	    return new Insets(1, 1, height, 1);
	case EAST:
	    return new Insets(1, 1, 1, height);
	default:
	    throw new RuntimeException();
	}
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
	final Insets i = getBorderInsets(c);
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
	if (edgeForPointer == CelestialDirection.NORTH) {
	    context.clearRect(0, 0, toPutThisAround.getWidth(), height);
	} else if (edgeForPointer == CelestialDirection.WEST) {
	    context.clearRect(0, 0, height, toPutThisAround.getWidth());
	} else if (edgeForPointer == CelestialDirection.SOUTH) {
	    context.clearRect(0, toPutThisAround.getHeight() - height, toPutThisAround.getWidth(), height);
	} else {
	    context.clearRect(toPutThisAround.getWidth() - height, 0, height, toPutThisAround.getHeight());
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
	final int leftX = midX - width / 2;
	final int rightX = midX + width / 2;
	final int upY = midY - width / 2;
	final int downY = midY + width / 2;
	if (edgeForPointer == CelestialDirection.NORTH) {
	    pointer.addPoint(leftX, height - 1);
	    pointer.addPoint(midX, 0);
	    pointer.addPoint(rightX, height - 1);
	} else if (edgeForPointer == CelestialDirection.WEST) {
	    pointer.addPoint(height - 1, upY);
	    pointer.addPoint(0, midY);
	    pointer.addPoint(height - 1, downY);
	} else if (edgeForPointer == CelestialDirection.SOUTH) {
	    pointer.addPoint(leftX, toPutThisAround.getHeight() - height);
	    pointer.addPoint(midX, toPutThisAround.getHeight());
	    pointer.addPoint(rightX, toPutThisAround.getHeight() - height);
	} else {
	    pointer.addPoint(toPutThisAround.getWidth() - height, upY);
	    pointer.addPoint(toPutThisAround.getWidth(), midY);
	    pointer.addPoint(toPutThisAround.getWidth() - height, downY);
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
	if (edgeForPointer == CelestialDirection.NORTH) {
	    pointer.addPoint(toPutThisAround.getWidth() - 1, height - 1); // up-right
	    pointer.addPoint(toPutThisAround.getWidth() - 1, toPutThisAround.getHeight() - 1); // down-right
	    pointer.addPoint(0, toPutThisAround.getHeight() - 1); // down-left
	    pointer.addPoint(0, height - 1); // up-left
	} else if (edgeForPointer == CelestialDirection.WEST) {
	    pointer.addPoint(height, toPutThisAround.getHeight() - 1); // down-left
	    pointer.addPoint(toPutThisAround.getWidth() - 1, toPutThisAround.getHeight() - 1); // down-right
	    pointer.addPoint(toPutThisAround.getWidth() - 1, 0); // up-right
	    pointer.addPoint(height, 0); // up-left
	} else if (edgeForPointer == CelestialDirection.SOUTH) {
	    pointer.addPoint(toPutThisAround.getWidth() - 1, toPutThisAround.getHeight() - height); // down-right
	    pointer.addPoint(toPutThisAround.getWidth() - 1, 0); // up-right
	    pointer.addPoint(0, 0); // up-left
	    pointer.addPoint(0, toPutThisAround.getHeight() - height); // down-left
	} else {
	    pointer.addPoint(toPutThisAround.getWidth() - height, toPutThisAround.getHeight() - 1); // down-right
	    pointer.addPoint(0, toPutThisAround.getHeight() - 1); // down-left
	    pointer.addPoint(0, 0); // up-left
	    pointer.addPoint(toPutThisAround.getWidth() - height, 0); // up-right
	}
	return pointer;
    }

}
