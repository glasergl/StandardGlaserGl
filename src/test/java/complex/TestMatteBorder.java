package complex;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import general.TestFrame;
import standard.implementations.MyLabel;

public class TestMatteBorder {

    static int spaceTop = 10, spaceLeft = 15, spaceBottom = 0, spaceRight = 5;
    static int fillTop = 0, fillLeft = 5, fillBottom = 10, fillRight = 15;

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    MyLabel helloThere = new MyLabel("Hello There");
	    helloThere.setBorder(new MyMatteBorder(spaceTop, spaceLeft, spaceBottom, spaceRight, fillTop, fillLeft, fillBottom, fillRight, Color.GREEN, Color.RED, Color.GRAY, Color.BLUE));
	    JPanel p = new JPanel();
	    p.setBackground(Color.ORANGE);
	    p.add(helloThere);
	    TestFrame.showFrameWithComponents(p);
	});
    }

}
