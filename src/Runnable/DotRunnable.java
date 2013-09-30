/**                          
 * Project:           RoboTank                               
 * Comments:          Tank robot game                                           
 * JDK version used:  JDK1.6                             
 * Namespace:         Bean                              
 * Author:            Vincent Li             
 * Create Date:       2013-09-24 
 */
package Runnable;

import java.awt.Component;

import Bean.Dot;
import Util.Global;

public class DotRunnable implements Runnable{

	private Dot dot;
	private Component component;

	public DotRunnable(Dot dot, Component component) {
		this.dot = dot;
		this.component = component;
	}

	@Override
	public void run() {
		try {
			while (dot.move(component.getBounds())) {
				Thread.sleep(Global.DOT_DELAY);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
