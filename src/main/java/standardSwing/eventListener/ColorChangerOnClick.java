package standardSwing.eventListener;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.Optional;

import standardSwing.entity.ColorType;
import standardSwing.eventListener.emptyImplementation.MyMouseListener;

/**
 * This MouseListener changes (and changes back) either the background or the
 * foreground of the given Component when it gets clicked. The Component, which
 * gets its color changed, can be given at the constructor or else is the source
 * of the event.
 *
 * @author Gabriel Glaser
 * @version 29.12.2021
 */
public final class ColorChangerOnClick extends ColorChanger implements MyMouseListener {

    private ColorChangerOnClick(final Optional<Component> componentToChange, final Color colorAfterClick, final ColorType colorType) {
	super(componentToChange, colorAfterClick, colorType);
    }

    public ColorChangerOnClick(final Component componentToChange, final Color colorWhileFocused, final ColorType colorType) {
	this(Optional.of(componentToChange), colorWhileFocused, colorType);
    }

    public ColorChangerOnClick(final Color colorWhileFocused, final ColorType colorType) {
	this(Optional.empty(), colorWhileFocused, colorType);
    }

    @Override
    public void mouseClicked(final MouseEvent mouseClickEvent) {
	if (!oldColor.isPresent()) {
	    changeColor(mouseClickEvent.getComponent());
	} else {
	    changeColorBack(mouseClickEvent.getComponent());
	}
    }
}
