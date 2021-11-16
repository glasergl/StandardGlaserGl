package standard.settings;

import java.awt.Font;
import javax.swing.plaf.FontUIResource;

/**
 * Consists all standard Fonts of different sizes.
 *
 * @author Gabriel Glaser
 * @version 16.11.2021
 */
public final class Fonts {

    public static Font standard() {
	return new FontUIResource(Font.SANS_SERIF, Font.PLAIN, 20);
    }

    public static Font resizedStandard(final float newSize) {
	return standard().deriveFont(newSize);
    }

    public static Font big() {
	return resizedStandard(40.0f);
    }

    public static Font small() {
	return resizedStandard(12.0f);
    }

    public static Font mediumSmall() {
	return resizedStandard(17.0f);
    }

    public static Font ofHeadline() {
	return standard().deriveFont(Font.BOLD);
    }

}
