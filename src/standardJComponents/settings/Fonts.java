package standardJComponents.settings;

import java.awt.Font;
import javax.swing.plaf.FontUIResource;

/**
 * Consists all standard Fonts of different sizes.
 *
 * @author Gabriel Glaser
 * @version 27.09.2021
 */
public final class Fonts {

    public static Font standard = new FontUIResource(Font.SANS_SERIF, Font.PLAIN, 20);
    public static Font big = standard.deriveFont(40.0f);
    public static Font small = standard.deriveFont(12.0f);
    public static Font mediumSmall = standard.deriveFont(17.0f);

    public static Font headLine = standard.deriveFont(Font.BOLD);

}
