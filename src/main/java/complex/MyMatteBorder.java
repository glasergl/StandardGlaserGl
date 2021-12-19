package complex;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.border.AbstractBorder;

public class MyMatteBorder extends AbstractBorder {

	private final int topSpace, leftSpace, botSpace, rightSpace;
	private final int top, left, bot, right;
	private final Color topColor, leftColor, botColor, rightColor;

	public MyMatteBorder(final int topSpace, final int leftSpace, final int botSpace, final int rightSpace,
			final int top, final int left, final int bot, final int right, final Color topColor, final Color leftColor,
			final Color botColor, final Color rightColor) {
		super();
		if (topSpace < 0 || leftSpace < 0 || botSpace < 0 || rightSpace < 0 || top < 0 || left < 0 || bot < 0
				|| right < 0) {
			throw new IllegalArgumentException("arguments cannot be negative");
		}
		this.topSpace = topSpace;
		this.leftSpace = leftSpace;
		this.botSpace = botSpace;
		this.rightSpace = rightSpace;

		this.top = top;
		this.left = left;
		this.bot = bot;
		this.right = right;

		this.topColor = topColor;
		this.leftColor = leftColor;
		this.botColor = botColor;
		this.rightColor = rightColor;
	}

	public MyMatteBorder(final int spaceTop, final int spaceLeft, final int spaceBottom, final int spaceRight,
			final int fillTop, final int fillLeft, final int fillBottom, final int fillRight, final Color color) {
		this(spaceTop, spaceLeft, spaceBottom, spaceRight, fillTop, fillLeft, fillBottom, fillRight, color, color,
				color, color);
	}

	public MyMatteBorder(final int top, final int left, final int bottom, final int right, final Color color) {
		this(0, 0, 0, 0, top, left, bottom, right, color);
	}

	public MyMatteBorder(final int width, final Color color) {
		this(width, width, width, width, color);
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		clearSpaces(c, g, width, height);
		g.setColor(topColor);
		g.fillRect(leftSpace, topSpace, width - leftSpace - rightSpace, top);
		g.setColor(leftColor);
		g.fillRect(leftSpace, topSpace + top, left, height - topSpace - top - botSpace - bot);
		g.setColor(botColor);
		g.fillRect(leftSpace, height - botSpace - bot, width - leftSpace - rightSpace, bot);
		g.setColor(rightColor);
		g.fillRect(width - rightSpace - right, topSpace + top, right, height - topSpace - top - botSpace - bot);
	}

	private void clearSpaces(Component c, Graphics g, int width, int height) {
		g.setColor(c.getParent().getBackground());
		g.fillRect(0, 0, width, top + topSpace);
		g.fillRect(0, 0, leftSpace + left, height);
		g.fillRect(0, height - botSpace - bot, width, bot + botSpace);
		g.fillRect(width - rightSpace - right, 0, right + rightSpace, height);
	}

	@Override
	public Insets getBorderInsets(Component c) {
		return new Insets(top + topSpace, left + leftSpace, bot + botSpace, right + rightSpace);
	}

	@Override
	public Insets getBorderInsets(Component c, Insets insets) {
		insets.set(top + topSpace, left + leftSpace, bot + botSpace, right + rightSpace);
		return insets;
	}
}
