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

	private static boolean shouldBeDarkMode = false;

	public static void setDarkModeEnabled(final boolean darkModeEnabled) {
		shouldBeDarkMode = darkModeEnabled;
	}

	public static boolean isDarkMode() {
		return shouldBeDarkMode;
	}

	/**
	 * Calculates a color depending on darkmode or lightmode with the given depth.
	 * 
	 * @param backgroundDepth - the number of the background depths, the higher the
	 *                        number, the brighter or darker (depends on darkmode or
	 *                        lightmode)
	 * @return Color with depth of background.
	 */
	public static Color getBackground(final int backgroundDepth) {
		final int value = shouldBeDarkMode ? 20 + backgroundDepth * 15 : 235 - 15 * backgroundDepth;
		return new Color(value, value, value);
	}

	public static Color ofText() {
		return shouldBeDarkMode ? new Color(220, 220, 220) : Color.BLACK;
	}

	public static Color ofError() {
		return Color.RED;
	}

	public static Color ofFocus() {
		return new Color(57, 176, 227);
	}

}
