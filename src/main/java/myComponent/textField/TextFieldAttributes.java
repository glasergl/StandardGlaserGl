package myComponent.textField;

import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import border.AnimatedUnderlineBorder;
import settings.Colors;

public class TextFieldAttributes {

    public static final Border BORDER_WHILE_UNFOCUSED = new MatteBorder(0, 0, 2, 0, Colors.ofText());
    public static final Border BORDER_WHILE_FOCUSED = new AnimatedUnderlineBorder(Colors.ofFocus(), Colors.ofText());
    public static final Color HINT_TEXT_COLOR = new Color(127, 127, 127);
    public static final float HINT_SIZE_FACTOR = 5.0f / 7.0f;

}
