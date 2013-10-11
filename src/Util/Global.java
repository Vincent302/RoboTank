/**                          
 * Project:           RoboTank                               
 * Comments:          Tank robot game                                           
 * JDK version used:  JDK1.6                             
 * Namespace:         Bean                              
 * Author:            Vincent Li             
 * Create Date:       2013-09-24 
 */
package Util;

public class Global {

	public static int dX = 1;
	public static int dY = 1;

	public static final int DELAY = 1;
	public static final int REPAINT_DELAY = 1;
	public static final int FIRE_DELAY = 400;
	public static final int DOT_DELAY = 5;
	public static final int EXPLODE_ROUND_DELAY = 100;

	public static final int FRAME_WIDTH = 900;
	public static final int FRAME_HEIGHT = 700;
	public static final int COMPONENT_PANEL_WIDTH = 900;
	public static final int COMPONENT_PANEL_HEIGHT = 600;
	public static final int BULLET_WIDTH = 5;
	public static final int BULLET_HEIGHT = 5;
	public static final int TANK_WIDTH = 30;
	public static final int TANK_HEIGHT = 30;

	public static final double BULLET_SPEED_X = 5;
	public static final double BULLET_SPEED_Y = 5;
	public static final double BULLET_SPEED = 0.5;
	public static final double EXPLODE_SPEED = 1;
	public static final double EXPLODE_SPEED_ACCELERATE = -0.012;
	public static final double TANK_SPEED_X = 0.3;
	public static final double TANK_SPEED_Y = 0.3;
	public static final double TANK_ANGLE_SPEED = 0.1 * Math.PI / 180;
	
	public static final int EXPLODE_DOT_NUMBER = 120;
	public static final int EXPLODE_ROUND = 3;

	public static final double DEFAULT_SIGHT_ANGLE = 135 * Math.PI / 180;
	
	public static final int DEFAULT_BLOOD = 100;
	public static final int DEFAULT_POWER = 50;
	
	public static final int ROBOTANK_NUMBER = 3;

}
