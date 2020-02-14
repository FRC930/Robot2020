package frc.robot.commands.autocommands.paths;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.intakecommands.DeployIntakeCommand;
import frc.robot.commands.intakecommands.ReturnIntakeCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.GyroSubsystem;
import frc.robot.commands.intakecommands.*;
import edu.wpi.first.wpilibj.controller.PIDController;

import java.util.List;

import edu.wpi.first.wpilibj.controller.RamseteController;

import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;


import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import frc.robot.Constants;
public class SaltAndPepperSkillet extends SequentialCommandGroup {
    private DriveSubsystem driveSubsystem;
    private GyroSubsystem gyroSubsystem;
    private DeployIntakeCommand deployIntakeCommand;
    private ReturnIntakeCommand returnIntakeCommand;
    public SaltAndPepperSkillet(DriveSubsystem dSubsystem, GyroSubsystem gSubsystem, DeployIntakeCommand DICommand, ReturnIntakeCommand RICommand){
        
        driveSubsystem = dSubsystem;
        gyroSubsystem = gSubsystem;
        deployIntakeCommand = DICommand;
        returnIntakeCommand = RICommand;

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

    // Generates a trajectory 
    Trajectory trajectory1 = TrajectoryGenerator.generateTrajectory(
        // Start at initiation line
        new Pose2d(0, 0, new Rotation2d(0)),
        List.of( 
            // Midpoints
        ),
        // End infront of the rendezvous point. Simply move forward 48 inches = 4 feet
        new Pose2d(2, 0, new Rotation2d(0)),
        // Pass config
        config

    );


    // -------- RAMSETE Commands -------- \\

    // Creates a command that can be added to the command scheduler in the sequential command
    // The Ramsete Controller is a trajectory tracker that is built in to WPILib.
    // This tracker can be used to accurately track trajectories with correction for minor disturbances.
    
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
        // pid info***
        new PIDController(Constants.KPDRIVEVEL, 0, 0),
        new PIDController(Constants.KPDRIVEVEL, 0, 0),
        // RamseteCommand passes volts to the callback
        driveSubsystem::tankDriveVolts,
        driveSubsystem 
    );
        //ParallelRaceGroup DeployIntakeAndDriveParrellelCommand = new ParallelRaceGroup(ramseteCommand1,deployIntakeCommand);
        
        addCommands(deployIntakeCommand,ramseteCommand1,new WaitCommand(1),returnIntakeCommand);
    }
}