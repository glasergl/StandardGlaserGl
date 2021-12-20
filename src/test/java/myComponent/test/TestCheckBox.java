package myComponent.test;

import javax.swing.SwingUtilities;
import container.test.TestFrame;
import myComponent.MyCheckBox;

public class TestCheckBox {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    String checkBoxText = "Bin ich Hashira Kocho Sama";
	    MyCheckBox checkBox = new MyCheckBox(checkBoxText);
	    TestFrame.showFrameWithComponents(checkBox);
	});
    }
}
