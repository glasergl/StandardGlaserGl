package standardGlaserGl.swing.myComponent.test;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import standardGlaserGl.swing.container.test.TestFrame;
import standardGlaserGl.swing.myComponent.textField.MyHintTextField;
import standardGlaserGl.swing.myComponent.textField.MyTextField;

public class TestTextField {
    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    MyHintTextField textField = new MyHintTextField("With hint", "and initial text");
	    MyHintTextField textFieldWithoutInitialText = new MyHintTextField("With hint and no initial text");
	    MyTextField textFieldWithHintAndSet = new MyTextField("TextField without hint");
	    MyTextField textFieldWithoutHintButSet = new MyTextField();

	    JFrame f = TestFrame.showFrameWithComponents(textField, textFieldWithoutInitialText, textFieldWithHintAndSet, textFieldWithoutHintButSet);
	    f.getContentPane().setBackground(Color.GRAY);
	});
    }
}
