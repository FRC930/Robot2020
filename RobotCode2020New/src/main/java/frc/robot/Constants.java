/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public final class Constants {

    //Refer to http://team358.org/files/programming/ControlSystem2009-/XBoxControlMapping.jpg

    // JOYSTICK Controller Values-----------------------------[
    public static final int DRIVER_CONTROLLER_ID = 0;
    public static final int CODRIVER_CONTROLLER_ID = 1;

    public static final int BUTTON_A = 1;
    public static final int BUTTON_B = 2;
    public static final int BUTTON_X = 3;
    public static final int BUTTON_Y = 4;
    public static final int BUTTON_LB = 5;
    public static final int BUTTON_RB = 6;
    public static final int BUTTON_BACK = 7;
    public static final int BUTTON_START = 8;
    public static final int BUTTON_LEFT_STICK = 9;
    public static final int BUTTON_RIGHT_STICK = 10;

    public static final int AXIS_LEFT_X = 0;
    public static final int AXIS_LEFT_Y = 1;
    public static final int AXIS_RIGHT_X = 4;
    public static final int AXIS_RIGHT_Y = 5;
    
    public static final int AXIS_LT = 2;
    public static final int AXIS_RT = 3;
    
    public static final double TRIGGER_PRESSED_THRESHOLD = 0.4;
    // JOYSTICK Controller Values-----------------------------]
    
    // DRIVETRAIN Constants --------------------------------]

    public static final double DRIVE_DEADBAND_JOYSTICK = 0.000124;
	public static final double DRIVE_TURNING_MULTIPLIER = 0.73;
    
    // DRIVETRAIN Constants --------------------------------]
}
