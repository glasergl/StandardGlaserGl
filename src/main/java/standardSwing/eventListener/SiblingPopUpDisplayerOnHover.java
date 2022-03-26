package standardSwing.eventListener;

import java.awt.event.MouseEvent;
import java.util.Optional;
import javax.swing.JComponent;

import standardSwing.entity.CelestialDirection;
import standardSwing.eventListener.emptyImplementation.MyMouseListener;
import standardSwing.general.SwingFunctions;
import standardSwing.myComponent.MySiblingPopUp;

/**
 * MouseListener which sets the PopUp visible when the mouse hovers over the
 * component this MouseListener is added to.
 * 
 * @author Gabriel Glaser
 * @version 28.12.2021
 */
public class SiblingPopUpDisplayerOnHover implements MyMouseListener {

    private final JComponent toShowAsPopUp;
    private final JComponent sibling;
    private final Optional<CelestialDirection> relativeDirectionOfPopUp;

    private Optional<MySiblingPopUp> popUp;

    private SiblingPopUpDisplayerOnHover(final JComponent toShowAsPopUp, final JComponent sibling, final Optional<CelestialDirection> relativeDirectionOfPopUp) {
	super();
	this.toShowAsPopUp = toShowAsPopUp;
	this.sibling = sibling;
	this.relativeDirectionOfPopUp = relativeDirectionOfPopUp;
	sibling.addMouseListener(this);
    }

    public SiblingPopUpDisplayerOnHover(final JComponent toShowAsPopUp, final JComponent sibling, final CelestialDirection relativeDirectionOfPopUp) {
	this(toShowAsPopUp, sibling, Optional.of(relativeDirectionOfPopUp));
    }

    public SiblingPopUpDisplayerOnHover(final JComponent toShowAsPopUp, final JComponent sibling) {
	this(toShowAsPopUp, sibling, Optional.empty());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
	final MySiblingPopUp popUp;
	if (relativeDirectionOfPopUp.isPresent()) {
	    popUp = new MySiblingPopUp(toShowAsPopUp, relativeDirectionOfPopUp.get(), SwingFunctions.getMyFrame(sibling), sibling);
	} else {
	    popUp = new MySiblingPopUp(toShowAsPopUp, SwingFunctions.getMyFrame(sibling), sibling);
	}
	this.popUp = Optional.of(popUp);
	popUp.setVisible(true);

    }

    @Override
    public void mouseExited(MouseEvent e) {
	popUp.get().setVisible(false);
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
