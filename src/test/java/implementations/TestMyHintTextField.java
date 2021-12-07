package implementations;

import general.TestFrame;
import standard.implementations.MyHintTextField;
import standard.implementations.MyTextButton;

public class TestMyHintTextField {

    static String initialText = "";
    static String hint = "";
    static MyHintTextField textField = new MyHintTextField(hint, initialText);

    public static void main(String[] args) {
	textField.setColumns(20);
	TestFrame.showFrameWithComponents(textField, new MyTextButton("change focus"));
    }

}
