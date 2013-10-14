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
import java.util.ArrayList;

import Interface.Core.ICore;
import UI.Panel.ComponentPanel;
import Util.Global;

public class Tank implements ICore {
	
	private double _ID;

	private double positionX;
	private double positionY;
	private double speedX;
	private double speedY;
	private double angle;
	private boolean is_on_fire;
	private boolean is_live;
	private int blood;
	private int power;
	private boolean is_robert;

	private boolean direction_north;
	private boolean direction_south;
	private boolean direction_west;
	private boolean direction_east;
	private boolean sight_rotate_pos;
	private boolean sight_rotate_neg;

	public Tank(double x, double y, double sx, double sy, double angle, int blood, int power, boolean is_robert){
		this._ID = Math.random();
		
		this.positionX = x;
		this.positionY = y;
		this.speedX = sx;
		this.speedY = sy;
		this.angle = angle;
		this.blood = blood;
		this.is_on_fire =false;
		this.is_live = true;
		this.power = power;
		this.is_robert = is_robert;
		this.direction_north = false;
		this.direction_south = false;
		this.direction_west = false;
		this.direction_east = false;
		this.sight_rotate_pos = false;
		this.sight_rotate_neg = false;
	}

	@Override
	public boolean move(ComponentPanel panel){
		//If killed
		if(this.blood <= 0){
			this.is_live = false;
			return false;
		}
		
		//Robert move
		if(this.is_robert){
			if(panel.getMainTank().isLive()){
				double main_tank_position_x = panel.getMainTank().getX();
				double main_tank_position_y = panel.getMainTank().getY();
				boolean pos_x = main_tank_position_x >= this.positionX;
				boolean pos_y = main_tank_position_y <= this.positionY;
				double des_angle = 0;
				
				if(pos_x && pos_y){
					des_angle = Math.atan((this.positionY - main_tank_position_y) / (main_tank_position_x - this.positionX));
				}else if(!pos_x && pos_y){
					des_angle = Math.PI - Math.atan((this.positionY - main_tank_position_y) / -(main_tank_position_x - this.positionX));
				}else if(!pos_x && !pos_y){
					des_angle = Math.atan((this.positionY - main_tank_position_y) / (main_tank_position_x - this.positionX)) + Math.PI;
				}else if(pos_x && !pos_y){
					des_angle = 2 * Math.PI - Math.atan(-(this.positionY - main_tank_position_y) / (main_tank_position_x - this.positionX));
				}
				
				des_angle -= this.angle;
				
				if(des_angle > 0){
					if(des_angle > Math.PI){
						sight_rotate_pos = false;
						sight_rotate_neg = true;
					}else{
						sight_rotate_pos = true;
						sight_rotate_neg = false;
					}
				}else{
					if(des_angle < -Math.PI){
						sight_rotate_pos = true;
						sight_rotate_neg = false;
					}else{
						sight_rotate_pos = false;
						sight_rotate_neg = true;
					}
				}
				
				
				if(Math.abs(des_angle) < Global.DEFAULT_ROBERT_FIRE_FANGLE){
					this.is_on_fire = true;
				}else{
					this.is_on_fire = false;
				}
			}else{
				this.is_on_fire = false;
				sight_rotate_pos = false;
				sight_rotate_neg = false;
			}
		}
		
		//Check obstacle
		ArrayList<Tank> tank_list = panel.getTankList();
		int tank_list_size = tank_list.size();
		boolean north_ok = true;
		boolean south_ok = true;
		boolean west_ok = true;
		boolean east_ok = true;
		for(int i=0;i<tank_list_size;i++){
			if(tank_list.get(i).getID() == _ID){
				continue;
			}
			if((positionX + speedX > tank_list.get(i).getX() - Global.TANK_WIDTH)
					&& (positionX < tank_list.get(i).getX())
					&& direction_east
					&& (positionY > tank_list.get(i).getY() - Global.TANK_HEIGHT)
					&& (positionY < tank_list.get(i).getY() + Global.TANK_HEIGHT)){
				east_ok = false;
			}
			if((positionX - speedX < tank_list.get(i).getX() + Global.TANK_WIDTH) 
					&& (positionX > tank_list.get(i).getX()) 
					&& direction_west
					&& (positionY > tank_list.get(i).getY() - Global.TANK_HEIGHT)
					&& (positionY < tank_list.get(i).getY() + Global.TANK_HEIGHT)){
				west_ok = false;
			}
			if((positionY + speedY > tank_list.get(i).getY() - Global.TANK_HEIGHT) 
					&& (positionY < tank_list.get(i).getY()) 
					&& direction_south
					&& (positionX > tank_list.get(i).getX() - Global.TANK_WIDTH)
					&& (positionX < tank_list.get(i).getX() + Global.TANK_WIDTH)){
				south_ok = false;
			}
			if((positionY - speedY < tank_list.get(i).getY() + Global.TANK_HEIGHT)  
					&& (positionY > tank_list.get(i).getY()) 
					&& direction_north
					&& (positionX > tank_list.get(i).getX() - Global.TANK_WIDTH)
					&& (positionX < tank_list.get(i).getX() + Global.TANK_WIDTH)){
				north_ok = false;
			}
		}
		

		//Move
		if (direction_north && north_ok) {
			positionY -= speedY;
		}
		if (direction_south && south_ok) {
			positionY += speedY;
		}
		if (direction_west && west_ok) {
			positionX -= speedX;
		}
		if (direction_east && east_ok) {
			positionX += speedX;
		}
		if (sight_rotate_pos) {
			angle += Global.TANK_ANGLE_SPEED;
			if(angle >= (2 * Math.PI)){
				angle -= (2 * Math.PI);
			}
		}
		if (sight_rotate_neg) {
			angle -= Global.TANK_ANGLE_SPEED;
			if(angle < 0){
				angle += (2 * Math.PI);
			}
		}
		
		//Match to bounds
		if(panel.getBounds().getMaxX() != 0){
			if (positionX < panel.getBounds().getMinX()) {
				positionX = (int) panel.getBounds().getMinX();
			}
			if (positionX > panel.getBounds().getMaxX() - Global.TANK_WIDTH) {
				positionX = (int) panel.getBounds().getMaxX() - Global.TANK_WIDTH;
			}
			if (positionY < panel.getBounds().getMinY()) {
				positionY = (int) panel.getBounds().getMinY();
			}
			if (positionY > panel.getBounds().getMaxY() - Global.TANK_HEIGHT) {
				positionY = (int) panel.getBounds().getMaxY() - Global.TANK_HEIGHT;
			}
		}
		return true;
	}

	public Rectangle2D getShape() {
		Rectangle2D tank_body = new Rectangle2D.Double(positionX, positionY,
				Global.TANK_WIDTH, Global.TANK_HEIGHT);
		return tank_body;
	}
	
	public Rectangle2D getBloodBar() {
		Rectangle2D blood_bar = new Rectangle2D.Double(
				positionX, 
				positionY - Global.BLOODBAR_ABOVE,
				Global.BLOODBAR_WIDTH * this.blood / Global.DEFAULT_BLOOD, 
				Global.BLOODBAR_HEIGHT);
		return blood_bar;
	}

	public Line2D getSight(){
		Line2D sight_line = new Line2D.Double(
				positionX + Global.TANK_WIDTH / 2, 
				positionY + Global.TANK_HEIGHT / 2, 
				positionX + Global.TANK_WIDTH / 2 + 100 * Math.cos(angle), 
				positionY + Global.TANK_HEIGHT / 2 - 100 * Math.sin(angle));

		return sight_line;
	}
	
	public Bullet fire() {
		Bullet bullet = new Bullet(
				positionX + Global.TANK_WIDTH / 2, 
				positionY + Global.TANK_HEIGHT / 2, 
				Global.BULLET_SPEED,
				angle,
				power,
				_ID);
		return bullet;
	}

	@Override
	public double getID() {
		return this._ID;
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

	@Override
	public void killed() {
		this.is_live = false;
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
	
	public void reduceBlood(int attack_power){
		this.blood -= attack_power;
	}
}
