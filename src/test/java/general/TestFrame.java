package general;

import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;

import standard.MyFrame;
import standard.implementations.MyLabel;
import standard.implementations.MyTextButton;
import standard.settings.Colors;

public class TestFrame extends MyFrame {

    public TestFrame() {
	super("Test");
	setLayout(new FlowLayout());
	setResizable(true);
	final MyLabel lb0 = new MyLabel("hehe");
	final MyLabel lb1 = new MyLabel("hehe");
	final MyLabel lb2 = new MyLabel("hehe");
	final MyLabel lb3 = new MyLabel("hehe");
	final MyLabel lb4 = new MyLabel("hehe");
	lb0.setOpaque(true);
	lb1.setOpaque(true);
	lb2.setOpaque(true);
	lb3.setOpaque(true);
	lb4.setOpaque(true);
	lb0.setBackground(Colors.getGray(0));
	lb1.setBackground(Colors.getGray(1));
	lb2.setBackground(Colors.getGray(2));
	lb3.setBackground(Colors.getGray(3));
	lb4.setBackground(Colors.getGray(4));
	System.out.println("" + Colors.getGray(0) + Colors.getGray(1) + Colors.getGray(2) + Colors.getGray(3) + Colors.getGray(4));
//	add(lb0);
//	add(lb1);
//	add(lb2);
//	add(lb3);
//	add(lb4);
	add(new MyTextButton("hehehehe"));
	setSize(500, 500);
	setLocationRelativeTo(null);
	setVisible(true);
	JDialog d = new JDialog(this);
	d.add(new MyLabel("hello there"));
	d.pack();
	d.setVisible(true);
    }

    public static void main(String[] args) {
	Colors.setDarkModeEnabled(true);
	SwingUtilities.invokeLater(() -> {
	    new TestFrame();
	});
    }

    public static void showFrameWithComponents(final JComponent... components) {
	final MyFrame frame = new MyFrame();
	frame.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
	for (final JComponent component : components) {
	    frame.getContentPane().add(component);
	}
	frame.start();
    }

}
