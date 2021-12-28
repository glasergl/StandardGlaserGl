package border;

import java.awt.Component;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.Timer;
import javax.swing.border.AbstractBorder;

/**
 * Border which underlines the JComponent it's added to. While doing so the
 * underline is built up as an animation.
 *
 * @author Gabriel Glaser
 * @version 28.12.2021
 */
public class AnimatedUnderlineBorder extends AbstractBorder {

    private static final int STANDARD_THICKNESS = 2;
    private static final int STANDARD_FIRST_STEP = 1;
    private static final int MAX_ANIMATION_STEP = 20;
    private static final int MS_BETWEEN_ANIMATION_STEP = 10;

    private final Color foreground;
    private final Color background;
    private final int thickness;

    private int currentAnimationStep;

    private AnimatedUnderlineBorder(final Color foreground, final Color background, final int thickness, final int currentStep) {
	this.foreground = foreground;
	this.background = background;
	this.thickness = thickness;
	this.currentAnimationStep = currentStep;
    }

    public AnimatedUnderlineBorder(final Color foreground, final Color background, final int thickness) {
	this(foreground, background, thickness, STANDARD_FIRST_STEP);
    }

    public AnimatedUnderlineBorder(final Color foreground, final Color background) {
	this(foreground, background, STANDARD_THICKNESS);
    }

    public AnimatedUnderlineBorder(final Color color) {
	this(color, color);
    }

    @Override
    public void paintBorder(Component component, Graphics context, int x, int y, int width, int height) {
	context.setColor(background);
	context.fillRect(x, height - thickness, width, thickness);
	context.setColor(foreground);
	final int currentWidth = (int) (((double) currentAnimationStep / MAX_ANIMATION_STEP) * width);
	final int middleX = (width / 2) - (currentWidth / 2);
	context.fillRect(middleX, height - thickness, currentWidth, thickness);
	if (currentAnimationStep <= MAX_ANIMATION_STEP) {
	    final Timer timer = new Timer(MS_BETWEEN_ANIMATION_STEP, (repaint) -> {
		((JComponent) component).setBorder(new AnimatedUnderlineBorder(foreground, background, thickness, currentAnimationStep + 1));
	    });
	    timer.setRepeats(false);
	    timer.start();
	}
    }

    @Override
    public Insets getBorderInsets(Component c) {
	return getBorderInsets(c, new Insets(0, 0, 0, 0));
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
	insets.set(0, 0, thickness, 0);
	return insets;
    }

}
