package myComponent.button;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import entity.ColorType;
import eventListener.ColorChangerOnHover;
import eventListener.CursorChangerOnHover;
import eventListener.emptyImplementation.MyMouseListener;
import settings.Fonts;

/**
 * Button that displays text.
 *
 * @author Gabriel Glaser
 * @version 30.12.2021
 */
public class MyTextButton extends JLabel {

    private static final boolean STANDARD_WITH_BORDER = true;

    private final List<ActionListener> actionListeners = new ArrayList<>();
    private final boolean withBorder;

    private Color backgroundWhileMouseHovers = MyTextButtonAttributes.getStandardBackgroundWhileMouseHovers();
    private Color foregroundWhileMouseHovered = MyTextButtonAttributes.getStandardForeground();
    private ColorChangerOnHover mouseListenerForBackground = new ColorChangerOnHover(backgroundWhileMouseHovers, ColorType.BACKGROUND);
    private ColorChangerOnHover mouseListenerForForeground = new ColorChangerOnHover(foregroundWhileMouseHovered, ColorType.FOREGROUND);

    public MyTextButton(final String text, final boolean withBorder) {
	super(text);
	this.withBorder = withBorder;
	setup();
    }

    public MyTextButton(final String text) {
	this(text, STANDARD_WITH_BORDER);
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
	addMouseListener(mouseListenerForBackground);
	addMouseListener(new CursorChangerOnHover(new Cursor(Cursor.HAND_CURSOR)));
	addMouseListener(new ActionActivater());
    }

    public void addActionListener(final ActionListener toAdd) {
	actionListeners.add(toAdd);
    }

    public void removeActionListener(final ActionListener toRemove) {
	actionListeners.remove(toRemove);
    }

    public void setBackgroundWhileMouseHovered(final Color backgroundWhileMouseHovered) {
	this.backgroundWhileMouseHovers = backgroundWhileMouseHovered;
	removeMouseListener(mouseListenerForBackground);
	mouseListenerForBackground = new ColorChangerOnHover(backgroundWhileMouseHovered, ColorType.BACKGROUND);
	addMouseListener(mouseListenerForBackground);
    }

    public void setForegroundWhileMouseHovered(final Color foregroundWhileMouseHovered) {
	this.foregroundWhileMouseHovered = foregroundWhileMouseHovered;
	removeMouseListener(mouseListenerForForeground);
	mouseListenerForForeground = new ColorChangerOnHover(foregroundWhileMouseHovered, ColorType.FOREGROUND);
	addMouseListener(mouseListenerForForeground);
    }

    public Color getBackgroundWhileMouseHovered() {
	return backgroundWhileMouseHovers;
    }

    public Color getTextColorWhileMouseHovered() {
	return foregroundWhileMouseHovered;
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
