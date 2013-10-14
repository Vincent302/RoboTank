/**                          
 * Project:           RoboTank                               
 * Comments:          Tank robot game                                           
 * JDK version used:  JDK1.6                             
 * Namespace:         Bean                              
 * Author:            Vincent Li             
 * Create Date:       2013-09-24 
 */
package Runnable;

import Bean.Bullet;
import UI.Panel.ComponentPanel;
import Util.Global;

public class BulletRunnable implements Runnable{

	private Bullet bullet;
	private ComponentPanel component;

	public BulletRunnable(Bullet bullet, ComponentPanel component) {
		this.bullet = bullet;
		this.component = component;
	}

	@Override
	public void run() {
		try {
			while (bullet.move(component)) {
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
