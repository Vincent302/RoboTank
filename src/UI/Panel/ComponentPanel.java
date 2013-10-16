/**                          
 * Project:           RoboTank                               
 * Comments:          Tank robot game                                           
 * JDK version used:  JDK1.6                             
 * Namespace:         Bean                              
 * Author:            Vincent Li             
 * Create Date:       2013-09-24 
 */
package UI.Panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import Action.TankAction;
import Bean.Bullet;
import Bean.Dot;
import Bean.Tank;
import Runnable.FireRunnable;
import Runnable.PaintRunnable;
import Runnable.TankRunnable;
import Util.Global;

public class ComponentPanel extends JPanel {

	private static final long serialVersionUID = -8037419449780220542L;
	
	private ArrayList<Bullet> bullet_list;
	private ArrayList<Tank> tank_list;
	private ArrayList<Dot> dot_list;
	private Tank main_tank;

	public ComponentPanel(){
		this.bullet_list = new ArrayList<Bullet>();
		this.tank_list = new ArrayList<Tank>();
		this.dot_list = new ArrayList<Dot>();
		this.setFocusable(true);
		this.setBackground(Color.BLACK);

		initPaintCanvas();
		initMainTank();
		initRoboTank();
		initFireEngine();
		initKeyListener();
	}

	private void initPaintCanvas(){
		PaintRunnable paint_runnable = new PaintRunnable(this);
		Thread paint_thread = new Thread(paint_runnable);
		paint_thread.start();
	}
	
	private void initFireEngine(){
		//FireRunnable fire_runnable = new FireRunnable(this, main_tank);
		//this.main_fire_thread = new Thread(fire_runnable);
		//main_fire_thread.start();
		for(Tank fire_tank : this.tank_list){
			FireRunnable fire_runnable = new FireRunnable(this, fire_tank);
			Thread fire_thread = new Thread(fire_runnable);
			fire_thread.start();
		}
	}

	private void initMainTank(){
		this.main_tank = new Tank(400, 
				300, 
				Global.MAIN_TANK_SPEED_X,
				Global.MAIN_TANK_SPEED_Y, 
				Global.DEFAULT_SIGHT_ANGLE,
				Global.DEFAULT_BLOOD, 
				Global.DEFAULT_POWER,
				false);
		TankRunnable main_tank_runnable = new TankRunnable(main_tank, this);
		Thread main_tank_thread = new Thread(main_tank_runnable);
		main_tank_thread.start();
		this.addTank(main_tank);
	}
	
	private void initRoboTank(){
L1:		for(int i=0;i<Global.ROBOTANK_NUMBER;i++){
			double temp_position_x = (Global.COMPONENT_PANEL_WIDTH - Global.TANK_WIDTH) * Math.random();
			double temp_position_y = (Global.COMPONENT_PANEL_HEIGHT - Global.TANK_HEIGHT) * Math.random();

			//Check tank overlapping
			for(int k=0;k<this.tank_list.size();k++){
				double exist_position_x = this.tank_list.get(k).getX();
				double exist_position_y = this.tank_list.get(k).getY();
				if((temp_position_x > exist_position_x - Global.TANK_WIDTH) 
						&& (temp_position_x < exist_position_x + Global.TANK_WIDTH)
						&& (temp_position_y > exist_position_y - Global.TANK_HEIGHT)
						&& (temp_position_y < exist_position_y + Global.TANK_HEIGHT)){
					i--;
					continue L1;
				}
			}
			
			//Add tank
			Tank robo_tank = new Tank(
					temp_position_x, 
					temp_position_y, 
					Global.TANK_SPEED_X,
					Global.TANK_SPEED_Y, Global.DEFAULT_SIGHT_ANGLE,
					Global.DEFAULT_BLOOD, Global.DEFAULT_POWER,
					true);
			TankRunnable robo_tank_runnable = new TankRunnable(robo_tank, this);
			Thread robo_tank_thread = new Thread(robo_tank_runnable);
			robo_tank_thread.start();
			this.addTank(robo_tank);
		}
		
	}
	
	private ComponentPanel getThisPanel() {
		return this;
	}

	private void initKeyListener() {
		
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				keyActionPressed(e.getKeyChar());
			}

			@Override
			public void keyReleased(KeyEvent e) {
				keyActionRelease(e.getKeyChar());
			}

			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			private void keyActionPressed(int key_char) {
				switch (key_char) {
				case 'w':
					main_tank.setDirectionNorth();
					break;
				case 's':
					main_tank.setDirectionSouth();
					break;
				case 'a':
					main_tank.setDirectionWest();
					break;
				case 'd':
					main_tank.setDirectionEast();
					break;
				case 'y':
					main_tank.setRotatePos();
					break;
				case 'u':
					main_tank.setRotateNeg();
					break;
				case 'i':
					main_tank.setOnFire();
					break;
				case KeyEvent.VK_ESCAPE:
					System.exit(0);
					break;
				}
			}
			
			private void keyActionRelease(int key_char) {
				switch (key_char){
				case 'w':
					main_tank.releaseDirectionNorth();
					break;
				case 's':
					main_tank.releaseDirectionSouth();
					break;
				case 'a':
					main_tank.releaseDirectionWest();
					break;
				case 'd':
					main_tank.releaseDirectionEast();
					break;
				case 'y':
					main_tank.releaseRotatePos();
					break;
				case 'u':
					main_tank.releaseRotateNeg();
					break;
				case 'i':
					main_tank.releaseOnFire();
					Bullet bullet = main_tank.fire();
					TankAction.fire(getThisPanel(), bullet);
					break;
				case KeyEvent.VK_ESCAPE:
					System.exit(0);
					break;
				}
			}
		});
	}

	public void addBullet(Bullet bullet) {
		this.bullet_list.add(bullet);
	}

	public void addTank(Tank tank) {
		this.tank_list.add(tank);
	}
	
	public void addDot(Dot dot) {
		this.dot_list.add(dot);
	}
	
	public void removeBullet(Bullet bullet) {
		this.bullet_list.remove(bullet);
	}

	public void removeTank(Tank tank) {
		this.tank_list.remove(tank);
	}
	
	public void removeDot(Dot dot) {
		this.dot_list.remove(dot);
	}
	
	public ArrayList<Tank> getTankList(){
		return this.tank_list;
	}
	
	public Tank getMainTank(){
		return this.main_tank;
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// Draw bullets
		g2.setColor(Color.GRAY);
		for (int i = 0; i < bullet_list.size(); i++){
			if (!bullet_list.get(i).isLive()){
				removeBullet(bullet_list.get(i));
				i--;
				continue;
			}
			g2.fill(bullet_list.get(i).getShape());
		}
		
		// Draw tanks
		for (int i = 0; i < tank_list.size(); i++){
			if (!tank_list.get(i).isLive()){
				removeTank(tank_list.get(i));
				i--;
				continue;
			}
			//Draw body
			/*
			g2.rotate(-tank_list.get(i).getBodyAngle(), 
					(int)tank_list.get(i).getX() + Global.TANK_WIDTH / 2, 
					(int)tank_list.get(i).getY() + Global.TANK_HEIGHT / 2);
		    */
			g2.drawImage(tank_list.get(i).getShape(), 
					(int)tank_list.get(i).getX(), 
					(int)tank_list.get(i).getY(), 
					Global.TANK_WIDTH, 
					Global.TANK_HEIGHT, 
					null);
			/*
			g2.rotate(tank_list.get(i).getBodyAngle(), 
					(int)tank_list.get(i).getX() + Global.TANK_WIDTH / 2, 
					(int)tank_list.get(i).getY() + Global.TANK_HEIGHT / 2);
			*/
			//Draw sight
			g2.rotate(-tank_list.get(i).getAngle(), 
					(int)tank_list.get(i).getX() + Global.TANK_WIDTH / 2, 
					(int)tank_list.get(i).getY() + Global.TANK_HEIGHT / 2);
			g2.drawImage(tank_list.get(i).getSight(), 
					(int)tank_list.get(i).getX() - 3, 
					(int)tank_list.get(i).getY() - 3, 
					Global.SIGHT_WIDTH, 
					Global.SIGHT_HEIGHT, 
					null);
			g2.rotate(tank_list.get(i).getAngle(), 
					(int)tank_list.get(i).getX() + Global.TANK_WIDTH / 2, 
					(int)tank_list.get(i).getY() + Global.TANK_HEIGHT / 2);
			//Draw blood
			g2.setColor(new Color(150, 0, 0));
			g2.fill(tank_list.get(i).getBloodBar());
		}
		//Draw exploding dot
		g2.setColor(Color.YELLOW);
		for (int i = 0; i < dot_list.size(); i++){
			if(dot_list.get(i) != null){
				if (!dot_list.get(i).isLive()){
					removeDot(dot_list.get(i));
					i--;
					continue;
				}
				g2.draw(dot_list.get(i).getShape());
			}
		}
	}
}
