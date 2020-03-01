/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.DutyCycle;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.logging.*;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {

    // [-------------------MOTOR IDS--------------------------]

    public static final int DRIVE_RIGHT_FRONT_ID = 3;
    public static final int DRIVE_RIGHT_BACK_ID = 4;
    public static final int DRIVE_LEFT_FRONT_ID = 1;
    public static final int DRIVE_LEFT_BACK_ID = 2;

    public static final int TURRET_ID = 5;
    public static final int INTAKE_ID = 6;

    public static final int SHOOTER_LEAD_ID = 8;
    public static final int SHOOTER_SLAVE_ID = 7;

    public static final int HOPPER_ID = 9;
    public static final int TOWER_ID = 10;
    public static final int KICKER_ID = 11;

    public static final int COLOR_WHEEL_ID = 333;

    public static final int CLIMBER_ARM_ID = 12;

    // [-------------------MOTOR IDS--------------------------]

    // [-------------------PISTON IDS-------------------------]

    public static final int INTAKE_SOLENOID_ID = 0;
    public static final int SHOOTER_SOLENOID_ID = 1;
    public static final int SHIFTER_SOLENOID_ID = 2;

    public static final int COMPRESSOR_PORT = 0;

    // [-------------------PISTON IDS-------------------------]

    // [-------------------ROBORIO PORT IDS---------------------------]

    public static final int ENCODER_PORT_ID = 0;
	  public static final int HOPPER_ENCODER_PORT_ID = 1; 
    public static final int CLIMBER_ENCODER_PORT_ID = 2;

    // [-------------------ROBORIO PORT IDS---------------------------]

    // [-------------------DRIVE------------------------]

    public static final double MOTOR_RAMP_RATE = 0.5;

    // [-------------------DRIVE------------------------]

    // [-------------------SPEEDS--------------------------]

    public static final double HOPPER_DEFAULT_SPEED = 0.3;
    public static final double HOPPER_SHOOTING_SPEED = 0.85; // 0.6
    public static final double TOWER_SPEED = 1.0;
    public static final double KICKER_SPEED = 0.6; // 0.6
    public static final double INTAKE_SPEED = 0.8; // 0.8
    public static final double FLYWHEEL_SPEED = 0.62;
    public static final double CLIMBER_EXTEND_SPEED = 0.1;
    public static final double CLIMBER_RETRACT_SPEED = -0.1;

    // [-------------------SPEEDS--------------------------]

    // [-------------------LIMITS--------------------------]

    public static final double ENCODER_ROTATION_LIMIT = 190;
    public static final double CLIMBER_LIMIT = -2.3;

    // [-------------------LIMITS--------------------------]

    // [-------------------TURRET--------------------------]

    // When the turret encoder is reset, the turret faces forward and the encoder is
    // reset to 180 degrees. These units are in raw values.
    public static final double UPPER_LIMIT = 0.640;//0.8;//.27
    public static final double LOWER_LIMIT = 0.247;//0.2; //-.27

    public static final double TURRET_MAX_SPEED = 0.6;//0.75;
    public static final double TURRET_MAX_SET_POSITION_SPEED = 0.4;

    public static final double TURRET_P = 0.025;//0.021;
    public static final double TURRET_I = 0.0;//0.135;
    public static final double TURRET_D = 0.0008;//0.00081;

    public static final double TURRET_SET_POSITION_P = 1.0;
    public static final double TURRET_SET_POSITION_I = 0.0;
    public static final double TURRET_SET_POSITION_D = 0.0;

    // encoder positions for setting turret to one of four directions
    public static final double TURRET_FRONT_POSITION = 0.659;
    public static final double TURRET_BACK_POSITION = 0.415;
    public static final double TURRET_LEFT_POSITION = 0.532;

    // speed used for turning the turret
    public static final double TURRET_TURNING_SPEED = 0.4;

    // deadband for the turret joystick
    public static final double JOYSTICK_TURRET_DEADBAND = 0.1;

    // deadband for the turret set position commands
    public static final double TURRET_DEADBAND = 0.001;

    // [-------------------TURRET--------------------------]

    // [-------------------ANGLES--------------------------]

    public static final double FLYWHEEL_LOWER_ANGLE = 31.4;
    public static final double FLYWHEEL_UPPER_ANGLE = 39.0;

    // [-------------------ANGLES--------------------------]

    // [-------------------STATES--------------------------]

    public static final boolean INTAKE_PISTONS_UP = false;
    public static final boolean INTAKE_PISTONS_DOWN = !INTAKE_PISTONS_UP;

    //[--------------------AUTO--------------------------]
    
    public static final double KSVOLTS = 0.411;//0.22; //0.073 //0.22
    public static final double KVVOLT = 0.227; //0.289; //0.234; // seconds per meter 0.289
    public static final double KAVOLT = 0.0249;//0.06; //0.0161 //this is in seconds squared per meter 0.06
    public static final double KMAXSPEED = 3; //in meters per second
    public static final double KMAXACCELERATION = 3; //in meters per seconds squared //Originally 0.5
    //gyro values
    public static final double KRAMSETEB = 2;
    public static final double KRAMSETEZETA = 0.7;
    
    //Track width of our robot
    public static final double KTRACKWIDTH = 0.69;//10.975; //in meters .69
    public static final double KPDRIVEVEL =  0.693;//0.00175; //0.673;//.693
    public static final DifferentialDriveKinematics KDRIVEKINEMATICS = new DifferentialDriveKinematics(KTRACKWIDTH);
    // AUTO code values---------------------------------------]

    // DRIVETRAIN Constants --------------------------------]

    public static final double DRIVE_DEADBAND_JOYSTICK = 0.000125;
    public static final double DRIVE_TURNING_MULTIPLIER = 0.5;

    // DRIVETRAIN Constants --------------------------------]

    //[-------------------LOGGER--------------------------]

    /*
    public static final Level LOG_LEVEL_FINE = Level.FINE;
    public static final Level LOG_LEVEL_FINER = Level.FINER;
    public static final Level LOG_LEVEL_FINEST = Level.FINEST;
    public static final Level LOG_LEVEL_INFO = Level.INFO;
    public static final Level LOG_LEVEL_WARNING = Level.WARNING; 
    */

    //TODO: We want to be able to change these values in Shuffleboard
    public static final Level LOG_LEVEL_FINE = Level.OFF;
    public static final Level LOG_LEVEL_FINER = Level.OFF;
    public static final Level LOG_LEVEL_FINEST = Level.OFF;
    public static final Level LOG_LEVEL_INFO = Level.OFF;
    public static final Level LOG_LEVEL_WARNING = Level.OFF;

    //[-------------------LOGGER--------------------------]

    // OFFSET
    
	public static final double KICKER_ENCODER_OFFSET = 0;
    
} // end of constants
