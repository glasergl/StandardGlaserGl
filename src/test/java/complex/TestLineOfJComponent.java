package complex;

import javax.swing.SwingUtilities;

import general.TestFrame;
import standard.helper.enums.Alignment;
import standard.implementations.MyLabel;
import standard.implementations.MyTextButton;

public class TestLineOfJComponent {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			LineOfJComponent horizontal = new LineOfJComponent(Alignment.HORIZONTAL, new MyLabel("Hee"),
					new MyTextButton("test"), new MyLabel("GG"));
			LineOfJComponent vertical = new LineOfJComponent(Alignment.VERTICAL, new MyLabel("Hello"),
					new MyLabel("GG"), new MyTextButton("test"));
			TestFrame.showFrameWithComponents(horizontal, vertical);
		});
	}

}
