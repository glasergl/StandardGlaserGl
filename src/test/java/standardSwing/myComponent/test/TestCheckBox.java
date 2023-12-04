package standardSwing.myComponent.test;

import javax.swing.SwingUtilities;

import de.glasergl.standard.swing.myComponent.MyCheckBox;
import standardSwing.container.test.TestFrame;

public class TestCheckBox {
    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    MyCheckBox checkBox = new MyCheckBox("Bin ich Hashira Kocho Sama");
	    TestFrame.showFrameWithComponents(checkBox);
	});
    }
}
