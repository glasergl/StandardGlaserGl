package standard.helper.emptyListenerImplementations;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Empty implementation of ComponentListener.
 *
 * @author Gabriel Glaser
 * @version 05.12.2021
 */
public class MyComponentListener implements ComponentListener {

    @Override
    public void componentResized(ComponentEvent e) {
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }

}
