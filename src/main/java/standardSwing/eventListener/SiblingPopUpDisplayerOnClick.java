package standardSwing.eventListener;

import java.awt.event.MouseEvent;
import java.util.Optional;
import javax.swing.JComponent;

import standardSwing.entity.CelestialDirection;
import standardSwing.eventListener.emptyImplementation.MyMouseListener;
import standardSwing.general.SwingFunctions;
import standardSwing.myComponent.MySiblingPopUp;

/**
 * MouseListener which changes the visibility of the pop up on click.
 * 
 * @author Gabriel Glaser
 * @version 28.12.2021
 */
public class SiblingPopUpDisplayerOnClick implements MyMouseListener {

    private final JComponent toShowAsPopUp;
    private final JComponent sibling;
    private final Optional<CelestialDirection> relativeDirectionOfPopUp;

    private Optional<MySiblingPopUp> popUp = Optional.empty();

    private SiblingPopUpDisplayerOnClick(final JComponent toShowAsPopUp, final JComponent sibling, final Optional<CelestialDirection> relativeDirectionOfPopUp) {
	super();
	this.toShowAsPopUp = toShowAsPopUp;
	this.sibling = sibling;
	this.relativeDirectionOfPopUp = relativeDirectionOfPopUp;
	sibling.addMouseListener(this);
    }

    public SiblingPopUpDisplayerOnClick(final JComponent toShowAsPopUp, final JComponent sibling, final CelestialDirection relativeDirectionOfPopUp) {
	this(toShowAsPopUp, sibling, Optional.of(relativeDirectionOfPopUp));
    }

    public SiblingPopUpDisplayerOnClick(final JComponent toShowAsPopUp, final JComponent sibling) {
	this(toShowAsPopUp, sibling, Optional.empty());
    }

    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
	if (!popUp.isPresent()) {
	    final MySiblingPopUp popUp;
	    if (relativeDirectionOfPopUp.isPresent()) {
		popUp = new MySiblingPopUp(toShowAsPopUp, relativeDirectionOfPopUp.get(), SwingFunctions.getMyFrame(sibling), sibling);
	    } else {
		popUp = new MySiblingPopUp(toShowAsPopUp, SwingFunctions.getMyFrame(sibling), sibling);
	    }
	    this.popUp = Optional.of(popUp);
	    popUp.setVisible(true);
	} else {
	    final MySiblingPopUp popUp = this.popUp.get();
	    popUp.setVisible(!popUp.isVisible());
	}
    }

    public MySiblingPopUp getPopUp() {
	if (!popUp.isPresent()) {
	    throw new RuntimeException("Pop Up wasn't created yet");
	} else {
	    return popUp.get();
	}
    }

    public boolean popUpHasBeenCreated() {
	return popUp.isPresent();
    }

}
