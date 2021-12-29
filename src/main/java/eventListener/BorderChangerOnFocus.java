package eventListener;

import java.awt.event.FocusEvent;
import java.util.Optional;
import javax.swing.JComponent;
import javax.swing.border.Border;
import eventListener.emptyImplementation.MyFocusListener;

/**
 * FocusListener which changes the Border of the JComponent it's added to while
 * it has focus and changes it back if the JComponent loses focus.
 *
 * @author Gabriel Glaser
 * @version 29.12.2021
 */
public class BorderChangerOnFocus extends MyFocusListener {

    private final Border borderWhileFocused;
    private final Optional<JComponent> jComponent;

    private Optional<Border> oldBorder = Optional.empty();

    private BorderChangerOnFocus(final Optional<JComponent> jComponent, final Border borderWhileFocused) {
	super();
	this.jComponent = jComponent;
	this.borderWhileFocused = borderWhileFocused;
    }

    public BorderChangerOnFocus(final JComponent jComponentToChangeBorderOf, final Border borderWhileFocused) {
	this(Optional.of(jComponentToChangeBorderOf), borderWhileFocused);
    }

    public BorderChangerOnFocus(final Border borderWhileFocused) {
	this(Optional.empty(), borderWhileFocused);
    }

    @Override
    public void focusGained(final FocusEvent focusGainedEvent) {
	final JComponent toChangeBorderOf;
	if (this.jComponent.isPresent()) {
	    toChangeBorderOf = this.jComponent.get();
	} else {
	    toChangeBorderOf = (JComponent) focusGainedEvent.getComponent();
	}
	oldBorder = Optional.of(toChangeBorderOf.getBorder());
	toChangeBorderOf.setBorder(borderWhileFocused);
    }

    @Override
    public void focusLost(final FocusEvent focusLostEvent) {
	if (jComponent.isPresent()) {
	    jComponent.get().setBorder(oldBorder.get());
	} else {
	    final JComponent jComponent = (JComponent) focusLostEvent.getComponent();
	    jComponent.setBorder(oldBorder.get());
	}
    }

}
