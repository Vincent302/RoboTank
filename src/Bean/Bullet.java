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

	private int positionX;
	private int positionY;
	private double speedX;
	private double speedY;
	
	
	public Bullet(int x, int y, double sx, double sy){
		this.positionX = x;
		this.positionY = y;
		this.speedX = sx;
		this.speedY = sy;
	}
	
	@Override
	public boolean move(Rectangle2D panel){
		positionX += speedX;
		positionY += speedY;
		
		if(positionX < panel.getMinX()){
			positionX = (int)panel.getMinX();
			speedX = -speedX;
		}
		if(positionX > panel.getMaxX() - Global.BULLET_WIDTH){
			positionX = (int)panel.getMaxX() - Global.BULLET_WIDTH;
			speedX = -speedX;
		}
		if(positionY < panel.getMinY()){
			positionY = (int)panel.getMinY();
			speedY = -speedY;
		}
		if(positionY > panel.getMaxY() - Global.BULLET_HEIGHT){
			positionY = (int)panel.getMaxY() - Global.BULLET_HEIGHT;
			speedY = -speedY;
		}
		return true;
	}

	public Ellipse2D getShape(){
		Ellipse2D bullet_ball = new Ellipse2D.Double(positionX, positionY, Global.BULLET_WIDTH, Global.BULLET_HEIGHT);
		return bullet_ball;
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
}
