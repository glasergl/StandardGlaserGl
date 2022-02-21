package standardSwing.myComponent.test;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.SwingUtilities;
import standardSwing.container.test.TestFrame;
import standardSwing.myComponent.button.CustomButton;
import standardSwing.myComponent.button.MyJButton;

public class TestButton {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    MyJButton myJButton = new MyJButton("click1");
	    CustomButton customButton = new CustomButton("click2", Color.GRAY, Color.WHITE);
	    CustomButton withPreferredSize = new CustomButton("preferredSize", Color.BLACK, Color.WHITE);
	    CustomButton withBackgroundChange = new CustomButton("custom background change", Color.CYAN, Color.WHITE);
	    CustomButton withForegroundChange = new CustomButton("custom foreground change", Color.CYAN, Color.WHITE);

	    myJButton.addActionListener(click -> {
		System.out.println("click1");
	    });
	    customButton.addActionListener(click -> {
		System.out.println("click2");
	    });
	    withPreferredSize.setPreferredSize(new Dimension(200, 100));
	    withBackgroundChange.setBackgroundWhileHovered(Color.BLUE);
	    withForegroundChange.setForegroundWhileHovered(Color.GRAY);
	    withForegroundChange.setBackgroundWhileHovered(withForegroundChange.getBackground());
	    TestFrame.showFrameWithComponents(myJButton, customButton, withPreferredSize, withBackgroundChange, withForegroundChange);
	});
    }

}
