package standard;

import java.awt.BorderLayout;
import javax.swing.SwingUtilities;
import complex.ListOfJComponent;
import standard.helper.enums.Alignment;
import standard.implementations.MyLabel;

public class TestFrame extends MyFrame {

    public TestFrame() {
	super("Test");
	MyLabel helloThere1 = new MyLabel("HelloThere1");
	MyLabel helloThere2 = new MyLabel("HelloThere2");
	MyLabel helloThere3 = new MyLabel("HelloThere3");
	MyLabel helloThere4 = new MyLabel("HelloThere4");
	ListOfJComponent list = new ListOfJComponent(Alignment.VERTICAL, 10, helloThere1, helloThere2, helloThere3, helloThere4);
	add(new MyLabel("Left"), BorderLayout.WEST);
	add(list, BorderLayout.CENTER);
	add(new MyLabel("Right"), BorderLayout.EAST);
	start();
    }

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    new TestFrame();
	});
    }

}
