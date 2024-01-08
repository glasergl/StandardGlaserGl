package standardGlaserGl.swing.eventListener;

import java.awt.event.FocusEvent;
import java.util.Optional;
import javax.swing.JComponent;
import javax.swing.border.Border;

import standardGlaserGl.swing.eventListener.emptyImplementation.MyFocusListener;

/**
 * FocusListener which changes the Border on focus of either the JComponent
 * given at constructor time, else the one it's added to. Automatically changes
 * it back when focus is lost.
 *
 * @author Gabriel Glaser
 */
public class BorderChangerOnFocus implements MyFocusListener {
    private final Border borderWhileFocused;
    private final Optional<JComponent> jComponentToChange;

    private Optional<Border> oldBorder = Optional.empty();

    private BorderChangerOnFocus(final Optional<JComponent> jComponent, final Border borderWhileFocused) {
	super();
	this.jComponentToChange = jComponent;
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
	final JComponent toChangeBorderOf = getCorrectJComponentToChange(focusGainedEvent);
	oldBorder = Optional.of(toChangeBorderOf.getBorder());
	toChangeBorderOf.setBorder(borderWhileFocused);
    }

    @Override
    public void focusLost(final FocusEvent focusLostEvent) {
	final JComponent toChangeBorderOf = getCorrectJComponentToChange(focusLostEvent);
	toChangeBorderOf.setBorder(oldBorder.get());
	oldBorder = Optional.empty();
    }

    private JComponent getCorrectJComponentToChange(final FocusEvent focusEvent) {
	if (jComponentToChange.isPresent()) {
	    return jComponentToChange.get();
	} else {
	    return (JComponent) focusEvent.getComponent();
	}
    }
}
