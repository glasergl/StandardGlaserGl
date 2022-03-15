package standardSwing.myComponent.test;

import javax.swing.SwingUtilities;

import standardSwing.container.test.TestFrame;
import standardSwing.myComponent.textField.MyTextField;

public class TestTextField {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    MyTextField textField = new MyTextField("With hint", "and initial text");
	    MyTextField textFieldWithoutInitialText = new MyTextField("With hint and no initial text");
	    MyTextField textFieldWithoutAnything = new MyTextField();
	    MyTextField textFieldWithHintAndSet = new MyTextField("Hint");
	    MyTextField textFieldWithoutHintButSet = new MyTextField();
	    
	    textFieldWithHintAndSet.setText("Setted Text");
	    textFieldWithoutHintButSet.setText("Setted Text");
	    
	    TestFrame.showFrameWithComponents(textField, textFieldWithoutInitialText, textFieldWithoutAnything, textFieldWithHintAndSet, textFieldWithoutHintButSet);
	});
    }

}
