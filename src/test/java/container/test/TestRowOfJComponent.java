package container.test;

import javax.swing.SwingUtilities;

import container.RowOfJComponent;
import myComponent.MyLabel;

public class TestRowOfJComponent {

    public static void main(String[] args) {
	SwingUtilities.invokeLater(() -> {
	    RowOfJComponent row = new RowOfJComponent(5, 5);
	    MyLabel left1 = new MyLabel("left1");
	    MyLabel left2 = new MyLabel("left2");
	    MyLabel left3 = new MyLabel("left3");

	    MyLabel middle1 = new MyLabel("middle1");
	    MyLabel middle2 = new MyLabel("middle2");

	    MyLabel right1 = new MyLabel("right1");
	    MyLabel right2 = new MyLabel("right2");

	    row.addToLeft(left1);
	    row.addToLeft(left2);
	    row.addToLeft(left3);

	    row.addToMiddle(middle1);
	    row.addToMiddle(middle2);

	    row.addToRight(right1);
	    row.addToRight(right2);

	    TestFrame.showFrameWithComponents(row);
	});
    }

}