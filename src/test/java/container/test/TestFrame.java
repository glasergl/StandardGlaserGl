package container.test;

import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import container.MyFrame;
import settings.Colors;

public class TestFrame extends MyFrame {

    public TestFrame() {
	super("Test");
	start();
    }

    public static void main(String[] args) {
	Colors.setDarkModeEnabled(false);
	SwingUtilities.invokeLater(() -> {
	    new TestFrame();
	});
    }

    public static void showFrameWithComponents(final JComponent... components) {
	final MyFrame frame = new MyFrame("Test");
	final Container contentPane = frame.getContentPane();
	contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
	for (final JComponent component : components) {
	    contentPane.add(component);
	}
	frame.start();
	frame.setSize(frame.getWidth(), frame.getHeight() + 50);
    }

}
