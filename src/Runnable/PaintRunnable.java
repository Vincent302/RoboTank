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

public class PaintRunnable implements Runnable {

	private Component component;

	public PaintRunnable(Component component) {
		this.component = component;
	}

	@Override
	public void run(){
		try {
			while (true){
				component.repaint();
				Thread.sleep(Global.REPAINT_DELAY);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}