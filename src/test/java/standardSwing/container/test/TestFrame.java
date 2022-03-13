package standardSwing.container.test;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import standardSwing.container.MyFrame;
import standardSwing.general.SwingFunctions;
import standardSwing.settings.Colors;

public class TestFrame extends MyFrame {

    public TestFrame() {
	super("Test");
    }

    public static void main(String[] args) {
	Colors.setDarkModeEnabled(false);
	SwingUtilities.invokeLater(() -> {
	    final TestFrame testFrame = new TestFrame();
	    testFrame.getContentPane().add(SwingFunctions.getInvisibleComponentWithBounds(50, 50));
	    testFrame.start();
	});
    }

    public static void showFrameWithComponents(final JComponent... components) {
	final MyFrame frame = new MyFrame("Test");
	final Container contentPane = frame.getContentPane();
	contentPane.setBackground(Color.WHITE);
	contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
	for (final JComponent component : components) {
	    contentPane.add(component);
	}
	frame.start();
	frame.setSize(frame.getWidth(), frame.getHeight() + 50);
    }

}
