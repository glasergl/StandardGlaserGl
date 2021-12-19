package implementations;

import general.TestFrame;
import standard.implementations.MyNumberField;
import standard.implementations.MyTextField;

public class TestTextField {

	static MyTextField normalTextField = new MyTextField("With hint", "and initial text");
	static MyNumberField numberField = new MyNumberField("NumberField", 20);

	static MyTextField withNoInitialText = new MyTextField("No initial text, but with hint");
	static MyTextField withNoHint = new MyTextField();
	static MyTextField withoutAnything = new MyTextField();

	public static void main(String[] args) {
		normalTextField.setColumns(20);
		numberField.setColumns(10);

		withNoInitialText.setColumns(30);
		withNoHint.setColumns(30);
		withNoHint.setText("With initial text, but no hint");
		withoutAnything.setColumns(15);
		TestFrame.showFrameWithComponents(normalTextField, numberField, withNoInitialText, withNoHint, withoutAnything);
	}

}
