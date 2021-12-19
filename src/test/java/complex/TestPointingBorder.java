package complex;

import javax.swing.SwingUtilities;
import general.TestFrame;
import standard.helper.enums.CelestialDirection;
import standard.implementations.MyLabel;

public class TestPointingBorder {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    MyLabel north = new MyLabel("North");
	    MyLabel east = new MyLabel("East");
	    MyLabel south = new MyLabel("South");
	    MyLabel west = new MyLabel("West");
	    north.setBorder(new PointingBorder(CelestialDirection.NORTH, north));
	    east.setBorder(new PointingBorder(CelestialDirection.EAST, east));
	    south.setBorder(new PointingBorder(CelestialDirection.SOUTH, south));
	    west.setBorder(new PointingBorder(CelestialDirection.WEST, west));
	    TestFrame.showFrameWithComponents(north, east, south, west);
	});
    }

}
