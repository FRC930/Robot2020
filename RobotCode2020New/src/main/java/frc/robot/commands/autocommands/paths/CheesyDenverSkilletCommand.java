/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autocommands.paths;

import edu.wpi.first.wpilibj.controller.RamseteController;

import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;

import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.controller.PIDController;

import edu.wpi.first.wpilibj2.command.*;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.GyroSubsystem;
import frc.robot.Constants;

import java.util.List;
public class CheesyDenverSkilletCommand extends SequentialCommandGroup {
 /**
   * Creates a new Autonomous.
   */
  DriveSubsystem driveSubsystem;
  GyroSubsystem gyroSubsystem;
  public CheesyDenverSkilletCommand(DriveSubsystem dSubsystem,GyroSubsystem gSubsystem) {
    driveSubsystem = dSubsystem;
    gyroSubsystem = gSubsystem;
    var autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(Constants.KSVOLTS,
            Constants.KVVOLT,
            Constants.KAVOLT),
            Constants.KDRIVEKINEMATICS,10);
    
    // Configurate the values of all trajectories for max velocity and acceleration
    TrajectoryConfig config =
      new TrajectoryConfig(Constants.KMAXSPEED,
      Constants.KMAXACCELERATION)
      // Add kinematics to ensure max speed is actually obeyed
      .setKinematics(Constants.KDRIVEKINEMATICS)
      // Apply the voltage constraint
      .addConstraint(autoVoltageConstraint);

    // -------- Trajectories -------- \\

    // All measurements are hand-calculated based on field diagrams and general sketches
        // Reference https://drive.google.com/open?id=1lzL53Y3nWV6dWDt5XFEnVk34D3QuE3gg for detailed diagram drawing
        
    // Generates a trajectory for a path to move towards Wheel of  and ball pickup
    Trajectory trajectory1 = TrajectoryGenerator.generateTrajectory(
        // Start at the origin (initiation line) facing towards the field
        new Pose2d(inchesToMeters(0), inchesToMeters(0), new Rotation2d(0)),
        List.of(),
        // End 3 meters straight ahead of where we started, still facing forward
        new Pose2d(inchesToMeters(130.0), inchesToMeters(0), new Rotation2d(0)),
        // Pass config
        config

    );

    // Generates a trajectory to move back into shooting position on center line
    Trajectory trajectory2 = TrajectoryGenerator.generateTrajectory(
        // Start at the W.O.F. facing towards the center line
        new Pose2d(inchesToMeters(130.0), inchesToMeters(0), new Rotation2d(0)),
        // Pass this waypoint to have a more drastic curve towards the second shooting point
        List.of(),
        // End half way within the field, near the initiation line
        new Pose2d(inchesToMeters(20.0), inchesToMeters(161.12), new Rotation2d(0)),
        // Pass config
        config
    );

    // Generates a trajectory for moving towards the center square for 3 ball pickup and shoot
    Trajectory trajectory3 = TrajectoryGenerator.generateTrajectory(
        // Start at center of field just behind intiation line
        new Pose2d(inchesToMeters(20.0), inchesToMeters(161.12), new Rotation2d(0)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(
            // Move to first of three balls first to grab balls
            // new Translation2d(inchesToMeters(107.83), inchesToMeters(145.2)),
            // new Translation2d(inchesToMeters(114.17), inchesToMeters(160.5))
        ),
        // Endpoint
            //***Requires future adjustments based on game field dimensions
        new Pose2d(inchesToMeters(120.51), inchesToMeters(175.8), new Rotation2d(0)),
        // Pass config
        config
    );

    // -------- RAMSETE Commands -------- \\
    // Creates a command that can be added to the command scheduler in the sequential command
    
    // Creates RAMSETE Command for first trajectory
    RamseteCommand ramseteCommand1 = new RamseteCommand(
        trajectory1,
        gyroSubsystem::getPose,
        new RamseteController(Constants.KRAMSETEB, Constants.KRAMSETEZETA),
        new SimpleMotorFeedforward(Constants.KSVOLTS,
                                   Constants.KVVOLT,
                                   Constants.KAVOLT),
        Constants.KDRIVEKINEMATICS,
        driveSubsystem::getWheelSpeeds,
        new PIDController(Constants.KPDRIVEVEL, 0, 0),
        new PIDController(Constants.KPDRIVEVEL, 0, 0),
        // RamseteCommand passes volts to the callback
        driveSubsystem::tankDriveVolts,
        driveSubsystem
    );

    // Creates RAMSETE Command for second trajectory
    RamseteCommand ramseteCommand2 = new RamseteCommand(
        trajectory2,
        gyroSubsystem::getPose,
        new RamseteController(Constants.KRAMSETEB, Constants.KRAMSETEZETA),
        new SimpleMotorFeedforward(Constants.KSVOLTS,
                                   Constants.KVVOLT,
                                   Constants.KAVOLT),
        Constants.KDRIVEKINEMATICS,
        driveSubsystem::getWheelSpeeds,
        new PIDController(Constants.KPDRIVEVEL, 0, 0),
        new PIDController(Constants.KPDRIVEVEL, 0, 0),
        // RamseteCommand passes volts to the callback
        driveSubsystem::tankDriveVolts,
        driveSubsystem
    );

    // Creates RAMSETE Command for third trajectory
    RamseteCommand ramseteCommand3 = new RamseteCommand(
        trajectory3,
        gyroSubsystem::getPose,
        new RamseteController(Constants.KRAMSETEB, Constants.KRAMSETEZETA),
        new SimpleMotorFeedforward(Constants.KSVOLTS,
                                   Constants.KVVOLT,
                                   Constants.KAVOLT),
        Constants.KDRIVEKINEMATICS,
        driveSubsystem::getWheelSpeeds,
        new PIDController(Constants.KPDRIVEVEL, 0, 0),
        new PIDController(Constants.KPDRIVEVEL, 0, 0),
        // RamseteCommand passes volts to the callback
        driveSubsystem::tankDriveVolts,
        driveSubsystem
    );
    
    /* 
    Path Explanation
    */

    addCommands(new WaitCommand(3),
        ramseteCommand1, 
        new WaitCommand(1), 
        ramseteCommand2, 
        new WaitCommand(5), 
        ramseteCommand3,
        new WaitCommand(2));
  }

  public double inchesToMeters(double inches) {
      double meters = inches / 39.37;
      return meters;
  }

}
