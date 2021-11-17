package standard.implementations;

import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JWindow;
import javax.swing.border.Border;

/**
 * This class allows to display a component anywhere on the screen.
 * 
 * @author Gabriel Glaser
 * @version 17.11.2021
 */
public class MyPopUpComponent extends JWindow {

    private JComponent toShowAsPopUp;

    public MyPopUpComponent(final JComponent toShowAsPopUp) {
	super();
	this.toShowAsPopUp = toShowAsPopUp;
	setBackground(new Color(0, true));
	add(toShowAsPopUp);
    }

    public MyPopUpComponent() {
	super();
	setBackground(new Color(0, true));
    }

    @Override
    public void setVisible(final boolean shouldBeVisible) {
	super.setVisible(shouldBeVisible);
	if (isVisible()) {
	    pack();
	}
    }

    public void setPopUpComponent(final JComponent toShowAsPopUp) {
	this.toShowAsPopUp = toShowAsPopUp;
	add(toShowAsPopUp);
    }

    public void setBorder(final Border toSetAroundThePopUpComponent) {
	toShowAsPopUp.setBorder(toSetAroundThePopUpComponent);
    }

}