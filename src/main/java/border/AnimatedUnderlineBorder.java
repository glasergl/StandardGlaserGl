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
 * @version 30.12.2021
 */
public final class AnimatedUnderlineBorder extends AbstractBorder {

    private static final int STANDARD_THICKNESS = 2;
    private static final Color STANDARD_BACKGROUND = new Color(0, 0, 0, 0);

    private static final int STANDARD_FIRST_STEP = 1;
    private static final int ANIMATION_STEPS = 20;
    private static final int MS_BETWEEN_ANIMATION_STEP = 10;

    private final Color colorOfUnderline;
    private final Color backgroundDuringAnimation;
    private final int thickness;

    private int currentAnimationStep;

    /**
     * @param colorOfUnderline
     * @param backgroundDuringAnimation
     * @param thickness
     * @param currentAnimationStep
     */
    private AnimatedUnderlineBorder(final Color colorOfUnderline, final Color backgroundDuringAnimation, final int thickness, final int currentAnimationStep) {
	assert 1 <= currentAnimationStep && currentAnimationStep <= ANIMATION_STEPS;

	this.colorOfUnderline = colorOfUnderline;
	this.backgroundDuringAnimation = backgroundDuringAnimation;
	this.thickness = thickness;
	this.currentAnimationStep = currentAnimationStep;
    }

    /**
     * @param foreground - Color of the underline.
     * @param background - Background which is visible while the underline is built.
     * @param thickness  - Thickness of the resulting underline.
     */
    public AnimatedUnderlineBorder(final Color foreground, final Color background, final int thickness) {
	this(foreground, background, thickness, STANDARD_FIRST_STEP);
    }

    /**
     * Constructor with default thickness of 2.
     * 
     * @param foreground - Color of the underline.
     * @param background - Background which is visible while the underline is built.
     */
    public AnimatedUnderlineBorder(final Color foreground, final Color background) {
	this(foreground, background, STANDARD_THICKNESS);
    }

    /**
     * Constructor with default invisible background and thickness of 2.
     * 
     * @param underlineColor
     */
    public AnimatedUnderlineBorder(final Color underlineColor) {
	this(underlineColor, STANDARD_BACKGROUND);
    }

    @Override
    public void paintBorder(final Component component, final Graphics context, final int x, final int y, final int width, final int height) {
	context.setColor(backgroundDuringAnimation);
	context.fillRect(x, height - thickness, width, thickness);
	context.setColor(colorOfUnderline);
	final int currentWidth = (int) (((double) currentAnimationStep / ANIMATION_STEPS) * width);
	final int middleX = (width / 2) - (currentWidth / 2);
	context.fillRect(middleX, height - thickness, currentWidth, thickness);
	if (currentAnimationStep <= ANIMATION_STEPS) {
	    setupNextStep(component);
	}
    }

    private void setupNextStep(final Component component) {
	final Timer timer = new Timer(MS_BETWEEN_ANIMATION_STEP, (nextStep) -> {
	    final JComponent jComponent = (JComponent) component;
	    jComponent.setBorder(new AnimatedUnderlineBorder(colorOfUnderline, backgroundDuringAnimation, thickness, currentAnimationStep + 1));
	});
	timer.setRepeats(false);
	timer.start();
    }

    @Override
    public Insets getBorderInsets(final Component component) {
	return getBorderInsets(component, new Insets(0, 0, 0, 0));
    }

    @Override
    public Insets getBorderInsets(final Component component, Insets insets) {
	insets.set(0, 0, thickness, 0);
	return insets;
    }

}
