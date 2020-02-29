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
import frc.robot.commands.hoppercommands.RunHopperCommand;
import frc.robot.commands.intakecommands.DeployIntakeCommand;
import frc.robot.commands.intakecommands.ReturnIntakeCommand;
import frc.robot.commands.shootercommands.FlywheelVelocityCommand;
import frc.robot.commands.shootercommands.ShootPowerCellCommandGroup;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.controller.PIDController;

import edu.wpi.first.wpilibj2.command.*;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FlywheelPistonSubsystem;
import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.KickerSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.TowerSubsystem;
import frc.robot.Constants;

import java.util.List;

// -------- PATH DESCRIPTION -------- \\
// Alliance Side - Initial 3 & Trench 3 & Rendezvous 2

public class CaliAvocadoSkilletCommand extends SequentialCommandGroup {
  /**
   * Path Description: ----------------- Shoot 3 from initiation line move through
   * trench to grab 3 balls Shoot 3 from trench position
   */

  private DriveSubsystem driveSubsystem;

  private ShootPowerCellCommandGroup shootPowerCellCommandGroup;

  public CaliAvocadoSkilletCommand(DriveSubsystem dSubsystem, DeployIntakeCommand deployIntakeCommand,
      ReturnIntakeCommand returnIntakeCommand, FlywheelSubsystem flywheelSubsystem, TowerSubsystem towerSubsystem,
      HopperSubsystem hopperSubsystem, KickerSubsystem kickerSubsystem, LimelightSubsystem limelightSubsystem,
      FlywheelPistonSubsystem flywheelPistonSubsystem, RunHopperCommand runHopperCommand) {
    driveSubsystem = dSubsystem;

    var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
        new SimpleMotorFeedforward(Constants.KSVOLTS, Constants.KVVOLT, Constants.KAVOLT), Constants.KDRIVEKINEMATICS,
        10);

    // Configurate the values of all trajectories for max velocity and acceleration
    TrajectoryConfig config = new TrajectoryConfig(Constants.KMAXSPEED, Constants.KMAXACCELERATION)
        // Add kinematics to ensure max speed is actually obeyed
        .setKinematics(Constants.KDRIVEKINEMATICS)
        // Apply the voltage constraint
        .addConstraint(autoVoltageConstraint);

    // -------- Trajectories -------- \\

    // Generates a trajectory for a path to move towards furthest ball in trench run
    Trajectory trajectory1 = TrajectoryGenerator.generateTrajectory(
        // Start at the origin (initiation line) facing towards the field
        new Pose2d(inchesToMeters(0.0), inchesToMeters(0.0), new Rotation2d(0)), List.of(
        // new Translation2d(inchesToMeters(14.38), inchesToMeters(4.63)),
        // new Translation2d(inchesToMeters(43.15), inchesToMeters(13.88)),
        // new Translation2d(inchesToMeters(71.92), inchesToMeters(23.15)),
        // new Translation2d(inchesToMeters(104.63), inchesToMeters(27.75)),
        // new Translation2d(inchesToMeters(122.63), inchesToMeters(27.75))
        ),
        // End at the furthest ball in the trench run (194.63 inches forward)
        new Pose2d(inchesToMeters(194.63), inchesToMeters(0.0), new Rotation2d(0)),
        // Pass config
        config

    );

    // -------- RAMSETE Commands -------- \\
    // Creates a command that can be added to the command scheduler in the
    // sequential command

    // Creates RAMSETE Command for first trajectory
    RamseteCommand ramseteCommand1 = new RamseteCommand(trajectory1, driveSubsystem::getPose,
        new RamseteController(Constants.KRAMSETEB, Constants.KRAMSETEZETA),
        new SimpleMotorFeedforward(Constants.KSVOLTS, Constants.KVVOLT, Constants.KAVOLT), Constants.KDRIVEKINEMATICS,
        driveSubsystem::getWheelSpeeds, new PIDController(Constants.KPDRIVEVEL, 0, 0),
        new PIDController(Constants.KPDRIVEVEL, 0, 0),
        // RamseteCommand passes volts to the callback
        driveSubsystem::tankDriveVolts, driveSubsystem);

    /*
     * Path Description: ----------------- Shoot 3 from initiation line move through
     * trench to grab 3 balls Shoot 3 from trench position
     */

    addCommands( ramseteCommand1
        //new ParallelRaceGroup(new WaitCommand(3),
            //new InstantCommand(() -> new ShootPowerCellCommandGroup(flywheelSubsystem, towerSubsystem, hopperSubsystem,
                //kickerSubsystem, limelightSubsystem, flywheelPistonSubsystem, runHopperCommand))), // Shoot 3 balls
        //new ParallelRaceGroup(ramseteCommand1, deployIntakeCommand), // Moving trajectory while intaking
        //returnIntakeCommand, // Stop intaking
        //new ParallelRaceGroup(new WaitCommand(3), shootPowerCellCommandGroup)
        ); // Shooting final 3 balls
  }

  public double inchesToMeters(double inches) {
    double meters = inches / 39.37;
    return meters;
  }

} // end of class
