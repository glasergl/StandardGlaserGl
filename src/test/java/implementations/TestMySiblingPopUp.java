package implementations;

import general.TestFrame;
import standard.helper.listeners.SiblingPopUpDisplayerOnClick;
import standard.helper.listeners.SiblingPopUpDisplayerOnHover;
import standard.implementations.MyLabel;

public class TestMySiblingPopUp {

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
