package popUpTest;

import java.awt.BorderLayout;

import javax.swing.SwingUtilities;
import standard.MyFrame;
import standard.helper.listeners.SiblingPopUpDisplayerOnClick;
import standard.helper.listeners.SiblingPopUpDisplayerOnHover;
import standard.implementations.MyLabel;

public class TestMySiblingPopUp extends MyFrame {

    MyLabel hoverTest = new MyLabel("hover");
    MyLabel clickTest = new MyLabel("click");

    MyLabel hoverPopUp = new MyLabel("hovered");
    MyLabel clickPopUp = new MyLabel("clicked");

    public TestMySiblingPopUp() {
	super();
	add(hoverTest, BorderLayout.EAST);
	add(clickTest, BorderLayout.WEST);
	new SiblingPopUpDisplayerOnHover(hoverPopUp, hoverTest);
	new SiblingPopUpDisplayerOnClick(clickPopUp, clickTest);
	start();
    }

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    new TestMySiblingPopUp();
	});
    }
}
