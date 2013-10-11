/**                          
 * Project:           RoboTank                               
 * Comments:          Tank robot game                                           
 * JDK version used:  JDK1.6                             
 * Namespace:         Bean                              
 * Author:            Vincent Li             
 * Create Date:       2013-09-24 
 */
package UI.Frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import UI.Panel.ComponentPanel;
import Util.Global;

public class TestFrame extends JFrame {

	private static final long serialVersionUID = 3901835535531770684L;
	
	private ComponentPanel component_panel;
	private JPanel button_panel;

	public TestFrame() {
		this.setSize(Global.FRAME_WIDTH, Global.FRAME_HEIGHT);
		this.setTitle("RoboTank_TEST");

		component_panel = new ComponentPanel();
		button_panel = new JPanel();

		addButton(button_panel, "Exit", new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		this.add(component_panel, BorderLayout.CENTER);
		this.add(button_panel, BorderLayout.SOUTH);
	}

	public void addButton(Container container, String name,
			ActionListener listener) {
		JButton new_button = new JButton(name);
		container.add(new_button);
		new_button.addActionListener(listener);
	}
}
