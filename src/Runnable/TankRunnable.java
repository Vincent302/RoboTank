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

import Bean.Tank;
import Util.Global;

public class TankRunnable implements Runnable {

	private Tank tank;
	private Component component;

	public TankRunnable(Tank tank, Component component) {
		this.tank = tank;
		this.component = component;
	}

	@Override
	public void run(){
		try {
			while (tank.move(component.getBounds())) {
				// component.repaint();
				Thread.sleep(Global.DELAY);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
