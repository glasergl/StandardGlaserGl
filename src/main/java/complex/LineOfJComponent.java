package complex;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.Arrays;
import java.util.function.Consumer;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class LineOfJComponent extends JPanel {

	private final JPanel left;
	private final JPanel middle;
	private final JPanel right;

	public LineOfJComponent(final int horizontalDistance, final int verticalDistance) {
		super(new BorderLayout());
		left = new JPanel(new FlowLayout(FlowLayout.LEFT, horizontalDistance, verticalDistance));
		middle = new JPanel(new FlowLayout(FlowLayout.CENTER, horizontalDistance, verticalDistance));
		right = new JPanel(new FlowLayout(FlowLayout.RIGHT, horizontalDistance, verticalDistance));
		add(left, BorderLayout.WEST);
		add(middle, BorderLayout.CENTER);
		add(right, BorderLayout.EAST);
	}

	public LineOfJComponent() {
		this(0, 0);
	}

	public void addToLeft(final JComponent toAdd) {
		left.add(toAdd);
	}

	public void addToMiddle(final JComponent toAdd) {
		middle.add(toAdd);
	}

	public void addToRight(final JComponent toAdd) {
		right.add(toAdd);
	}

	@Override
	public void setBackground(final Color newBackground) {
		super.setBackground(newBackground);
		if (left != null && middle != null && right != null) {
			left.setBackground(newBackground);
			middle.setBackground(newBackground);
			right.setBackground(newBackground);
		}
	}

	public void setBackgroundForSubComponents(final Color newBackground) {
		forEachSubComponentDo((jComponent) -> {
			jComponent.setBackground(newBackground);
		});
	}

	public void setBorderForSubComponents(final Border newBorder) {
		forEachSubComponentDo((jComponent) -> {
			((JComponent) jComponent).setBorder(newBorder);
		});
	}

	public void forEachSubComponentDo(final Consumer<? super Component> consumer) {
		Arrays.stream(left.getComponents()).forEach(consumer);
		Arrays.stream(middle.getComponents()).forEach(consumer);
		Arrays.stream(right.getComponents()).forEach(consumer);
	}
}
