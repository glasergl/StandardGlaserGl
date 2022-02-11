package standardSwing.myComponent.textField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;

import standardSwing.eventListener.emptyImplementation.MyDocumentListener;
import standardSwing.eventListener.emptyImplementation.MyMouseListener;
import standardSwing.myComponent.button.MyTextButton;

/**
 * Simple implementation of a TextField with a X-Button to clear the text.
 *
 * @author Gabriel Glaser
 * @version 11.2.2022
 */
public final class MyTextFieldWithXButton extends JPanel {

    private static final int MARGIN_OF_X_BUTTON = 5;

    private final JTextField baseImplementation = new JTextField();
    private final MyTextButton deleteButton = new MyTextButton("X", false, MyTextFieldAttributes.getBackgroundColor(), MyTextFieldAttributes.getxButtonForegroundColorWhileHovered());

    protected MyTextFieldWithXButton(final String initialContent) {
	super();
	setup();
	baseImplementation.setText(initialContent);
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
	setLayout(new BorderLayout());
	setupTextField();
	setupDeleteButton();
	setBorder(new EmptyBorder(0, 3, 0, 0));
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
	deleteButton.setForeground(MyTextFieldAttributes.getXButtonForegroundColor());
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
