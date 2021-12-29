package myComponent.textField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusListener;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import eventListener.BorderChangerOnFocus;
import eventListener.emptyImplementation.MyDocumentListener;
import myComponent.MyTextButton;
import settings.Colors;
import settings.Fonts;

/**
 * Modern implementation of a TextField with a X-Button to clear the content.
 *
 * @author Gabriel Glaser
 * @version 29.12.2021
 */
public class MyTextField extends JPanel {

    protected final JTextField textField = new JTextField();
    private final MyTextButton deleteButton = new MyTextButton("X");

    public MyTextField(final String initialContent) {
	super();
	setup();
	setText(initialContent);
    }

    public MyTextField() {
	this("");
    }

    public String getText() {
	return textField.getText();
    }

    public void setText(final String newText) {
	textField.setText(newText);
    }

    public boolean hasText() {
	final String currentText = textField.getText();
	return currentText.length() > 0;
    }

    public void setColumns(final int newColumns) {
	textField.setColumns(newColumns);
    }

    public int getColumns() {
	return textField.getColumns();
    }

    public void addDocumentListener(final DocumentListener toAdd) {
	final Document document = textField.getDocument();
	document.addDocumentListener(toAdd);
    }

    @Override
    public void setBackground(final Color newBackground) {
	super.setBackground(newBackground);
	if (textField != null && deleteButton != null) {
	    textField.setBackground(newBackground);
	    deleteButton.setBackground(newBackground);
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
	if (textField != null && deleteButton != null) {
	    textField.setFont(newFont);
	    deleteButton.setFont(newFont);
	}
    }

    @Override
    public Dimension getPreferredSize() {
	final Dimension preferredSizeOfTextField = textField.getPreferredSize();
	final Dimension preferredSizeOfXButton = deleteButton.getPreferredSize();
	return new Dimension(preferredSizeOfTextField.width + preferredSizeOfXButton.width, preferredSizeOfTextField.height);
    }

    @Override
    public void requestFocus() {
	textField.requestFocus();
    }

    @Override
    public void addFocusListener(final FocusListener focusListener) {
	textField.addFocusListener(focusListener);
    }

    private void setup() {
	setLayout(new BorderLayout());
	setBackground(Colors.getGray(2));
	setForeground(Colors.ofText());
	setFont(Fonts.standard());
	setBorder(TextFieldAttributes.UNFOCUSED_BORDER);
	addFocusListener(new BorderChangerOnFocus(this, TextFieldAttributes.FOCUSED_BORDER));
	setFocusable(true);

	setupTextField();
	setupDeleteButton();

	add(textField, BorderLayout.CENTER);
	add(deleteButton, BorderLayout.EAST);
    }

    private void setupTextField() {
	textField.setBorder(new EmptyBorder(0, 0, 0, 0));
	addDocumentListener(new MyDocumentListener() {
	    @Override
	    public void update() {
		final String currentText = textField.getText();
		deleteButton.setVisible(currentText.length() > 0);
	    }
	});
    }

    private void setupDeleteButton() {
	deleteButton.setBackgroundWhileMouseHovered(getBackground());
	deleteButton.setForeground(TextFieldAttributes.HINT_TEXT_COLOR);
	deleteButton.setForegroundWhileMouseHovered(TextFieldAttributes.HINT_TEXT_COLOR.darker());
	deleteButton.setBorder(new EmptyBorder(0, 0, 0, 5));
	deleteButton.setFocusable(false);
	deleteButton.setVisible(false);
	deleteButton.addActionListener((click) -> {
	    textField.setText("");
	});
    }

}
