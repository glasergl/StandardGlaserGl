package myComponent.test;

import javax.swing.SwingUtilities;

import container.test.TestFrame;
import myComponent.MyNumberField;
import myComponent.MyTextField;

public class TestTextField {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    MyTextField normalTextField = new MyTextField("With hint", "and initial text");
	    MyNumberField numberField = new MyNumberField("NumberField", 20);
	    MyTextField withNoInitialText = new MyTextField("No initial text, but with hint");
	    MyTextField withNoHint = new MyTextField();
	    MyTextField withoutAnything = new MyTextField();

	    normalTextField.setColumns(20);
	    numberField.setColumns(10);
	    withNoInitialText.setColumns(30);
	    withNoHint.setColumns(30);
	    withNoHint.setText("With initial text, but no hint");
	    withoutAnything.setColumns(15);
	    
	    TestFrame.showFrameWithComponents(normalTextField, numberField, withNoInitialText, withNoHint, withoutAnything);
	});
    }

}
