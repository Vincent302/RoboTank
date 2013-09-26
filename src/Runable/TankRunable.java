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
package Runable;

import java.awt.*;
import Util.*;
import Bean.*;

public class TankRunable implements Runnable{

	private Tank tank;
	private Component component;
	
	public TankRunable(Tank tank, Component component){
		this.tank = tank;
		this.component = component;
	}
	
	@Override
	public void run(){
		try{
			while(tank.move(component.getBounds())){
				component.repaint();
				Thread.sleep(Global.DELAY);
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
