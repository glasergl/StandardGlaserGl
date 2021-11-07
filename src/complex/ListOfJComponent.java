package complex;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Arrays;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;

import standard.helper.enums.Alignment;

/**
 * @author Gabriel Glaser
 * @version 10.9.2021
 */
public final class ListOfJComponent extends JPanel {

	private static final int STANDARD_DISTANCE = 1;

	private final Alignment ofTheList;
	private final List<? extends JComponent> toDepict;
	private final int distance;

	public ListOfJComponent(final Alignment ofTheList, final List<? extends JComponent> toDepict, final int distance) {
		this.ofTheList = ofTheList;
		this.toDepict = toDepict;
		this.distance = distance;
		setCorrectLayout();
		addComponentsAndDistances();
	}

	public ListOfJComponent(final Alignment ofTheList, final List<? extends JComponent> toDepict) {
		this(ofTheList, toDepict, STANDARD_DISTANCE);
	}

	public ListOfJComponent(final Alignment ofTheList, final JComponent... toDepict) {
		this(ofTheList, STANDARD_DISTANCE, toDepict);
	}

	public ListOfJComponent(final Alignment ofTheList, final int distance, final JComponent... toDepict) {
		this(ofTheList, Arrays.asList(toDepict), distance);
	}

	@Override
	public void setBackground(final Color newBackground) {
		super.setBackground(newBackground);
		if (toDepict != null) {
			for (final JComponent jComponent : toDepict) {
				jComponent.setBackground(newBackground);
			}
		}
	}

	@Override
	public void setOpaque(final boolean shouldBeOpaque) {
		super.setOpaque(shouldBeOpaque);
		if (toDepict != null) {
			for (final JComponent jComponent : toDepict) {
				jComponent.setOpaque(shouldBeOpaque);
			}
		}
	}

	@Override
	public void setFont(final Font newFont) {
		super.setFont(newFont);
		if (toDepict != null) {
			for (final JComponent jComponent : toDepict) {
				jComponent.setFont(newFont);
			}
		}
	}

	public void setSubBorders(final Border forEachComponent) {
		for (final JComponent toAddBorderFor : toDepict) {
			toAddBorderFor.setBorder(forEachComponent);
		}
	}

	private void setCorrectLayout() {
		setLayout(new BorderLayout());
		if (ofTheList == Alignment.HORIZONTAL) {
			final FlowLayout xDirection = new FlowLayout(FlowLayout.LEFT, distance, 0);
			xDirection.setAlignOnBaseline(true);
			setLayout(xDirection);
		} else {
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		}
	}

	private void addComponentsAndDistances() {
		for (int i = 0; i < toDepict.size(); i++) {
			add(toDepict.get(i));
			if (ofTheList == Alignment.VERTICAL && i < toDepict.size()) {
				add(Box.createVerticalStrut(distance));
			} else if (ofTheList == Alignment.HORIZONTAL && i < toDepict.size()) {
				add(Box.createHorizontalStrut(distance));
			}
		}
	}

}
