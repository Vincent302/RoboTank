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
package UI.Panel;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import Bean.*;
import Runable.*;
import Util.*;

public class ComponentPanel extends JPanel{

	private ArrayList<Bullet> bullet_list;
	private ArrayList<Tank> tank_list;
	private Tank main_tank;
	
	
	public ComponentPanel(){
		this.bullet_list = new ArrayList<Bullet>();
		this.tank_list = new ArrayList<Tank>();
		this.setFocusable(true);
		
		initMainTank();
		initKeyListener();
	}
	
	public void addBullet(Bullet bullet){
		this.bullet_list.add(bullet);
	}
	
	public void addTank(Tank tank){
		this.tank_list.add(tank);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		//Draw bullets
		for(Bullet temp_bullet : bullet_list){
			g2.fill(temp_bullet.getShape());
		}
		//Draw tanks
		for(Tank temp_tank : tank_list){
			g2.fill(temp_tank.getShape());
		}
		//Draw sight
		g2.draw(main_tank.getSight());
		//Draw main tank
		g2.fill(main_tank.getShape());
	}
	
	private void initMainTank(){
		this.main_tank = new Tank(0 ,0, Global.TANK_SPEED_X, Global.TANK_SPEED_Y, Global.DEFAULT_SIGHT_ANGLE);
		TankRunable main_tank_runable = new TankRunable(main_tank, this);
		Thread main_tank_thread = new Thread(main_tank_runable);
		main_tank_thread.start();
	}
	
	private void fireBullet(){
		double angle = main_tank.getAngle();
		Bullet bullet = new Bullet(
				main_tank.getX() + Global.TANK_WIDTH / 2 ,
				main_tank.getY() + Global.TANK_HEIGHT / 2 , 
				Global.BULLET_SPEED * Math.sin(angle), 
				-Global.BULLET_SPEED * Math.cos(angle));
		this.addBullet(bullet);
		BulletRunable bullet_runable = new BulletRunable(bullet, this);
		Thread thread = new Thread(bullet_runable);
		thread.start();
	}

	private void initKeyListener(){
		this.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				keyActionSet(e.getKeyCode());
			}

			@Override
			public void keyReleased(KeyEvent e){
				keyActionRelease(e.getKeyCode());
			}

			@Override
			public void keyTyped(KeyEvent e){
				keyActionSet(e.getKeyCode());
			}
			
			private void keyActionSet(int key_code){
				switch(key_code){
				case KeyEvent.VK_W:
					main_tank.setDirectionNorth();
					break;
				case KeyEvent.VK_S:
					main_tank.setDirectionSouth();
					break;
				case KeyEvent.VK_A:
					main_tank.setDirectionWest();
					break;
				case KeyEvent.VK_D:
					main_tank.setDirectionEast();
					break;
				case KeyEvent.VK_Y:
					main_tank.addAngle();
					break;
				case KeyEvent.VK_U:
					main_tank.reduceAngle();
					break;
				case KeyEvent.VK_I:
					fireBullet();
					break;
				}
			}
			
			private void keyActionRelease(int key_code){
				switch(key_code){
				case KeyEvent.VK_W:
					main_tank.releaseDirectionNorth();
					break;
				case KeyEvent.VK_S:
					main_tank.releaseDirectionSouth();
					break;
				case KeyEvent.VK_A:
					main_tank.releaseDirectionWest();
					break;
				case KeyEvent.VK_D:
					main_tank.releaseDirectionEast();
					break;
				}
			}
		});
	}
}
