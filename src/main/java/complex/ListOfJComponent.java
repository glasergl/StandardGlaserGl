package complex;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;
import standard.helper.enums.Alignment;

/**
 * Class which represents a List of JComponents, which are placed from left to
 * right or from top to bottom.
 * 
 * @author Gabriel Glaser
 * @version 16.11.2021
 */
public final class ListOfJComponent extends JPanel {

	private static final int STANDARD_DISTANCE_BETWEEN_JCOMPONENTS = 1;

	private final Alignment ofJComponentsToEachOther;
	private final List<? extends JComponent> toDepict;
	private final int distanceBetweenJComponents;

	public ListOfJComponent(final Alignment ofJComponentsToEachOther, final List<? extends JComponent> toDepict,
			final int distanceBetweenJComponents) {
		this.ofJComponentsToEachOther = ofJComponentsToEachOther;
		this.toDepict = toDepict;
		this.distanceBetweenJComponents = distanceBetweenJComponents;
		setCorrectLayout();
		addComponentsAndCreateDistances();
	}

	public ListOfJComponent(final Alignment ofTheList, final List<? extends JComponent> toDepict) {
		this(ofTheList, toDepict, STANDARD_DISTANCE_BETWEEN_JCOMPONENTS);
	}

	public ListOfJComponent(final Alignment ofTheList, final int distance, final JComponent... toDepict) {
		this(ofTheList, List.of(toDepict), distance);
	}

	public ListOfJComponent(final Alignment ofTheList, final JComponent... toDepict) {
		this(ofTheList, STANDARD_DISTANCE_BETWEEN_JCOMPONENTS, toDepict);
	}

	private void setCorrectLayout() {
		if (ofJComponentsToEachOther == Alignment.HORIZONTAL) {
			final FlowLayout xDirection = new FlowLayout(FlowLayout.LEFT, 0, 0);
			xDirection.setAlignOnBaseline(true);
			setLayout(xDirection);
		} else {
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		}
	}

	private void addComponentsAndCreateDistances() {
		for (int i = 0; i < toDepict.size(); i++) {
			add(toDepict.get(i));
			if (i < toDepict.size() - 1) {
				if (ofJComponentsToEachOther == Alignment.VERTICAL) {
					add(Box.createVerticalStrut(distanceBetweenJComponents));
				} else {
					add(Box.createHorizontalStrut(distanceBetweenJComponents));
				}
			}
		}
	}

	public void setBackgroundOfChildren(final Color newBackground) {
		for (final JComponent jComponent : toDepict) {
			jComponent.setBackground(newBackground);
		}
	}

	public void setForegroundOfChildren(final Color newForeground) {
		for (final JComponent jComponent : toDepict) {
			jComponent.setBackground(newForeground);
		}
	}

	public void setBorderOfChildren(final Border newBorder) {
		for (final JComponent jComponent : toDepict) {
			jComponent.setBorder(newBorder);
		}
	}

	public void setFontOfChildren(final Font newFont) {
		for (final JComponent jComponent : toDepict) {
			jComponent.setFont(newFont);
		}
	}

}
