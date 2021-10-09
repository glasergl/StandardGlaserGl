package standardJComponents.implementations;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.Border;
import standardJComponents.helper.UnderlineBorder;
import standardJComponents.helper.HintController;
import standardJComponents.helper.StandardFocusListener;
import standardJComponents.settings.Colors;
import standardJComponents.settings.Fonts;

public class MyTextField extends JTextField {

    public static Color background = Colors.isDarkMode() ? new Color(100, 100, 100) : new Color(200, 200, 200);
    public static Color backgroundWhileFocused = Colors.isDarkMode() ? new Color(120, 120, 120) : new Color(190, 190, 190);
    public static Color ofHintText = Colors.isDarkMode() ? new Color(150, 150, 150) : new Color(80, 80, 80);
    public static Color ofUnderline = Colors.isDarkMode() ? new Color(225, 225, 225) : new Color(40, 40, 40);
    public static Border border = new UnderlineBorder(2, ofUnderline);

    protected final String hint;
    protected final HintController hintController;

    public MyTextField(final String hint, final String initialText, final int columns) {
	super(initialText, columns);
	setStandardProperties();
	this.hint = hint;
	this.hintController = new HintController(hint, initialText.equals(""), this);
	addFocusListener(hintController);
	addFocusListener(new StandardFocusListener(backgroundWhileFocused, background));
    }

    public MyTextField(final String hint, final String initialText) {
	this(hint, initialText, 0);
    }

    public MyTextField(final String hint) {
	this(hint, "");
    }

    public MyTextField() {
	this("");
    }

    @Override
    public String getText() {
	if (hintController.showingHint()) {
	    return "";
	} else {
	    return super.getText();
	}
    }

    private void setStandardProperties() {
	setBackground(background);
	setForeground(Colors.ofText());
	setBorder(border);
	setFont(Fonts.standard);
	setCaretColor(Colors.ofText());
    }

}
