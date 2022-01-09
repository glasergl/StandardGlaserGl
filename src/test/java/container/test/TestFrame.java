package container.test;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import container.MyFrame;
import general.SwingFunctions;
import settings.Colors;

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
	contentPane.setBackground(Color.RED);
	contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
	for (final JComponent component : components) {
	    contentPane.add(component);
	}
	frame.start();
	frame.setSize(frame.getWidth(), frame.getHeight() + 50);
    }

}
