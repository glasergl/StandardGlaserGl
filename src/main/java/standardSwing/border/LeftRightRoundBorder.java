package standardSwing.border;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.util.Optional;
import javax.swing.border.AbstractBorder;

/**
 * Based on https://stackoverflow.com/a/16909994
 * 
 * Represents a round border at the left and right edge of the component.
 *
 * @author Gabriel Glaser
 * @version 30.03.2022
 */
public class LeftRightRoundBorder extends AbstractBorder {

    private static final int DEFAULT_WIDTH_OF_SIDES = 7;
    private static final int DEFAULT_ROUNDNESS = 15;

    private final Optional<Color> color;
    private final int widthOfSides;
    private final int roundness;

    private LeftRightRoundBorder(final Optional<Color> color, final int widthOfSides, final int roundness) {
	this.widthOfSides = widthOfSides;
	this.roundness = roundness;
	this.color = color;
    }

    public LeftRightRoundBorder(final Color color, final int widthOfSides, final int roundness) {
	this(Optional.of(color), widthOfSides, roundness);
    }

    public LeftRightRoundBorder(final Color color) {
	this(color, DEFAULT_WIDTH_OF_SIDES, DEFAULT_ROUNDNESS);
    }

    public LeftRightRoundBorder(final int widthOfSides, final int roundness) {
	this(Optional.empty(), widthOfSides, roundness);
    }

    public LeftRightRoundBorder() {
	this(Optional.empty(), DEFAULT_WIDTH_OF_SIDES, DEFAULT_ROUNDNESS);
    }

    @Override
    public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int w, final int h) {
	final Graphics2D g2D = (Graphics2D) g;
	g2D.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

	g2D.setColor(c.getParent().getBackground());
	g2D.fillRect(0, 0, widthOfSides / 2, h);
	g2D.fillRect(w - widthOfSides / 2, 0, widthOfSides / 2, h);

	g2D.setColor(color.isPresent() ? color.get() : c.getBackground());
	g2D.fillRoundRect(0, 0, widthOfSides, h, roundness, roundness);
	g2D.fillRoundRect(w - widthOfSides, 0, widthOfSides, h, roundness, roundness);
    }

    @Override
    public Insets getBorderInsets(Component c) {
	return new Insets(0, widthOfSides, 0, widthOfSides);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
	return getBorderInsets(c);
    }

}
