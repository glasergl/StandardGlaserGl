package implementations;

import general.TestFrame;
import standard.helper.listeners.SiblingPopUpDisplayerOnClick;
import standard.helper.listeners.SiblingPopUpDisplayerOnHover;
import standard.implementations.MyLabel;

public class TestMySiblingPopUp {

    static MyLabel hoverTest = new MyLabel("hover");
    static MyLabel clickTest = new MyLabel("click");

    static MyLabel hoverPopUp = new MyLabel("hovered");
    static MyLabel clickPopUp = new MyLabel("clicked");

    public static void main(String[] args) {
	new SiblingPopUpDisplayerOnHover(hoverPopUp, hoverTest);
	new SiblingPopUpDisplayerOnClick(clickPopUp, clickTest);
	TestFrame.showFrameWithComponents(hoverTest, clickTest);
    }
}
