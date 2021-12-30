package eventListener.test;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import container.test.TestFrame;
import entity.ColorType;
import eventListener.BorderChangerOnFocus;
import eventListener.ColorChangerOnClick;
import eventListener.ColorChangerOnFocus;
import eventListener.ColorChangerOnHover;
import eventListener.CursorChangerOnHover;

public class TestChanger {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    JButton colorHoverTest = new JButton("ColorHoverTest");
	    JButton colorFocusTest = new JButton("ColorFocusTest");
	    JButton colorClickTest = new JButton("ColorClickTest");
	    colorHoverTest.addMouseListener(new ColorChangerOnHover(Color.RED, ColorType.BACKGROUND));
	    colorHoverTest.addMouseListener(new ColorChangerOnHover(Color.WHITE, ColorType.FOREGROUND));
	    colorFocusTest.addFocusListener(new ColorChangerOnFocus(Color.RED, ColorType.BACKGROUND));
	    colorFocusTest.addFocusListener(new ColorChangerOnFocus(Color.WHITE, ColorType.FOREGROUND));
	    colorClickTest.addMouseListener(new ColorChangerOnClick(Color.RED, ColorType.BACKGROUND));
	    colorClickTest.addMouseListener(new ColorChangerOnClick(Color.WHITE, ColorType.FOREGROUND));

	    JButton borderFocusTest = new JButton("BorderFocusTest");
	    borderFocusTest.setBorder(new LineBorder(Color.GREEN));
	    borderFocusTest.addFocusListener(new BorderChangerOnFocus(new LineBorder(Color.RED)));

	    JButton cursorHoverTest = new JButton("CursorHoverTest");
	    cursorHoverTest.addMouseListener(new CursorChangerOnHover(new Cursor(Cursor.CROSSHAIR_CURSOR)));

	    TestFrame.showFrameWithComponents(colorHoverTest, colorFocusTest, colorClickTest, borderFocusTest, cursorHoverTest);
	});
    }

}
