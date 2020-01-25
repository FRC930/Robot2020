/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.subsystems.ColorSensor2;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.AutonomousCommand;

import frc.robot.subsystems.DriveSubsystem;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  //-------- CONSTANTS --------\\

  //-------- DECLARATIONS --------\\

  private Joystick driverJoystick;
  private Joystick coDriverJoystick;

  //-------- SUBSYSTEMS --------\\

  private final ColorSensor2 m_ColorSensor2 = new ColorSensor2();

  private final DriveSubsystem driveSubsystem;


  //-------- COMMANDS --------\\

  private final DriveCommand driveCommand;
  private final AutonomousCommand autoCommand;
  
  //-------- CONSTRUCTOR ---------\\

  public RobotContainer(){

    //Controllers
    driverJoystick = new Joystick(Constants.DRIVER_CONTROLLER_ID);
    coDriverJoystick = new Joystick(Constants.CODRIVER_CONTROLLER_ID);

    //Subsystems
    driveSubsystem = new DriveSubsystem();

    //Commands
    driveCommand = new DriveCommand(driveSubsystem, driverJoystick);
    autoCommand = new AutonomousCommand(driveSubsystem);

    // Configure the button bindings

    beginRunCommands();
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
  }
  
  private void beginRunCommands() {
    CommandScheduler.getInstance().setDefaultCommand(driveSubsystem, driveCommand);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    System.out.println("Command");
    return autoCommand;
    
    //return ramseteCommand1.andThen(() ->  ramseteCommand2.andThen(() -> m_drive.tankDriveVolts(0, 0)));
    // Run path following command, then stop at the end.
  }
}
