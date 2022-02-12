package standardSwing.myComponent.test;

import java.awt.Color;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import standardSwing.container.test.TestFrame;
import standardSwing.myComponent.button.CustomButton;
import standardSwing.myComponent.button.MyJButton;

public class TestButton {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    MyJButton myJButton = new MyJButton("Hello");
	    CustomButton textButton = new CustomButton("Hello", Color.GRAY, Color.WHITE, new LineBorder(Color.GREEN, 1));

	    myJButton.addActionListener(click -> {
		System.out.println("click1");
	    });
	    textButton.addActionListener(click -> {
		System.out.println("click2");
	    });
	    TestFrame.showFrameWithComponents(myJButton, textButton);
	});
    }

}
