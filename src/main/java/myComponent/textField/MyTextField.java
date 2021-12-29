package myComponent.textField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.util.Optional;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import eventListener.CursorChangerOnHover;
import eventListener.emptyImplementation.MyDocumentListener;
import eventListener.emptyImplementation.MyFocusListener;
import myComponent.MyTextButton;
import settings.Colors;
import settings.Fonts;

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

    public void setColumns(final int columns) {
	if (columns < 0) {
	    throw new IllegalArgumentException("columns can't be less than zero, but is " + columns);
	}
	textField.setColumns(columns);
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
    public void setFont(final Font newFont) {
	super.setFont(newFont);
	if (textField != null) {
	    textField.setFont(newFont);
	}
    }

    @Override
    public Dimension getPreferredSize() {
	final Dimension preferredSizeOfTextField = textField.getPreferredSize();
	final Dimension preferredSizeOfXButton = deleteButton.getPreferredSize();
	return new Dimension(preferredSizeOfTextField.width + preferredSizeOfXButton.width, preferredSizeOfTextField.height);
    }

    private void setup() {
	setLayout(new BorderLayout());
	setBackground(Colors.getGray(2));
	setFont(Fonts.standard());
	setBorder(TextFieldAttributes.UNFOCUSED_BORDER);

	setupTextField();
	setupDeleteButton();
	add(textField, BorderLayout.CENTER);
    }

    private void setupTextField() {
	textField.setForeground(Colors.ofText());
	textField.setBorder(new EmptyBorder(0, 0, 0, 0));
	textField.addFocusListener(new BorderController());
	addDocumentListener(new DeleteButtonController());
    }

    private void setupDeleteButton() {
	deleteButton.setBackground(getBackground());
	deleteButton.setBackgroundWhileMouseHovered(getBackground());
	deleteButton.setForeground(TextFieldAttributes.HINT_TEXT_COLOR);
	deleteButton.setForegroundWhileMouseHovered(TextFieldAttributes.HINT_TEXT_COLOR.darker());
	deleteButton.setFont(getFont());
	deleteButton.setBorder(new EmptyBorder(0, 0, 0, 5));
	deleteButton.addMouseListener(new CursorChangerOnHover(new Cursor(Cursor.HAND_CURSOR)));
	deleteButton.setFocusable(false);
	deleteButton.addActionListener((click) -> {
	    textField.setText("");
	});
    }

    private class BorderController extends MyFocusListener {

	private Optional<Border> old = Optional.empty();

	@Override
	public void focusGained(final FocusEvent gainedFocusEvent) {
	    old = Optional.of(getBorder());
	    setBorder(TextFieldAttributes.FOCUSED_BORDER);
	}

	@Override
	public void focusLost(FocusEvent lostFocusEvent) {
	    setBorder(old.get());
	    old = Optional.empty();
	}

    }

    private class DeleteButtonController extends MyDocumentListener {
	@Override
	public void update() {
	    if (textField.getText().length() == 0) {
		remove(deleteButton);
	    } else {
		add(deleteButton, BorderLayout.EAST);
	    }
	    revalidate();
	    repaint();
	}
    }

}
