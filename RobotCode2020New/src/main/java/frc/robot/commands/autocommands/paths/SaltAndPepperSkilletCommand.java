/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.autocommands.paths;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.RamseteCommand;

import frc.robot.commands.intakecommands.DeployIntakeCommand;
import frc.robot.commands.intakecommands.ReturnIntakeCommand;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.GyroSubsystem;

import edu.wpi.first.wpilibj.controller.PIDController;

import java.util.List;

import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;

import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;

import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Pose2d;

import frc.robot.Constants;

//-------- COMMANDGROUP CLASS --------\\

public class SaltAndPepperSkilletCommand extends SequentialCommandGroup {

    //-------- DELCARATIONS --------\\

    private DriveSubsystem driveSubsystem;
    private GyroSubsystem gyroSubsystem;
    private DeployIntakeCommand deployIntakeCommand;
    private ReturnIntakeCommand returnIntakeCommand;
    //private ShootPowerCellCommand shootPowerCellCommand;

    //-------- CONSTRUCTOR --------\\

    //TODO: Add ShootPowerCellCommand SPCCommand
    public SaltAndPepperSkilletCommand(DriveSubsystem dSubsystem, GyroSubsystem gSubsystem, DeployIntakeCommand DICommand, ReturnIntakeCommand RICommand) {

        //-- instantiation of subsystems and commands used
        driveSubsystem = dSubsystem;
        gyroSubsystem = gSubsystem;
        deployIntakeCommand = DICommand;
        returnIntakeCommand = RICommand;
        //shootPowerCellCommand = SPCCommand;

        //-- this is our config for how much power goes to the motors
        var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(Constants.KSVOLTS, Constants.KVVOLT, Constants.KAVOLT),
            Constants.KDRIVEKINEMATICS, 10); //end of autoVoltageConstraint instantiation

        //-- Configurate the values of all trajectories for max velocity and acceleration
        TrajectoryConfig config = new TrajectoryConfig(Constants.KMAXSPEED, Constants.KMAXACCELERATION)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(Constants.KDRIVEKINEMATICS)
            // Apply the voltage constraint
            .addConstraint(autoVoltageConstraint);
    
        //a second trajectory config this one is reversed
        TrajectoryConfig reverseConfig = new TrajectoryConfig(Constants.KMAXSPEED, Constants.KMAXACCELERATION)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(Constants.KDRIVEKINEMATICS)
            // Apply the voltage constraint
            .addConstraint(autoVoltageConstraint)
            // Reverses the trajectory path
            .setReversed(true);

        // -------- Trajectories -------- \\

        //this is our first trajectory
        Trajectory trajectory1 = TrajectoryGenerator.generateTrajectory(

            // Robot starts at X: 0 Y: 0 and a rotation of 0 
            new Pose2d(0, 0, new Rotation2d(Math.toRadians(0))),

            //This is a list of midpoints for the curve TODO: Uncomment
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

            //end of first trajectory at X: 160 inches Y:-160 inches and -65 degrees from orgin
            new Pose2d(inchesToMeters(160), inchesToMeters(-160), new Rotation2d(Math.toRadians(-65))),
            // Pass config
            config
        ); // end of trajectory1

        //this is our second trajectory, it is the inverse of the first one
        Trajectory trajectory2 = TrajectoryGenerator.generateTrajectory(

            // Starts X: 160 inches Y: -160 inches and -65 degrees 
            new Pose2d(inchesToMeters(160), inchesToMeters(-160), new Rotation2d(Math.toRadians(-65))), //-65

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

            // goes to X: -40 inches and Y: -20 inches with a rotation of 0
            //TODO: Remove the fudged numbers of -40, -20 (theoretically, this should be 0, 0)
            new Pose2d(inchesToMeters(-40), inchesToMeters(-20), new Rotation2d(Math.toRadians(0))),
            // uses the second config
            reverseConfig
        ); // end of trajectory2

        //this is our third trajectory; it drives forward in the trench run
        Trajectory trajectory3 = TrajectoryGenerator.generateTrajectory(

            // Starts X: 0 inches Y: 0 inches and 0 degrees 
            new Pose2d(0, 0, new Rotation2d(Math.toRadians(0))), //TODO: Change to -40, -20 (the fudge numbers)

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

            // goes forward X: 200 inches and Y: 0 inches with a rotation of 0
            new Pose2d(inchesToMeters(200), 0, new Rotation2d(Math.toRadians(0))),
            // uses the second config
            config
        ); // end of trajectory3

        //-------- RAMSETE Commands --------\\
    
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

        // this third auto command is the forward path through the trench run
        RamseteCommand ramseteCommand3 = new RamseteCommand(
            trajectory3,
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

        //-------- Adding the commands to the path --------\\

        
        // add commands here to run during auto
        addCommands(ramseteCommand1, ramseteCommand2, ramseteCommand3);

        //DeployIntakeAndDriveParrellelCommand,new WaitCommand(1),returnIntakeCommand);//shootPowerCellCommand);
        //ParallelRaceGroup DeployIntakeAndDriveParrellelCommand = new ParallelRaceGroup(ramseteCommand1,deployIntakeCommand);

    } // end of constructor SaltAndPepperSkilletCommand()

    //converts our inches into meters
    private double inchesToMeters(double inch){
        return inch/39.3701;
    }
    
} // end of class SaltAndPepperSkilletCommand