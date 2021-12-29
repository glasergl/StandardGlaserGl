package myComponent.textField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import eventListener.CursorChangerOnHover;
import eventListener.emptyImplementation.MyFocusListener;
import eventListener.emptyImplementation.MyMouseListener;

/**
 * An implementation of a TextField which displays a hint-text.
 *
 * @author Gabriel Glaser
 * @version 29.12.2021
 */
public class MyHintTextField extends JPanel {

    protected final String hint;
    protected final MyTextField textField;
    protected final JLabel hintDisplay;

    public MyHintTextField(final String hint, final String initialText) {
	super();
	if (hint.length() == 0) {
	    throw new IllegalArgumentException("The hint has to contain atleast one character. Use MyTextField instead.");
	}
	this.textField = new MyTextField(initialText);
	this.hint = hint;
	this.hintDisplay = new JLabel(hint);
	setup();
    }

    public MyHintTextField(final String hint) {
	this(hint, "");
    }

    public void setHint(final String newHint) {
	if (newHint.length() == 0) {
	    throw new IllegalArgumentException("The hint has to contain atleast one character. Use MyTextField instead.");
	}
	hintDisplay.setText(newHint);
    }

    public String getHint() {
	return hint;
    }

    public void setText(final String newText) {
	textField.setText(newText);
    }

    public String getText() {
	return textField.getText();
    }

    public void setColumns(final int newColumns) {
	textField.setColumns(newColumns);
    }

    public int getColumns() {
	return textField.getColumns();
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
    public void setForeground(final Color newForeground) {
	super.setForeground(newForeground);
	if (textField != null) {
	    textField.setForeground(newForeground);
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
	final JLabel toCalculatePreferredSize = new JLabel("do not remove this text");
	toCalculatePreferredSize.setFont(getHintFont());
	final Dimension preferredSizeOfTextField = textField.getPreferredSize();
	final Dimension preferredSizeOfHintDisplay = toCalculatePreferredSize.getPreferredSize();
	return new Dimension(preferredSizeOfTextField.width, preferredSizeOfTextField.height + preferredSizeOfHintDisplay.height);
    }

    private void setup() {
	setLayout(new BorderLayout());
	setBackground(textField.getBackground());

	setupTextField();
	setupHintDisplay();

	add(hintDisplay, BorderLayout.CENTER);
	add(textField, BorderLayout.SOUTH);

	showInitialState();
    }

    private void setupTextField() {
	textField.addFocusListener(new MyFocusListener() {
	    public void focusLost(FocusEvent focusEvent) {
		if (!textField.hasText()) {
		    textField.setVisible(false);
		    new HintAnimationTransitioner(false);
		    setBorder(TextFieldAttributes.UNFOCUSED_BORDER);
		}
	    }
	});
    }

    private void setupHintDisplay() {
	hintDisplay.setForeground(TextFieldAttributes.HINT_TEXT_COLOR);
	hintDisplay.addMouseListener(new CursorChangerOnHover(new Cursor(Cursor.TEXT_CURSOR)));
	hintDisplay.addMouseListener(new MyMouseListener() {
	    public void mouseClicked(MouseEvent mouseEvent) {
		new HintAnimationTransitioner(true);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		textField.setVisible(true);
		textField.requestFocus();
	    }
	});
    }

    private void showInitialState() {
	if (!textField.hasText()) {
	    setBorder(TextFieldAttributes.UNFOCUSED_BORDER);
	    hintDisplay.setFont(textField.getFont());
	    textField.setVisible(false);
	} else {
	    hintDisplay.setFont(getHintFont());
	}
    }

    private Font getHintFont() {
	final Font fontOfTextField = textField.getFont();
	final float smallerSize = TextFieldAttributes.HINT_SIZE_FACTOR * fontOfTextField.getSize2D();
	return fontOfTextField.deriveFont(smallerSize);
    }

    private final class HintAnimationTransitioner implements ActionListener {

	private final Timer timer = new Timer(TextFieldAttributes.LENGTH_OF_STEP_MS, this);
	private final Font startFont;
	private final Font endFont;
	private final float startFontSize;
	private final float endFontSize;

	private int currentTransitionIndex = 1;

	public HintAnimationTransitioner(final boolean fromFocusedToUnfocused) {
	    super();
	    startFont = hintDisplay.getFont();
	    if (fromFocusedToUnfocused) {
		endFont = textField.getFont().deriveFont(TextFieldAttributes.HINT_SIZE_FACTOR * textField.getFont().getSize2D());
	    } else {
		endFont = textField.getFont();
	    }
	    startFontSize = startFont.getSize2D();
	    endFontSize = endFont.getSize2D();
	    timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    if (currentTransitionIndex <= TextFieldAttributes.NUMBER_OF_HINT_TRANSITION_STEPS) {
		final float alpha = (float) currentTransitionIndex / TextFieldAttributes.NUMBER_OF_HINT_TRANSITION_STEPS;
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
