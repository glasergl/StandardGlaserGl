package eventListener.test;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import container.test.TestFrame;
import entity.ColorType;
import eventListener.ColorChangerOnClick;
import eventListener.ColorChangerOnFocus;
import eventListener.ColorChangerOnHover;

public class TestColorChanger {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    JButton hoverTest = new JButton("HoverTest");
	    JButton focusTest = new JButton("FocusTest");
	    JButton clickTest = new JButton("ClickTest");
	    hoverTest.addMouseListener(new ColorChangerOnHover(Color.RED, ColorType.BACKGROUND));
	    hoverTest.addMouseListener(new ColorChangerOnHover(Color.WHITE, ColorType.FOREGROUND));
	    focusTest.addFocusListener(new ColorChangerOnFocus(Color.RED, ColorType.BACKGROUND));
	    focusTest.addFocusListener(new ColorChangerOnFocus(Color.WHITE, ColorType.FOREGROUND));
	    clickTest.addMouseListener(new ColorChangerOnClick(Color.RED, ColorType.BACKGROUND));
	    clickTest.addMouseListener(new ColorChangerOnClick(Color.WHITE, ColorType.FOREGROUND));
	    TestFrame.showFrameWithComponents(hoverTest, focusTest, clickTest);
	});
    }

}
