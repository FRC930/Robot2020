package frc.robot.commands.autocommands.paths;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
// import frc.robot.commands.intakecommands.DeployIntakeCommand;
// import frc.robot.commands.intakecommands.ReturnIntakeCommand;
import frc.robot.subsystems.DriveSubsystem;
// import frc.robot.subsystems.GyroSubsystem;
import frc.robot.commands.intakecommands.*;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj.controller.PIDController;

import java.util.List;

import edu.wpi.first.wpilibj.controller.RamseteController;

import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;

import frc.robot.commands.shootercommands.ShootPowerCellCommandGroup;

import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import frc.robot.Constants;

public class SaltAndPepperSkilletCommand extends SequentialCommandGroup {
/*
    Path Description:
    -----------------
      - Drive off intiation line
      - Move to the side 2 Rendezvous Point balls
      - Pick up two rendezvous point balls
      - Shoot all 5 balls held
*/
    private DriveSubsystem driveSubsystem;

    private DeployIntakeCommand deployIntakeCommand;
    private ReturnIntakeCommand returnIntakeCommand;

    private ShootPowerCellCommandGroup shootPowerCellCommandGroup;

    public SaltAndPepperSkilletCommand(DriveSubsystem dSubsystem, DeployIntakeCommand DICommand, ReturnIntakeCommand RICommand,ShootPowerCellCommandGroup SPCCommand){
        driveSubsystem = dSubsystem;

        deployIntakeCommand = DICommand;
        returnIntakeCommand = RICommand;
        
        shootPowerCellCommandGroup = SPCCommand;

        //this is our config for how much power goes to the motors
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
    
      //a second trajectory config this one is reversed
    TrajectoryConfig reverseConfig =
      new TrajectoryConfig(Constants.KMAXSPEED,
      Constants.KMAXACCELERATION)
      // Add kinematics to ensure max speed is actually obeyed
      .setKinematics(Constants.KDRIVEKINEMATICS)
      // Apply the voltage constraint
      .addConstraint(autoVoltageConstraint)
      .setReversed(true);
    // -------- Trajectories -------- \\
    // Generates a trajectory 

    //this is our first trajectory
    Trajectory trajectory1 = TrajectoryGenerator.generateTrajectory(
        // Robot starts at X: 0 Y: 0 and a rotation of 0 
        new Pose2d(0, 0, new Rotation2d(Math.toRadians(0))),
        List.of( 
            // Midpoints
            //new Translation2d(inchesToMeters(40), inchesToMeters(0)),
            //new Translation2d(inchesToMeters(80), inchesToMeters(30))
            // new Translation2d(inchesToMeters(12.5), inchesToMeters(1)),
            // new Translation2d(inchesToMeters(27.5), inchesToMeters(1)),
            // new Translation2d(inchesToMeters(52.5), inchesToMeters(1)),
            // new Translation2d(inchesToMeters(67.5), inchesToMeters(2)),
            // new Translation2d(inchesToMeters(75), inchesToMeters(3)),
            // new Translation2d(inchesToMeters(90), inchesToMeters(7)),
            // new Translation2d(inchesToMeters(100), inchesToMeters(13)),
            // new Translation2d(inchesToMeters(110), inchesToMeters(24))
        ),
        //this is our end point we end our first trajectory at X: 80 inches Y:-80 inches and -65 degrees from orgin
        new Pose2d(inchesToMeters(114.94), inchesToMeters(-20), new Rotation2d(Math.toRadians(-65))),
        // Pass config
        config
    );

    //this is our second trajectory it should be a inverse of the first one
    Trajectory trajectory2 = TrajectoryGenerator.generateTrajectory(
        // Starts X: 0 inches Y: 0 inches and -65 degrees 
        new Pose2d(inchesToMeters(114.94), inchesToMeters(-20), new Rotation2d(Math.toRadians(-65))), //-65
        List.of( 
            // Midpoints
            //new Translation2d(inchesToMeters(40), inchesToMeters(0)),
            //new Translation2d(inchesToMeters(80), inchesToMeters(30))
            // new Translation2d(inchesToMeters(12.5), inchesToMeters(1)),
            // new Translation2d(inchesToMeters(27.5), inchesToMeters(1)),
            // new Translation2d(inchesToMeters(52.5), inchesToMeters(1)),
            // new Translation2d(inchesToMeters(67.5), inchesToMeters(2)),
            // new Translation2d(inchesToMeters(75), inchesToMeters(3)),
            // new Translation2d(inchesToMeters(90), inchesToMeters(7)),
            // new Translation2d(inchesToMeters(100), inchesToMeters(13)),
            // new Translation2d(inchesToMeters(110), inchesToMeters(24))
        ),
        // return to intial position
        new Pose2d(inchesToMeters(0), inchesToMeters(0), new Rotation2d(Math.toRadians(0))),
        // uses the second config
        reverseConfig
    );

    // -------- RAMSETE Commands -------- \\
    // Creates a command that can be added to the command scheduler in the sequential command
    // The Ramsete Controller is a trajectory tracker that is built in to WPILib.
    // This tracker can be used to accurately track trajectories with correction for minor disturbances.
    
    // This is our first atuo command this will run the drivetrain using the first trajectory we made
    RamseteCommand ramseteCommand1 = new RamseteCommand(
        trajectory1,
        driveSubsystem::getPose,
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
    
    // this is our second auto command it uses the second trajectory which is an inverse of the first one as seen earlier
    RamseteCommand ramseteCommand2 = new RamseteCommand(
        trajectory2,
        driveSubsystem::getPose,
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

    /*
    Path Description:
    -----------------
      - Drive off intiation line
      - Move to the side 2 Rendezvous Point balls
      - Pick up two rendezvous point balls
      - Return to initiation line
      - Shoot all 5 balls held
    */

    // add commands here to run during auto
    addCommands(new ParallelRaceGroup(ramseteCommand1, deployIntakeCommand),     // Move to the rendezvous point side 2 balls while intaking
        returnIntakeCommand,                                                     // Stop intake motors and return intake
        ramseteCommand2,                                                         // Move back to initiation line
        new ParallelRaceGroup(new WaitCommand(3), shootPowerCellCommandGroup));  // Shoot 5 balls held from position at initiation line
    }
    
    //converts our inches into meters
    private double inchesToMeters(double inch){
        return inch/39.3701;
    }
}
