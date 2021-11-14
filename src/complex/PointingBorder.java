package complex;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.RenderingHints;
import javax.swing.border.AbstractBorder;

import standard.helper.enums.CelestialDirection;

/**
 * Border which draws a triangle pointing at the defined celestial direction.
 * 
 * @author Gabriel Glaser
 * @version 7.11.2021
 */
public class PointingBorder extends AbstractBorder {

    private static final CelestialDirection STANDARD_DIRECTION = CelestialDirection.NORTH;
    private static final int STANDARD_HEIGHT = 15;
    private static final int STANDARD_WIDTH = 25;

    private final CelestialDirection locationAtEdgeOfComponent;
    private final int height;
    private final int width;
    private final Color ofPointer;
    private final Color ofLineBorder;

    public PointingBorder(final CelestialDirection locationAtEdgeOfComponent, final int height, final int width, final Color ofPointer,
	    final Color ofLineBorder) {
	super();
	this.locationAtEdgeOfComponent = locationAtEdgeOfComponent;
	this.height = height;
	this.width = width;
	this.ofPointer = ofPointer;
	this.ofLineBorder = ofLineBorder;
    }

    public PointingBorder(final CelestialDirection locationAtEdgeOfComponent, final Color ofPointer, final Color ofLineBorder) {
	this(locationAtEdgeOfComponent, STANDARD_HEIGHT, STANDARD_WIDTH, ofPointer, ofLineBorder);
    }

    public PointingBorder(final Color ofPointer, final Color ofLineBorder) {
	this(STANDARD_DIRECTION, ofPointer, ofLineBorder);
    }

    public PointingBorder(final Color color) {
	this(color, color);
    }

    @Override
    public void paintBorder(Component toPutThisAround, Graphics g, int x, int y, int w, int h) {
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
	switch (locationAtEdgeOfComponent) {
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

    private void deleteCorrectPlace(final Graphics context, final Component toPutThisAround) {
	if (locationAtEdgeOfComponent == CelestialDirection.NORTH) {
	    context.clearRect(0, 0, toPutThisAround.getWidth(), height);
	} else if (locationAtEdgeOfComponent == CelestialDirection.WEST) {
	    context.clearRect(0, 0, height, toPutThisAround.getWidth());
	} else if (locationAtEdgeOfComponent == CelestialDirection.SOUTH) {
	    context.clearRect(0, toPutThisAround.getHeight() - height, toPutThisAround.getWidth(), height);
	} else {
	    context.clearRect(toPutThisAround.getWidth() - height, 0, height, toPutThisAround.getHeight());
	}
    }

    private Polygon calculatePointer(final Component toPutThisAround) {
	final Polygon pointer = new Polygon();
	final int midX = toPutThisAround.getWidth() / 2;
	final int midY = toPutThisAround.getHeight() / 2;
	final int leftX = midX - width / 2;
	final int rightX = midX + width / 2;
	final int upY = midY - width / 2;
	final int downY = midY + width / 2;
	if (locationAtEdgeOfComponent == CelestialDirection.NORTH) {
	    pointer.addPoint(leftX, height - 1);
	    pointer.addPoint(midX, 0);
	    pointer.addPoint(rightX, height - 1);
	} else if (locationAtEdgeOfComponent == CelestialDirection.WEST) {
	    pointer.addPoint(height - 1, upY);
	    pointer.addPoint(0, midY);
	    pointer.addPoint(height - 1, downY);
	} else if (locationAtEdgeOfComponent == CelestialDirection.SOUTH) {
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

    private Polygon calculateLineBorder(final Component toPutThisAround) {
	final Polygon pointer = calculatePointer(toPutThisAround);
	if (locationAtEdgeOfComponent == CelestialDirection.NORTH) {
	    pointer.addPoint(toPutThisAround.getWidth() - 1, height - 1); // up-right
	    pointer.addPoint(toPutThisAround.getWidth() - 1, toPutThisAround.getHeight() - 1); // down-right
	    pointer.addPoint(0, toPutThisAround.getHeight() - 1); // down-left
	    pointer.addPoint(0, height - 1); // up-left
	} else if (locationAtEdgeOfComponent == CelestialDirection.WEST) {
	    pointer.addPoint(height, toPutThisAround.getHeight() - 1); // down-left
	    pointer.addPoint(toPutThisAround.getWidth() - 1, toPutThisAround.getHeight() - 1); // down-right
	    pointer.addPoint(toPutThisAround.getWidth() - 1, 0); // up-right
	    pointer.addPoint(height, 0); // up-left
	} else if (locationAtEdgeOfComponent == CelestialDirection.SOUTH) {
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
