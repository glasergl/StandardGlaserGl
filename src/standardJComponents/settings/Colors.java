package standardJComponents.settings;

import java.awt.Color;

public final class Colors {

    public static Color ofError = Color.RED;
    public static Color focus = new Color(57, 176, 227);

    private static boolean shouldBeDarkMode = false;

    public static void setDarkModeEnabled(final boolean darkModeEnabled) {
	shouldBeDarkMode = darkModeEnabled;
    }

    public static boolean isDarkMode() {
	return shouldBeDarkMode;
    }

    public static Color getBackground(final int background) {
	switch (background) {
	case 1:
	    return shouldBeDarkMode ? new Color(35, 35, 35) : new Color(240, 240, 240);
	case 2:
	    return shouldBeDarkMode ? new Color(50, 50, 50) : new Color(220, 220, 220);
	case 3:
	    return shouldBeDarkMode ? new Color(65, 65, 65) : new Color(200, 200, 200);
	case 4:
	    return shouldBeDarkMode ? new Color(80, 80, 80) : new Color(180, 180, 180);
	case 5:
	    return shouldBeDarkMode ? new Color(95, 95, 95) : new Color(160, 160, 160);
	default:
	    throw new IllegalArgumentException("No background for number " + background);
	}
    }

    public static Color ofText() {
	return shouldBeDarkMode ? new Color(220, 220, 220) : Color.BLACK;
    }

    public static Color ofHintText() {
	return new Color(127, 127, 127);
    }

}
