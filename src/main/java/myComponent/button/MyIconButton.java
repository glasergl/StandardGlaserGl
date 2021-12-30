package myComponent.button;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import entity.ColorType;
import eventListener.ColorChangerOnHover;
import eventListener.CursorChangerOnHover;
import eventListener.emptyImplementation.MyMouseListener;
import general.SwingFunctions;
import settings.Colors;

public class MyIconButton extends JLabel {

    private final List<ActionListener> actionListeners = new ArrayList<>();

    public MyIconButton(final ImageIcon toDisplay, final int width, final int height) {
	super(SwingFunctions.scale(toDisplay, width, height));
	setup();
    }

    public MyIconButton(final ImageIcon toDisplay) {
	this(toDisplay, toDisplay.getIconWidth(), toDisplay.getIconHeight());
    }

    public void addActionListener(final ActionListener toAdd) {
	actionListeners.add(toAdd);
    }

    public void removeActionListener(final ActionListener toRemove) {
	actionListeners.remove(toRemove);
    }

    private void setup() {
	setOpaque(true);
	setFocusable(true);
	addMouseListener(new ColorChangerOnHover(Colors.ofFocus(), ColorType.BACKGROUND));
	addMouseListener(new CursorChangerOnHover(new Cursor(Cursor.HAND_CURSOR)));
	addMouseListener(new ActionActivater());
    }

    private final class ActionActivater implements MyMouseListener {
	@Override
	public void mouseClicked(MouseEvent mouseClickEvent) {
	    final ActionEvent click = new ActionEvent(MyIconButton.this, ActionEvent.ACTION_PERFORMED, "");
	    for (final ActionListener actionListener : actionListeners) {
		actionListener.actionPerformed(click);
	    }
	    requestFocus();
	}
    }

}
