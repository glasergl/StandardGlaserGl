package de.glasergl.standard.swing.eventListener.emptyImplementation;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Interface extension of DocumentListener which, by default, calls the method
 * update() on insert, remove and change.
 * 
 * update() does nothing by default.
 *
 * @author Gabriel Glaser
 * @version 29.12.2021
 */
public interface MyDocumentListener extends DocumentListener {

    /**
     * Calls update().
     * 
     * @param insertEvent
     */
    @Override
    public default void insertUpdate(final DocumentEvent insertEvent) {
	update();
    }

    /**
     * Calls update().
     * 
     * @param removeEvent
     */
    @Override
    public default void removeUpdate(final DocumentEvent removeEvent) {
	update();
    }

    /**
     * Calls update().
     * 
     * @param changeEvent
     */
    @Override
    public default void changedUpdate(final DocumentEvent changeEvent) {
	update();
    }

    /**
     * Does nothing. By default: Gets called by insertUpdate(), removeUpdate() and
     * changedUpdate().
     */
    public default void update() {
    }

}
