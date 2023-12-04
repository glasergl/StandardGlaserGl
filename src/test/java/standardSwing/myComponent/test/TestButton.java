package standardSwing.myComponent.test;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingUtilities;

import de.glasergl.standard.swing.myComponent.button.CustomTextButton;
import standardSwing.container.test.TestFrame;

public class TestButton {
    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    CustomTextButton customButton = new CustomTextButton("click", Color.GRAY, Color.WHITE);
	    CustomTextButton withPreferredSize = new CustomTextButton("preferredSize", Color.BLACK, Color.WHITE);
	    CustomTextButton withBackgroundChange = new CustomTextButton("custom background change", Color.CYAN, Color.WHITE);
	    CustomTextButton withForegroundChange = new CustomTextButton("custom foreground change", Color.CYAN, Color.WHITE);

	    customButton.addActionListener(click -> {
		System.out.println("click");
	    });
	    withPreferredSize.setPreferredSize(new Dimension(200, 100));
	    withBackgroundChange.setBackgroundWhileHovered(Color.BLUE);
	    withForegroundChange.setForegroundWhileHovered(Color.GRAY);
	    withForegroundChange.setBackgroundWhileHovered(withForegroundChange.getBackground());
	    TestFrame.showFrameWithComponents(customButton, withPreferredSize, withBackgroundChange, withForegroundChange);
	});
    }
}
