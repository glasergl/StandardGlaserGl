package standardSwing.border.test;

import java.awt.Color;

import javax.swing.SwingUtilities;

import de.glasergl.standard.swing.border.PointingBorder;
import de.glasergl.standard.swing.entity.CelestialDirection;
import de.glasergl.standard.swing.myComponent.MyLabel;
import standardSwing.container.test.TestFrame;

public class TestPointingBorder {
    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    MyLabel north = new MyLabel("North");
	    MyLabel west = new MyLabel("West");
	    MyLabel south = new MyLabel("South");
	    MyLabel east = new MyLabel("East");
	    north.setBorder(new PointingBorder(CelestialDirection.NORTH, Color.RED, Color.BLUE));
	    west.setBorder(new PointingBorder(CelestialDirection.WEST, Color.RED, Color.BLUE));
	    south.setBorder(new PointingBorder(CelestialDirection.SOUTH, Color.RED, Color.BLUE));
	    east.setBorder(new PointingBorder(CelestialDirection.EAST, Color.RED, Color.BLUE));
	    TestFrame.showFrameWithComponents(north, west, south, east);
	});
    }
}
