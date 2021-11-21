package standard;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JFrame;
import standard.implementations.MyPopUpComponent;
import standard.settings.Colors;

/**
 * Top-Level container with standard properties to prevent boiler-plate code.
 *
 * @author Gabriel Glaser
 * @version 16.11.2021
 */
public class MyFrame extends JFrame {

	private static final Set<MyPopUpComponent> CONTROLLED_POP_UPS = new HashSet<>();

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

	public static void controllPopUp(final MyPopUpComponent toControl) {
		CONTROLLED_POP_UPS.add(toControl);
	}

	public static void stopControllingPopUp(final MyPopUpComponent toRemove) {
		CONTROLLED_POP_UPS.remove(toRemove);
	}

	@Override
	public void dispose() {
		super.dispose();
		CONTROLLED_POP_UPS.stream().forEach(MyPopUpComponent::dispose);
	}

}
