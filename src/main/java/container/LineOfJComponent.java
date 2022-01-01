package container;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;
import entity.Alignment;

/**
 * Class which represents a List of JComponents, which are placed from left to
 * right or from top to bottom.
 * 
 * @author Gabriel Glaser
 * @version 20.12.2021
 */
public final class LineOfJComponent extends JPanel {

    private static final int STANDARD_DISTANCE_BETWEEN = 0;

    private final Alignment alignment;
    private final List<? extends JComponent> toDepict;
    private final int distanceBetween;

    public LineOfJComponent(final Alignment alignment, final List<? extends JComponent> toDepict, final int distanceBetween) {
	this.alignment = alignment;
	this.toDepict = toDepict;
	this.distanceBetween = distanceBetween;
	setCorrectLayout();
	addComponentsAndCreateDistances();
    }

    public LineOfJComponent(final Alignment ofTheList, final List<? extends JComponent> toDepict) {
	this(ofTheList, toDepict, STANDARD_DISTANCE_BETWEEN);
    }

    public LineOfJComponent(final Alignment ofTheList, final int distanceBetween) {
	this(ofTheList, List.of(), distanceBetween);
    }

    public LineOfJComponent(final Alignment ofTheList) {
	this(ofTheList, STANDARD_DISTANCE_BETWEEN);
    }

    public LineOfJComponent(final Alignment ofTheList, final int distanceBetween, final JComponent... toDepict) {
	this(ofTheList, List.of(toDepict), distanceBetween);
    }

    public LineOfJComponent(final Alignment ofTheList, final JComponent... toDepict) {
	this(ofTheList, STANDARD_DISTANCE_BETWEEN, toDepict);
    }

    private void setCorrectLayout() {
	if (alignment == Alignment.HORIZONTAL) {
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
		if (alignment == Alignment.VERTICAL) {
		    add(Box.createVerticalStrut(distanceBetween));
		} else {
		    add(Box.createHorizontalStrut(distanceBetween));
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

    public void forEachChildDo(final Consumer<? super Component> consumer) {
	toDepict.forEach(consumer);
    }

}
