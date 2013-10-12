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
L1:			while (true) {
				tank.move(component.getBounds());
				double position_x = tank.getX();
				double position_y = tank.getY();
				ArrayList<Tank> tank_list = component.getTankList();
				
				int tank_list_size = tank_list.size();
				for(int i=0;i<tank_list_size;i++){
					if(tank_list.get(i).getID() == this.tank.getID()){
						continue;
					}
					if((position_x >= tank_list.get(i).getX() - Global.TANK_WIDTH) &&
							(position_x <= tank_list.get(i).getX() + Global.TANK_WIDTH) &&
							(position_y >= tank_list.get(i).getY() - Global.TANK_HEIGHT) &&
							(position_y <= tank_list.get(i).getY() + Global.TANK_HEIGHT)){
						tank.moveBack(component.getBounds());
						Thread.sleep(Global.DELAY);
						continue L1;
					}
				}
				Thread.sleep(Global.DELAY);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
