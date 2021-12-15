package implementations;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import general.TestFrame;
import standard.implementations.MyIconButton;
import standard.implementations.MyTextButton;

public class TestButton {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			String buttonText = "Click me!";
			MyTextButton textButton = new MyTextButton(buttonText);
			ImageIcon icon = new ImageIcon("src\\test\\resources\\TestIcon.png");
			MyIconButton iconButton = new MyIconButton(icon, 100, 100);
			TestFrame.showFrameWithComponents(textButton, iconButton);
		});
	}

}
