package complexJComponents;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.JPanel;
import standardJComponents.implementations.MyTextField;

public class AddressField extends JPanel {

    private final MyTextField prename = new MyTextField();
    private final MyTextField surname = new MyTextField();
    private final MyTextField street = new MyTextField();
    private final MyTextField housenumber = new MyTextField();
    private final MyTextField zipcode = new MyTextField();
    private final MyTextField city = new MyTextField();
    private final MyTextField country = new MyTextField();

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
