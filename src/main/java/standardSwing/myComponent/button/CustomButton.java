package standardSwing.myComponent.button;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Optional;
import javax.swing.AbstractButton;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import standardSwing.entity.ColorType;
import standardSwing.eventListener.ColorChangerOnHover;
import standardSwing.eventListener.CursorChangerOnHover;
import standardSwing.eventListener.emptyImplementation.MyMouseListener;
import standardSwing.general.SwingFunctions;
import standardSwing.settings.Colors;
import standardSwing.settings.Fonts;

/**
 * Fully customizable button which displays text.
 *
 * @author Gabriel Glaser
 * @version 12.02.2022
 */
public class CustomButton extends AbstractButton {

    private static final Border DEFAULT_BORDER = new EmptyBorder(0, 0, 0, 0);
    private static final float STANDARD_BACKGROUND_COLOR_CHANGE_FACTOR = 0.1f;
    private static final float STANDARD_FOREGROUND_COLOR_CHANGE_FACTOR = 0.1f;
    private static final int STANDARD_BACKGROUND_COLOR_CHANGE_OFFSET = 10;
    private static final int STANDARD_FOREGROUND_COLOR_CHANGE_OFFSET = 60;
    private static final boolean STANDARD_CHANGE_BACKGROUND_ON_HOVER = true;
    private static final boolean STANDARD_CHANGE_FOREGROUND_ON_HOVER = false;

    private static Font defaultFont = Fonts.standard();

    private final boolean changeBackground;
    private final boolean changeTextColor;
    private final Optional<Color> mouseBackground;
    private final Optional<Color> mouseTextColor;

    private String currentText;

    private CustomButton(final String initialText, final Color background, final Color textColor, final Border border, final Optional<Color> mouseBackground, final Optional<Color> mouseTextColor, final boolean changeBackground,
	    final boolean changeTextColor) {
	super();
	this.mouseBackground = mouseBackground;
	this.mouseTextColor = mouseTextColor;
	this.changeBackground = changeBackground;
	this.changeTextColor = changeTextColor;
	this.currentText = initialText;
	setBackground(background);
	setForeground(textColor);
	setBorder(border);
	setDefaultValues();
	addMouseListener(new ActionListenerNotifier());
	addMouseListener(new CursorChangerOnHover(new Cursor(Cursor.HAND_CURSOR)));
	addCorrectMouseListenerForBackground();
	addCorrectMouseListenerForForeground();
    }

    public CustomButton(final String initialText, final Color background, final Color textColor, final Border border, final Color backgrundWhileMouseHovers, final Color foregroundWhileMouseHovers) {
	this(initialText, background, textColor, border, Optional.of(backgrundWhileMouseHovers), Optional.of(foregroundWhileMouseHovers), true, true);
    }

    public CustomButton(final String initialText, final Color background, final Color textColor, final Border border, final Color backgrundWhileMouseHovers) {
	this(initialText, background, textColor, border, Optional.of(backgrundWhileMouseHovers), Optional.empty(), true, STANDARD_CHANGE_FOREGROUND_ON_HOVER);
    }

    public CustomButton(final String initialText, final Color background, final Color textColor, final Border border) {
	this(initialText, background, textColor, border, Optional.empty(), Optional.empty(), STANDARD_CHANGE_BACKGROUND_ON_HOVER, STANDARD_CHANGE_FOREGROUND_ON_HOVER);
    }

    public CustomButton(final Color background, final Color textColor, final Border border) {
	this("", background, textColor, border);
    }

    public CustomButton(final Color background, final Color textColor) {
	this(background, textColor, DEFAULT_BORDER);
    }

    @Override
    public void paintComponent(final Graphics drawContext) {
	SwingFunctions.setAntialiasing(drawContext, true);
	drawContext.setColor(getBackground());
	final Dimension size = getSize();
	drawContext.fillRect((int) 0, (int) 0, size.width, size.height);
	drawContext.setColor(getForeground());
	drawContext.setFont(getFont());
	drawCenteredString(drawContext, currentText, new Rectangle(0, 0, size.width, size.height));
    }

    @Override
    public Dimension getPreferredSize() {
	final Graphics drawContext = getGraphics();
	FontMetrics fontMetrics = drawContext.getFontMetrics();
	return new Dimension(fontMetrics.stringWidth(currentText), fontMetrics.getHeight());
    }

    private void setDefaultValues() {
	setFont(defaultFont);
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

    private void addCorrectMouseListenerForBackground() {
	if (changeBackground) {
	    final Color background;
	    if (mouseBackground.isPresent()) {
		background = mouseBackground.get();
	    } else {
		background = Colors.deriveWithAverage(getBackground(), STANDARD_BACKGROUND_COLOR_CHANGE_FACTOR, STANDARD_BACKGROUND_COLOR_CHANGE_OFFSET);
	    }
	    addMouseListener(new ColorChangerOnHover(background, ColorType.BACKGROUND));
	}
    }

    private void addCorrectMouseListenerForForeground() {
	if (changeTextColor) {
	    final Color foreground;
	    if (mouseTextColor.isPresent()) {
		foreground = mouseTextColor.get();
	    } else {
		foreground = Colors.deriveWithAverage(getForeground(), STANDARD_FOREGROUND_COLOR_CHANGE_FACTOR, STANDARD_FOREGROUND_COLOR_CHANGE_OFFSET);
	    }
	    addMouseListener(new ColorChangerOnHover(foreground, ColorType.FOREGROUND));
	}
    }

    /**
     * MouseListener which notifies all added ActionListeners that a click occurred.
     *
     * @author Gabriel Glaser
     * @version 12.02.2022
     */
    private final class ActionListenerNotifier implements MyMouseListener {
	@Override
	public void mouseClicked(final MouseEvent mouseClickEvent) {
	    final ActionEvent click = new ActionEvent(CustomButton.this, ActionEvent.ACTION_PERFORMED, "click");
	    for (final ActionListener toNotify : getActionListeners()) {
		toNotify.actionPerformed(click);
	    }
	}
    }

}
