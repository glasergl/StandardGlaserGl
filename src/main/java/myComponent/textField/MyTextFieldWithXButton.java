package myComponent.textField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import eventListener.emptyImplementation.MyDocumentListener;
import eventListener.emptyImplementation.MyMouseListener;
import myComponent.MyTextButton;

/**
 * Simple implementation of a TextField with a X-Button to clear the text.
 *
 * @author Gabriel Glaser
 * @version 30.12.2021
 */
public final class MyTextFieldWithXButton extends JPanel {

    private static final int MARGIN_OF_X_BUTTON = 5;

    private final JTextField baseImplementation = new JTextField();
    private final MyTextButton deleteButton = new MyTextButton("X");

    protected MyTextFieldWithXButton(final String initialContent) {
	super();
	setup();
	baseImplementation.setText(initialContent);
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
	setLayout(new BorderLayout());
	setupTextField();
	setupDeleteButton();
	add(baseImplementation, BorderLayout.CENTER);
	add(deleteButton, BorderLayout.EAST);
    }

    private void setupTextField() {
	baseImplementation.setBorder(new EmptyBorder(0, 0, 0, 0));
	baseImplementation.getDocument().addDocumentListener(new MyDocumentListener() {
	    @Override
	    public void update() {
		deleteButton.setVisible(baseImplementation.getText().length() > 0);
	    }
	});
    }

    private void setupDeleteButton() {
	deleteButton.setBackgroundWhileMouseHovered(TextFieldAttributes.getBackgroundColor());
	deleteButton.setForeground(TextFieldAttributes.getxButtonForegroundColor());
	deleteButton.setForegroundWhileMouseHovered(TextFieldAttributes.getxButtonForegroundColorWhileHovered());
	deleteButton.setBorder(new EmptyBorder(0, MARGIN_OF_X_BUTTON, 0, MARGIN_OF_X_BUTTON));
	deleteButton.setVisible(false);
	deleteButton.setFocusable(false);
	deleteButton.addActionListener((click) -> {
	    baseImplementation.setText("");
	});
	deleteButton.addMouseListener(new MyMouseListener() {
	    @Override
	    public void mouseClicked(MouseEvent focusGainEvent) {
		baseImplementation.requestFocus();
	    }
	});
    }

}