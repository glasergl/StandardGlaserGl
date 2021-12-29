package eventListener.emptyImplementation;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Interface extension of MouseListener which does nothing, by default.
 *
 * @author Gabriel Glaser
 * @version 29.12.2021
 */
public interface MyMouseListener extends MouseListener {

    /**
     * Does nothing.
     * 
     * @param mouseClickEvent
     */
    @Override
    public default void mouseClicked(final MouseEvent mouseClickEvent) {
    }

    /**
     * Does nothing.
     * 
     * @param mousePressEvent
     */
    @Override
    public default void mousePressed(final MouseEvent mousePressEvent) {
    }

    /**
     * Does nothing.
     * 
     * @param mouseReleaseEvent
     */
    @Override
    public default void mouseReleased(final MouseEvent mouseReleaseEvent) {
    }

    /**
     * Does nothing.
     * 
     * @param mouseEnterEvent
     */
    @Override
    public default void mouseEntered(final MouseEvent mouseEnterEvent) {
    }

    /**
     * Does nothing.
     * 
     * @param mouseExitEvent
     */
    @Override
    public default void mouseExited(final MouseEvent mouseExitEvent) {
    }

}
