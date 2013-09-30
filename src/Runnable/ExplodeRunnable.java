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

import Action.BulletAction;
import Bean.Bullet;
import UI.Panel.ComponentPanel;
import Util.Global;

public class ExplodeRunnable implements Runnable{
	private Bullet bullet;
	private Component component;

	public ExplodeRunnable(Bullet bullet, Component component) {
		this.bullet = bullet;
		this.component = component;
	}
	
	@Override
	public void run() {
		try {
			for(int i=0;i<Global.EXPLODE_ROUND;i++){
				BulletAction.explode((ComponentPanel)component, bullet);
				Thread.sleep(Global.EXPLODE_ROUND_DELAY);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
