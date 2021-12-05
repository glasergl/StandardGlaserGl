package popUpTest;

import javax.swing.SwingUtilities;
import standard.MyFrame;
import standard.helper.listeners.PopUpDisplayerOnClick;
import standard.helper.listeners.PopUpDisplayerOnHover;
import standard.implementations.MyLabel;
import standard.implementations.MySiblingPopUp;

public class TestMySiblingPopUp extends MyFrame {

    MyLabel testLabel = new MyLabel("test");
    MyLabel testPopUpComponent = new MyLabel("popUp hehe");
    MySiblingPopUp testPopUp = new MySiblingPopUp(testPopUpComponent, this, testLabel);

    public TestMySiblingPopUp() {
	super();
	add(testLabel);
	testLabel.addMouseListener(new PopUpDisplayerOnClick(testPopUp));
	start();
    }

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    new TestMySiblingPopUp();
	});
    }
}
