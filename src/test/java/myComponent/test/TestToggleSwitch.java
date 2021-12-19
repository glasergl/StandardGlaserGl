package myComponent.test;

import javax.swing.SwingUtilities;

import container.test.TestFrame;
import myComponent.MyToggleSwitch;

public class TestToggleSwitch {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    MyToggleSwitch tsUntoggled = new MyToggleSwitch();
	    MyToggleSwitch tsToggled = new MyToggleSwitch();
	    TestFrame.showFrameWithComponents(tsUntoggled, tsToggled);
	});
    }

}
