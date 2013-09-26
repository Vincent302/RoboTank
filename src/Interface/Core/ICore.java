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
package Interface.Core;

import java.awt.geom.*;

public interface ICore{
	public boolean move(Rectangle2D panle, int step);
	public void setX(double x);
	public void setY(double y);
	public void setAngle(double angle);
	public void setSpeed(double speed);
	public double getX();
	public double getY();
	public double getAngle();
	public double getSpeed();
	public boolean isLive();
}
