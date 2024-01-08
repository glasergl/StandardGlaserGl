package standardGlaserGl.swing.settings;

import java.awt.Color;

/**
 * Class which contains default Colors to use. Furthermore, a darkmode is
 * supported.
 *
 * @author Gabriel Glaser
 */
public final class Colors {
    public static final int NUMBER_OF_BLUE_COLORS = 11;

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
	    throw new IllegalArgumentException("Brightness has to be in [0, 10], but is " + brightness);
	}
	final int rgbEntry = darkMode ? 35 + brightness * 20 : 255 - 20 - 15 * brightness;
	return new Color(rgbEntry, rgbEntry, rgbEntry);
    }

    /**
     * Calculates a version of blue depending on darkmode or lightmode with the
     * given brightness.
     * 
     * @param brightness of the returned blue (depends on darkmode or lightmode),
     *                   has to be in [0, 10].
     * @return Blue with the given brightness.
     */
    public static Color getBlue(final int brightness) {
	switch (brightness) {
	case 0:
	    return new Color(209, 224, 255);
	case 1:
	    return new Color(197, 209, 237);
	case 2:
	    return new Color(163, 178, 212);
	case 3:
	    return new Color(138, 154, 191);
	case 4:
	    return new Color(99, 121, 173);
	case 5:
	    return new Color(78, 106, 173);
	case 6:
	    return new Color(64, 96, 173);
	case 7:
	    return new Color(51, 85, 166);
	case 8:
	    return new Color(35, 82, 194);
	case 9:
	    return new Color(8, 64, 199);
	case 10:
	    return new Color(0, 63, 214);
	default:
	    throw new IllegalArgumentException("Brightness has to be in [0, 10], but is " + brightness);
	}
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

    /**
     * Calculates a color which will stand out in comparison to the given color.
     * 
     * @param color
     * @return A relative color which will stand out in contact with the given
     *         color.
     */
    public static Color deriveStandOutColor(final Color color) {
	final int average = averageOf(color);
	final float factor = 0.15f;
	if (average > 127) {
	    return Colors.derive(color, 1 - factor, 0);
	} else {
	    final int offset = average < 50 ? 50 - average / 2 : 0;
	    return Colors.derive(color, 1 + factor, offset);
	}
    }

    /**
     * Multiplies R -, G - and B entry of the given Color with the given factor and
     * adds the offset to it. Also clamps the results in [0, 255]
     * 
     * @param toDerive
     * @param factor
     * @param offset
     * @return The deriven Color.
     */
    public static Color derive(final Color toDerive, final float factor, final int offset) {
	final int newR = clampRGBEntry((int) (factor * toDerive.getRed() + offset));
	final int newG = clampRGBEntry((int) (factor * toDerive.getGreen() + offset));
	final int newB = clampRGBEntry((int) (factor * toDerive.getBlue() + offset));
	return new Color(newR, newG, newB);
    }

    /**
     * @param color
     * @return Average value of the rgb-entries.
     */
    public static int averageOf(final Color color) {
	return (color.getRed() + color.getGreen() + color.getBlue()) / 3;
    }

    /**
     * Every value inside [0, 255] will just be returned again, if it's outside,
     * this function will return the closest edge of the interval.
     * 
     * @param rgbEntry - any number
     * @return The given rgbEntry clamped to [0, 255].
     */
    public static int clampRGBEntry(final int rgbEntry) {
	if (rgbEntry < 0) {
	    return 0;
	} else if (rgbEntry > 255) {
	    return 255;
	} else {
	    return rgbEntry;
	}
    }
}
