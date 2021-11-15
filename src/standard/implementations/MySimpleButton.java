package standard.implementations;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import standard.helper.CursorChangerOnHover;
import standard.settings.Colors;
import standard.settings.Fonts;

public class MySimpleButton extends JLabel {

    private final List<ActionListener> actionListeners = new ArrayList<>();

    public MySimpleButton(final String initialText) {
	super(initialText);
	setOpaque(true);
	setBackground(Colors.getBackground(3));
	setFont(Fonts.standard);
	setHorizontalAlignment(SwingConstants.CENTER);
	addMouseListener(new SimpleButtonController());
	addMouseListener(new CursorChangerOnHover(new Cursor(Cursor.HAND_CURSOR)));
    }

    public MySimpleButton(final int initialNumber) {
	this(String.valueOf(initialNumber));
    }

    public MySimpleButton() {
	this("");
    }

    public void addActionListener(final ActionListener toAdd) {
	actionListeners.add(toAdd);
    }

    private final class SimpleButtonController implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
	    final ActionEvent click = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "");
	    for (final ActionListener actionListener : actionListeners) {
		actionListener.actionPerformed(click);
	    }
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	    setBackground(Colors.getBackground(4));
	}

	@Override
	public void mouseExited(MouseEvent e) {
	    setBackground(Colors.getBackground(3));
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

    }

}
