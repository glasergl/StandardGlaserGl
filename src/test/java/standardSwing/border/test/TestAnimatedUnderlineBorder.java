package standardSwing.border.test;

import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import de.glasergl.standard.swing.border.AnimatedUnderlineBorder;
import de.glasergl.standard.swing.eventListener.emptyImplementation.MyMouseListener;
import standardSwing.container.test.TestFrame;

public class TestAnimatedUnderlineBorder {
    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    JLabel label = new JLabel("Click me to see Border");
	    label.addMouseListener(new MyMouseListener() {
		boolean drawAnimatedBorder = true;

		@Override
		public void mouseClicked(final MouseEvent click) {
		    label.setBorder(drawAnimatedBorder ? new AnimatedUnderlineBorder(Color.GRAY, Color.RED, 5) : new EmptyBorder(0, 0, 0, 0));
		    drawAnimatedBorder = !drawAnimatedBorder;
		}
	    });
	    TestFrame.showFrameWithComponents(label);
	});
    }
}
