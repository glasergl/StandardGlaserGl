package standardGlaserGl.swing.container.test;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import standardGlaserGl.swing.container.LineOfJComponent;
import standardGlaserGl.swing.entity.Alignment;

public class TestLineOfJComponent {
    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    LineOfJComponent horizontal = new LineOfJComponent(Alignment.HORIZONTAL, 20, new JLabel("Hee"), new JLabel("GG"));
	    LineOfJComponent vertical = new LineOfJComponent(Alignment.VERTICAL, 50, new JLabel("Hello"), new JLabel("GG"));
	    TestFrame.showFrameWithComponents(horizontal, vertical);
	});
    }
}
