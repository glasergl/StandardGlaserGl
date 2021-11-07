package complexJComponents;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import standardJComponents.implementations.MyLabel;

public class JComponentWithTitle extends JPanel {

	private final JComponent toDisplay;
	private final MyLabel withTitle;
	private final boolean isVertical;

	public JComponentWithTitle(final JComponent toDisplay, final String title, final boolean shouldBeVertical) {
		super();
		this.toDisplay = toDisplay;
		this.withTitle = new MyLabel(title);
		this.isVertical = shouldBeVertical;
		if (shouldBeVertical) {
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		} else {
			setLayout(new FlowLayout(FlowLayout.LEFT));
		}
		add(withTitle);
		add(toDisplay);
	}

	public JComponent getDisplayed() {
		return toDisplay;
	}

	public String getTitle() {
		return withTitle.getText();
	}

	public boolean isVertical() {
		return isVertical;
	}

}
