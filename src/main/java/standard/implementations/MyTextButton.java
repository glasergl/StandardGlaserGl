package standard.implementations;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import standard.helper.emptyListenerImplementations.MyMouseListener;
import standard.helper.listeners.CursorChangerOnHover;
import standard.settings.Colors;
import standard.settings.Fonts;

public class MyTextButton extends JLabel {

	private static final Color BACKGROUND = new Color(210, 210, 210);
	private static final Color BACKGROUND_WHILE_FOCUSED = new Color(190, 190, 190);
	private static final Color OF_TEXT = Color.BLACK;

	private final List<ActionListener> actionListeners = new ArrayList<>();

	public MyTextButton(final String text) {
		super(text);
		if (text.length() == 0) {
			throw new IllegalArgumentException("Button text has to contain atleast one character.");
		}
		setOpaque(true);
		setBackground(BACKGROUND);
		setForeground(OF_TEXT);
		setHorizontalAlignment(SwingConstants.CENTER);
		setVerticalAlignment(SwingConstants.CENTER);
		setFont(Fonts.standard());
		setBorder(new LineBorder(Colors.ofText(), 1));
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
