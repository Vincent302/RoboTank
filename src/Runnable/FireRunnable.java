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

import Action.TankAction;
import Bean.Bullet;
import Bean.Tank;
import UI.Panel.ComponentPanel;
import Util.Global;

public class FireRunnable implements Runnable{

	private Component component;
	private Tank tank;

	public FireRunnable(Component component, Tank tank){
		this.component = component;
		this.tank = tank;
	}
	
	@Override
	public void run(){
		try {
			while(true){
				if(tank.isOnFire()){
					Bullet bullet = tank.fire();
					TankAction.fire((ComponentPanel)component, bullet);
					component.repaint();
				}
				Thread.sleep(Global.FIRE_DELAY);
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
