/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.*;
//import frc.robot.commands.DriveCommand;
//import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.CompressorOnCommand;
import frc.robot.commands.CompressorOffCommand;
import frc.robot.commands.IntakeStopCommand;

//import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeMotors;
import frc.robot.subsystems.IntakePistons;
import frc.robot.subsystems.Compresser;
/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  //-------- CONSTANTS --------\\

  //-------- DECLARATIONS --------\\

  private final Joystick driverJoystick;
  private final Joystick coDriverJoystick;

  //-------- SUBSYSTEMS --------\\

  //private final DriveSubsystem driveSubsystem;
  private final IntakePistons intakePistons;
  private final IntakeMotors intakeMotors;
  private final Compresser compressor;

  //-------- COMMANDS --------\\

  //private final DriveCommand driveCommand;
  //private final AutonomousCommand autoCommand;
  private final IntakeCommand intakeCommand;
  private final CompressorOnCommand compressorOnCommand;
  private final CompressorOffCommand compressorOffCommand;
  private final IntakeStopCommand intakeStopCommand;
  //-------- CONSTRUCTOR ---------\\

  public RobotContainer() {

    //Controllers
    driverJoystick = new Joystick(Constants.DRIVER_CONTROLLER_ID);
    coDriverJoystick = new Joystick(Constants.CODRIVER_CONTROLLER_ID);

    //Subsystems
    //driveSubsystem = new DriveSubsystem();
    intakePistons = new IntakePistons();
    intakeMotors = new IntakeMotors();
    compressor = new Compresser();

    //Commands
    //driveCommand = new DriveCommand(driveSubsystem, driverJoystick);
    //autoCommand = new AutonomousCommand(driveSubsystem);
    intakeCommand = new IntakeCommand(intakePistons, intakeMotors,coDriverJoystick);
    intakeStopCommand = new IntakeStopCommand(intakePistons, intakeMotors, coDriverJoystick);
    compressorOnCommand = new CompressorOnCommand(compressor);
    compressorOffCommand = new CompressorOffCommand(compressor);

    // Configure the button bindings

    //beginRunCommands();
    configureButtonBindings();
  }

  //-------- METHODS --------\\

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    beginRunCommands();
      //compressorOnCommand.schedule(true);
      
    JoystickButton intakeButton = new JoystickButton(coDriverJoystick,3);
    intakeButton.whenActive(intakeCommand);
    intakeButton.whenInactive(intakeStopCommand);
  }
  
  private void beginRunCommands() {
   // CommandScheduler.getInstance().setDefaultCommand(intakePistons, intakeCommand);
  
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    System.out.println("Command");
    return null;
    
    //return ramseteCommand1.andThen(() ->  ramseteCommand2.andThen(() -> m_drive.tankDriveVolts(0, 0)));
    // Run path following command, then stop at the end.
  }
}
