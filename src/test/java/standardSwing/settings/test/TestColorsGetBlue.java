package standardSwing.settings.test;

import java.awt.Dimension;
import javax.swing.JLabel;

import de.glasergl.standard.swing.settings.Colors;
import standardSwing.container.test.TestFrame;

public class TestColorsGetBlue {
    public static void main(String[] args) {
	JLabel[] labelsWithBlueColors = new JLabel[Colors.NUMBER_OF_BLUE_COLORS];
	for (int i = 0; i < Colors.NUMBER_OF_BLUE_COLORS; i++) {
	    JLabel lb = new JLabel();
	    lb.setPreferredSize(new Dimension(30, 30));
	    lb.setOpaque(true);
	    lb.setBackground(Colors.getBlue(i));
	    labelsWithBlueColors[i] = lb;
	}
	TestFrame.showFrameWithComponents(labelsWithBlueColors);
    }
}
