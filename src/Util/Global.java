/**                          
 * Project:           RoboTank                               
 * Comments:          Tank robot game                                           
 * JDK version used:  JDK1.6                             
 * Namespace:         Bean                              
 * Author:            Vincent Li             
 * Create Date:       2013-09-24 
 */
package Util;

import java.awt.Image;
import java.awt.Toolkit;

public class Global {

	public static int dX = 1;
	public static int dY = 1;

	public static final int DELAY = 1;
	public static final int REPAINT_DELAY = 1;
	public static final int FIRE_DELAY = 300;
	public static final int DOT_DELAY = 5;
	public static final int EXPLODE_ROUND_DELAY = 120;

	public static final int FRAME_WIDTH = 900;
	public static final int FRAME_HEIGHT = 700;
	public static final int COMPONENT_PANEL_WIDTH = 900;
	public static final int COMPONENT_PANEL_HEIGHT = 600;
	public static final int BULLET_WIDTH = 5;
	public static final int BULLET_HEIGHT = 5;
	public static final int TANK_WIDTH = 18;
	public static final int TANK_HEIGHT = 18;
	public static final int SIGHT_WIDTH = 24;
	public static final int SIGHT_HEIGHT = 24;

	public static final double BULLET_SPEED_X = 5;
	public static final double BULLET_SPEED_Y = 5;
	public static final double BULLET_SPEED = 0.5;
	public static final double EXPLODE_SPEED = 1;
	public static final double EXPLODE_SPEED_ACCELERATE = -0.025;
	public static final double TANK_SPEED_X = 0.1;
	public static final double TANK_SPEED_Y = 0.1;
	public static final double MAIN_TANK_SPEED_X = 0.3;
	public static final double MAIN_TANK_SPEED_Y = 0.3;
	public static final double TANK_ANGLE_SPEED = Math.toRadians(0.1);
	
	public static final int EXPLODE_DOT_NUMBER = 100;
	public static final int EXPLODE_ROUND = 2;

	public static final double DEFAULT_SIGHT_ANGLE = Math.toRadians(0);
	public static final double DEFAULT_ROBERT_FIRE_FANGLE = Math.toRadians(20);
	
	public static final int DEFAULT_BLOOD = 10000;
	public static final int DEFAULT_POWER = 5;
	
	public static final int ROBOTANK_NUMBER = 30;
	
	//Blood
	public static final int BLOODBAR_ABOVE = 5;
	public static final int BLOODBAR_HEIGHT = 2;
	public static final int BLOODBAR_WIDTH = 18;
	
	//Deviation
	public static final double DEVIATION = 2.0;
	public static final int ROBERT_MOVE_COUNTER = 1000;
	
	//Image
	public static final Image TANK_IMAGE = Toolkit.getDefaultToolkit().getImage("image/tank.png");
	public static final Image SIGHT_IMAGE = Toolkit.getDefaultToolkit().getImage("image/sight.png");

}
