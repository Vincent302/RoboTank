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
				Thread.sleep(Global.DELAY);
			}
			//BulletAction.explode((ComponentPanel)component, bullet);
			ExplodeRunnable explode_runnable = new ExplodeRunnable(bullet, component);
			Thread explode_thread = new Thread(explode_runnable);
			explode_thread.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
