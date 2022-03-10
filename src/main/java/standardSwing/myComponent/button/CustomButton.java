package standardSwing.myComponent.button;

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
 * @version 10.03.2022
 */
public class CustomButton extends AbstractButton {

    private static final boolean STANDARD_BACKGROUND_CHANGE = true;
    
    private String currentText;
    private Optional<ColorChangerOnHover> currentBackgroundChanger = Optional.empty();
    private Optional<ColorChangerOnHover> currentForegroundChanger = Optional.empty();;

    public CustomButton(final String initialText, final Color background, final Color textColor, final boolean standardBackgroundChange) {
	super();
	this.currentText = initialText;
	setBackground(background);
	setForeground(textColor);
	setFont(Fonts.standard());
	addMouseListener(new ActionListenerNotifier());
	addMouseListener(new CursorChangerOnHover(new Cursor(Cursor.HAND_CURSOR)));
	setBackgroundWhileHovered(Colors.deriveStandOutColor(background));
	setPreferredSize(getDefaultPreferredSize());
    }
    
    public CustomButton(final String initialText, final Color background, final Color textColor) {
	this(initialText, background, textColor, STANDARD_BACKGROUND_CHANGE);
    }

    public void setBackgroundWhileHovered(final Color backgroundWhileHovered) {
	if (currentBackgroundChanger.isPresent()) {
	    removeMouseListener(currentBackgroundChanger.get());
	}
	final ColorChangerOnHover newColorChangerForBackground = new ColorChangerOnHover(backgroundWhileHovered, ColorType.BACKGROUND);
	addMouseListener(newColorChangerForBackground);
	currentBackgroundChanger = Optional.of(newColorChangerForBackground);

    }

    public void setForegroundWhileHovered(final Color foregroundWhileHovered) {
	if (currentForegroundChanger.isPresent()) {
	    removeMouseListener(currentForegroundChanger.get());
	}
	final ColorChangerOnHover newColorChangerForForeground = new ColorChangerOnHover(foregroundWhileHovered, ColorType.FOREGROUND);
	addMouseListener(newColorChangerForForeground);
	currentForegroundChanger = Optional.of(newColorChangerForForeground);
    }

    @Override
    public void paintComponent(final Graphics drawContext) {
	SwingFunctions.setAntialiasing(drawContext, true);
	drawContext.setColor(getBackground());
	final Dimension size = getSize();
	drawContext.fillRect(0, 0, size.width, size.height);
	drawContext.setColor(getForeground());
	drawContext.setFont(getFont());
	drawCenteredString(drawContext, currentText, new Rectangle(0, 0, size.width, size.height));
    }

    public Dimension getDefaultPreferredSize() {
	final FontMetrics fontMetrics = getFontMetrics(getFont());
	final Insets currentInsets = getInsets();
	final int preferredWidth = fontMetrics.stringWidth(currentText) + currentInsets.left + currentInsets.right;
	final int preferredHeight = fontMetrics.getHeight() + currentInsets.top + currentInsets.bottom;
	return new Dimension(preferredWidth, preferredHeight);
    }

    public void setText(final String newText) {
	currentText = newText;
	repaint();
    }

    public String getText() {
	return currentText;
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
