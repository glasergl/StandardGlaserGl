package de.glasergl.standard.swing.myComponent.textField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentListener;

import de.glasergl.standard.swing.eventListener.BorderChangerOnFocus;
import de.glasergl.standard.swing.eventListener.emptyImplementation.MyDocumentListener;
import de.glasergl.standard.swing.myComponent.button.CustomTextButton;

/**
 * Implementation of a TextField with a X-Button to clear the text and an
 * animated Border if the TextField gets focused.
 *
 * @author Gabriel Glaser
 */
public final class MyTextField extends JPanel {
    private static final int MARGIN_OF_X_BUTTON = 4;

    private final JTextField baseImplementation = new JTextField();
    private final CustomTextButton deleteButton = new CustomTextButton("x", MyTextFieldAttributes.getBackgroundColor(), MyTextFieldAttributes.getXButtonForegroundColor(), false);
    private final BorderChangerOnFocus borderChangerOnFocus;

    public MyTextField(final String initialContent, final int columns) {
	super();
	this.borderChangerOnFocus = new BorderChangerOnFocus(this, MyTextFieldAttributes.getBorderWhileFocused());
	setup();
	baseImplementation.setText(initialContent);
	baseImplementation.setColumns(columns);
    }

    public MyTextField(final String initialContent) {
	this(initialContent, MyTextFieldAttributes.getStandardNumberOfColumns());
    }

    public MyTextField(final int columns) {
	this("", columns);
    }

    public MyTextField() {
	this("");
    }

    public void setText(final String text) {
	baseImplementation.setText(text);
    }

    public void setColumns(final int columns) {
	baseImplementation.setColumns(columns);
    }

    public String getText() {
	return baseImplementation.getText();
    }

    public int getColumns() {
	return baseImplementation.getColumns();
    }

    @Override
    public void addMouseListener(final MouseListener toAdd) {
	baseImplementation.addMouseListener(toAdd);
    }

    public void addDocumentListener(final DocumentListener toAdd) {
	baseImplementation.getDocument().addDocumentListener(toAdd);
    }

    public JTextField getBaseImplementation() {
	return baseImplementation;
    }

    @Override
    public void setBackground(final Color newBackground) {
	super.setBackground(newBackground);
	if (baseImplementation != null && deleteButton != null) {
	    baseImplementation.setBackground(newBackground);
	    deleteButton.setBackground(newBackground);
	}
    }

    @Override
    public void setForeground(final Color newForeground) {
	super.setForeground(newForeground);
	if (baseImplementation != null) {
	    baseImplementation.setForeground(newForeground);
	}
    }

    @Override
    public void setFont(final Font newFont) {
	super.setFont(newFont);
	if (baseImplementation != null && deleteButton != null) {
	    baseImplementation.setFont(newFont);
	    deleteButton.setFont(newFont);
	}
    }

    @Override
    public Dimension getPreferredSize() {
	final Dimension preferredSizeOfTextField = baseImplementation.getPreferredSize();
	final Dimension preferredSizeOfXButton = deleteButton.getPreferredSize();
	return new Dimension(preferredSizeOfTextField.width + preferredSizeOfXButton.width, preferredSizeOfTextField.height);
    }

    private void setup() {
	setLayout(new BorderLayout(MARGIN_OF_X_BUTTON, 0));
	setBackground(MyTextFieldAttributes.getBackgroundColor());
	setupTextField();
	setBorder(new MatteBorder(0, 0, 2, 0, Color.BLACK));
	setupDeleteButton();
	add(baseImplementation, BorderLayout.CENTER);
	add(deleteButton, BorderLayout.EAST);
    }

    private void setupTextField() {
	baseImplementation.setBorder(new EmptyBorder(0, 0, 0, 0));
	baseImplementation.setFont(MyTextFieldAttributes.getFont());
	baseImplementation.getDocument().addDocumentListener(new MyDocumentListener() {
	    @Override
	    public void update() {
		deleteButton.setVisible(baseImplementation.getText().length() > 0);
	    }
	});
	baseImplementation.addFocusListener(borderChangerOnFocus);
    }

    private void setupDeleteButton() {
	deleteButton.setVisible(false);
	deleteButton.setFocusable(false);
	deleteButton.setForegroundWhileHovered(MyTextFieldAttributes.getXButtonForegroundColorWhileHovered());
	deleteButton.setBorder(new EmptyBorder(0, 0, 0, MARGIN_OF_X_BUTTON));
	deleteButton.addActionListener((click) -> {
	    baseImplementation.setText("");
	});
	deleteButton.addActionListener((click) -> {
	    baseImplementation.requestFocus();
	});
    }
}
