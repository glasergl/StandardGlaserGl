package myComponent.test;

import javax.swing.SwingUtilities;
import container.test.TestFrame;
import myComponent.MyLabel;

public class TestLabel {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    MyLabel lb = new MyLabel("Test12312");
	    TestFrame.showFrameWithComponents(lb);
	});
    }

}
