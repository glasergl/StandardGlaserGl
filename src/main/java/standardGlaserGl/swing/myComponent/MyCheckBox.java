package standardGlaserGl.swing.myComponent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import standardGlaserGl.swing.eventListener.CursorChangerOnHover;
import standardGlaserGl.swing.eventListener.emptyImplementation.MyMouseListener;
import standardGlaserGl.swing.general.SwingFunctions;
import standardGlaserGl.swing.settings.Colors;
import standardGlaserGl.swing.settings.Fonts;

/**
 * Custom CheckBox implementation which adapts to the parents background.
 *
 * @author Gabriel Glaser
 */
public class MyCheckBox extends JPanel {
    private static final boolean STANDARD_IS_SELECTED = false;
    private static final int DISTANCE_BETWEEN_CHECK_AND_TEXT = 5;
    private static final Color STANDARD_COLOR_OF_CHECK = Color.BLACK;
    private static final Color STANDARD_CHECK_BACKGROUND = Color.WHITE;
    private static final Color STANDARD_CHECK_BACKGROUND_WHILE_HOVERED = Colors.ofFocus();
    private static final Color STANDARD_CHECK_BORDER_COLOR = STANDARD_COLOR_OF_CHECK;
    private static final int CHECK_BORDER_THICKNESS = 1;

    private final Check check = new Check();
    private final JLabel title = new JLabel();
    private final List<ChangeListener> changeListeners = new ArrayList<>();

    private Color checkColor = STANDARD_COLOR_OF_CHECK;
    private Color checkBackground = STANDARD_CHECK_BACKGROUND;
    private Color checkBackgroundWhileHovered = STANDARD_CHECK_BACKGROUND_WHILE_HOVERED;
    private Color checkBorderColor = STANDARD_CHECK_BORDER_COLOR;
    private boolean isSelected;

    public MyCheckBox(final String title, final boolean isSelected) {
	super(new BorderLayout(DISTANCE_BETWEEN_CHECK_AND_TEXT, 0));
	this.isSelected = isSelected;
	this.title.setText(title);
	setup();
    }

    public MyCheckBox(final String title) {
	this(title, STANDARD_IS_SELECTED);
    }

    public boolean isSelected() {
	return isSelected;
    }

    public void setSelected(final boolean isSelected) {
	this.isSelected = isSelected;
	check.revalidate();
	check.repaint();
	notifyChangeListeners();
    }

    public void setColorOfCheck(final Color checkColor) {
	this.checkColor = checkColor;
	check.revalidate();
	check.repaint();
    }

    public void setBackgroundOfCheck(final Color checkBackground) {
	this.checkBackground = checkBackground;
	check.revalidate();
	check.repaint();
    }

    public void setBackgroundOfCheckWhileHovered(final Color checkBackgroundWhileHovered) {
	this.checkBackgroundWhileHovered = checkBackgroundWhileHovered;
	check.revalidate();
	check.repaint();
    }

    public void setBorderColorOfCheck(final Color checkBorderColor) {
	this.checkBorderColor = checkBorderColor;
	check.revalidate();
	check.repaint();
    }

    public void addChangeListener(final ChangeListener toAdd) {
	changeListeners.add(toAdd);
    }

    private void notifyChangeListeners() {
	final ChangeEvent changeEvent = new ChangeEvent(this);
	for (final ChangeListener changeListener : changeListeners) {
	    changeListener.stateChanged(changeEvent);
	}
    }

    @Override
    public void setForeground(final Color newForeground) {
	super.setForeground(newForeground);
	if (title != null) {
	    title.setForeground(newForeground);
	}
    }

    @Override
    public void setFont(final Font newFont) {
	super.setFont(newFont);
	if (title != null) {
	    title.setFont(newFont);
	}
    }

    /**
     * Additionally, sets the background to the same background as the parent. (And
     * does the standard painting, too)
     * 
     * @param context
     */
    @Override
    public void paintComponent(final Graphics context) {
	final Container parent = getParent();
	setBackground(parent.getBackground());
	super.paintComponent(context);
    }

    private void setup() {
	setBackground(Colors.getGray(2));
	setForeground(Colors.ofText());
	setFont(Fonts.standard());
	check.setBackground(checkBackground);
	addMouseListener(new CursorChangerOnHover(new Cursor(Cursor.HAND_CURSOR)));
	addMouseListener(new CheckBoxController());
	add(check, BorderLayout.WEST);
	add(title, BorderLayout.EAST);
    }

    private final class CheckBoxController implements MyMouseListener {
	@Override
	public void mouseClicked(final MouseEvent mouseEvent) {
	    isSelected = !isSelected;
	    revalidate();
	    repaint();
	    notifyChangeListeners();
	    requestFocus();
	}

	@Override
	public void mouseEntered(final MouseEvent mouseEvent) {
	    check.setBackground(checkBackgroundWhileHovered);
	}

	@Override
	public void mouseExited(final MouseEvent mouseEvent) {
	    check.setBackground(checkBackground);
	}
    }

    private final class Check extends JPanel {

	public Check() {
	    super();
	    setBorder(new LineBorder(checkBorderColor, CHECK_BORDER_THICKNESS));
	}

	@Override
	public void paintComponent(final Graphics context) {
	    SwingFunctions.setAntialiasing(context, true);
	    super.paintComponent(context);
	    if (isSelected) {
		context.setColor(checkColor);
		context.fillPolygon(calculateCheck());
	    }
	}

	@Override
	public Dimension getPreferredSize() {
	    final Dimension preferredSizeOfTitle = title.getPreferredSize();
	    return new Dimension(preferredSizeOfTitle.height, preferredSizeOfTitle.height);
	}

	private Polygon calculateCheck() {
	    final Polygon check = new Polygon();
	    final Dimension preferredSize = getPreferredSize();
	    final int width = preferredSize.width;
	    final int height = preferredSize.height;
	    check.addPoint((int) (0.434 * width), (int) (0.85 * height));
	    check.addPoint((int) (0.96 * width), (int) (0.13 * height));
	    check.addPoint((int) (0.882 * width), (int) (0.078 * height));
	    check.addPoint((int) (0.434 * width), (int) (0.706 * height));
	    check.addPoint((int) (0.132 * width), (int) (0.386 * height));
	    check.addPoint((int) (0.1 * width), (int) (0.436 * height));
	    return check;
	}
    }
}
