package standard.implementations;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import complex.UnderlineBorder;
import standard.settings.Colors;
import standard.settings.Fonts;

public class MyHintTextField extends JPanel {

    protected static final Color OF_HINT_TEXT = new Color(127, 127, 127);

    private final JLabel hintDisplay;

    protected final String hint;
    protected final JTextField textField = new JTextField();

    public MyHintTextField(final String hint, final String initialContent) {
	super();
	this.hint = hint;
	this.hintDisplay = new JLabel(hint);
	setup();
	textField.setText(initialContent);
    }

    public MyHintTextField(final String hint) {
	this(hint, "");
    }

    private void setup() {
	hintDisplay.setForeground(OF_HINT_TEXT);
	hintDisplay.setBorder(new EmptyBorder(2, 2, 2, 2));

	textField.setForeground(Colors.ofText());
	textField.setBorder(new UnderlineBorder(2, Colors.ofText()));
	textField.setOpaque(false);
	textField.getDocument().addDocumentListener(new HintController());

	setLayout(null);
	setFont(Fonts.standard);
	setBackground(Colors.getBackground(3));
	add(textField);
	add(hintDisplay);
    }

    public void setColumns(final int columns) {
	textField.setColumns(columns);
    }

    public void setText(final String newContent) {
	textField.setText(newContent);
    }

    public String getText() {
	return textField.getText();
    }

    public String getHintText() {
	return hint;
    }

    @Override
    public void setBounds(final int x, final int y, final int width, final int height) {
	super.setBounds(x, y, width, height);
	hintDisplay.setBounds(0, 0, width, height);
	textField.setBounds(0, 0, width, height);
    }

    @Override
    public void setFont(final Font newFont) {
	super.setFont(newFont);
	if (hintDisplay != null && textField != null) {
	    hintDisplay.setFont(newFont);
	    textField.setFont(newFont);
	}
    }

    @Override
    public void setBackground(final Color newBackground) {
	super.setBackground(newBackground);
	if (hintDisplay != null) {
	    hintDisplay.setBackground(newBackground);
	}
    }

    @Override
    public Dimension getPreferredSize() {
	return textField.getPreferredSize();
    }

    @Override
    public void addFocusListener(final FocusListener toAdd) {
	textField.addFocusListener(toAdd);
    }

    @Override
    public void setBorder(final Border newBorder) {
	if (textField != null) {
	    textField.setBorder(newBorder);
	} else {
	    super.setBorder(newBorder);
	}
    }

    @Override
    public Border getBorder() {
	if (textField != null) {
	    return textField.getBorder();
	} else {
	    return super.getBorder();
	}
    }

    private class HintController implements DocumentListener {

	@Override
	public void insertUpdate(DocumentEvent e) {
	    updateHintComponent();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
	    updateHintComponent();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
	    updateHintComponent();
	}

	private void updateHintComponent() {
	    if (textField.getText().length() == 0) {
		hintDisplay.setText(hint);
	    } else {
		hintDisplay.setText("");
	    }
	}

    }

}
