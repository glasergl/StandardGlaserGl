package standardSwing.myComponent.test;

import javax.swing.SwingUtilities;
import standardSwing.container.test.TestFrame;
import standardSwing.myComponent.button.MyTextButton;

public class TestButton {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    MyTextButton textButton = new MyTextButton("Click me!");
	    TestFrame.showFrameWithComponents(textButton);
	});
    }

}
