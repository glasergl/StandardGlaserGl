package de.glasergl.standard.swing.eventListener;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.util.Optional;

import de.glasergl.standard.swing.entity.ColorType;
import de.glasergl.standard.swing.eventListener.emptyImplementation.MyFocusListener;

/**
 * This FocusListener changes either the background or the foreground of the
 * given Component when the Component this is added to is focused. The
 * Component, which gets its color changed, can be given at the constructor or
 * else is the source of the event.
 *
 * @author Gabriel Glaser
 */
public class ColorChangerOnFocus extends ColorChanger implements MyFocusListener {
    private ColorChangerOnFocus(final Optional<Component> componentToChange, final Color colorAfterClick, final ColorType colorType) {
	super(componentToChange, colorAfterClick, colorType);
    }

    public ColorChangerOnFocus(final Component componentToChange, final Color colorWhileFocused, final ColorType colorType) {
	this(Optional.of(componentToChange), colorWhileFocused, colorType);
    }

    public ColorChangerOnFocus(final Color colorWhileFocused, final ColorType colorType) {
	this(Optional.empty(), colorWhileFocused, colorType);
    }

    @Override
    public void focusGained(final FocusEvent focusGainEvent) {
	changeColor(focusGainEvent.getComponent());
    }

    @Override
    public void focusLost(final FocusEvent focusLostEvent) {
	changeColorBack(focusLostEvent.getComponent());
    }
}
