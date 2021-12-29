package myComponent.test;

import javax.swing.SwingUtilities;
import container.test.TestFrame;
import myComponent.textField.MyTextField;

public class TestTextField {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    MyTextField textFieldWithoutInitialContent = new MyTextField();
	    MyTextField textFieldWithInitialContent = new MyTextField("Initial Content");
//	    MyHintTextField normalTextField = new MyHintTextField("With hint", "and initial text");
//	    MyNumberField numberField = new MyNumberField("NumberField", 20);
//	    MyHintTextField withNoInitialText = new MyHintTextField("No initial text, but with hint");
	    textFieldWithoutInitialContent.setColumns(15);
	    textFieldWithInitialContent.setColumns(25);
	    TestFrame.showFrameWithComponents(textFieldWithoutInitialContent, textFieldWithInitialContent);
	});
    }

}
