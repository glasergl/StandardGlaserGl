package standardSwing.myComponent.textFieldD;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

import standardSwing.eventListener.BorderChangerOnFocus;
import standardSwing.eventListener.CursorChangerOnHover;
import standardSwing.eventListener.emptyImplementation.MyFocusListener;
import standardSwing.eventListener.emptyImplementation.MyMouseListener;

/**
 * An implementation of a TextField which displays a hint-text and a X-Button to
 * clear the text.
 *
 * @author Gabriel Glaser
 * @version 30.12.2021
 */
public class MyTextField extends JPanel {

    protected final String hint;
    protected final MyTextFieldWithXButton textField;
    protected final JTextField baseImplementation;
    protected final JLabel hintDisplay;

    public MyTextField(final String hint, final String initialText, final int columns) {
	super();
	this.textField = new MyTextFieldWithXButton(initialText);
	this.baseImplementation = textField.getBaseImplementation();
	this.hint = hint;
	this.hintDisplay = new JLabel(hint);
	setColumns(columns);
	setup();
    }

    public MyTextField(final String hint, final int columns) {
	this(hint, "", columns);
    }

    public MyTextField(final int columns) {
	this("", columns);
    }

    public MyTextField(final String hint, final String initialText) {
	this(hint, initialText, MyTextFieldAttributes.getStandardNumberOfColumns());
    }

    public MyTextField(final String hint) {
	this(hint, "");
    }

    public MyTextField() {
	this("");
    }

    public void setText(final String newText) {
	baseImplementation.setText(newText);
	if (newText.length() > 0) {
	    textField.setVisible(true);
	    hintDisplay.setFont(getReducedHintFont());
	    setBorder(MyTextFieldAttributes.getBorder());
	} else {
	    textField.setVisible(false);
	    hintDisplay.setFont(getFont());
	    setBorder(MyTextFieldAttributes.getBorder());
	}
    }

    public String getText() {
	return baseImplementation.getText();
    }

    public boolean hasText() {
	final String currentText = baseImplementation.getText();
	return currentText.length() > 0;
    }

    public void setColumns(final int newColumns) {
	baseImplementation.setColumns(newColumns);
    }

    public int getColumns() {
	return baseImplementation.getColumns();
    }

    public JTextField getBaseImplementation() {
	return baseImplementation;
    }

    public void addMouseListener(final MouseListener toAdd) {
	textField.addMouseListener(toAdd);
    }

    public void addDocumentListener(final DocumentListener toAdd) {
	textField.addDocumentListener(toAdd);
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
	final JLabel toCalculatePreferredSize = new JLabel(hint);
	toCalculatePreferredSize.setFont(getReducedHintFont());
	final Dimension preferredSizeOfTextField = textField.getPreferredSize();
	final Dimension preferredSizeOfHintDisplay = toCalculatePreferredSize.getPreferredSize();
	return new Dimension(Math.max(preferredSizeOfTextField.width, preferredSizeOfHintDisplay.width), preferredSizeOfTextField.height + preferredSizeOfHintDisplay.height);
    }

    private void setup() {
	setLayout(new BorderLayout());
	setBackground(MyTextFieldAttributes.getBackgroundColor());
	setForeground(MyTextFieldAttributes.getForegroundColor());
	setFont(MyTextFieldAttributes.getFont());
	setBorder(MyTextFieldAttributes.getBorder());
	baseImplementation.addFocusListener(new BorderChangerOnFocus(this, MyTextFieldAttributes.getBorderWhileFocused()));

	setupHintDisplay();
	setupTextField();

	add(hintDisplay, BorderLayout.CENTER);
	add(textField, BorderLayout.SOUTH);

	showInitialHintState();
    }

    private void setupHintDisplay() {
	hintDisplay.setForeground(MyTextFieldAttributes.getHintTextColor());
	hintDisplay.addMouseListener(new CursorChangerOnHover(new Cursor(Cursor.TEXT_CURSOR)));
	hintDisplay.addMouseListener(new MyMouseListener() {
	    public void mouseClicked(MouseEvent mouseEvent) {
		textField.setVisible(true);
		hintDisplay.setFont(getReducedHintFont());
		baseImplementation.requestFocus();
	    }
	});
    }

    private void setupTextField() {
	baseImplementation.addFocusListener(new MyFocusListener() {
	    public void focusLost(FocusEvent focusEvent) {
		if (!hasText()) {
		    textField.setVisible(false);
		    hintDisplay.setFont(getFont());
		    setBorder(MyTextFieldAttributes.getBorder());
		}
	    }
	});
    }

    private void showInitialHintState() {
	if (!hasText()) {
	    hintDisplay.setFont(getFont());
	    textField.setVisible(false);
	} else {
	    hintDisplay.setFont(getReducedHintFont());
	}
    }

    private Font getReducedHintFont() {
	final Font fontOfTextField = getFont();
	final float smallerSize = MyTextFieldAttributes.getHintSizeFactor() * fontOfTextField.getSize2D();
	return fontOfTextField.deriveFont(smallerSize);
    }

}
