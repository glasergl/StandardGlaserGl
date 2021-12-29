package eventListener.emptyImplementation;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Interface extension of ComponentListener which does nothing, by default.
 *
 * @author Gabriel Glaser
 * @version 29.12.2021
 */
public interface MyComponentListener extends ComponentListener {

    /**
     * Does nothing.
     * 
     * @param resizeEvent
     */
    @Override
    public default void componentResized(final ComponentEvent resizeEvent) {
    }

    /**
     * Does nothing.
     * 
     * @param moveEvent
     */
    @Override
    public default void componentMoved(final ComponentEvent moveEvent) {
    }

    /**
     * Does nothing.
     * 
     * @param shownEvent
     */
    @Override
    public default void componentShown(final ComponentEvent shownEvent) {
    }

    /**
     * Does nothing.
     * 
     * @param hiddenEvent
     */
    @Override
    public default void componentHidden(final ComponentEvent hiddenEvent) {
    }

}
