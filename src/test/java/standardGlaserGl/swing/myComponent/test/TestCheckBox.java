package standardGlaserGl.swing.myComponent.test;

import javax.swing.SwingUtilities;

import standardGlaserGl.swing.container.test.TestFrame;
import standardGlaserGl.swing.myComponent.MyCheckBox;

public class TestCheckBox {
    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    MyCheckBox checkBox = new MyCheckBox("Bin ich Hashira Kocho Sama");
	    TestFrame.showFrameWithComponents(checkBox);
	});
    }
}
