/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autocommands;

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

import java.util.List;
public class CheesyDenverSkilletCommand extends SequentialCommandGroup {
  /**
   * Creates a new Autonomous.
   */
  DriveSubsystem m_drive;
  public CheesyDenverSkilletCommand(DriveSubsystem subsystem) {
    m_drive = subsystem;
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

    // Generates a trajectory for a path to move towards opposing Wheel of Fortune
    Trajectory trajectory1 = TrajectoryGenerator.generateTrajectory(
        // Start at the origin (initiation line) facing towards the field
        new Pose2d(0, 0, new Rotation2d(90)),
        List.of(
            // Pass through no interior waypoints, so this field is empty
        ),
        // End 3 meters straight ahead of where we started, still facing forward
            //***Requires future adjustments based on game field dimensions
        new Pose2d(3, 0, new Rotation2d(90)),
        // Pass config
        config

    );

    // Generates a trajectory to move into shooting range for 5 W.O.F. balls
    Trajectory trajectory2 = TrajectoryGenerator.generateTrajectory(
        // Start at the W.O.F. facing towards the goal, away from the W.O.F.
        new Pose2d(0, 0, new Rotation2d(270)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(
            //***Requires future adjustments based on game field dimensions
            new Translation2d(1, 1)
        ),
        // End 3 meters straight ahead of where we started, still facing forward
            //***Requires future adjustments based on game field dimensions
        new Pose2d(3, 0, new Rotation2d(90)),
        // Pass config
        config
    );

    // Generates a trajectory for moving towards the center square for 2 ball pickup and shoot
    Trajectory trajectory3 = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing towards the field
        new Pose2d(0, 0, new Rotation2d(90)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(
            // Pass through no interior waypoints, so this field is empty
        ),
        // Endpoint
            //***Requires future adjustments based on game field dimensions
        new Pose2d(3, 0, new Rotation2d(90)),
        // Pass config
        config
    );

    // -------- RAMSETE Commands -------- \\
    // Creates a command that can be added to the command scheduler in the sequential command
    
    // Creates RAMSETE Command for first trajectory
    RamseteCommand ramseteCommand1 = new RamseteCommand(
        trajectory1,
        m_drive::getPose,
        new RamseteController(Constants.KRAMSETEB, Constants.KRAMSETEZETA),
        new SimpleMotorFeedforward(Constants.KSVOLTS,
                                   Constants.KVVOLT,
                                   Constants.KAVOLT),
        Constants.KDRIVEKINEMATICS,
        m_drive::getWheelSpeeds,
        new PIDController(Constants.KPDRIVEVEL, 0, 0),
        new PIDController(Constants.KPDRIVEVEL, 0, 0),
        // RamseteCommand passes volts to the callback
        m_drive::tankDriveVolts,
        m_drive
    );

    // Creates RAMSETE Command for second trajectory
    RamseteCommand ramseteCommand2 = new RamseteCommand(
        trajectory2,
        m_drive::getPose,
        new RamseteController(Constants.KRAMSETEB, Constants.KRAMSETEZETA),
        new SimpleMotorFeedforward(Constants.KSVOLTS,
                                   Constants.KVVOLT,
                                   Constants.KAVOLT),
        Constants.KDRIVEKINEMATICS,
        m_drive::getWheelSpeeds,
        new PIDController(Constants.KPDRIVEVEL, 0, 0),
        new PIDController(Constants.KPDRIVEVEL, 0, 0),
        // RamseteCommand passes volts to the callback
        m_drive::tankDriveVolts,
        m_drive
    );

    // Creates RAMSETE Command for third trajectory
    RamseteCommand ramseteCommand3 = new RamseteCommand(
        trajectory3,
        m_drive::getPose,
        new RamseteController(Constants.KRAMSETEB, Constants.KRAMSETEZETA),
        new SimpleMotorFeedforward(Constants.KSVOLTS,
                                   Constants.KVVOLT,
                                   Constants.KAVOLT),
        Constants.KDRIVEKINEMATICS,
        m_drive::getWheelSpeeds,
        new PIDController(Constants.KPDRIVEVEL, 0, 0),
        new PIDController(Constants.KPDRIVEVEL, 0, 0),
        // RamseteCommand passes volts to the callback
        m_drive::tankDriveVolts,
        m_drive
    );
    
    /* Wait 3 seconds to theoretically shoot 3 balls, run ramseteCommand1 
    to move forward towards the wheel of fortune and move over the five balls 
    along that path, wait for a second, run ramseteCommand2 to move back into
    shooting range, wait 5 seconds to shoot 5 balls, run ramseteCommand3 to
    move to the center square and grab two balls along that path, then wait to
    shoot final two balls
    */

    addCommands(new WaitCommand(3),
        ramseteCommand1, 
        new WaitCommand(1), 
        ramseteCommand2, 
        new WaitCommand(5), 
        ramseteCommand3,
        new WaitCommand(2));
  }
}
