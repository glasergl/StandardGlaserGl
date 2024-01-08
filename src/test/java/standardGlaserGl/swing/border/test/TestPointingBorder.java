package standardGlaserGl.swing.border.test;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import standardGlaserGl.swing.border.PointingBorder;
import standardGlaserGl.swing.container.test.TestFrame;
import standardGlaserGl.swing.entity.CelestialDirection;

public class TestPointingBorder {
    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    JLabel north = new JLabel("North");
	    JLabel west = new JLabel("West");
	    JLabel south = new JLabel("South");
	    JLabel east = new JLabel("East");
	    north.setPreferredSize(new Dimension(100, 100));
	    west.setPreferredSize(new Dimension(100, 100));
	    south.setPreferredSize(new Dimension(100, 100));
	    east.setPreferredSize(new Dimension(100, 100));
	    
	    north.setBorder(new PointingBorder(CelestialDirection.NORTH, Color.RED, Color.BLUE));
	    west.setBorder(new PointingBorder(CelestialDirection.WEST, Color.RED, Color.BLUE));
	    south.setBorder(new PointingBorder(CelestialDirection.SOUTH, Color.RED, Color.BLUE));
	    east.setBorder(new PointingBorder(CelestialDirection.EAST, Color.RED, Color.BLUE));
	    
	    TestFrame.showFrameWithComponents(north, west, south, east);
	});
    }
}
