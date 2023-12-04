package de.glasergl.standard.swing.eventListener;

import java.awt.event.MouseEvent;
import java.util.Optional;
import javax.swing.JComponent;

import de.glasergl.standard.swing.entity.CelestialDirection;
import de.glasergl.standard.swing.eventListener.emptyImplementation.MyMouseListener;
import de.glasergl.standard.swing.general.SwingFunctions;
import de.glasergl.standard.swing.myComponent.MySiblingPopUp;

/**
 * MouseListener which sets the PopUp visible when the mouse hovers over the
 * component this MouseListener is added to.
 * 
 * @author Gabriel Glaser
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
	    popUp = new MySiblingPopUp(toShowAsPopUp, relativeDirectionOfPopUp.get(), SwingFunctions.getJFrame(sibling), sibling);
	} else {
	    popUp = new MySiblingPopUp(toShowAsPopUp, SwingFunctions.getJFrame(sibling), sibling);
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
