package myComponent.test;

import javax.swing.SwingUtilities;
import container.test.TestFrame;
import myComponent.textField.MyHintTextField;
import myComponent.textField.MyTextField;

public class TestTextField {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    MyTextField textField = new MyTextField();
	    MyTextField textFieldWithInitialContent = new MyTextField("Initial Content");
	    MyHintTextField hintTextField = new MyHintTextField("No initial text, but with hint");
	    MyHintTextField hintTextFieldWithInitialContent = new MyHintTextField("With hint", "and initial text");
	    textField.setColumns(15);
	    textFieldWithInitialContent.setColumns(25);
	    hintTextField.setColumns(15);
	    hintTextFieldWithInitialContent.setColumns(25);
	    TestFrame.showFrameWithComponents(textField, textFieldWithInitialContent, hintTextField, hintTextFieldWithInitialContent);
	});
    }

}
