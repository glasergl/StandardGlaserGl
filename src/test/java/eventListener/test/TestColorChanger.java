package eventListener.test;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import container.test.TestFrame;
import eventListener.ColorChangerOnFocus;
import eventListener.ColorChangerOnHover;

public class TestColorChanger {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    JButton bt1 = new JButton("Hello there");
	    bt1.setOpaque(true);
	    JButton bt2 = new JButton("General Kenobi");
	    bt1.addMouseListener(new ColorChangerOnHover(Color.RED, Color.WHITE));
	    bt2.addFocusListener(new ColorChangerOnFocus(Color.RED, Color.WHITE));
	    TestFrame.showFrameWithComponents(bt1, bt2);
	});
    }

}
