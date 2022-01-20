package standardSwing.eventListener;

import java.awt.Color;
import java.awt.Component;
import java.util.Optional;

import standardSwing.entity.ColorType;

/**
 * Class which changes either the background or the foreground of the given
 * Component. The given Component can either be the Component given in the
 * constructor, or else the Component given as argument in the changeColor() or
 * changeColorBack() methods.
 *
 * @author Gabriel Glaser
 * @version 29.12.2021
 */
public class ColorChanger {

    protected final Color changedColor;
    protected final ColorType colorType;
    protected final Optional<Component> componentToChange;

    protected Optional<Color> oldColor = Optional.empty();

    protected ColorChanger(final Optional<Component> componentToChange, final Color changedColor, final ColorType colorType) {
	super();
	this.componentToChange = componentToChange;
	this.changedColor = changedColor;
	this.colorType = colorType;
    }

    public ColorChanger(final Component componentToChange, final Color colorWhileFocused, final ColorType colorType) {
	this(Optional.of(componentToChange), colorWhileFocused, colorType);
    }

    public ColorChanger(final Color colorWhileFocused, final ColorType colorType) {
	this(Optional.empty(), colorWhileFocused, colorType);
    }

    public void changeColor(final Component component) {
	final Component componentToChange = this.componentToChange.isPresent() ? this.componentToChange.get() : component;
	if (colorType == ColorType.BACKGROUND) {
	    oldColor = Optional.of(componentToChange.getBackground());
	    componentToChange.setBackground(changedColor);
	} else {
	    oldColor = Optional.of(componentToChange.getForeground());
	    componentToChange.setForeground(changedColor);
	}
    }

    public void changeColorBack(final Component component) {
	final Component componentToChangeBack = componentToChange.isPresent() ? componentToChange.get() : component;
	if (colorType == ColorType.BACKGROUND) {
	    componentToChangeBack.setBackground(oldColor.get());
	} else {
	    componentToChangeBack.setForeground(oldColor.get());
	}
	oldColor = Optional.empty();
    }
}
