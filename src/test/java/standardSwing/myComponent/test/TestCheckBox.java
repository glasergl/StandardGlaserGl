package standardSwing.myComponent.test;

import javax.swing.SwingUtilities;

import standardSwing.container.test.TestFrame;
import standardSwing.myComponent.MyCheckBox;

public class TestCheckBox {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    MyCheckBox checkBox = new MyCheckBox("Bin ich Hashira Kocho Sama");
	    TestFrame.showFrameWithComponents(checkBox);
	});
    }
}
