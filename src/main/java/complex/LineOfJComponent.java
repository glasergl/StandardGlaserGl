package complex;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class LineOfJComponent extends JPanel {

    private final JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private final JPanel middle = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private final JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    public LineOfJComponent() {
	super(new BorderLayout());
	add(left, BorderLayout.WEST);
	add(middle, BorderLayout.CENTER);
	add(right, BorderLayout.EAST);
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
}
