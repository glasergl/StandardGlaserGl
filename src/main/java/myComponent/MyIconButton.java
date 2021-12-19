package myComponent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.util.Optional;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import eventListener.CursorChangerOnHover;
import eventListener.emptyImplementation.MyMouseListener;
import settings.Colors;

public class MyIconButton extends JPanel {

    private final JLabel label = new JLabel();

    public MyIconButton(final ImageIcon toDisplay, final int width, final int height) {
	super(new BorderLayout());
	final ImageIcon scaled = new ImageIcon(toDisplay.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
	label.setIcon(scaled);
	label.setBorder(new EmptyBorder(10, 10, 10, 10));
	add(label);
	addMouseListener(new ButtonController());
	addMouseListener(new CursorChangerOnHover(new Cursor(Cursor.HAND_CURSOR)));
    }

    private class ButtonController extends MyMouseListener {

	private Optional<Color> oldBackground = Optional.empty();

	@Override
	public void mouseEntered(final MouseEvent e) {
	    oldBackground = Optional.of(getBackground());
	    setBackground(Colors.ofFocus());
	}

	@Override
	public void mouseExited(final MouseEvent e) {
	    setBackground(oldBackground.get());
	}
    }

}
