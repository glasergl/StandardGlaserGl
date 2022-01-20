package standardSwing.myComponent.test;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import standardSwing.container.test.TestFrame;
import standardSwing.general.SwingFunctions;
import standardSwing.myComponent.button.MyIconButton;
import standardSwing.myComponent.button.MyTextButton;

public class TestButton {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    MyTextButton textButton = new MyTextButton("Click me!");
	    ImageIcon icon = new ImageIcon(SwingFunctions.getImage("TestIcon.png", TestButton.class));
	    MyIconButton iconButton = new MyIconButton(icon, 100, 100);
	    MyTextButton textButton1 = new MyTextButton("Click me with other colors!");
	    textButton1.setBackground(Color.RED);
	    textButton1.setBackgroundWhileMouseHovered(Color.GREEN);
	    textButton1.setForegroundWhileMouseHovered(Color.WHITE);
	    TestFrame.showFrameWithComponents(textButton, textButton1, iconButton);
	});
    }

}
