package standardJComponents.implementations;

import java.awt.Cursor;

import javax.swing.JCheckBox;
import standardJComponents.helper.CursorHoverController;
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
	setFocusPainted(false);
	addMouseListener(new CursorHoverController(new Cursor(Cursor.HAND_CURSOR)));
    }

}
