package standard.implementations;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import standard.helper.CursorChangerOnHover;
import standard.settings.Fonts;

public class MyTextButton extends JLabel {

    private static final Color BACKGROUND = new Color(210, 210, 210);
    private static final Color BACKGROUND_WHILE_HOVERED = new Color(190, 190, 190);
    private static final Color OF_TEXT = Color.BLACK;

    private final List<ActionListener> actionListeners = new ArrayList<>();

    public MyTextButton(final String text) {
	super(text);
	setOpaque(true);
	setBackground(BACKGROUND);
	setForeground(OF_TEXT);
	setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	setFont(Fonts.standard());
	addMouseListener(new CursorChangerOnHover(new Cursor(Cursor.HAND_CURSOR)));
	addMouseListener(new ButtonController());
    }

    public void addActionListener(final ActionListener toAdd) {
	actionListeners.add(toAdd);
    }

    private final class ButtonController implements MouseListener {

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
	    setBackground(BACKGROUND_WHILE_HOVERED);
	}

	@Override
	public void mouseExited(MouseEvent e) {
	    setBackground(BACKGROUND);
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

    }

}
