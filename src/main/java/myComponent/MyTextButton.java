package myComponent;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import entity.ColorType;
import eventListener.ColorChangerOnHover;
import eventListener.CursorChangerOnHover;
import eventListener.emptyImplementation.MyMouseListener;
import settings.Colors;
import settings.Fonts;

public class MyTextButton extends JLabel {

    private static final Color STANDARD_BACKGROUND = Colors.getGray(2);
    private static final Color STANDARD_BACKGROUND_WHILE_MOUSE_HOVERED = Colors.getGray(4);
    private static final Color STANDARD_COLOR_OF_TEXT = Colors.ofText();
    private static final int ADDITIONAL_WIDTH = 25;
    private static final int ADDITIONAL_HEIGHT = 8;
    private static final Border STANDARD_BORDER = new CompoundBorder(new LineBorder(Colors.getBlue(1), 2), new EmptyBorder(ADDITIONAL_HEIGHT, ADDITIONAL_WIDTH, ADDITIONAL_HEIGHT, ADDITIONAL_WIDTH));
    private static final boolean STANDARD_WITH_BORDER = true;

    private final List<ActionListener> actionListeners = new ArrayList<>();
    private final boolean withBorder;

    private Color backgroundWhileMouseHovered = STANDARD_BACKGROUND_WHILE_MOUSE_HOVERED;
    private Color foregroundWhileMouseHovered = STANDARD_COLOR_OF_TEXT;
    private ColorChangerOnHover mouseListenerForBackground = new ColorChangerOnHover(backgroundWhileMouseHovered, ColorType.BACKGROUND);
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
	setBackground(STANDARD_BACKGROUND);
	setForeground(STANDARD_COLOR_OF_TEXT);
	setHorizontalAlignment(SwingConstants.CENTER);
	setVerticalAlignment(SwingConstants.CENTER);
	setFont(Fonts.standard());
	if (withBorder) {
	    setBorder(STANDARD_BORDER);
	}
	addMouseListener(mouseListenerForBackground);
	addMouseListener(new CursorChangerOnHover(new Cursor(Cursor.HAND_CURSOR)));
	addMouseListener(new ButtonController());
    }

    public void addActionListener(final ActionListener toAdd) {
	actionListeners.add(toAdd);
    }

    public void setBackgroundWhileMouseHovered(final Color backgroundWhileMouseHovered) {
	this.backgroundWhileMouseHovered = backgroundWhileMouseHovered;
	removeMouseListener(mouseListenerForBackground);
	mouseListenerForBackground = new ColorChangerOnHover(backgroundWhileMouseHovered, ColorType.BACKGROUND);
	addMouseListener(mouseListenerForBackground);
    }

    public void setForegroundWhileMouseHovered(final Color foregroundWhileMouseHovered) {
	this.foregroundWhileMouseHovered = foregroundWhileMouseHovered;
	removeMouseListener(mouseListenerForForeground);
	mouseListenerForForeground = new ColorChangerOnHover(foregroundWhileMouseHovered, ColorType.BACKGROUND);
	addMouseListener(mouseListenerForForeground);
    }

    public Color getBackgroundWhileMouseHovered() {
	return backgroundWhileMouseHovered;
    }

    public Color getTextColorWhileMouseHovered() {
	return foregroundWhileMouseHovered;
    }

    private final class ButtonController implements MyMouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
	    final ActionEvent click = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "");
	    for (final ActionListener actionListener : actionListeners) {
		actionListener.actionPerformed(click);
	    }
	    requestFocus();
	}

    }

}
