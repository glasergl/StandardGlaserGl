package standardSwing.myComponent.textFieldD;

import java.awt.Color;
import java.awt.Font;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import standardSwing.border.AnimatedUnderlineBorder;
import standardSwing.settings.Colors;
import standardSwing.settings.Fonts;

/**
 * Class which contains the default attributes for a MyTextField.
 * 
 * Each setter needs to be called before instantiating a MyTextField.
 *
 * @author Gabriel Glaser
 * @version 30.12.2021
 */
public final class MyTextFieldAttributes {

    private static Color backgroundColor = Colors.isDarkMode() ? Color.BLACK : Color.WHITE;
    private static Color foregroundColor = Colors.ofText();
    private static Color hintTextColor = new Color(127, 127, 127);
    private static Color xButtonForegroundColor = Colors.getGray(5);
    private static Color xButtonForegroundColorWhileHovered = Colors.getGray(7);

    private static Font font = Fonts.standard();

    private static Border border = new MatteBorder(0, 0, 2, 0, Colors.ofText());
    private static Border borderWhileFocused = new AnimatedUnderlineBorder(Colors.ofFocus(), Colors.ofText());

    private static float hintSizeFactor = 5.0f / 7.0f;

    private static int standardNumberOfColumns = 15;

    public static Color getBackgroundColor() {
	return backgroundColor;
    }

    public static Color getForegroundColor() {
	return foregroundColor;
    }

    public static Color getHintTextColor() {
	return hintTextColor;
    }

    public static Color getXButtonForegroundColor() {
	return xButtonForegroundColor;
    }

    public static Color getxButtonForegroundColorWhileHovered() {
	return xButtonForegroundColorWhileHovered;
    }

    public static Font getFont() {
	return font;
    }

    public static Border getBorder() {
	return border;
    }

    public static Border getBorderWhileFocused() {
	return borderWhileFocused;
    }

    public static float getHintSizeFactor() {
	return hintSizeFactor;
    }

    public static int getStandardNumberOfColumns() {
	return standardNumberOfColumns;
    }

    public static void setBackgroundColor(final Color backgroundColor) {
	MyTextFieldAttributes.backgroundColor = backgroundColor;
    }

    public static void setForegroundColor(final Color foregroundColor) {
	MyTextFieldAttributes.foregroundColor = foregroundColor;
    }

    public static void setHintTextColor(final Color hintTextColor) {
	MyTextFieldAttributes.hintTextColor = hintTextColor;
    }

    public static void setxForegroundButtonColor(final Color xButtonForegroundColor) {
	MyTextFieldAttributes.xButtonForegroundColor = xButtonForegroundColor;
    }

    public static void setxButtonForegroundColorWhileHovered(final Color xButtonForegroundColorWhileHovered) {
	MyTextFieldAttributes.xButtonForegroundColorWhileHovered = xButtonForegroundColorWhileHovered;
    }

    public static void setFont(final Font font) {
	MyTextFieldAttributes.font = font;
    }

    public static void setBorder(final Border border) {
	MyTextFieldAttributes.border = border;
    }

    public static void setBorderWhileFocused(final Border borderWhileFocused) {
	MyTextFieldAttributes.borderWhileFocused = borderWhileFocused;
    }

    public static void setHintSizeFactor(final float hintSizeFactor) {
	MyTextFieldAttributes.hintSizeFactor = hintSizeFactor;
    }

    public static void setStandardNumberOfColumns(final int standardNumberOfColumns) {
	MyTextFieldAttributes.standardNumberOfColumns = standardNumberOfColumns;
    }

}
