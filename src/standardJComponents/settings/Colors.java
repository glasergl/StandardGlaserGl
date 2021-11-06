package standardJComponents.settings;

import java.awt.Color;

public final class Colors {

    private static boolean shouldBeDarkMode = false;

    public static void setDarkModeEnabled(final boolean darkModeEnabled) {
	shouldBeDarkMode = darkModeEnabled;
    }

    public static boolean isDarkMode() {
	return shouldBeDarkMode;
    }

    public static Color getBackground(final int background) {
	final int value = shouldBeDarkMode ? 20 + background * 15 : 235 - 15 * background;
	return new Color(value, value, value);
    }

    public static Color ofText() {
	return shouldBeDarkMode ? new Color(220, 220, 220) : Color.BLACK;
    }

    public static Color ofHintText() {
	return new Color(127, 127, 127);
    }

    public static Color ofError() {
	return Color.RED;
    }

    public static Color ofFocus() {
	return new Color(57, 176, 227);
    }

}
