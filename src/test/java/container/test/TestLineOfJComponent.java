package container.test;

import javax.swing.SwingUtilities;

import container.LineOfJComponent;
import entities.Alignment;
import myComponent.MyLabel;
import myComponent.MyTextButton;

public class TestLineOfJComponent {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    LineOfJComponent horizontal = new LineOfJComponent(Alignment.HORIZONTAL, new MyLabel("Hee"), new MyTextButton("test"), new MyLabel("GG"));
	    LineOfJComponent vertical = new LineOfJComponent(Alignment.VERTICAL, new MyLabel("Hello"), new MyLabel("GG"), new MyTextButton("test"));
	    TestFrame.showFrameWithComponents(horizontal, vertical);
	});
    }

}
