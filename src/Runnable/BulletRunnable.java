/**                          
 * Project:           RoboTank                               
 * Comments:          Tank robot game                                           
 * JDK version used:  JDK1.6                             
 * Namespace:         Bean                              
 * Author:            Vincent Li             
 * Create Date:       2013-09-24 
 */
package Runnable;

import java.util.ArrayList;

import Bean.Bullet;
import Bean.Tank;
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
L1:			while (bullet.move(component.getBounds())) {
				double position_x = bullet.getX();
				double position_y = bullet.getY();
				ArrayList<Tank> tank_list = component.getTankList();
				
				int tank_list_size = tank_list.size();
				for(int i=0;i<tank_list_size;i++){
					if(tank_list.get(i).getID() == bullet.getID()){
						continue;
					}
					if((position_x >= tank_list.get(i).getX()) &&
							(position_x <= tank_list.get(i).getX() + Global.TANK_WIDTH) &&
							(position_y >= tank_list.get(i).getY()) &&
							(position_y <= tank_list.get(i).getY() + Global.TANK_HEIGHT)){
						tank_list.get(i).reduceBlood(bullet.getPower());
						bullet.killed();
						break L1;
					}
				}
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
