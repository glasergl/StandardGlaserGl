package standardSwing.container.test;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import de.glasergl.standard.swing.container.RowOfJComponent;

public class TestRowOfJComponent {
    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    RowOfJComponent row = new RowOfJComponent(5, 5);
	    JLabel left1 = new JLabel("left1");
	    JLabel left2 = new JLabel("left2");
	    JLabel left3 = new JLabel("left3");

	    JLabel middle1 = new JLabel("middle1");
	    JLabel middle2 = new JLabel("middle2");

	    JLabel right1 = new JLabel("right1");
	    JLabel right2 = new JLabel("right2");

	    row.addToLeft(left1);
	    row.addToLeft(left2);
	    row.addToLeft(left3);

	    row.addToMiddle(middle1);
	    row.addToMiddle(middle2);

	    row.addToRight(right1);
	    row.addToRight(right2);

	    row.setPreferredSize(new Dimension(800, row.getPreferredSize().height));

	    TestFrame.showFrameWithComponents(row);
	});
    }
}
