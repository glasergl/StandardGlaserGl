package de.glasergl.standard.swing.eventListener.emptyImplementation;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Interface extension of FocusListener which does nothing, by default.
 *
 * @author Gabriel Glaser
 */
public interface MyFocusListener extends FocusListener {
    /**
     * Does nothing.
     * 
     * @param focusGainEvent
     */
    @Override
    public default void focusGained(final FocusEvent focusGainEvent) {
    }

    /**
     * Does nothing.
     * 
     * @param focusLostEvent
     */
    @Override
    public default void focusLost(final FocusEvent focusLostEvent) {
    }
}
