package standardGlaserGl.swing.container.test;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import standardGlaserGl.swing.container.JPanelFactory;

public class TestJPanelFactory {
    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    JPanel jPanel = JPanelFactory.create(Color.RED, new JButton("Click me"), new JLabel("Hello there"));
	    TestFrame.showFrameWithComponents(jPanel);
	});
    }
}
