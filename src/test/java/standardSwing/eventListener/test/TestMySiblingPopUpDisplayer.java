package standardSwing.eventListener.test;

import javax.swing.JLabel;

import de.glasergl.standard.swing.eventListener.SiblingPopUpDisplayerOnClick;
import de.glasergl.standard.swing.eventListener.SiblingPopUpDisplayerOnHover;
import standardSwing.container.test.TestFrame;

public class TestMySiblingPopUpDisplayer {
    public static void main(String[] args) {
	JLabel hoverTest = new JLabel("hover");
	JLabel clickTest = new JLabel("click");

	JLabel hoverPopUp = new JLabel("hovered");
	JLabel clickPopUp = new JLabel("clicked");

	new SiblingPopUpDisplayerOnHover(hoverPopUp, hoverTest);
	new SiblingPopUpDisplayerOnClick(clickPopUp, clickTest);

	TestFrame.showFrameWithComponents(hoverTest, clickTest);
    }
}
