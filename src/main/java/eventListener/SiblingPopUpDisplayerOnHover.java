package eventListener;

import java.awt.event.MouseEvent;
import java.util.Optional;
import javax.swing.JComponent;
import eventListener.emptyImplementation.MyMouseListener;
import general.SwingFunctions;
import myComponent.MySiblingPopUp;

/**
 * MouseListener which sets the PopUp visible when the mouse hovers over the
 * component this MouseListener is added to.
 * 
 * @author Gabriel Glaser
 * @version 20.12.2021
 */
public class SiblingPopUpDisplayerOnHover extends MyMouseListener {
// TODO add constructor to set direction.
    private final JComponent toShowAsPopUp;
    private final JComponent sibling;

    private Optional<MySiblingPopUp> popUp;

    public SiblingPopUpDisplayerOnHover(final JComponent toShowAsPopUp, final JComponent sibling) {
	super();
	this.toShowAsPopUp = toShowAsPopUp;
	this.sibling = sibling;
	sibling.addMouseListener(this);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
	final MySiblingPopUp popUp = new MySiblingPopUp(toShowAsPopUp, SwingFunctions.getMyFrame(sibling), sibling);
	this.popUp = Optional.of(popUp);
	popUp.setVisible(true);

    }

    @Override
    public void mouseExited(MouseEvent e) {
	popUp.get().setVisible(false);
    }

    public MySiblingPopUp getPopUp() {
	if (popUp.isEmpty()) {
	    throw new RuntimeException("Pop Up wasn't created yet");
	} else {
	    return popUp.get();
	}
    }

}
