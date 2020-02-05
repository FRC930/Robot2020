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

    //[--------------------AUTO--------------------------]

    public static final double ksVolts = 0.22;
    public static final double kvVoltSecondsPerMeter = 0.289;
    public static final double kaVoltSecondsSquaredPerMeter = 0.06;
    public static final double kMaxSpeedMetersPerSecond = 1;
    public static final double kMaxAccelerationMetersPerSecondSquared = 3;
    public static final double kRamseteB = 2;
    public static final double kRamseteZeta = 0.7;
    public static final double kTrackwidthMeters = 0.69;
    public static final double kPDriveVel = 1;
    public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(kTrackwidthMeters);

  
    //[-------------------AUTO--------------------------]

    //[------------------JOYSTICK-----------------------]
    
    //[------------------JOYSTICK-----------------------]
        
    //[------------------DRIVETRAIN---------------------]
    
    public static final double DRIVE_DEADBAND_JOYSTICK = 0.000125;
    public static final double DRIVE_TURNING_MULTIPLIER = 0.73;
        
    //[------------------DRIVETRAIN---------------------]
  
    //[--------------------UTILITIES--------------------]

    // Sets the global logger level
    public static final Level LOG_LEVEL = Level.INFO;

    //[--------------------UTILITIES--------------------]
  
} //End of Constants.java
