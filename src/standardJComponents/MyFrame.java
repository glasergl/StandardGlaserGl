package standardJComponents;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
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
		getContentPane().setBackground(Colors.getBackground(1));
	}

	public void start() {
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * This code is necessary to load an image in a .jar-File.
	 * 
	 * @param imageFileName
	 * @return The desired Image
	 */
	public static <T> Image getImageWithInJar(final String imageFileName, final Class<T> relative) {
		try {
			return ImageIO.read(relative.getClassLoader().getResourceAsStream(imageFileName));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
