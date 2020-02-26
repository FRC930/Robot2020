/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
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
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.controller.PIDController;

import edu.wpi.first.wpilibj2.command.*;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.GyroSubsystem;
import frc.robot.Constants;

import java.util.List;

// -------- PATH DESCRIPTION -------- \\
// Alliance Side - Initial 3 & Trench 3 + 2

public class PeachtreeSkilletCommand extends SequentialCommandGroup {
  /**
   * Creates a new Autonomous.
   */
  DriveSubsystem driveSubsystem;
  GyroSubsystem gyroSubsystem;
  public PeachtreeSkilletCommand(DriveSubsystem dSubsystem,GyroSubsystem gSubsystem) {
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

    // Generates a trajectory for a path to move towards furthest ball in trench run
    Trajectory trajectory1 = TrajectoryGenerator.generateTrajectory(
        // Start at the origin (initiation line) facing towards the field
        new Pose2d(inchesToMeters(0.0), inchesToMeters(0), new Rotation2d(0)),
        List.of(
            // Pass through no interior waypoints, so this field is empty
        ),
        // End at the WOF
        new Pose2d(inchesToMeters(258.9), inchesToMeters(0), new Rotation2d(0)),
        // Pass config
        config

    );

    // Generates a trajectory two move into shooting range
    Trajectory trajectory2 = TrajectoryGenerator.generateTrajectory(
        // Start at the W.O.F. facing towards the goal, away from the W.O.F.
        new Pose2d(inchesToMeters(0), inchesToMeters(0), new Rotation2d(0)),
        // Pass this waypoint to have a more drastic curve towards the second shooting point
        List.of(
            // No waypoints
        ),
        // End at location of first trench run ball, facing rendezvous balls
        new Pose2d(inchesToMeters(48.0), inchesToMeters(-12.0), new Rotation2d(0)),
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
        driveSubsystem::getPose,
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
      Shoot 3 from initiation line
      move through trench to grab 5 balls and shoot from trench
    */

    addCommands(new WaitCommand(3), 
        ramseteCommand1, 
        // Turn in place 180 degrees
        ramseteCommand2, 
        new WaitCommand(5));
        
  }

  public double inchesToMeters(double inches) {
      double meters = inches / 39.37;
      return meters;
  }

} // end of class
