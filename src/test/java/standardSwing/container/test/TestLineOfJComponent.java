package standardSwing.container.test;

import javax.swing.SwingUtilities;

import de.glasergl.standard.swing.container.LineOfJComponent;
import de.glasergl.standard.swing.entity.Alignment;
import de.glasergl.standard.swing.myComponent.MyLabel;

public class TestLineOfJComponent {
    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    LineOfJComponent horizontal = new LineOfJComponent(Alignment.HORIZONTAL, 20, new MyLabel("Hee"), new MyLabel("GG"));
	    LineOfJComponent vertical = new LineOfJComponent(Alignment.VERTICAL, 50, new MyLabel("Hello"), new MyLabel("GG"));
	    TestFrame.showFrameWithComponents(horizontal, vertical);
	});
    }
}
