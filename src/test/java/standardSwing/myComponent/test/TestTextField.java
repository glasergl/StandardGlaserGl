package standardSwing.myComponent.test;

import javax.swing.SwingUtilities;

import standardSwing.container.test.TestFrame;
import standardSwing.myComponent.textField_Old.MyHintTextField;
import standardSwing.myComponent.textField_Old.MyTextField;

public class TestTextField {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    MyHintTextField textField = new MyHintTextField("With hint", "and initial text");
	    MyHintTextField textFieldWithoutInitialText = new MyHintTextField("With hint and no initial text");
	    MyTextField textFieldWithHintAndSet = new MyTextField("TextField without hint");
	    MyTextField textFieldWithoutHintButSet = new MyTextField();

	    TestFrame.showFrameWithComponents(textField, textFieldWithoutInitialText, textFieldWithHintAndSet, textFieldWithoutHintButSet);
	});
    }

}
