package standardGlaserGl.swing.eventListener.test;

import javax.swing.JLabel;

import standardGlaserGl.swing.container.test.TestFrame;
import standardGlaserGl.swing.eventListener.SiblingPopUpDisplayerOnClick;
import standardGlaserGl.swing.eventListener.SiblingPopUpDisplayerOnHover;

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
