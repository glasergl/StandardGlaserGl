package standardSwing.myComponent.textfield_Old;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import standardSwing.settings.Colors;
import standardSwing.settings.Fonts;

public class MyJTextField extends JTextField {

    private static Color defaultColorOfText = Colors.ofText();
    private static Font defaultFontOfText = Fonts.standard();
    private static int defaultColumns = 15;

    public MyJTextField(final String initialText, final int columns) {
	super(initialText, columns);
	setDefaultValues();
    }

    public MyJTextField(final String initialText) {
	this(initialText, initialText.length());
    }

    public MyJTextField(final int columns) {
	this("", columns);
    }

    public MyJTextField() {
	this(defaultColumns);
    }

    public void setDefaultValues() {
	setForeground(defaultColorOfText);
	setFont(defaultFontOfText);
	setColumns(defaultColumns);
    }

    public static Color getDefaultColorOfText() {
	return defaultColorOfText;
    }

    public static Font getDefaultFontOfText() {
	return defaultFontOfText;
    }

    public static int getDefaultColumns() {
	return defaultColumns;
    }

    public static void setDefaultColorOfText(Color newColorOfText) {
	MyJTextField.defaultColorOfText = newColorOfText;
    }

    public static void setDefaultFontOfText(Font newFontOfText) {
	MyJTextField.defaultFontOfText = newFontOfText;
    }

    public static void setDefaultColumns(int newColumns) {
	MyJTextField.defaultColumns = newColumns;
    }

}
