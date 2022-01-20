package standardSwing.eventListener.test;

import standardSwing.container.test.TestFrame;
import standardSwing.eventListener.SiblingPopUpDisplayerOnClick;
import standardSwing.eventListener.SiblingPopUpDisplayerOnHover;
import standardSwing.myComponent.MyLabel;

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
