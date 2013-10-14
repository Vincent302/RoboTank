/**                          
 * Project:           RoboTank                               
 * Comments:          Tank robot game                                           
 * JDK version used:  JDK1.6                             
 * Namespace:         Bean                              
 * Author:            Vincent Li             
 * Create Date:       2013-09-24 
 */
package Bean;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import Interface.Core.ICore;
import UI.Panel.ComponentPanel;
import Util.Global;

public class Bullet implements ICore {

	private double _ID;
	
	private double positionX;
	private double positionY;
	private double speed;
	private double angle;
	private boolean is_live;
	private int power;

	public Bullet(double x, double y, double speed, double angle, int power, double id) {
		this._ID = id;
		
		this.positionX = x;
		this.positionY = y;
		this.speed = speed;
		this.angle = angle;
		this.is_live = true;
		this.power = power;
	}

	@Override
	public boolean move(ComponentPanel panel) {
		positionX += speed * Math.sin(angle);
		positionY -= speed * Math.cos(angle);

		if ((positionX < panel.getBounds().getMinX())
				|| (positionX > panel.getBounds().getMaxX() - Global.BULLET_WIDTH)
				|| (positionY < panel.getBounds().getMinY())
				|| (positionY > panel.getBounds().getMaxY() - Global.BULLET_HEIGHT)) {
			this.is_live = false;
			return false;
		}
		
		ArrayList<Tank> tank_list = panel.getTankList();
		int tank_list_size = tank_list.size();
		for(int i=0;i<tank_list_size;i++){
			if(tank_list.get(i).getID() == _ID){
				continue;
			}
			if((positionX >= tank_list.get(i).getX()) &&
					(positionX <= tank_list.get(i).getX() + Global.TANK_WIDTH) &&
					(positionY >= tank_list.get(i).getY()) &&
					(positionY <= tank_list.get(i).getY() + Global.TANK_HEIGHT)){
				tank_list.get(i).reduceBlood(this.power);
				this.is_live = false;
				return false;
			}
		}
		return true;
	}

	public Ellipse2D getShape() {
		Ellipse2D bullet_ball = new Ellipse2D.Double(
				positionX - Global.BULLET_WIDTH / 2, 
				positionY - Global.BULLET_HEIGHT / 2,
				Global.BULLET_WIDTH, 
				Global.BULLET_HEIGHT);
		return bullet_ball;
	}

	@Override
	public double getID() {
		return this._ID;
	}

	@Override
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	@Override
	public double getSpeed() {
		return this.speed;
	}

	@Override
	public void setX(double x) {
		this.positionX = x;
	}

	@Override
	public double getX() {
		return positionX;
	}

	@Override
	public void setY(double y) {
		this.positionY = y;
	}

	@Override
	public double getY() {
		return positionY;
	}

	@Override
	public void setAngle(double angle) {
		this.angle = angle;
	}

	@Override
	public double getAngle() {
		return this.angle;
	}

	@Override
	public boolean isLive() {
		return this.is_live;
	}
	
	@Override
	public void killed() {
		this.is_live = false;
	}
	
	public int getPower(){
		return this.power;
	}
}
