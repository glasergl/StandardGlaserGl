package standardGlaserGl.swing.border.test;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import standardGlaserGl.swing.border.LeftRightRoundBorder;
import standardGlaserGl.swing.container.test.TestFrame;

public class TestRoundedBorder {
    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    JLabel label = new JLabel("round border");
	    label.setOpaque(true);
	    label.setBackground(Color.CYAN);
	    label.setBorder(new LeftRightRoundBorder());
	    TestFrame.showFrameWithComponents(label);
	});
    }
}
