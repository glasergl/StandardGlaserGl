package standardSwing.myComponent.textFieldD;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import standardSwing.entity.ColorType;
import standardSwing.eventListener.ColorChangerOnHover;
import standardSwing.eventListener.CursorChangerOnHover;
import standardSwing.eventListener.emptyImplementation.MyMouseListener;
import standardSwing.settings.Colors;
import standardSwing.settings.Fonts;

/**
 * Button that displays text.
 * 
 * Use updateBackground() and updateForeground() to update the respective
 * Colors. They need to be called to update the Background- and
 * ForegroundChangers, too.
 *
 * @author Gabriel Glaser
 * @version 11.2.2022
 */
public class MyTextButton extends JLabel {

    private static final boolean STANDARD_WITH_BORDER = true;
    private static final float STANDARD_BACKGROUND_COLOR_CHANGE_FACTOR = 0.1f;
    private static final float STANDARD_FOREGROUND_COLOR_CHANGE_FACTOR = 0.1f;
    private static final int STANDARD_BACKGROUND_COLOR_CHANGE_OFFSET = 10;
    private static final int STANDARD_FOREGROUND_COLOR_CHANGE_OFFSET = 60;
    private static final boolean STANDARD_CHANGE_BACKGROUND_ON_HOVER = true;
    private static final boolean STANDARD_CHANGE_FOREGROUND_ON_HOVER = false;

    private final List<ActionListener> actionListeners = new ArrayList<>();
    private final boolean withBorder;
    private final boolean changeBackgroundOnHover;
    private final boolean changeForegroundOnHover;
    private final Optional<Color> backgroundWhileMouseHovers;
    private final Optional<Color> foregroundWhileMouseHovers;

    private Optional<ColorChangerOnHover> oldBackgroundMouseListener = Optional.empty();
    private Optional<ColorChangerOnHover> oldForegroundMouseListener = Optional.empty();

    public MyTextButton(final String text, final boolean withBorder, final Optional<Color> backgroundWhileMouseHovers, final Optional<Color> foregroundWhileMouseHovers, final boolean changeBackgroundOnHover, final boolean changeForegroundOnHover) {
	super(text);
	this.withBorder = withBorder;
	this.backgroundWhileMouseHovers = backgroundWhileMouseHovers;
	this.foregroundWhileMouseHovers = foregroundWhileMouseHovers;
	this.changeBackgroundOnHover = changeBackgroundOnHover;
	this.changeForegroundOnHover = changeForegroundOnHover;
	setup();
    }

    public MyTextButton(final String text, final boolean withBorder, final Color backgrundWhileMouseHovers, final Color foregroundWhileMouseHovers) {
	this(text, withBorder, Optional.of(backgrundWhileMouseHovers), Optional.of(foregroundWhileMouseHovers), true, true);
    }

    public MyTextButton(final String text, final boolean withBorder, final Color backgrundWhileMouseHovers) {
	this(text, withBorder, Optional.of(backgrundWhileMouseHovers), Optional.empty(), true, STANDARD_CHANGE_FOREGROUND_ON_HOVER);
    }

    public MyTextButton(final String text, final Color backgrundWhileMouseHovers, final Color foregroundWhileMouseHovers) {
	this(text, STANDARD_WITH_BORDER, Optional.of(backgrundWhileMouseHovers), Optional.of(foregroundWhileMouseHovers), true, true);
    }

    public MyTextButton(final String text, final boolean withBorder) {
	this(text, withBorder, Optional.empty(), Optional.empty(), STANDARD_CHANGE_BACKGROUND_ON_HOVER, STANDARD_CHANGE_FOREGROUND_ON_HOVER);
    }

    public MyTextButton(final String text) {
	this(text, STANDARD_WITH_BORDER);
    }

    public void addActionListener(final ActionListener toAdd) {
	actionListeners.add(toAdd);
    }

    public void removeActionListener(final ActionListener toRemove) {
	actionListeners.remove(toRemove);
    }

    /**
     * Sets the background and updates the background seen when the mouse hovers
     * over this.
     * 
     * The background seen when the mouse hovers is not changed, if
     * changeBackgroundOnHover is false or backgroundWhileMouseHovers is present.
     * 
     * @param newBackground
     */
    public void updateBackground(final Color newBackground) {
	setBackground(newBackground);
	removeMouseListener(oldBackgroundMouseListener.get());
	addCorrectMouseListenerForBackground();
    }

    /**
     * Sets the foreground and updates the foreground seen when the mouse hovers
     * over this.
     * 
     * The foreground seen when the mouse hovers is not changed, if
     * changeForegroundOnHover is false or foregroundWhileMouseHovers is present.
     * 
     * @param newForeground
     */
    public void updateForeground(final Color newForeground) {
	setForeground(newForeground);
	removeMouseListener(oldForegroundMouseListener.get());
	addCorrectMouseListenerForForeground();
    }

    private void setup() {
	setOpaque(true);
	setBackground(MyTextButtonAttributes.getStandardBackground());
	setForeground(MyTextButtonAttributes.getStandardForeground());
	setHorizontalAlignment(SwingConstants.CENTER);
	setVerticalAlignment(SwingConstants.CENTER);
	setFont(Fonts.standard());
	if (withBorder) {
	    setBorder(MyTextButtonAttributes.getStandardBorder());
	}
	addMouseListener(new CursorChangerOnHover(new Cursor(Cursor.HAND_CURSOR)));
	addCorrectMouseListenerForBackground();
	addCorrectMouseListenerForForeground();
	addMouseListener(new ActionActivater());
    }

    private void addCorrectMouseListenerForBackground() {
	if (changeBackgroundOnHover) {
	    final Color background;
	    if (backgroundWhileMouseHovers.isPresent()) {
		background = backgroundWhileMouseHovers.get();
	    } else {
		background = Colors.deriveWithAverage(getBackground(), STANDARD_BACKGROUND_COLOR_CHANGE_FACTOR, STANDARD_BACKGROUND_COLOR_CHANGE_OFFSET);
	    }
	    final ColorChangerOnHover forBackground = new ColorChangerOnHover(background, ColorType.BACKGROUND);
	    oldBackgroundMouseListener = Optional.of(forBackground);
	    addMouseListener(forBackground);
	}
    }

    private void addCorrectMouseListenerForForeground() {
	if (changeForegroundOnHover) {
	    final Color foreground;
	    if (foregroundWhileMouseHovers.isPresent()) {
		foreground = foregroundWhileMouseHovers.get();
	    } else {
		foreground = Colors.deriveWithAverage(getForeground(), STANDARD_FOREGROUND_COLOR_CHANGE_FACTOR, STANDARD_FOREGROUND_COLOR_CHANGE_OFFSET);
	    }
	    final ColorChangerOnHover forForeground = new ColorChangerOnHover(foreground, ColorType.FOREGROUND);
	    oldForegroundMouseListener = Optional.of(forForeground);
	    addMouseListener(forForeground);
	}
    }

    private final class ActionActivater implements MyMouseListener {
	@Override
	public void mouseClicked(final MouseEvent mouseClickEvent) {
	    final ActionEvent click = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "");
	    for (final ActionListener actionListener : actionListeners) {
		actionListener.actionPerformed(click);
	    }
	    requestFocus();
	}

    }

}
