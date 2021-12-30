package container.test;

import javax.swing.SwingUtilities;
import container.LineOfJComponent;
import entity.Alignment;
import myComponent.MyLabel;
import myComponent.button.MyTextButton;

public class TestLineOfJComponent {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    LineOfJComponent horizontal = new LineOfJComponent(Alignment.HORIZONTAL, 20, new MyLabel("Hee"), new MyTextButton("test"), new MyLabel("GG"));
	    LineOfJComponent vertical = new LineOfJComponent(Alignment.VERTICAL, 50, new MyLabel("Hello"), new MyLabel("GG"), new MyTextButton("test"));
	    TestFrame.showFrameWithComponents(horizontal, vertical);
	});
    }

}
