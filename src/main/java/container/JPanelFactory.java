package container;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import javax.swing.JComponent;
import javax.swing.JPanel;
import settings.Colors;

/**
 * Factory which contains methods to create JPanels, setting their
 * layouts/background and adding all components in one line to make some layouts
 * easier.
 *
 * @author Gabriel Glaser
 * @version 20.12.2021
 */
public class JPanelFactory {

    private static final Color STANDARD_BACKGROUND = Colors.getGray(0);
    private static final LayoutManager STANDARD_LAYOUT_MANAGER = new FlowLayout(FlowLayout.LEFT, 5, 5);

    /**
     * Creates a JPanel with the given LayoutManager and background and adds the
     * given JComponents in their order to it.
     * 
     * @param layout
     * @param background
     * @param jComponentsToAdd
     * @return The created JPanel.
     */
    public static JPanel create(final LayoutManager layout, final Color background, final JComponent... jComponentsToAdd) {
	final JPanel jPanel = new JPanel(layout);
	jPanel.setBackground(background);
	for (final JComponent toAdd : jComponentsToAdd) {
	    jPanel.add(toAdd);
	}
	return jPanel;
    }

    /**
     * Creates a JPanel with the given LayoutManager and adds the given JComponent
     * in their order to it.
     * 
     * @param layout
     * @param jComponents
     * @return The created JPanel.
     */
    public static JPanel create(final LayoutManager layout, final JComponent... jComponents) {
	return create(layout, STANDARD_BACKGROUND, jComponents);
    }

    /**
     * Creates a JPanel and adds the given JComponent in their order to it.
     * 
     * @param jComponents
     * @return The created JPanel.
     */
    public static JPanel create(final JComponent... jComponents) {
	return create(STANDARD_LAYOUT_MANAGER, jComponents);
    }

}
