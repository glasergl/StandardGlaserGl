package standardSwing.container.test;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class TestFrame {
    public static JFrame showFrameWithComponents(final JComponent... components) {
	final JFrame frame = new JFrame("Test");
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	final Container contentPane = frame.getContentPane();
	contentPane.setBackground(Color.WHITE);
	contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
	for (final JComponent component : components) {
	    contentPane.add(component);
	}
	frame.pack();
	frame.setSize(frame.getWidth(), frame.getHeight() + 50);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
	return frame;
    }
}
