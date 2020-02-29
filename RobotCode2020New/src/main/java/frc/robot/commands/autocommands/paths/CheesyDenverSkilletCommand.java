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
import frc.robot.commands.drivecommands.StopDriveCommand;
import frc.robot.commands.intakecommands.DeployIntakeCommand;
import frc.robot.commands.intakecommands.ReturnIntakeCommand;
import frc.robot.commands.shootercommands.ShootPowerCellCommandGroup;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.controller.PIDController;

import edu.wpi.first.wpilibj2.command.*;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.GyroSubsystem;
import frc.robot.Constants;

import java.util.List;

// -------- PATH DESCRIPTION -------- \\
// Opponent Side - Trench 2 & Initial 3 & Rendezvous 3

public class CheesyDenverSkilletCommand extends SequentialCommandGroup {
/**
    Path Description:
    -----------------
        Starts on initiation line with 3 power cells
        Moves towards away team trench and collects 2 power cells 
        Moves toward the rendezvous zone 
        Shoots 5 power cells
        Goes to rendezvous zone and collects 3 power cells
        Moves off of rendezvous zone and shoots 3 power cells
*/

    private DriveSubsystem driveSubsystem;

    private DeployIntakeCommand deployIntakeCommand;
    private ReturnIntakeCommand returnIntakeCommand;
    
    private ShootPowerCellCommandGroup shootPowerCellCommandGroup;

    public CheesyDenverSkilletCommand(DriveSubsystem dSubsystem, DeployIntakeCommand DICommand, ReturnIntakeCommand RICommand,ShootPowerCellCommandGroup SPCCommand) {
        driveSubsystem = dSubsystem;

        deployIntakeCommand = DICommand;
        returnIntakeCommand = RICommand;
        
        shootPowerCellCommandGroup = SPCCommand;

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
        new Pose2d(inchesToMeters(0), inchesToMeters(0), new Rotation2d(Math.toRadians(0))),
        List.of(),
        // End 3 meters straight ahead of where we started, still facing forward
        new Pose2d(inchesToMeters(130.36), inchesToMeters(0), new Rotation2d(Math.toRadians(0))),
        // Pass config
        config

    );

    // Generates a trajectory to move back into shooting position on center line
    Trajectory trajectory2 = TrajectoryGenerator.generateTrajectory(
        // Start at the W.O.F. facing towards the center line
        new Pose2d(inchesToMeters(130.36), inchesToMeters(0), new Rotation2d(Math.toRadians(0))),
        // Pass this waypoint to have a more drastic curve towards the second shooting point
        List.of(),
        // End half way within the field, near the initiation line
        new Pose2d(inchesToMeters(0), inchesToMeters(160.5), new Rotation2d(Math.toRadians(0))),
        // Pass config
        config
    );

    // Generates a trajectory for moving towards the center square for 3 ball pickup and shoot
    Trajectory trajectory3 = TrajectoryGenerator.generateTrajectory(
        // Start at center of field just behind intiation line
        new Pose2d(inchesToMeters(0), inchesToMeters(160.5), new Rotation2d(Math.toRadians(0))),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(
            // Move to first two of three balls first to grab balls
            //new Translation2d(inchesToMeters(107.83), inchesToMeters(0)),
            //new Translation2d(inchesToMeters(7.0), inchesToMeters(-15.0))
        ),
        // Endpoint
        new Pose2d(inchesToMeters(120.51), inchesToMeters(175.8), new Rotation2d(Math.toRadians(0))),
        // Pass config
        config
    );

    // -------- RAMSETE Commands -------- \\
    // Creates a command that can be added to the command scheduler in the sequential command
    
    // Creates RAMSETE Command for first trajectory
    RamseteCommand ramseteCommand1 = new RamseteCommand(
        trajectory1,
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

    // Creates RAMSETE Command for third trajectory
    RamseteCommand ramseteCommand3 = new RamseteCommand(
        trajectory3,
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
    Path Description:
    -----------------
        Starts on initiation line with 3 power cells
        Moves towards enemy team trench and collects 2 power cells 
        Moves toward the rendezvous zone 
        Shoots 5 power cells
        Goes to rendezvous zone and collects 3 power cells
        Moves off of rendezvous zone and shoots 3 power cells
    */

    addCommands(new ParallelRaceGroup(deployIntakeCommand,ramseteCommand1),    // Move to enemy trench run while running intake
        new StopDriveCommand(driveSubsystem),                                                    // Stop intake motors and return intake
        ramseteCommand2,
        new StopDriveCommand(driveSubsystem),                                                        // Move to center of field near intiation line for shooting
        new ParallelRaceGroup(new WaitCommand(3), shootPowerCellCommandGroup),  // Shoot 5 power cells that are held from current position
        new ParallelRaceGroup(ramseteCommand3, deployIntakeCommand),          // Move to rendezvous point and run intake for 3 front balls
        new StopDriveCommand(driveSubsystem),
        returnIntakeCommand,                                                    // Stop intake motors and return intake
        new ParallelRaceGroup(new WaitCommand(3), shootPowerCellCommandGroup)); // Shoot 3 power cells that are held from rendezvous point
  }

  public double inchesToMeters(double inches) {
      double meters = inches / 39.37;
      return meters;
  }

}
