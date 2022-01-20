package standardSwing.myComponent.button;

import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import standardSwing.settings.Colors;

public final class MyTextButtonAttributes {

    private static Color standardBackground = Colors.getGray(2);
    private static Color standardBackgroundWhileMouseHovers = Colors.getGray(4);
    private static Color standardForeground = Colors.ofText();
    private static Color borderColor = Colors.getGray(7);

    private static int additionalWidth = 25;
    private static int additionalHeight = 8;
    private static Border standardBorder = new CompoundBorder(new LineBorder(borderColor, 2), new EmptyBorder(additionalHeight, additionalWidth, additionalHeight, additionalWidth));

    public static Color getStandardBackground() {
	return standardBackground;
    }

    public static Color getStandardBackgroundWhileMouseHovers() {
	return standardBackgroundWhileMouseHovers;
    }

    public static Color getStandardForeground() {
	return standardForeground;
    }

    public static Color getBorderColor() {
	return borderColor;
    }

    public static int getAdditionalWidth() {
	return additionalWidth;
    }

    public static int getAdditionalHeight() {
	return additionalHeight;
    }

    public static Border getStandardBorder() {
	return standardBorder;
    }

    public static void setStandardBackground(final Color standardBackground) {
	MyTextButtonAttributes.standardBackground = standardBackground;
    }

    public static void setStandardBackgroundWhileMouseHovered(final Color standardBackgroundWhileMouseHovered) {
	MyTextButtonAttributes.standardBackgroundWhileMouseHovers = standardBackgroundWhileMouseHovered;
    }

    public static void setStandardForeground(final Color standardForeground) {
	MyTextButtonAttributes.standardForeground = standardForeground;
    }

    public static void setBorderColor(final Color borderColor) {
	MyTextButtonAttributes.borderColor = borderColor;
	standardBorder = new CompoundBorder(new LineBorder(borderColor, 2), new EmptyBorder(additionalHeight, additionalWidth, additionalHeight, additionalWidth));
    }

    public static void setAdditionalWidth(final int additionalWidth) {
	MyTextButtonAttributes.additionalWidth = additionalWidth;
	standardBorder = new CompoundBorder(new LineBorder(borderColor, 2), new EmptyBorder(additionalHeight, additionalWidth, additionalHeight, additionalWidth));
    }

    public static void setAdditionalHeight(final int additionalHeight) {
	MyTextButtonAttributes.additionalHeight = additionalHeight;
	standardBorder = new CompoundBorder(new LineBorder(borderColor, 2), new EmptyBorder(additionalHeight, additionalWidth, additionalHeight, additionalWidth));
    }

    public static void setStandardBorder(final Border standardBorder) {
	MyTextButtonAttributes.standardBorder = standardBorder;
    }

}
