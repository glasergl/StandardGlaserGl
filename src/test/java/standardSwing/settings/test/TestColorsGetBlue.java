package standardSwing.settings.test;

import java.awt.Dimension;
import javax.swing.JLabel;
import standardSwing.container.test.TestFrame;
import standardSwing.settings.Colors;

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
