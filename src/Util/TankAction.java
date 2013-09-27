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
package Util;

import Bean.*;
import Runnable.*;
import UI.Panel.*;

public class TankAction {
	public static void fire(ComponentPanel cp, Bullet bullet){
		cp.addBullet(bullet);
		BulletRunnable bullet_runnable = new BulletRunnable(bullet, cp);
		Thread thread = new Thread(bullet_runnable);
		thread.start();
	}
}
