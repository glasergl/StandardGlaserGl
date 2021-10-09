package standardJComponents.implementations;

import javax.swing.JCheckBox;
import standardJComponents.helper.CursorClickController;
import standardJComponents.settings.Colors;
import standardJComponents.settings.Fonts;

public class MyCheckBox extends JCheckBox {

    public MyCheckBox(final String title, final boolean shouldBeSelected) {
	super(title, shouldBeSelected);
	setStandardProperties();
    }

    public MyCheckBox(final String title) {
	this(title, false);
    }

    private void setStandardProperties() {
	setFont(Fonts.standard);
	setForeground(Colors.ofText());
	setBackground(MyTextField.background);
	setFocusPainted(false);
	addMouseListener(new CursorClickController());
    }

}
