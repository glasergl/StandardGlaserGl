package standard;

import java.awt.Color;
import java.awt.Image;
import javax.swing.JFrame;
import standard.settings.Colors;

/**
 * Top-Level container with standard properties to prevent boiler-plate code.
 *
 * @author Gabriel Glaser
 * @version 16.11.2021
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

    public MyFrame() {
	this("");
    }

    private void setup() {
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setResizable(false);
	getContentPane().setBackground(Colors.isDarkMode() ? Color.BLACK : Color.WHITE);
    }

    /**
     * Sets the size and position of all components, centers the whole frame and
     * makes it visible.
     */
    public void start() {
	pack();
	setLocationRelativeTo(null);
	setVisible(true);
    }

}
