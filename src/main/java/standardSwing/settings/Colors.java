package standardSwing.settings;

import java.awt.Color;

/**
 * Class which contains default Colors to use. Furthermore, a darkmode is
 * supported.
 *
 * @author Gabriel Glaser
 * @version 11.2.2022
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
     * brightness.
     * 
     * @param brightness of the returned gray (depends on darkmode or lightmode),
     *                   has to be in [0,10]
     * @return Gray with the given brightness.
     */
    public static Color getGray(final int brightness) {
	if (brightness < 0 || brightness > 10) {
	    throw new IllegalArgumentException("Brightness has to be in {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, but is " + brightness);
	}
	final int rgbEntry = darkMode ? 35 + brightness * 20 : 255 - 20 - 15 * brightness;
	return new Color(rgbEntry, rgbEntry, rgbEntry);
    }

    /**
     * Calculates a version of blue depending on darkmode or lightmode with the
     * given brightness.
     * 
     * @param brightness of the returned blue (depends on darkmode or lightmode),
     *                   has to be in [0, 2].
     * @return Blue with the given brightness.
     */
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

    public static Color deriveWithAverage(final Color color, final float factor, final int offset) {
	final float average = (color.getRed() + color.getGreen() + color.getBlue()) / 3.0f;
	if (average > 127) {
	    return Colors.derive(color, 1 - factor, -offset);
	} else {
	    return Colors.derive(color, 1 + factor, offset);
	}
    }

    /**
     * @param rgbEntry
     * @return The given rgbEntry clamped to [0, 255].
     */
    public static int colorEntryInCorrectArea(final int rgbEntry) {
	if (rgbEntry < 0) {
	    return 0;
	} else if (rgbEntry > 255) {
	    return 255;
	} else {
	    return rgbEntry;
	}
    }

    /**
     * Multiplies R -, G - and B entry of the given Color with the given factor and
     * adds the offset to it.
     * 
     * @param toDerive
     * @param factor
     * @param offset
     * @return The calculated Color.
     */
    public static Color derive(final Color toDerive, final float factor, final int offset) {
	final int newR = colorEntryInCorrectArea((int) (factor * toDerive.getRed() + offset));
	final int newG = colorEntryInCorrectArea((int) (factor * toDerive.getGreen() + offset));
	final int newB = colorEntryInCorrectArea((int) (factor * toDerive.getBlue() + offset));
	return new Color(newR, newG, newB);
    }

}
