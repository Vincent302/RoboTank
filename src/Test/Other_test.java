package Test;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *在JPanel中监听键盘事件 By [url=http://eastsun.javaeye.com]Eastsun[/url]
 */
public class Other_test {
	public static void main(String[] args) {
		JFrame frame = new JFrame("KeyLis");
		final JLabel label = new JLabel("key press: ");
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(320, 240));
		panel.add(label);
		panel.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				label.setText("key press:" + e.getKeyChar());
			}
		});
		panel.setFocusable(true);
		frame.add(panel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}