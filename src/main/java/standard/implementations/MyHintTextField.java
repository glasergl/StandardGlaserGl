package standard.implementations;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.util.Optional;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentListener;

import standard.helper.emptyListenerImplementations.MyFocusListener;
import standard.helper.emptyListenerImplementations.MyMouseListener;
import standard.helper.listeners.CursorChangerOnHover;
import standard.settings.Colors;
import standard.settings.Fonts;

/**
 * An implementation of a TextField which displays a hint at the background when
 * there is no textinput, yet.
 *
 * @author Gabriel Glaser
 * @version 17.11.2021
 */
public class MyHintTextField extends JPanel {

	private static final Border FOCUSED_BORDER = new LineBorder(Colors.ofFocus(), 1);
	private static final Border UNFOCUSED_BORDER = new LineBorder(Colors.ofText(), 1);
	private static final Color HINT_TEXT_COLOR = new Color(127, 127, 127);
	private static final float HINT_SIZE_FACTOR = 5.0f / 7.0f;
	private static final int NUMBER_OF_HINT_TRANSITION_STEPS = 20;
	private static final int LENGTH_OF_STEP_MS = 5;

	protected final String hint;
	protected final JTextField textField;
	private final JLabel hintDisplay;

	public MyHintTextField(final String hint, final String initialContent) {
		super();
		this.hint = hint;
		this.textField = new JTextField(initialContent);
		this.hintDisplay = new JLabel(hint);
		setup();
	}

	public MyHintTextField(final String hint) {
		this(hint, "");
	}

	public void setText(final String newContent) {
		textField.setText(newContent);
	}

	public String getText() {
		return textField.getText();
	}

	public String getHint() {
		return hint;
	}

	public void setColumns(final int columns) {
		textField.setColumns(columns);
		hintDisplay.setPreferredSize(new Dimension((int) textField.getPreferredSize().getWidth(),
				(int) hintDisplay.getPreferredSize().getHeight()));
	}

	public void addDocumentListener(final DocumentListener toAdd) {
		textField.getDocument().addDocumentListener(toAdd);
	}

	@Override
	public void setBackground(final Color newBackground) {
		super.setBackground(newBackground);
		if (textField != null && hintDisplay != null) {
			textField.setBackground(newBackground);
			hintDisplay.setBackground(newBackground);
		}
	}

	@Override
	public void setFont(final Font newFont) {
		super.setFont(newFont);
		if (textField != null) {
			textField.setFont(newFont);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		final Dimension preferredSizeOfTextField = textField.getPreferredSize();
		final Dimension preferredSizeOfHintDisplay = hintDisplay.getPreferredSize();
		return new Dimension((int) preferredSizeOfTextField.getWidth(),
				(int) (preferredSizeOfTextField.getHeight() + preferredSizeOfHintDisplay.getHeight()));
	}

	private void setup() {
		setLayout(new BorderLayout());
		setBackground(Colors.getGray(2));
		setFont(Fonts.standard());
		setBorder(UNFOCUSED_BORDER);

		textField.setForeground(Colors.ofText());
		textField.setBorder(new EmptyBorder(0, 0, 0, 0));
		textField.addFocusListener(new BorderController());
		textField.addFocusListener(new HintDeactivaterForTextField());

		hintDisplay.setForeground(HINT_TEXT_COLOR);
		hintDisplay.addMouseListener(new HintActivaterForHintLabel());
		hintDisplay.addMouseListener(new CursorChangerOnHover(new Cursor(Cursor.TEXT_CURSOR)));

		showInitialState();
	}

	private void showInitialState() {
		if (textField.getText().length() == 0) {
			hintDisplay.setFont(textField.getFont());
			add(hintDisplay, BorderLayout.CENTER);
		} else {
			final Font ofTextField = textField.getFont();
			final Font smallerFont = ofTextField.deriveFont(HINT_SIZE_FACTOR * ofTextField.getSize2D());
			hintDisplay.setFont(smallerFont);
			add(hintDisplay, BorderLayout.NORTH);
			add(textField, BorderLayout.CENTER);
		}
	}

	private void showFocusedState() {
		remove(hintDisplay);
		new HintAnimationTransitioner(true);
		add(hintDisplay, BorderLayout.NORTH);
		add(textField, BorderLayout.CENTER);
		revalidate();
		repaint();
	}

	private void showUnfocusedState() {
		if (textField.getText().length() == 0) {
			remove(textField);
			remove(hintDisplay);
			add(hintDisplay, BorderLayout.CENTER);
			new HintAnimationTransitioner(false);
			revalidate();
			repaint();
		}
	}

	private class BorderController extends MyFocusListener {
		private Optional<Border> old = Optional.empty();

		@Override
		public void focusGained(FocusEvent e) {
			old = Optional.of(getBorder());
			setBorder(FOCUSED_BORDER);
		}

		@Override
		public void focusLost(FocusEvent e) {
			setBorder(old.get());
			old = Optional.empty();
		}
	}

	private class HintActivaterForHintLabel extends MyMouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (!textField.isFocusOwner()) {
				showFocusedState();
				textField.requestFocusInWindow();
			}
		}
	}

	private class HintDeactivaterForTextField extends MyFocusListener {
		@Override
		public void focusLost(FocusEvent e) {
			showUnfocusedState();
		}
	}

	private class HintAnimationTransitioner implements ActionListener {

		private final Timer timer = new Timer(LENGTH_OF_STEP_MS, this);
		private final Font startFont;
		private final Font endFont;
		private final float startFontSize;
		private final float endFontSize;
		private int currentTransitionIndex = 1;

		public HintAnimationTransitioner(final boolean fromFocusedToUnfocused) {
			super();
			startFont = hintDisplay.getFont();
			if (fromFocusedToUnfocused) {
				endFont = textField.getFont().deriveFont(HINT_SIZE_FACTOR * textField.getFont().getSize2D());
			} else {
				endFont = textField.getFont();
			}
			startFontSize = startFont.getSize2D();
			endFontSize = endFont.getSize2D();
			timer.start();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (currentTransitionIndex <= NUMBER_OF_HINT_TRANSITION_STEPS) {
				final float alpha = (float) currentTransitionIndex / NUMBER_OF_HINT_TRANSITION_STEPS;
				final float newSize = (1 - alpha) * startFontSize + alpha * endFontSize;
				final Font newFont = startFont.deriveFont(newSize);
				hintDisplay.setFont(newFont);
				currentTransitionIndex++;
			} else {
				timer.stop();
			}
		}
	}
}
