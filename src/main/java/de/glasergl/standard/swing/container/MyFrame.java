package de.glasergl.standard.swing.container;

import java.awt.Color;
import java.awt.Image;
import javax.swing.JFrame;

import de.glasergl.standard.swing.general.SwingFunctions;
import de.glasergl.standard.swing.settings.Colors;

/**
 * Top-Level container with standard properties to prevent boiler-plate code.
 *
 * @author Gabriel Glaser
 * @version 28.12.2021
 */
public class MyFrame extends JFrame {

    private static final Image STANDARD_ICON = SwingFunctions.getImage("StandardIcon.png", MyFrame.class);

    public MyFrame(final String title, final Image icon) {
	super(title);
	setIconImage(icon);
	setup();
    }

    public MyFrame(final String title) {
	this(title, STANDARD_ICON);
    }

    public MyFrame() {
	this("");
    }

    private void setup() {
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setResizable(false);
	setBackground(Colors.isDarkMode() ? Color.BLACK : Color.WHITE);
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
