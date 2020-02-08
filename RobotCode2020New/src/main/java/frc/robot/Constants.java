/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import java.util.logging.*;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    //[-------------------PORTS--------------------------]

    public static final int HOPPER_MOTOR_PORT = 3;
    public static final int TOWER_MOTOR_PORT = 4;
    
    //[-------------------PORTS--------------------------]

    //[-------------------HOPPER--------------------------]

    public static final double HOPPER_DEFAULT_SPEED = 0.2;
    public static final double HOPPER_SHOOTING_SPEED = 0.8;
    public static final double TOWER_SPEED = 0.75;

    //[-------------------HOPPER--------------------------]

    //[--------------------AUTO--------------------------]
    public static final double KSVOLTS = 0.22;//0.22
    public static final double KVVOLT = 0.289; // seconds per meter 0.289
    public static final double KAVOLT = 0.06; //this is in seconds squared per meter 0.06
    public static final double KMAXSPEED = 2; //in meters per second
    public static final double KMAXACCELERATION = 1; //in meters per seconds squared
    //gyro values
    public static final double KRAMSETEB = 2;
    public static final double KRAMSETEZETA = 0.7;
    //Track width of our robot
    public static final double KTRACKWIDTH = 0.69; //in meters
    public static final double KPDRIVEVEL = 0;
    public static final DifferentialDriveKinematics KDRIVEKINEMATICS = new DifferentialDriveKinematics(KTRACKWIDTH);


    //[-------------------AUTO--------------------------]
        
    //[------------------DRIVETRAIN---------------------]
    
    public static final double DRIVE_DEADBAND_JOYSTICK = 0.000125;
    public static final double DRIVE_TURNING_MULTIPLIER = 0.5;
        
    //[------------------DRIVETRAIN---------------------]
  
    //[--------------------UTILITIES--------------------]

    // Sets the global logger level
    public static final Level LOG_LEVEL = Level.INFO;

    //[--------------------UTILITIES--------------------]
  
} //End of Constants.java
