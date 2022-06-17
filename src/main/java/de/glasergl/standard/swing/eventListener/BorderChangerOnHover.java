package de.glasergl.standard.swing.eventListener;

import java.awt.event.MouseEvent;
import java.util.Optional;
import javax.swing.JComponent;
import javax.swing.border.Border;

import de.glasergl.standard.swing.eventListener.emptyImplementation.MyMouseListener;

/**
 * 
 *
 * @author Gabriel Glaser
 * @version 29.12.2021
 */
public class BorderChangerOnHover implements MyMouseListener {

    private final Border borderWhileHovered;
    private final Optional<JComponent> jComponentToChange;

    private Optional<Border> oldBorder = Optional.empty();

    private BorderChangerOnHover(final Optional<JComponent> jComponent, final Border borderWhileHovered) {
	super();
	this.jComponentToChange = jComponent;
	this.borderWhileHovered = borderWhileHovered;
    }

    public BorderChangerOnHover(final JComponent jComponentToChangeBorderOf, final Border borderWhileFocused) {
	this(Optional.of(jComponentToChangeBorderOf), borderWhileFocused);
    }

    public BorderChangerOnHover(final Border borderWhileFocused) {
	this(Optional.empty(), borderWhileFocused);
    }

    @Override
    public void mouseEntered(final MouseEvent mouseEnterEvent) {
	final JComponent toChangeBorderOf = getCorrectJComponentToChange(mouseEnterEvent);
	oldBorder = Optional.of(toChangeBorderOf.getBorder());
	toChangeBorderOf.setBorder(borderWhileHovered);
    }

    @Override
    public void mouseExited(final MouseEvent mouseExitEvent) {
	final JComponent toChangeBorderOf = getCorrectJComponentToChange(mouseExitEvent);
	toChangeBorderOf.setBorder(oldBorder.get());
	oldBorder = Optional.empty();
    }

    private JComponent getCorrectJComponentToChange(final MouseEvent focusEvent) {
	if (jComponentToChange.isPresent()) {
	    return jComponentToChange.get();
	} else {
	    return (JComponent) focusEvent.getComponent();
	}
    }

}