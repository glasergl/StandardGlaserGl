package eventListener.test;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import container.test.TestFrame;
import entity.ColorType;
import eventListener.BorderChangerOnFocus;
import eventListener.ColorChangerOnClick;
import eventListener.ColorChangerOnFocus;
import eventListener.ColorChangerOnHover;

public class TestChanger {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    JButton colorHoverTest = new JButton("HoverTest");
	    JButton colorFocusTest = new JButton("FocusTest");
	    JButton colorClickTest = new JButton("ClickTest");
	    colorHoverTest.addMouseListener(new ColorChangerOnHover(Color.RED, ColorType.BACKGROUND));
	    colorHoverTest.addMouseListener(new ColorChangerOnHover(Color.WHITE, ColorType.FOREGROUND));
	    colorFocusTest.addFocusListener(new ColorChangerOnFocus(Color.RED, ColorType.BACKGROUND));
	    colorFocusTest.addFocusListener(new ColorChangerOnFocus(Color.WHITE, ColorType.FOREGROUND));
	    colorClickTest.addMouseListener(new ColorChangerOnClick(Color.RED, ColorType.BACKGROUND));
	    colorClickTest.addMouseListener(new ColorChangerOnClick(Color.WHITE, ColorType.FOREGROUND));

	    JButton borderFocusTest = new JButton("BorderFocusTest");
	    borderFocusTest.setBorder(new LineBorder(Color.GREEN));
	    borderFocusTest.addFocusListener(new BorderChangerOnFocus(new LineBorder(Color.RED)));
	    TestFrame.showFrameWithComponents(colorHoverTest, colorFocusTest, colorClickTest, borderFocusTest);
	});
    }

}
