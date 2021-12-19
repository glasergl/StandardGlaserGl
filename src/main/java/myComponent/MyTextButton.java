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

import eventListener.CursorChangerOnHover;
import eventListener.emptyImplementation.MyMouseListener;
import settings.Colors;
import settings.Fonts;

public class MyTextButton extends JLabel {

    private static final Color BACKGROUND = Colors.getGray(4);
    private static final Color BACKGROUND_WHILE_FOCUSED = Colors.getGray(5);
    private static final Color OF_TEXT = Colors.ofText();
    private static final int ADDITIONAL_WIDTH = 25;
    private static final int ADDITIONAL_HEIGHT = 8;
    private static final Border STANDARD_BORDER = new CompoundBorder(new LineBorder(Colors.getBlue(1), 3), new EmptyBorder(ADDITIONAL_HEIGHT, ADDITIONAL_WIDTH, ADDITIONAL_HEIGHT, ADDITIONAL_WIDTH));

    private final List<ActionListener> actionListeners = new ArrayList<>();
    private final boolean withBorder;

    public MyTextButton(final String text, final boolean withBorder) {
	super(text);
	if (text.length() == 0) {
	    throw new IllegalArgumentException("Button text has to contain atleast one character.");
	}
	this.withBorder = withBorder;
	setup();
    }

    public MyTextButton(final String text) {
	this(text, true);
    }

    private void setup() {
	setOpaque(true);
	setBackground(BACKGROUND);
	setForeground(OF_TEXT);
	setHorizontalAlignment(SwingConstants.CENTER);
	setVerticalAlignment(SwingConstants.CENTER);
	setFont(Fonts.standard());
	if (withBorder) {
	    setBorder(STANDARD_BORDER);
	}
	addMouseListener(new CursorChangerOnHover(new Cursor(Cursor.HAND_CURSOR)));
	addMouseListener(new ButtonController());
    }

    public void addActionListener(final ActionListener toAdd) {
	actionListeners.add(toAdd);
    }

    private final class ButtonController extends MyMouseListener {

	private Color old;

	@Override
	public void mouseClicked(MouseEvent e) {
	    final ActionEvent click = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "");
	    for (final ActionListener actionListener : actionListeners) {
		actionListener.actionPerformed(click);
	    }
	    requestFocus();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	    old = getBackground();
	    setBackground(BACKGROUND_WHILE_FOCUSED);
	}

	@Override
	public void mouseExited(MouseEvent e) {
	    setBackground(old);
	}

    }

}
