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

import Bean.Bullet;
import Util.Global;

public class BulletRunnable implements Runnable{

	private Bullet bullet;
	private Component component;

	public BulletRunnable(Bullet bullet, Component component) {
		this.bullet = bullet;
		this.component = component;
	}

	@Override
	public void run() {
		try {
			while (bullet.move(component.getBounds())) {
				// component.repaint();
				Thread.sleep(Global.DELAY);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
