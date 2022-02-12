package standardSwing.container.test;

import javax.swing.SwingUtilities;

import standardSwing.container.LineOfJComponent;
import standardSwing.entity.Alignment;
import standardSwing.myComponent.MyLabel;
import standardSwing.myComponent.textFieldD.MyTextButton;

public class TestLineOfJComponent {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    LineOfJComponent horizontal = new LineOfJComponent(Alignment.HORIZONTAL, 20, new MyLabel("Hee"), new MyTextButton("test"), new MyLabel("GG"));
	    LineOfJComponent vertical = new LineOfJComponent(Alignment.VERTICAL, 50, new MyLabel("Hello"), new MyLabel("GG"), new MyTextButton("test"));
	    TestFrame.showFrameWithComponents(horizontal, vertical);
	});
    }

}
