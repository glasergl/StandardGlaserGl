package standard.helper.listeners;

import java.awt.event.MouseEvent;
import java.util.Optional;
import javax.swing.JComponent;
import standard.SwingFunctions;
import standard.helper.emptyListenerImplementations.MyMouseListener;
import standard.implementations.MySiblingPopUp;

/**
 * MouseListener which changes the visibility of the pop up on click.
 * 
 * @author Gabriel Glaser
 * @version 5.12.2021
 */
public class SiblingPopUpDisplayerOnClick extends MyMouseListener {

    private final JComponent toShowAsPopUp;
    private final JComponent sibling;

    private Optional<MySiblingPopUp> popUp = Optional.empty();

    public SiblingPopUpDisplayerOnClick(final JComponent toShowAsPopUp, final JComponent sibling) {
	super();
	this.toShowAsPopUp = toShowAsPopUp;
	this.sibling = sibling;
	sibling.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	if (popUp.isEmpty()) {
	    final MySiblingPopUp popUp = new MySiblingPopUp(toShowAsPopUp, SwingFunctions.getMyFrame(sibling), sibling);
	    this.popUp = Optional.of(popUp);
	    popUp.setVisible(true);
	} else {
	    final MySiblingPopUp popUp = this.popUp.get();
	    popUp.setVisible(!popUp.isVisible());
	}
    }

}
