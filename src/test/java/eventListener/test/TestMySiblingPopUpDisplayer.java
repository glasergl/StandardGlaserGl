package eventListener.test;

import container.test.TestFrame;
import eventListener.SiblingPopUpDisplayerOnClick;
import eventListener.SiblingPopUpDisplayerOnHover;
import myComponent.MyLabel;

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
