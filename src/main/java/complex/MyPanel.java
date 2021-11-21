package complex;

import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class MyPanel extends JPanel {

	public MyPanel(final LayoutManager layout, final Color background, final JComponent... components) {
		super(layout);
		setBackground(background);
		for(final JComponent toAdd : components) {
			add(toAdd);
		}
	}

}
