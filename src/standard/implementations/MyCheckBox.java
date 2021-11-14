package standard.implementations;

import java.awt.Cursor;
import javax.swing.JCheckBox;
import standard.helper.CursorChangerOnHover;
import standard.settings.Colors;
import standard.settings.Fonts;

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
	setBackground(Colors.getBackground(3));
	setFocusPainted(false);
	addMouseListener(new CursorChangerOnHover(new Cursor(Cursor.HAND_CURSOR)));
    }

}
