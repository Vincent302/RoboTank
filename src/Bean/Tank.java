/**                          
 * Project:           RoboTank                               
 * Comments:          Tank robot game                                           
 * JDK version used:  JDK1.6                             
 * Namespace:         Bean                              
 * Author:            Vincent Li             
 * Create Date:       2013-09-24 
 */
package Bean;

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import Interface.Core.ICore;
import Util.Global;

public class Tank implements ICore {

	private double positionX;
	private double positionY;
	private double speedX;
	private double speedY;
	private double angle;
	private boolean is_on_fire;
	private boolean is_live;

	private boolean direction_north;
	private boolean direction_south;
	private boolean direction_west;
	private boolean direction_east;
	private boolean sight_rotate_pos;
	private boolean sight_rotate_neg;

	public Tank(double x, double y, double sx, double sy, double angle){
		this.positionX = x;
		this.positionY = y;
		this.speedX = sx;
		this.speedY = sy;
		this.angle = angle;
		this.is_on_fire =false;
		this.is_live = true;
		this.direction_north = false;
		this.direction_south = false;
		this.direction_west = false;
		this.direction_east = false;
		this.sight_rotate_pos = false;
		this.sight_rotate_neg = false;
	}

	@Override
	public boolean move(Rectangle2D panel){
		if (direction_north) {
			positionY -= speedY;
		}
		if (direction_south) {
			positionY += speedY;
		}
		if (direction_west) {
			positionX -= speedX;
		}
		if (direction_east) {
			positionX += speedX;
		}
		if (sight_rotate_pos) {
			angle += Global.TANK_ANGLE_SPEED;
		}
		if (sight_rotate_neg) {
			angle -= Global.TANK_ANGLE_SPEED;
		}

		if(panel.getMaxX() != 0){
			if (positionX < panel.getMinX()) {
				positionX = (int) panel.getMinX();
			}
			if (positionX > panel.getMaxX() - Global.TANK_WIDTH) {
				positionX = (int) panel.getMaxX() - Global.TANK_WIDTH;
			}
			if (positionY < panel.getMinY()) {
				positionY = (int) panel.getMinY();
			}
			if (positionY > panel.getMaxY() - Global.TANK_HEIGHT) {
				positionY = (int) panel.getMaxY() - Global.TANK_HEIGHT;
			}
		}
		return true;
	}

	public Rectangle2D getShape() {
		Rectangle2D tank_body = new Rectangle2D.Double(positionX, positionY,
				Global.TANK_WIDTH, Global.TANK_HEIGHT);
		return tank_body;
	}

	public Line2D getSight(){
		Line2D sight_line = new Line2D.Double(
				positionX + Global.TANK_WIDTH / 2, positionY
						+ Global.TANK_HEIGHT / 2, positionX + Global.TANK_WIDTH
						/ 2 + 100 * Math.sin(angle), positionY
						+ Global.TANK_HEIGHT / 2 - 100 * Math.cos(angle));

		return sight_line;
	}
	
	public Bullet fire() {
		Bullet bullet = new Bullet(
				positionX + Global.TANK_WIDTH / 2, 
				positionY + Global.TANK_HEIGHT / 2, 
				Global.BULLET_SPEED,
				angle);
		return bullet;
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
	public void setSpeed(double speed) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return 0;
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
	
	public boolean isOnFire(){
		return this.is_on_fire;
	}

	public void setDirectionNorth() {
		direction_north = true;
	}

	public void setDirectionSouth() {
		direction_south = true;
	}

	public void setDirectionWest() {
		direction_west = true;
	}

	public void setDirectionEast() {
		direction_east = true;
	}

	public void setRotatePos() {
		sight_rotate_pos = true;
	}

	public void setRotateNeg() {
		sight_rotate_neg = true;
	}
	
	public void setOnFire() {
		this.is_on_fire = true;
	}

	public void releaseDirectionNorth() {
		direction_north = false;
	}

	public void releaseDirectionSouth() {
		direction_south = false;
	}

	public void releaseDirectionWest() {
		direction_west = false;
	}

	public void releaseDirectionEast() {
		direction_east = false;
	}

	public void releaseRotatePos() {
		sight_rotate_pos = false;
	}

	public void releaseRotateNeg() {
		sight_rotate_neg = false;
	}
	
	public void releaseOnFire(){
		this.is_on_fire = false;
	}
}
