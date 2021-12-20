package eventListener.emptyImplementation;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Empty implementation of ComponentListener.
 *
 * @author Gabriel Glaser
 * @version 20.12.2021
 */
public class MyComponentListener implements ComponentListener {

    @Override
    public void componentResized(final ComponentEvent componentEvent) {
    }

    @Override
    public void componentMoved(final ComponentEvent componentEvent) {
    }

    @Override
    public void componentShown(final ComponentEvent componentEvent) {
    }

    @Override
    public void componentHidden(final ComponentEvent componentEvent) {
    }

}
