package standardSwing.eventListener.test;

import de.glasergl.standard.swing.eventListener.SiblingPopUpDisplayerOnClick;
import de.glasergl.standard.swing.eventListener.SiblingPopUpDisplayerOnHover;
import de.glasergl.standard.swing.myComponent.MyLabel;
import standardSwing.container.test.TestFrame;

public class TestMySiblingPopUpDisplayer {
    public static void main(String[] args) {
	MyLabel hoverTest = new MyLabel("hover");
	MyLabel clickTest = new MyLabel("click");

	MyLabel hoverPopUp = new MyLabel("hovered");
	MyLabel clickPopUp = new MyLabel("clicked");

	new SiblingPopUpDisplayerOnHover(hoverPopUp, hoverTest);
	new SiblingPopUpDisplayerOnClick(clickPopUp, clickTest);

	TestFrame.showFrameWithComponents(hoverTest, clickTest);
    }
}
