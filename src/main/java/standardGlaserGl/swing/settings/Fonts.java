package standardGlaserGl.swing.settings;

import java.awt.Font;

/**
 * Consists all standard Fonts of different sizes.
 *
 * @author Gabriel Glaser
 */
public final class Fonts {
    public static Font standard() {
	return new Font(Font.SANS_SERIF, Font.PLAIN, 20);
    }

    public static Font resizedStandard(final float newSize) {
	return standard().deriveFont(newSize);
    }

    public static Font big() {
	return resizedStandard(40.0f);
    }

    public static Font mediumBig() {
	return resizedStandard(32.0f);
    }

    public static Font mediumSmall() {
	return resizedStandard(17.0f);
    }

    public static Font small() {
	return resizedStandard(12.0f);
    }

    public static Font bold() {
	return standard().deriveFont(Font.BOLD);
    }
}
