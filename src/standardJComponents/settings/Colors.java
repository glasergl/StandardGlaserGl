package standardJComponents.settings;

import java.awt.Color;

public final class Colors {

    public static Color ofError = Color.RED;
    public static Color focus = new Color(57, 176, 227);

    private static boolean shouldBeDarkMode = true;

    public static void setDarkModeEnabled(final boolean darkModeEnabled) {
	shouldBeDarkMode = darkModeEnabled;
    }

    public static boolean isDarkMode() {
	return shouldBeDarkMode;
    }

    public static Color getBackground(final int background) {
	switch (background) {
	case 1:
	    return shouldBeDarkMode ? new Color(34, 34, 34) : new Color(237, 237, 237);
	case 2:
	    return shouldBeDarkMode ? new Color(43, 43, 43) : new Color(217, 217, 217);
	case 3:
	    return shouldBeDarkMode ? new Color(52, 52, 52) : new Color(197, 197, 197);
	case 4:
	    return shouldBeDarkMode ? new Color(61, 61, 61) : new Color(177, 177, 177);
	case 5:
	    return shouldBeDarkMode ? new Color(70, 70, 70) : new Color(157, 157, 157);
	default:
	    throw new IllegalArgumentException("No background for number " + background);
	}
    }

    public static Color ofText() {
	return shouldBeDarkMode ? new Color(220, 220, 220) : Color.BLACK;
    }

}
