package standard;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.HashSet;
import java.util.Set;
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

	private final Set<MySimplePopUp> controlledPopUps = new HashSet<>();

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
		addComponentListener(new PopUpMover());
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

	private class PopUpMover implements ComponentListener {

		@Override
		public void componentMoved(ComponentEvent e) {

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

	}

}
