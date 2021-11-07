package complex;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class AddressField extends JPanel {

	public AddressField() {
		super();
		setLayout(new GridBagLayout());
	}

	private void setupComponent(JComponent toAdd, int x, int y, int width, int height, Insets insets) {
		final GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		constraints.insets = insets;
		add(toAdd, constraints);
	}

}
