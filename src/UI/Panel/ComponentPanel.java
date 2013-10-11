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

	private ArrayList<Bullet> bullet_list;
	private ArrayList<Tank> tank_list;
	private ArrayList<Dot> dot_list;
	private Tank main_tank;
	private Thread main_fire_thread;

	public ComponentPanel(){
		this.bullet_list = new ArrayList<Bullet>();
		this.tank_list = new ArrayList<Tank>();
		this.dot_list = new ArrayList<Dot>();
		this.setFocusable(true);
		this.setBackground(Color.BLACK);

		initPaintCanvas();
		initMainTank();
		initFireEngine();
		initKeyListener();
	}

	private void initPaintCanvas(){
		PaintRunnable paint_runnable = new PaintRunnable(this);
		Thread paint_thread = new Thread(paint_runnable);
		paint_thread.start();
	}
	
	private void initFireEngine(){
		FireRunnable fire_runnable = new FireRunnable(this, main_tank);
		this.main_fire_thread = new Thread(fire_runnable);
		main_fire_thread.start();
	}

	private void initMainTank(){
		this.main_tank = new Tank(400, 300, Global.TANK_SPEED_X,
				Global.TANK_SPEED_Y, Global.DEFAULT_SIGHT_ANGLE,
				Global.DEFAULT_BLOOD, Global.DEFAULT_POWER);
		TankRunnable main_tank_runnable = new TankRunnable(main_tank, this);
		Thread main_tank_thread = new Thread(main_tank_runnable);
		main_tank_thread.start();
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

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// Draw bullets
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
			g2.fill(tank_list.get(i).getShape());
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
		// Draw sight
		g2.setColor(Color.GRAY);
		g2.draw(main_tank.getSight());
		// Draw main tank
		g2.setColor(Color.GRAY);
		g2.fill(main_tank.getShape());
	}
}
