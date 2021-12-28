package border.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import border.AnimatedUnderlineBorder;
import container.test.TestFrame;
import eventListener.emptyImplementation.MyMouseListener;

public class TestAnimatedUnderlineBorder {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    JPanel panel = new JPanel();
	    panel.setPreferredSize(new Dimension(100, 100));
	    panel.addMouseListener(new MyMouseListener() {
		boolean drawAnimatedBorder = true;

		@Override
		public void mouseClicked(final MouseEvent click) {
		    panel.setBorder(drawAnimatedBorder ? new AnimatedUnderlineBorder(Color.RED, Color.GREEN, 5) : new EmptyBorder(0, 0, 0, 0));
		    drawAnimatedBorder = !drawAnimatedBorder;
		}
	    });
	    TestFrame.showFrameWithComponents(panel);
	});
    }

}
