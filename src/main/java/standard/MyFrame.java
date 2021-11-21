package standard;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import standard.implementations.MySimplePopUp;
import standard.settings.Colors;

/**
 * Top-Level container with standard properties to prevent boiler-plate code.
 *
 * @author Gabriel Glaser
 * @version 16.11.2021
 */
public class MyFrame extends JFrame {

	private final List<MySimplePopUp> controlledPopUps = new LinkedList<>();

	public MyFrame(final String title, final Image icon) {
		super(title);
		setIconImage(icon);
		setup();
	}

	public MyFrame(final String title) {
		this(title, null);
	}

	public MyFrame() {
		this("");
	}

	private void setup() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());
		getContentPane().setBackground(Colors.getBackground(1));
		final PopUpController forAllPopUps = new PopUpController();
		addComponentListener(forAllPopUps);
		addWindowListener(forAllPopUps);
	}

	/**
	 * Sets the size and position of all components, centers the whole frame and
	 * makes it visible.
	 */
	public void start() {
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void controllPopUp(final MySimplePopUp toControl) {
		controlledPopUps.add(toControl);
	}

	public void stopControllingPopUp(final MySimplePopUp toRemove) {
		controlledPopUps.remove(toRemove);
	}

	@Override
	public void dispose() {
		super.dispose();
		controlledPopUps.stream().forEach(MySimplePopUp::dispose);
	}

	private class PopUpController implements WindowListener, ComponentListener {
		
		@Override
		public void componentMoved(ComponentEvent e) {
			controlledPopUps.stream().forEach(MySimplePopUp::updateLocation);
		}

		@Override
		public void windowActivated(WindowEvent e) {
			controlledPopUps.stream().forEach((popUp) -> {
				popUp.setVisible(true);
			});
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			controlledPopUps.stream().forEach((popUp) -> {
				popUp.setVisible(false);
			});
		}

		@Override
		public void componentResized(ComponentEvent e) {
		}

		@Override
		public void componentShown(ComponentEvent e) {
		}

		@Override
		public void componentHidden(ComponentEvent e) {
		}
		
		@Override
		public void windowOpened(WindowEvent e) {
		}

		@Override
		public void windowClosing(WindowEvent e) {
		}

		@Override
		public void windowClosed(WindowEvent e) {
		}

		@Override
		public void windowIconified(WindowEvent e) {
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
		}

	}

}
