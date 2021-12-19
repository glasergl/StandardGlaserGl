package general;

import java.awt.Image;
import java.awt.Window;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import container.MyFrame;

/**
 * Class which contains functions which are generally applicable for swing
 * programs.
 *
 * @author Gabriel Glaser
 * @version 16.11.2021
 */
public final class SwingFunctions {

    /**
     * Loads an image while the whole program being stored in a .jar-File.
     * 
     * @param imageFileName
     * @return The desired Image.
     */
    public static <T> Image getImage(final String imageFileName, final Class<T> relative) {
	try {
	    return ImageIO.read(relative.getClassLoader().getResourceAsStream(imageFileName));
	} catch (IOException e) {
	    e.printStackTrace();
	    throw new RuntimeException("Couldn't get image from jar with name " + imageFileName);
	}
    }

    public static MyFrame getMyFrame(final JComponent toGetFrameOf) {
	final Window window = SwingUtilities.windowForComponent(toGetFrameOf);
	if (!(window instanceof MyFrame)) {
	    throw new RuntimeException(toGetFrameOf + " was not added to a frame yet or not added to a MyFrame");
	} else {
	    return (MyFrame) window;
	}
    }
}
