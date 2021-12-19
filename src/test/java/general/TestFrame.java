package general;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import standard.MyFrame;
import standard.settings.Colors;

public class TestFrame extends MyFrame {

	public TestFrame() {
		super("Test");
		getContentPane().add(new MyPanel());
		start();
	}

	public static void main(String[] args) {
		Colors.setDarkModeEnabled(false);
		SwingUtilities.invokeLater(() -> {
			new TestFrame();
		});
	}

	public static void showFrameWithComponents(final JComponent... components) {
		final MyFrame frame = new MyFrame();
		final Container contentPane = frame.getContentPane();
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		for (final JComponent component : components) {
			contentPane.add(component);
		}
		frame.start();
		frame.setSize(frame.getWidth(), frame.getHeight() + 50);
	}

	private class MyPanel extends JPanel {
		private final JTextField tf = new JTextField();

		public MyPanel() {
			super(null);
			tf.setColumns(20);
			setOpaque(false);
			tf.setBorder(null);
			tf.setOpaque(false);
			tf.setBackground(new Color(0,0,0,0));
			tf.setBounds(0, 0, getPreferredSize().width, getPreferredSize().height);
			add(tf);
		}

		@Override
		public Dimension getPreferredSize() {
			return tf.getPreferredSize();
		}

		@Override
		public void paintComponent(Graphics g) {
			g.drawString("Heeeeello There", 0, g.getFont().getSize());
			super.paintComponent(g);
		}
	}

}
