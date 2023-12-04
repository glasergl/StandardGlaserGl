package de.glasergl.standard.swing.myComponent.textField;

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
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;

import de.glasergl.standard.swing.eventListener.CursorChangerOnHover;
import de.glasergl.standard.swing.eventListener.emptyImplementation.MyFocusListener;
import de.glasergl.standard.swing.eventListener.emptyImplementation.MyMouseListener;

/**
 * An implementation of a TextField which displays a hint-text and a X-Button to
 * clear the text.
 *
 * @author Gabriel Glaser
 */
public class MyHintTextField extends JPanel {
    protected final String hint;
    protected final MyTextField textField;
    protected final JTextField baseImplementation;
    protected final JLabel hintDisplay;

    public MyHintTextField(final String hint, final String initialText, final int columns) {
	super();
	this.textField = new MyTextField(initialText);
	this.baseImplementation = textField.getBaseImplementation();
	this.hint = hint;
	this.hintDisplay = new JLabel(hint);
	setColumns(columns);
	setup();
    }

    public MyHintTextField(final String hint, final int columns) {
	this(hint, "", columns);
    }

    public MyHintTextField(final int columns) {
	this("", columns);
    }

    public MyHintTextField(final String hint, final String initialText) {
	this(hint, initialText, MyTextFieldAttributes.getStandardNumberOfColumns());
    }

    public MyHintTextField(final String hint) {
	this(hint, "");
    }

    public MyHintTextField() {
	this("");
    }

    public void setText(final String newText) {
	baseImplementation.setText(newText);
	if (newText.length() > 0) {
	    textField.setVisible(true);
	    hintDisplay.setFont(getReducedHintFont());
	} else {
	    textField.setVisible(false);
	    hintDisplay.setFont(getFont());
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
		} else {
		    setBorder(new EmptyBorder(0, 0, 0, 0));
		}
	    }

	    public void focusGained(FocusEvent focusEvent) {
		setBorder(new EmptyBorder(0, 0, 0, 0));
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
