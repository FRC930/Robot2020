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
import edu.wpi.first.wpilibj.controller.PIDController;

import edu.wpi.first.wpilibj2.command.*;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.Constants;

import java.util.List;

// -------- PATH DESCRIPTION -------- \\
// Alliance Side - Initial 3 & Trench 3 + 2 & Rendezvous 2

public class LoadedSkilletCommand extends SequentialCommandGroup {
  /**
   * Creates a new Autonomous.
   */
  public LoadedSkilletCommand(DriveSubsystem dSubsystem) {

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
        // Reference https://drive.google.com/open?id=1lmhSdpIyVqGV13hMAeo5CIO-BRiFMvaV for detailed diagram drawing

    // Generates a trajectory for a path to move towards Wheel of Fortune
    Trajectory initLineToTrench5Trajectory = TrajectoryGenerator.generateTrajectory(
        // Start at the origin (initiation line) facing towards the field
        new Pose2d(inchesToMeters(0.0), inchesToMeters(0), new Rotation2d(0)),
        List.of(
            // Pass through no interior waypoints, so this field is empty
        ),
        // End infront of the wheel of fortune
        new Pose2d(inchesToMeters(258.9), inchesToMeters(0), new Rotation2d(0)),
        // Pass config
        config

    );

    // Generates a trajectory two move into shooting range for 5 W.O.F. balls
    Trajectory trench5ToRendezvous2Trajectory = TrajectoryGenerator.generateTrajectory(
        // Start at the W.O.F. facing towards the goal, away from the W.O.F.
        new Pose2d(inchesToMeters(0), inchesToMeters(0), new Rotation2d(0)),
        // Pass this waypoint to have a more drastic curve towards the second shooting point
        List.of(
            // No waypoints
        ),
        // End at location of first trench run ball, facing rendezvous balls
        new Pose2d(inchesToMeters(48.0), inchesToMeters(-12.0), new Rotation2d(Math.toRadians(270))),
        // Pass config
        config
    );

    // Generates a trajectory for moving towards the center square for 2 ball pickup and shoot
    Trajectory trajectory3 = TrajectoryGenerator.generateTrajectory(
        // Start at the origin facing towards the field
        new Pose2d(inchesToMeters(0), inchesToMeters(0), new Rotation2d(0)),
        // Pass through these two interior waypoints, making an 's' curve path
        List.of(
            // Pass through no interior waypoints, so this field is empty
        ),
        // End about 80 inches forward from previous point, moved in y direction to end at rendevous point balls
        new Pose2d(inchesToMeters(0), inchesToMeters(81.04), new Rotation2d(0)),
        // Pass config
        config
    );

    // -------- RAMSETE Commands -------- \\
    // Creates a command that can be added to the command scheduler in the sequential command
    
    // Creates RAMSETE Command for first trajectory
    RamseteCommand ramseteCommand1 = new RamseteCommand(
        initLineToTrench5Trajectory,
        dSubsystem::getPose,
        new RamseteController(Constants.KRAMSETEB, Constants.KRAMSETEZETA),
        new SimpleMotorFeedforward(Constants.KSVOLTS,
                                   Constants.KVVOLT,
                                   Constants.KAVOLT),
        Constants.KDRIVEKINEMATICS,
        dSubsystem::getWheelSpeeds,
        new PIDController(Constants.KPDRIVEVEL, 0, 0),
        new PIDController(Constants.KPDRIVEVEL, 0, 0),
        // RamseteCommand passes volts to the callback
        dSubsystem::tankDriveVolts,
        dSubsystem
    );

    // Creates RAMSETE Command for second trajectory
    RamseteCommand ramseteCommand2 = new RamseteCommand(
        trench5ToRendezvous2Trajectory,
        dSubsystem::getPose,
        new RamseteController(Constants.KRAMSETEB, Constants.KRAMSETEZETA),
        new SimpleMotorFeedforward(Constants.KSVOLTS,
                                   Constants.KVVOLT,
                                   Constants.KAVOLT),
        Constants.KDRIVEKINEMATICS,
        dSubsystem::getWheelSpeeds,
        new PIDController(Constants.KPDRIVEVEL, 0, 0),
        new PIDController(Constants.KPDRIVEVEL, 0, 0),
        // RamseteCommand passes volts to the callback
        dSubsystem::tankDriveVolts,
        dSubsystem
    );

    // Creates RAMSETE Command for third trajectory
    RamseteCommand ramseteCommand3 = new RamseteCommand(
        trajectory3,
        dSubsystem::getPose,
        new RamseteController(Constants.KRAMSETEB, Constants.KRAMSETEZETA),
        new SimpleMotorFeedforward(Constants.KSVOLTS,
                                   Constants.KVVOLT,
                                   Constants.KAVOLT),
        Constants.KDRIVEKINEMATICS,
        dSubsystem::getWheelSpeeds,
        new PIDController(Constants.KPDRIVEVEL, 0, 0),
        new PIDController(Constants.KPDRIVEVEL, 0, 0),
        // RamseteCommand passes volts to the callback
        dSubsystem::tankDriveVolts,
        dSubsystem
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
        // Turn in place 180 degrees
        ramseteCommand2, 
        new WaitCommand(5), 
        ramseteCommand3,
        new WaitCommand(2));
  } // End of Constructor

  // Method to convert distances
  public double inchesToMeters(double inches) {
      double meters = inches / 39.37;
      return meters;
  }

} // End of Class