package eventListener;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.Optional;
import entity.ColorType;
import eventListener.emptyImplementation.MyMouseListener;

/**
 * This MouseListener changes either the background or the foreground of the
 * given Component when the Mouse hovers over the Component this is added to.
 * The Component, which gets its color changed, can be given at the constructor
 * or else is the source of the event.
 *
 * @author Gabriel Glaser
 * @version 29.12.2021
 */
public class ColorChangerOnHover extends ColorChanger implements MyMouseListener {

    private ColorChangerOnHover(final Optional<Component> componentToChange, final Color colorAfterClick, final ColorType colorType) {
	super(componentToChange, colorAfterClick, colorType);
    }

    public ColorChangerOnHover(final Component componentToChange, final Color colorWhileFocused, final ColorType colorType) {
	this(Optional.of(componentToChange), colorWhileFocused, colorType);
    }

    public ColorChangerOnHover(final Color colorWhileFocused, final ColorType colorType) {
	this(Optional.empty(), colorWhileFocused, colorType);
    }

    @Override
    public void mouseEntered(final MouseEvent mouseEnterEvent) {
	changeColor(mouseEnterEvent.getComponent());
    }

    @Override
    public void mouseExited(final MouseEvent mouseExitEvent) {

	changeColorBack(mouseExitEvent.getComponent());
    }

}
