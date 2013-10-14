/**                          
 * Project:           RoboTank                               
 * Comments:          Tank robot game                                           
 * JDK version used:  JDK1.6                             
 * Namespace:         Bean                              
 * Author:            Vincent Li             
 * Create Date:       2013-09-24 
 */
package Runnable;

import Bean.Tank;
import UI.Panel.ComponentPanel;
import Util.Global;

public class TankRunnable implements Runnable {

	private Tank tank;
	private ComponentPanel component;

	public TankRunnable(Tank tank, ComponentPanel component) {
		this.tank = tank;
		this.component = component;
	}

	@Override
	public void run(){
		try {
			while (tank.move(component) && tank.isLive()) {
				Thread.sleep(Global.DELAY);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
