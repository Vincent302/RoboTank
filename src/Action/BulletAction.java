/**                          
 * Project:           RoboTank                               
 * Comments:          Tank robot game                                           
 * JDK version used:  JDK1.6                             
 * Namespace:         Bean                              
 * Author:            Vincent Li             
 * Create Date:       2013-09-24 
 */
package Action;

import java.util.ArrayList;
import java.util.List;

import Bean.Bullet;
import Bean.Dot;
import Runnable.DotRunnable;
import UI.Panel.ComponentPanel;
import Util.Global;

public class BulletAction {
	public static void explode(ComponentPanel cp, Bullet bullet){
		double positionX = bullet.getX();
		double positionY = bullet.getY();
		
		List<Dot> dots = new ArrayList<Dot>();
		for(int i=0;i<Global.EXPLODE_DOT_NUMBER;i++){
			double random_angle = Math.random() * 2 * Math.PI;
			Dot dot = new Dot(positionX, positionY, random_angle);
			cp.addDot(dot);
			dots.add(dot);
		}
		DotRunnable dot_runnable = new DotRunnable(dots, cp);
		Thread dot_thread = new Thread(dot_runnable);
		dot_thread.start();
	}
}
