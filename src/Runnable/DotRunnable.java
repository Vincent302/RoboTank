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
import java.util.List;

import Bean.Dot;
import Util.Global;

public class DotRunnable implements Runnable{

	private List<Dot> dots;
	private Component component;

	public DotRunnable(List<Dot> dots, Component component) {
		this.dots = dots;
		this.component = component;
	}

	@Override
	public void run() {
		try {
			boolean is_all_dead = false;
			while (!is_all_dead) {
				int dead_number = 0;
				for(Dot dot : dots){
					if(!dot.move(component.getBounds())){
						dead_number++;
					}
				}
				if(dead_number == dots.size()){
					is_all_dead = true;
				}
				Thread.sleep(Global.DOT_DELAY);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
