package general;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Window;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import container.MyFrame;

/**
 * Class which contains functions which are generally applicable for swing
 * programs.
 *
 * @author Gabriel Glaser
 * @version 20.12.2021
 */
public final class SwingFunctions {

    /**
     * Function which Loads an Image.
     * 
     * The function works while the programm is packed in a .jar, too.
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

    /**
     * @param toGetFrameOf
     * @return The Window casted to MyFrame of the given JComponent.
     */
    public static MyFrame getMyFrame(final JComponent toGetFrameOf) {
	final Window window = SwingUtilities.windowForComponent(toGetFrameOf);
	if (!(window instanceof MyFrame)) {
	    throw new RuntimeException(toGetFrameOf + " was not added to a frame yet or not added to a MyFrame");
	} else {
	    return (MyFrame) window;
	}
    }

    /**
     * <<<<<<< HEAD Activates or deactivates Anti-Aliasing on a graphics context.
     * 
     * If Anti-Aliasing isn't supported on the Graphics instance, nothing happens.
     * 
     * @param context
     * @param antialiasingOn
     */
    public static void setAntialiasing(final Graphics context, final boolean antialiasingOn) {
	if (context instanceof Graphics2D) {
	    final Graphics2D graphics2D = (Graphics2D) context;
	    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antialiasingOn ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
	}
    }

    /**
     * @param width
     * @param height
     * @return An invisible JComponent with the desired width and height.
     */
    public static JComponent getInvisibleComponentWithBounds(final int width, final int height) {
	final JPanel invisibleJPanel = new JPanel();
	invisibleJPanel.setBackground(new Color(0, 0, 0, 0));
	invisibleJPanel.setPreferredSize(new Dimension(width, height));
	return invisibleJPanel;
    }

    /**
     * revalidates and repaints the given JComponent.
     * 
     * @param toUpdate
     */
    public static void updateJComponent(final JComponent toUpdate) {
	toUpdate.revalidate();
	toUpdate.repaint();
    }

    /**
     * @param toScale
     * @param newWidth
     * @param newHeight
     * @return The scaled ImageIcon.
     */
    public static ImageIcon scale(final ImageIcon toScale, final int newWidth, final int newHeight) {
	return new ImageIcon(toScale.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
    }
}
