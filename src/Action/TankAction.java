/**                          
 * Project:           RoboTank                               
 * Comments:          Tank robot game                                           
 * JDK version used:  JDK1.6                             
 * Namespace:         Bean                              
 * Author:            Vincent Li             
 * Create Date:       2013-09-24 
 */
package Action;

import Bean.Bullet;
import Runnable.BulletRunnable;
import UI.Panel.ComponentPanel;

public class TankAction {
	public static void fire(ComponentPanel cp, Bullet bullet) {
		cp.addBullet(bullet);
		BulletRunnable bullet_runnable = new BulletRunnable(bullet, cp);
		Thread thread = new Thread(bullet_runnable);
		thread.start();
	}
}
