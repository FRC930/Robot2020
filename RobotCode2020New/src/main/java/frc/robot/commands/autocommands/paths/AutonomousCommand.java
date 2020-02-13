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
import frc.robot.Constants;
import frc.robot.commands.intakecommands.*;
import frc.robot.subsystems.*;

import java.util.List;
public class AutonomousCommand extends SequentialCommandGroup {
  /**
   * Creates a new Autonomous.
   */
  DriveSubsystem drive;

  DeployIntakeCommand deployIntakeCommand;
  ReturnIntakeCommand returnIntakeCommand;

  IntakeMotorSubsystem intakeMotor;
  IntakePistonSubsystem intakePiston;

  public AutonomousCommand(DriveSubsystem dSubsystem, IntakeMotorSubsystem iMSubsystem, IntakePistonSubsystem iPSubsystem) {
    drive = dSubsystem;
    intakeMotor = iMSubsystem;
    intakePiston = iPSubsystem;
    deployIntakeCommand = new DeployIntakeCommand(intakePiston, intakeMotor);
    returnIntakeCommand = new ReturnIntakeCommand(intakePiston, intakeMotor);
    var autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(Constants.KSVOLTS,
            Constants.KVVOLT,
            Constants.KAVOLT),
            Constants.KDRIVEKINEMATICS,10);
    
    TrajectoryConfig config =
      new TrajectoryConfig(Constants.KMAXSPEED,
      Constants.KMAXACCELERATION)
      // Add kinematics to ensure max speed is actually obeyed
      .setKinematics(Constants.KDRIVEKINEMATICS)
      // Apply the voltage constraint
      .addConstraint(autoVoltageConstraint);

  Trajectory trajectory1 = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(0, 0, new Rotation2d(0)),
        List.of(),
        //new Translation2d(1, 2)),
        // End 1 meters straight ahead of where we started, facing forward
        new Pose2d(1, 0, new Rotation2d(0)),
        // Pass config
        config);
        
    RamseteCommand ramseteCommand1 = new RamseteCommand(
        trajectory1,
        drive::getPose,
        new RamseteController(Constants.KRAMSETEB, Constants.KRAMSETEZETA),
        new SimpleMotorFeedforward(Constants.KSVOLTS,
                                   Constants.KVVOLT,
                                   Constants.KAVOLT),
        Constants.KDRIVEKINEMATICS,
        drive::getWheelSpeeds,
        new PIDController(Constants.KPDRIVEVEL, 0, 0),
        new PIDController(Constants.KPDRIVEVEL, 0, 0),
        // RamseteCommand passes volts to the callback
        drive::tankDriveVolts,
        drive
    );
    Trajectory trajectory2 = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing the +X direction
        new Pose2d(0, 0, new Rotation2d(0)),
        List.of(),
        //new Translation2d(1, 2)),
        // End 3 meters straight ahead of where we started, facing forward
        new Pose2d(2, 0, new Rotation2d(0)),
        // Pass config
        config);
     RamseteCommand ramseteCommand2 = new RamseteCommand(
        trajectory2,
        drive::getPose,
        new RamseteController(Constants.KRAMSETEB, Constants.KRAMSETEZETA),
        new SimpleMotorFeedforward(Constants.KSVOLTS,
                                   Constants.KVVOLT,
                                   Constants.KAVOLT),
        Constants.KDRIVEKINEMATICS,
        drive::getWheelSpeeds,
        new PIDController(Constants.KPDRIVEVEL, 0, 0),
        new PIDController(Constants.KPDRIVEVEL, 0, 0),
        // RamseteCommand passes volts to the callback
        drive::tankDriveVolts,
        drive
    );
    addCommands(/*ramseteCommand1,*/deployIntakeCommand,new WaitCommand(5), returnIntakeCommand/*ramseteCommand2*/);
  }
}
