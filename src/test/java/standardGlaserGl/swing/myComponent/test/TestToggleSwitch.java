package standardGlaserGl.swing.myComponent.test;

import java.awt.Dimension;
import javax.swing.SwingUtilities;

import standardGlaserGl.swing.container.test.TestFrame;
import standardGlaserGl.swing.myComponent.MyToggleSwitch;

public class TestToggleSwitch {
    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    MyToggleSwitch tsUntoggled = new MyToggleSwitch();
	    MyToggleSwitch tsToggled = new MyToggleSwitch();
	    tsUntoggled.setPreferredSize(new Dimension(200, 100));
	    tsToggled.setPreferredSize(new Dimension(200, 100));
	    TestFrame.showFrameWithComponents(tsUntoggled, tsToggled);
	});
    }
}
