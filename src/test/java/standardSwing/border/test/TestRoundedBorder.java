package standardSwing.border.test;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import de.glasergl.standard.swing.border.LeftRightRoundBorder;
import standardSwing.container.test.TestFrame;

/**
 * @author Gabriel Glaser
 * @version 30.03.2022
 */
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
