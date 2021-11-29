package standard.settings;

import java.awt.Color;

/**
 * Class which contains default Colors to use. Furthermore, a darkmode is
 * supported.
 *
 * @author Gabriel Glaser
 * @version 16.11.2021
 */
public final class Colors {

    private static boolean darkMode = false;

    public static void setDarkModeEnabled(final boolean shouldBeDarkMode) {
	darkMode = shouldBeDarkMode;
    }

    public static boolean isDarkMode() {
	return darkMode;
    }

    /**
     * Calculates a color depending on darkmode or lightmode with the given
     * intensity.
     * 
     * @param brightness - the number of the background depths, the higher the
     *                   number, the brighter or darker (depends on darkmode or
     *                   lightmode)
     * @return Color with depth of background.
     */
    public static Color getGray(final int brightness) {
	if (brightness < 0 || brightness > 5) {
	    throw new IllegalArgumentException("Brightness has to be in {0, 1, 2, 3, 4}, but is " + brightness);
	}
	final int rgbEntry = darkMode ? 35 + brightness * 20 : 255 - 20 - 15 * brightness;
	return new Color(rgbEntry, rgbEntry, rgbEntry);
    }

    public static Color getBlue(final int brightness) {
	if (brightness == 0) {
	    return darkMode ? new Color(0, 72, 255) : new Color(79, 129, 255);
	} else if (brightness == 1) {
	    return new Color(43, 103, 255);
	} else if (brightness == 2) {
	    return darkMode ? new Color(79, 129, 255) : new Color(0, 72, 255);
	}
	throw new IllegalArgumentException("Brightness has to be in {0, 1, 2}, but is " + brightness);
    }

    public static Color ofText() {
	return darkMode ? new Color(220, 220, 220) : Color.BLACK;
    }

    public static Color ofError() {
	return new Color(232, 49, 49);
    }

    public static Color ofFocus() {
	return new Color(57, 176, 227);
    }

}
