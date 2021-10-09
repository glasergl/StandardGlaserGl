package standardJComponents;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.JFrame;
import standardJComponents.helper.CelestialDirection;
import standardJComponents.helper.PopUpOnHoverController;
import standardJComponents.implementations.MyLabel;
import standardJComponents.implementations.MyPopUpComponent;
import standardJComponents.settings.Colors;

/**
 * Top-Level container with standard properties to prevent boiler-plate code.
 *
 * @author Gabriel Glaser
 * @version 27.09.2021
 */
public class MyFrame extends JFrame {

    public MyFrame(final String title, final Image icon) {
	super(title);
	setIconImage(icon);
	setup();
    }

    public MyFrame(final String title) {
	this(title, null);
    }

    private void setup() {
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setResizable(false);
	setLayout(new BorderLayout());
	setBackground(Colors.getBackground(1));
    }

    public void start() {
	pack();
	setLocationRelativeTo(null);
	setVisible(true);
    }

    public static void main(String[] args) {
	final MyFrame frame = new MyFrame("test");
	final MyLabel lb = new MyLabel("HoverMe!");
	lb.setPreferredSize(new Dimension(100, 100));
	final MyLabel lbHover = new MyLabel("Hover successful!");
	final MyPopUpComponent hover = new MyPopUpComponent(lbHover);
	lb.addMouseListener(new PopUpOnHoverController(hover, CelestialDirection.EAST));
	frame.add(lb);
	frame.start();
    }

}
