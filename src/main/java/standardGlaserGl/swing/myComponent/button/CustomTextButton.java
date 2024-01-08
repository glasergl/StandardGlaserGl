package standardGlaserGl.swing.myComponent.button;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Optional;
import javax.swing.AbstractButton;

import standardGlaserGl.swing.border.LeftRightRoundBorder;
import standardGlaserGl.swing.entity.ColorType;
import standardGlaserGl.swing.eventListener.ColorChangerOnHover;
import standardGlaserGl.swing.eventListener.CursorChangerOnHover;
import standardGlaserGl.swing.eventListener.emptyImplementation.MyMouseListener;
import standardGlaserGl.swing.general.SwingFunctions;
import standardGlaserGl.swing.settings.Colors;
import standardGlaserGl.swing.settings.Fonts;

/**
 * Customizable button which displays text.
 *
 * @author Gabriel Glaser
 */
public class CustomTextButton extends AbstractButton {
    private static final boolean STANDARD_BACKGROUND_CHANGE = true;
    private static final Color STANDARD_BACKGROUND_COLOR = Colors.getGray(0);
    private static final Color STANDARD_TEXT_COLOR = Colors.ofText();

    private String currentText;
    private Optional<ColorChangerOnHover> currentBackgroundChanger = Optional.empty();
    private Optional<ColorChangerOnHover> currentForegroundChanger = Optional.empty();
    private Optional<Dimension> customPreferredSizeWrapper = Optional.empty();

    /**
     * @param initialText
     * @param background
     * @param textColor
     * @param standardBackgroundChange
     */
    public CustomTextButton(final String initialText, final Color background, final Color textColor, final boolean standardBackgroundChange) {
	super();
	this.currentText = initialText;
	setBackground(background);
	setForeground(textColor);
	setFont(Fonts.standard());
	setBorder(new LeftRightRoundBorder(15, 15));
	addMouseListener(new ActionListenerNotifier());
	addMouseListener(new CursorChangerOnHover(new Cursor(Cursor.HAND_CURSOR)));
	if (standardBackgroundChange) {
	    setDefaultBackgroundWhileHovered();
	}
    }

    /**
     * CustomTextButton with standard text color.
     * 
     * @param initialText
     * @param background
     * @param standardBackgroundChange
     */
    public CustomTextButton(final String initialText, final Color background, final boolean standardBackgroundChange) {
	this(initialText, background, STANDARD_TEXT_COLOR, standardBackgroundChange);
    }

    /**
     * CustomTextButton with default background change.
     * 
     * @param initialText
     * @param background
     * @param textColor
     */
    public CustomTextButton(final String initialText, final Color background, final Color textColor) {
	this(initialText, background, textColor, STANDARD_BACKGROUND_CHANGE);
    }

    /**
     * CustomTextButton with standard background color and standard text color.
     * 
     * @param initialText
     * @param standardBackgroundChange
     */
    public CustomTextButton(final String initialText, final boolean standardBackgroundChange) {
	this(initialText, STANDARD_BACKGROUND_COLOR, STANDARD_TEXT_COLOR, standardBackgroundChange);
    }

    /**
     * CustomTextButton with standard text color and default background change.
     * 
     * @param initialText
     * @param background
     */
    public CustomTextButton(final String initialText, final Color background) {
	this(initialText, background, STANDARD_TEXT_COLOR, STANDARD_BACKGROUND_CHANGE);
    }

    /**
     * CustomTextButton with a standard background, standard text color default
     * background change.
     * 
     * @param initialText
     */
    public CustomTextButton(final String initialText) {
	this(initialText, STANDARD_BACKGROUND_COLOR, STANDARD_TEXT_COLOR, STANDARD_BACKGROUND_CHANGE);
    }

    /**
     * Calculates a color which stands out to the current background and sets it as
     * the background while the mouse hovers over this.
     */
    public void setDefaultBackgroundWhileHovered() {
	setBackgroundWhileHovered(Colors.deriveStandOutColor(getBackground()));
    }

    /**
     * Sets the given color as the new background while the mouse hovers over this.
     * 
     * @param backgroundWhileHovered
     */
    public void setBackgroundWhileHovered(final Color backgroundWhileHovered) {
	if (currentBackgroundChanger.isPresent()) {
	    removeMouseListener(currentBackgroundChanger.get());
	}
	final ColorChangerOnHover newColorChangerForBackground = new ColorChangerOnHover(backgroundWhileHovered, ColorType.BACKGROUND);
	addMouseListener(newColorChangerForBackground);
	currentBackgroundChanger = Optional.of(newColorChangerForBackground);

    }

    /**
     * Sets the given color as the new foreground while the mouse hovers over this.
     * 
     * @param foregroundWhileHovered
     */
    public void setForegroundWhileHovered(final Color foregroundWhileHovered) {
	if (currentForegroundChanger.isPresent()) {
	    removeMouseListener(currentForegroundChanger.get());
	}
	final ColorChangerOnHover newColorChangerForForeground = new ColorChangerOnHover(foregroundWhileHovered, ColorType.FOREGROUND);
	addMouseListener(newColorChangerForForeground);
	currentForegroundChanger = Optional.of(newColorChangerForForeground);
    }

    /**
     * @return The size which this needs to barely show the whole text.
     */
    @Override
    public Dimension getPreferredSize() {
	if (customPreferredSizeWrapper.isEmpty()) {
	    final FontMetrics fontMetrics = getFontMetrics(getFont());
	    final Insets currentInsets = getInsets();
	    final int preferredWidth = fontMetrics.stringWidth(currentText) + currentInsets.left + currentInsets.right;
	    final int preferredHeight = fontMetrics.getHeight() + currentInsets.top + currentInsets.bottom;
	    return new Dimension(preferredWidth, preferredHeight);
	} else {
	    return customPreferredSizeWrapper.get();
	}
    }

    @Override
    public void setPreferredSize(final Dimension preferredSize) {
	if (preferredSize != null) {
	    this.customPreferredSizeWrapper = Optional.of(preferredSize);
	} else {
	    this.customPreferredSizeWrapper = Optional.empty();
	}
    }

    public void setText(final String newText) {
	currentText = newText;
	revalidate();
	repaint();
    }

    public String getText() {
	return currentText;
    }

    @Override
    protected void paintComponent(final Graphics drawContext) {
	super.paintComponent(drawContext);
	SwingFunctions.setAntialiasing(drawContext, true);
	drawContext.setColor(getBackground());
	final Dimension size = getSize();
	drawContext.fillRect(0, 0, size.width, size.height);
	drawContext.setColor(getForeground());
	drawContext.setFont(getFont());
	drawCenteredString(drawContext, currentText, new Rectangle(0, 0, size.width, size.height));
    }

    /**
     * Draws the given String in the center of the given Rectangle.
     * 
     * @param drawContext
     * @param textToDraw
     * @param boundsToCenterIn
     */
    private void drawCenteredString(final Graphics drawContext, final String textToDraw, final Rectangle boundsToCenterIn) {
	final FontMetrics metrics = drawContext.getFontMetrics();
	final int x = boundsToCenterIn.x + (boundsToCenterIn.width - metrics.stringWidth(textToDraw)) / 2;
	final int y = boundsToCenterIn.y + ((boundsToCenterIn.height - metrics.getHeight()) / 2) + metrics.getAscent();
	drawContext.drawString(textToDraw, x, y);
    }

    /**
     * MouseListener which notifies all added ActionListeners that a click occurred.
     *
     * @author Gabriel Glaser
     */
    private final class ActionListenerNotifier implements MyMouseListener {
	@Override
	public void mouseClicked(final MouseEvent mouseClickEvent) {
	    final ActionEvent click = new ActionEvent(CustomTextButton.this, ActionEvent.ACTION_PERFORMED, "click");
	    for (final ActionListener toNotify : getActionListeners()) {
		toNotify.actionPerformed(click);
	    }
	}
    }
}
