package eventListener.emptyImplementation;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Implementation of a DocumentListener which, by default, calls the new method
 * update() on insert, remove and change.
 *
 * @author Gabriel Glaser
 * @version 29.12.2021
 */
public class MyDocumentListener implements DocumentListener {

    /**
     * Calls update().
     * 
     * @param insertDocumentEvent
     */
    @Override
    public void insertUpdate(final DocumentEvent insertDocumentEvent) {
	update();
    }

    /**
     * Calls update().
     * 
     * @param insertDocumentEvent
     */
    @Override
    public void removeUpdate(final DocumentEvent removeDocumentEvent) {
	update();
    }

    /**
     * Calls update().
     * 
     * @param insertDocumentEvent
     */
    @Override
    public void changedUpdate(final DocumentEvent documentChanged) {
	update();
    }

    /**
     * Does nothing. By default: Gets called by insertUpdate(), removeUpdate() and
     * changedUpdate().
     */
    public void update() {
    }

}
