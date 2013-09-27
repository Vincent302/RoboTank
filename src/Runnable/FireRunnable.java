/**                          
 * Project:           RoboTank                               
 * Comments:          Tank robot game                                           
 * JDK version used:  JDK1.6                             
 * Namespace:         Bean                              
 * Author��                              Vincent Li             
 * Create Date��                2013-09-24
 * Modified By��                Vincent Li                                     
 * Modified Date:     2013-09-24                  
 * Version:           V0.1                       
 */
package Runnable;

import java.awt.*;
import UI.Panel.*;
import Util.*;
import Bean.*;

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
					double angle = tank.getAngle();
					Bullet bullet = new Bullet(tank.getX()
							+ Global.TANK_WIDTH / 2, tank.getY()
							+ Global.TANK_HEIGHT / 2, Global.BULLET_SPEED,
							angle);
					TankAction.fire((ComponentPanel)component, bullet);
					component.repaint();
				}else{
					break;
				}
				Thread.sleep(Global.FIRE_DELAY);
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
