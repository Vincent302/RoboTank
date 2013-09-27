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
import Util.*;
import Bean.*;

public class BulletRunnable implements Runnable{

	private Bullet bullet;
	private Component component;

	public BulletRunnable(Bullet bullet, Component component) {
		this.bullet = bullet;
		this.component = component;
	}

	@Override
	public void run() {
		try {
			int step = 1;
			while (bullet.move(component.getBounds(), step)) {
				// component.repaint();
				Thread.sleep(Global.DELAY);
				step++;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
