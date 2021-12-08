package implementations;

import javax.swing.SwingUtilities;

import general.TestFrame;
import standard.implementations.MyCheckBox;

public class TestCheckBox {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    String checkBoxText = "Bin ich Hashira Kocho Sama";
	    MyCheckBox checkBox = new MyCheckBox(checkBoxText);
	    TestFrame.showFrameWithComponents(checkBox);
	});
    }
}
