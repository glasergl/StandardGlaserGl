package standardSwing.container;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * Class which represents a whole line of JComponents. Each instance has three
 * type of contained JComponents: left, middle and right.
 * 
 * For example, it can be used for headers and footers.
 *
 * @author Gabriel Glaser
 * @version 29.11.2021
 */
public class RowOfJComponent extends JPanel {

    private static final int STANDARD_HORIZONTAL_DISTANCE = 0;
    private static final int STANDARD_VERTICAL_DISTANCE = 0;

    private final JPanel left = new JPanel();
    private final JPanel middle = new JPanel();
    private final JPanel right = new JPanel();
    private final List<JComponent> leftJComponents = new LinkedList<>();
    private final List<JComponent> middleJComponents = new LinkedList<>();
    private final List<JComponent> rightJComponents = new LinkedList<>();

    public RowOfJComponent(final int horizontalDistance, final int verticalDistance) {
	super();
	setLayout(new BorderLayout());
	left.setLayout(new FlowLayout(FlowLayout.LEFT, horizontalDistance, verticalDistance));
	middle.setLayout(new FlowLayout(FlowLayout.CENTER, horizontalDistance, verticalDistance));
	right.setLayout(new FlowLayout(FlowLayout.RIGHT, horizontalDistance, verticalDistance));
	add(left, BorderLayout.WEST);
	add(middle, BorderLayout.CENTER);
	add(right, BorderLayout.EAST);
    }

    public RowOfJComponent() {
	this(STANDARD_HORIZONTAL_DISTANCE, STANDARD_VERTICAL_DISTANCE);
    }

    public void addToLeft(final JComponent toAdd) {
	leftJComponents.add(toAdd);
	left.add(toAdd);
    }

    public void addToMiddle(final JComponent toAdd) {
	middleJComponents.add(toAdd);
	middle.add(toAdd);
    }

    public void addToRight(final JComponent toAdd) {
	rightJComponents.add(toAdd);
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

    public void setBackgroundOfChildren(final Color newBackground) {
	forEachChildDo((jComponent) -> {
	    jComponent.setBackground(newBackground);
	});
    }

    public void setForegroundOfChildren(final Color newForeground) {
	forEachChildDo((jComponent) -> {
	    jComponent.setForeground(newForeground);
	});
    }

    public void setBorderOfChildren(final Border newBorder) {
	forEachChildDo((jComponent) -> {
	    jComponent.setBorder(newBorder);
	});
    }

    public void setFontOfChildren(final Font newFont) {
	forEachChildDo((jComponent) -> {
	    jComponent.setFont(newFont);
	});
    }

    public void forEachChildDo(final Consumer<JComponent> consumer) {
	forEachLeftChildDo(consumer);
	forEachMiddleChildDo(consumer);
	forEachRightChildDo(consumer);
    }

    public void forEachLeftChildDo(final Consumer<JComponent> consumer) {
	leftJComponents.forEach(consumer);
    }

    public void forEachMiddleChildDo(final Consumer<JComponent> consumer) {
	middleJComponents.forEach(consumer);
    }

    public void forEachRightChildDo(final Consumer<JComponent> consumer) {
	rightJComponents.forEach(consumer);
    }

    public JPanel getLeft() {
	return left;
    }

    public JPanel getMiddle() {
	return middle;
    }

    public JPanel getRight() {
	return right;
    }
}
