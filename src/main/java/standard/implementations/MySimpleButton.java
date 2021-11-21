package standard.implementations;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import standard.helper.CursorChangerOnHover;
import standard.settings.Colors;
import standard.settings.Fonts;

/**
 * Implementation of a simple button which displays text or an icon.
 *
 * @author Gabriel Glaser
 * @version 17.11.2021
 */
public class MySimpleButton extends JLabel {

	private final List<ActionListener> actionListeners = new ArrayList<>();

	public MySimpleButton(final String initialText) {
		super(initialText);
		setOpaque(true);
		setBackground(Colors.getBackground(3));
		setForeground(Colors.ofText());
		setFont(Fonts.standard());
		setHorizontalAlignment(SwingConstants.CENTER);
		addMouseListener(new SimpleButtonController());
		addMouseListener(new CursorChangerOnHover(new Cursor(Cursor.HAND_CURSOR)));
	}

	public MySimpleButton(final Icon toDisplay) {
		this("");
		setIcon(toDisplay);
	}

	public MySimpleButton() {
		this("");
	}

	public void addActionListener(final ActionListener toAdd) {
		actionListeners.add(toAdd);
	}

	private final class SimpleButtonController implements MouseListener {

		private Optional<Color> originalBackground;

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
			originalBackground = Optional.of(getBackground());
			setBackground(Colors.getBackground(4));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			setBackground(originalBackground.get());
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

	}

}
