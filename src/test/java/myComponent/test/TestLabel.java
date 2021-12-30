package myComponent.test;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import container.test.TestFrame;
import myComponent.MyLabel;

public class TestLabel {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    MyLabel labelWithText = new MyLabel("Test12312");
	    MyLabel labelWithIcon = new MyLabel(new ImageIcon("src\\test\\resources\\TestIcon.png"), 150, 150);
	    TestFrame.showFrameWithComponents(labelWithText, labelWithIcon);
	});
    }

}
