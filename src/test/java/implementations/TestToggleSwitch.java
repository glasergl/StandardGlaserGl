package implementations;

import javax.swing.SwingUtilities;
import general.TestFrame;
import standard.implementations.MyToggleSwitch;

public class TestToggleSwitch {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    TestFrame.showFrameWithComponents(new MyToggleSwitch());
	});
    }

}
