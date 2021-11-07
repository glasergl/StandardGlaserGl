package standard.implementations;

import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JWindow;

public class MyPopUpComponent extends JWindow {

	public MyPopUpComponent(final JComponent toShowAsPopUp) {
		super();
		setBackground(new Color(0, true));
		add(toShowAsPopUp);
	}

	@Override
	public void setVisible(final boolean shouldBeVisible) {
		super.setVisible(shouldBeVisible);
		if (isVisible()) {
			pack();
		}
	}

}
