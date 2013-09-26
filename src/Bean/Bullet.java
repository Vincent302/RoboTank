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

public class Bullet implements ICore{

	private double positionX;
	private double positionY;
	private double speed;
	private double angle;
	private boolean isLive;
	
	
	public Bullet(double x, double y, double speed, double angle){
		this.positionX = x;
		this.positionY = y;
		this.speed = speed;
		this.angle = angle;
		this.isLive = true;
	}
	
	@Override
	public boolean move(Rectangle2D panel, int step){
		positionX += (speed * step * Math.sin(angle)) / Global.TIME_CONVERSION;
		positionY -= (speed * step * Math.cos(angle)) / Global.TIME_CONVERSION;
		
		if((positionX < panel.getMinX())
				|| (positionX > panel.getMaxX() - Global.BULLET_WIDTH)
				|| (positionY < panel.getMinY())
				|| (positionY > panel.getMaxY() - Global.BULLET_HEIGHT)){
			this.isLive = false;
			return false;
		}
		return true;
	}

	public Ellipse2D getShape(){
		Ellipse2D bullet_ball = new Ellipse2D.Double(positionX, positionY, Global.BULLET_WIDTH, Global.BULLET_HEIGHT);
		return bullet_ball;
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
	public void setX(double x){
		this.positionX = x;
	}

	@Override
	public double getX(){
		return positionX;
	}

	@Override
	public void setY(double y){
		this.positionY = y;
	}

	@Override
	public double getY(){
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
		return this.isLive;
	}
}
