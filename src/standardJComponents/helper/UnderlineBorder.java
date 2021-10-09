package standardJComponents.helper;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.border.AbstractBorder;

/**
 * Class to represent an Border which underlines its component.
 * 
 * The difference to using MatteBorder is that the edges except the bottom one
 * are transparent, they exist and take the place, but they aren't visible.
 *
 * @author Gabriel Glaser
 * @version 27.09.2021
 */
public class UnderlineBorder extends AbstractBorder {

    private final int top, left, bottom, right;
    private final Color topC, leftC, bottomC, rightC;

    private UnderlineBorder(int top, int left, int bottom, int right, Color topC, Color leftC, Color bottomC, Color rightC) {
	super();
	this.top = top;
	this.left = left;
	this.bottom = bottom;
	this.right = right;
	this.topC = topC;
	this.leftC = leftC;
	this.bottomC = bottomC;
	this.rightC = rightC;
    }

    private UnderlineBorder(int top, int left, int bottom, int right, Color bottomC) {
	this(top, left, bottom, right, new Color(0, true), new Color(0, true), bottomC, new Color(0, true));
    }

    public UnderlineBorder(int heightOfUnderline, Color ofUnderline) {
	this(heightOfUnderline, heightOfUnderline, heightOfUnderline, heightOfUnderline, ofUnderline);
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	super.paintBorder(c, g, x, y, width, height);
	g.setColor(topC);
	g.fillRect(0, 0, c.getWidth(), top);
	g.setColor(leftC);
	g.fillRect(0, 0, left, c.getHeight() + top + bottom);
	g.setColor(bottomC);
	g.fillRect(0, c.getHeight() - bottom, c.getWidth(), bottom);
	g.setColor(rightC);
	g.fillRect(c.getWidth() - right, 0, right, c.getHeight() + top + bottom);
    }

    @Override
    public Insets getBorderInsets(Component c) {
	return new Insets(top, left, bottom, right);
    }

}
