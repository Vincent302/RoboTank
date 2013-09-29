/**                          
 * Project:           RoboTank                               
 * Comments:          Tank robot game                                           
 * JDK version used:  JDK1.6                             
 * Namespace:         Bean                              
 * Author:            Vincent Li             
 * Create Date:       2013-09-24 
 */
package Test;

import javax.swing.JFrame;

import UI.Frame.TestFrame;

public class Test_Run {
	public static void main(String[] args) {
		TestFrame tf = new TestFrame();
		tf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tf.setVisible(true);
	}
}
