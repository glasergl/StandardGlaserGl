package implementations;

import javax.swing.SwingUtilities;
import general.TestFrame;
import standard.implementations.MyLabel;

public class TestLabel {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			TestFrame.showFrameWithComponents(new MyLabel("Test1231231"));
		});
	}

}
