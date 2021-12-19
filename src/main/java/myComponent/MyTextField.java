package myComponent;

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
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentListener;

import eventListener.CursorChangerOnHover;
import eventListener.emptyImplementation.MyFocusListener;
import eventListener.emptyImplementation.MyMouseListener;
import settings.Colors;
import settings.Fonts;

/**
 * An implementation of a TextField which displays a hint at the background when
 * there is no textinput, yet.
 *
 * @author Gabriel Glaser
 * @version 17.11.2021
 */
public class MyTextField extends JPanel {

    private static final Border UNFOCUSED_BORDER = new MatteBorder(0, 0, 2, 0, Colors.ofText());
    private static final Border FOCUSED_BORDER = new MatteBorder(0, 0, 2, 0, Colors.ofFocus());
    private static final Color HINT_TEXT_COLOR = new Color(127, 127, 127);
    private static final float HINT_SIZE_FACTOR = 5.0f / 7.0f;
    private static final int NUMBER_OF_HINT_TRANSITION_STEPS = 10;
    private static final int LENGTH_OF_STEP_MS = 5;

    protected final String hint;
    protected final JTextField textField;
    private final JLabel hintDisplay;

    public MyTextField(final String hint, final String initialContent) {
	super();
	this.hint = hint;
	this.textField = new JTextField(initialContent);
	this.hintDisplay = new JLabel(hint);
	setup();
    }

    public MyTextField(final String hint) {
	this(hint, "");
    }

    public MyTextField() {
	this("");
    }

    public void setText(final String newContent) {
	textField.setText(newContent);
	if (newContent.length() == 0) {
	    showUnfocusedState(false);
	} else {
	    showFocusedState(false);
	}
    }

    public String getText() {
	return textField.getText();
    }

    public String getHint() {
	return hint;
    }

    public void setColumns(final int columns) {
	textField.setColumns(columns);
	hintDisplay.setPreferredSize(new Dimension(textField.getPreferredSize().width, hintDisplay.getPreferredSize().height));
    }

    public int getColumns() {
	return textField.getColumns();
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
	final JTextField textFieldToCalculatePreferredSize = new JTextField();
	textFieldToCalculatePreferredSize.setFont(getFont());
	textFieldToCalculatePreferredSize.setColumns(getColumns());
	final JLabel labelToCalculatePreferredSize = new JLabel("calculate preferred size");
	labelToCalculatePreferredSize.setFont(getFont().deriveFont(HINT_SIZE_FACTOR * getFont().getSize2D()));
	final Dimension preferredSizeOfTextField = textFieldToCalculatePreferredSize.getPreferredSize();
	final Dimension preferredSizeOfHintDisplay = labelToCalculatePreferredSize.getPreferredSize();
	final int preferredWidth = preferredSizeOfTextField.width;
	final int preferredHeight = preferredSizeOfTextField.height + (hint.length() > 0 ? preferredSizeOfHintDisplay.height : 0);
	return new Dimension(preferredWidth, preferredHeight);
    }

    private void setup() {
	setLayout(new BorderLayout());
	setBackground(Colors.getGray(2));
	setFont(Fonts.standard());
	setBorder(UNFOCUSED_BORDER);

	textField.setForeground(Colors.ofText());
	textField.setBorder(new EmptyBorder(0, 1, 0, 0));
	textField.addFocusListener(new BorderController());
	textField.addFocusListener(new HintDeactivaterForTextField());

	hintDisplay.setForeground(HINT_TEXT_COLOR);
	hintDisplay.addMouseListener(new HintActivaterForHintLabel());
	hintDisplay.addMouseListener(new CursorChangerOnHover(new Cursor(Cursor.TEXT_CURSOR)));
	hintDisplay.setBorder(new EmptyBorder(0, 1, 0, 0));

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

    private void showFocusedState(final boolean withAnimation) {
	remove(hintDisplay);
	if (withAnimation) {
	    new HintAnimationTransitioner(true);
	}
	add(hintDisplay, BorderLayout.NORTH);
	add(textField, BorderLayout.CENTER);
	revalidate();
	repaint();
    }

    private void showUnfocusedState(final boolean withAnimation) {
	if (textField.getText().length() == 0) {
	    remove(textField);
	    remove(hintDisplay);
	    add(hintDisplay, BorderLayout.CENTER);
	    if (withAnimation) {
		new HintAnimationTransitioner(false);
	    }
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
		showFocusedState(true);
		textField.requestFocusInWindow();
	    }
	}
    }

    private class HintDeactivaterForTextField extends MyFocusListener {
	@Override
	public void focusLost(FocusEvent e) {
	    showUnfocusedState(true);
	}
    }

    private class HintAnimationTransitioner implements ActionListener {

	private final Timer timer = new Timer(LENGTH_OF_STEP_MS, this);
	private final Font startFont;
	private final Font endFont;
	private final float startFontSize;
	private final float endFontSize;
	private final boolean fromFocusedToUnfocused;

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
	    this.fromFocusedToUnfocused = fromFocusedToUnfocused;
	    timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    if (currentTransitionIndex <= NUMBER_OF_HINT_TRANSITION_STEPS) {
		final float alpha = (float) currentTransitionIndex / NUMBER_OF_HINT_TRANSITION_STEPS;
		final float newSize = (1 - alpha) * startFontSize + alpha * endFontSize;
		final Font newFont = startFont.deriveFont(newSize);
		hintDisplay.setFont(newFont);
		if (NUMBER_OF_HINT_TRANSITION_STEPS / 2 <= currentTransitionIndex) {
		    hintDisplay.setVerticalAlignment(fromFocusedToUnfocused ? SwingConstants.TOP : SwingConstants.CENTER);
		}
		currentTransitionIndex++;
	    } else {
		timer.stop();
	    }
	}
    }
}
