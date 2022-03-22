package standardSwing.myComponent.test;

import javax.swing.SwingUtilities;

import standardSwing.container.test.TestFrame;
import standardSwing.myComponent.textField_Old.MyHintTextField;

public class TestTextField {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    MyHintTextField textField = new MyHintTextField("With hint", "and initial text");
	    MyHintTextField textFieldWithoutInitialText = new MyHintTextField("With hint and no initial text");
	    MyHintTextField textFieldWithoutAnything = new MyHintTextField();
	    MyHintTextField textFieldWithHintAndSet = new MyHintTextField("Hint");
	    MyHintTextField textFieldWithoutHintButSet = new MyHintTextField();
	    
	    textFieldWithHintAndSet.setText("Setted Text");
	    textFieldWithoutHintButSet.setText("Setted Text");
	    
	    TestFrame.showFrameWithComponents(textField, textFieldWithoutInitialText, textFieldWithoutAnything, textFieldWithHintAndSet, textFieldWithoutHintButSet);
	});
    }

}
