package myComponent.textField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;
import eventListener.CursorChangerOnHover;
import eventListener.emptyImplementation.MyFocusListener;
import eventListener.emptyImplementation.MyMouseListener;
import myComponent.MyTextButton;
import settings.Colors;
import settings.Fonts;

/**
 * An implementation of a TextField which displays a hint at the background when
 * there is no textinput, yet.
 *
 * @author Gabriel Glaser
 * @version 29.12.2021
 */
public class MyHintTextField extends JPanel {
    // TODO getText(hasToHaveAValue: boolean) -> error

    protected final String hint;
    protected final JTextField textField;
    private final JLabel hintDisplay;
    private final MyTextButton deleteButton;

    public MyHintTextField(final String hint, final String initialContent) {
	super();
	this.hint = hint;
	this.textField = new JTextField(initialContent);
	this.hintDisplay = new JLabel(hint);
	this.deleteButton = new MyTextButton("X");
	setup();
    }

    public MyHintTextField(final String hint) {
	this(hint, "");
    }

    public MyHintTextField() {
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
	labelToCalculatePreferredSize.setFont(getFont().deriveFont(TextFieldAttributes.HINT_SIZE_FACTOR * getFont().getSize2D()));
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
	setBorder(TextFieldAttributes.UNFOCUSED_BORDER);

	textField.setForeground(Colors.ofText());
	textField.setBorder(new EmptyBorder(0, 1, 0, 0));
	textField.addFocusListener(new HintDeactivaterForTextField());

	hintDisplay.setForeground(TextFieldAttributes.HINT_TEXT_COLOR);
	hintDisplay.addMouseListener(new HintActivaterForHintLabel());
	hintDisplay.addMouseListener(new CursorChangerOnHover(new Cursor(Cursor.TEXT_CURSOR)));
	hintDisplay.setBorder(new EmptyBorder(0, 1, 0, 0));

	deleteButton.setBackground(getBackground());
	deleteButton.setBackgroundWhileMouseHovered(getBackground());
	deleteButton.setForeground(TextFieldAttributes.HINT_TEXT_COLOR);
	deleteButton.setForegroundWhileMouseHovered(Colors.getGray(4));
	deleteButton.setFont(getFont());
	deleteButton.setBorder(new EmptyBorder(0, 0, 0, 5));
	deleteButton.addMouseListener(new CursorChangerOnHover(new Cursor(Cursor.HAND_CURSOR)));
	deleteButton.setFocusable(false);
	deleteButton.addActionListener((click) -> {
	    textField.setText("");
	});
	showInitialState();
    }

    private void showInitialState() {
	if (textField.getText().length() == 0) {
	    hintDisplay.setFont(textField.getFont());
	    add(hintDisplay, BorderLayout.CENTER);
	} else {
	    final Font ofTextField = textField.getFont();
	    final Font smallerFont = ofTextField.deriveFont(TextFieldAttributes.HINT_SIZE_FACTOR * ofTextField.getSize2D());
	    hintDisplay.setFont(smallerFont);
	    add(hintDisplay, BorderLayout.NORTH);
	    add(textField, BorderLayout.CENTER);
	    add(deleteButton, BorderLayout.EAST);
	}
    }

    private void showFocusedState(final boolean withAnimation) {
	remove(hintDisplay);
	if (withAnimation) {
	    new HintAnimationTransitioner(true);
	}
	add(hintDisplay, BorderLayout.NORTH);
	add(textField, BorderLayout.CENTER);
	add(deleteButton, BorderLayout.EAST);
	revalidate();
	repaint();
    }

    private void showUnfocusedState(final boolean withAnimation) {
	if (textField.getText().length() == 0) {
	    remove(textField);
	    remove(hintDisplay);
	    remove(deleteButton);
	    add(hintDisplay, BorderLayout.CENTER);
	    if (withAnimation) {
		new HintAnimationTransitioner(false);
	    }
	    revalidate();
	    repaint();
	}
    }

    private final class HintActivaterForHintLabel extends MyMouseListener {
	@Override
	public void mouseClicked(MouseEvent e) {
	    if (!textField.isFocusOwner()) {
		showFocusedState(true);
		textField.requestFocusInWindow();
	    }
	}
    }

    private final class HintDeactivaterForTextField extends MyFocusListener {
	@Override
	public void focusLost(FocusEvent e) {
	    showUnfocusedState(true);
	}
    }

}
