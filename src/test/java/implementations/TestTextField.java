package implementations;

import general.TestFrame;
import standard.implementations.MyHintNumberField;
import standard.implementations.MyHintTextField;
import standard.implementations.MyTextButton;

public class TestTextField {

    static String initialText = "Hello there";
    static String hint = "Star Wars quote";
    static MyHintTextField textField = new MyHintTextField(hint, initialText);

    static int initialNumber = 5;
    static String numberHint = "Alter";
    static MyHintNumberField numberField = new MyHintNumberField(numberHint, initialNumber);

    public static void main(String[] args) {
	textField.setColumns(20);
	numberField.setColumns(10);
	TestFrame.showFrameWithComponents(textField, numberField, new MyTextButton("Change focus"));
    }

}
