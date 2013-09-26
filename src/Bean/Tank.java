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
package Bean;

import java.awt.geom.*;

import Interface.Core.*;
import Util.Global;

public class Tank implements ICore{

	private int positionX;
	private int positionY;
	private double speedX;
	private double speedY;
	private double angle;
	
	private boolean direction_north;
	private boolean direction_south;
	private boolean direction_west;
	private boolean direction_east;
	
	public Tank(int x, int y, double sx, double sy, double angle){
		this.positionX = x;
		this.positionY = y;
		this.speedX = sx;
		this.speedY = sy;
		this.angle = angle;
	}
	
	@Override
	public boolean move(Rectangle2D panel){
		if(direction_north){
			positionY -= speedY;
		}
		if(direction_south){
			positionY += speedY;
		}
		if(direction_west){
			positionX -= speedX;
		}
		if(direction_east){
			positionX += speedX;
		}
		
		if(positionX < panel.getMinX()){
			positionX = (int)panel.getMinX();
		}
		if(positionX > panel.getMaxX() - Global.TANK_WIDTH){
			positionX = (int)panel.getMaxX() - Global.TANK_WIDTH;
		}
		if(positionY < panel.getMinY()){
			positionY = (int)panel.getMinY();
		}
		if(positionY > panel.getMaxY() - Global.TANK_HEIGHT){
			positionY = (int)panel.getMaxY() - Global.TANK_HEIGHT;
		}
		return true;
	}

	public Rectangle2D getShape(){
		Rectangle2D tank_body = new Rectangle2D.Double(positionX, positionY, Global.TANK_WIDTH, Global.TANK_HEIGHT);
		return tank_body ;
	}
	
	public Line2D getSight(){
		Line2D sight_line = new Line2D.Double(
				positionX + Global.TANK_WIDTH / 2,
				positionY + Global.TANK_HEIGHT / 2,
				positionX + Global.TANK_WIDTH / 2 + 100 * Math.sin(angle),
				positionY + Global.TANK_HEIGHT / 2 - 100 * Math.cos(angle));
		
		return sight_line;
	}
	
	@Override
	public void setX(int x){
		this.positionX = x;
	}

	@Override
	public int getX(){
		return positionX;
	}

	@Override
	public void setY(int y){
		this.positionY = y;
	}

	@Override
	public int getY(){
		return positionY;
	}

	@Override
	public void setSpeedX(double sx){
		this.speedX = sx;
	}
	
	@Override
	public double getSpeedX(){
		return this.speedX;
	}

	@Override
	public void setSpeedY(double sy){
		this.speedY = sy;
	}
	
	@Override
	public double getSpeedY(){
		return this.speedY;
	}
	
	public void setAngle(int ang){
		this.angle = ang;
	}

	public double getAngle(){
		return this.angle;
	}

	public void setDirectionNorth(){
		direction_north = true;
	}
	
	public void setDirectionSouth(){
		direction_south = true;
	}
	
	public void setDirectionWest(){
		direction_west = true;
	}
	
	public void setDirectionEast(){
		direction_east = true;
	}
	
	public void releaseDirectionNorth(){
		direction_north = false;
	}
	
	public void releaseDirectionSouth(){
		direction_south = false;
	}
	
	public void releaseDirectionWest(){
		direction_west = false;
	}
	
	public void releaseDirectionEast(){
		direction_east = false;
	}

	public void addAngle(){
		angle += Global.TANK_ANGLE_SPEED;
	}

	public void reduceAngle(){
		angle -= Global.TANK_ANGLE_SPEED;
	}
}
