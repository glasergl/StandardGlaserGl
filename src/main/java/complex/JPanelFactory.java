package complex;

import java.awt.Color;
import java.awt.LayoutManager;
import javax.swing.JComponent;
import javax.swing.JPanel;
import standard.settings.Colors;

/**
 * Factory which contains methods to create JPanels, setting their
 * layouts/background and adding all components in one line to make some layouts
 * easier.
 *
 * @author Gabriel Glaser
 * @version 29.11.2021
 */
public class JPanelFactory {

	private static final Color STANDARD_BACKGROUND = Colors.getGray(0);

	public static JPanel create(final LayoutManager layout, final Color background,
			final JComponent... componentsToAdd) {
		final JPanel panel = new JPanel(layout);
		panel.setBackground(background);
		for (final JComponent toAdd : componentsToAdd) {
			panel.add(toAdd);
		}
		return panel;
	}

	/**
	 * Sets the background to Colors.getGray(0).
	 * 
	 * @param layout
	 * @param components
	 * @return
	 */
	public static JPanel create(final LayoutManager layout, final JComponent... components) {
		return create(layout, STANDARD_BACKGROUND, components);
	}

}
