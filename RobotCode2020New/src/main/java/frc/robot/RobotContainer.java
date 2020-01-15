/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

import frc.robot.commands.*;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

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

  //  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  //private final DriveSubsystem driveSubsystem;
  private final DriveSubsystem driveSubsystem;

  //-------- COMMANDS --------\\

  private final DriveCommand driveCommand;

  //private final DriveCommand driveCommand;
  //  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  //-------- CONSTRUCTOR ---------\\

  public RobotContainer() {

    driverJoystick = new Joystick(Constants.DRIVER_CONTROLLER_ID);
    coDriverJoystick = new Joystick(Constants.CODRIVER_CONTROLLER_ID);

    driveSubsystem = new DriveSubsystem();


    driveCommand = new DriveCommand(driveSubsystem);
    // Configure the button bindings

    beginRunCommands();
    configureButtonBindings();
  }

  //-------- METHODS --------\\

  private void beginRunCommands() {
      
    CommandScheduler.getInstance().setDefaultCommand(driveSubsystem, new RunCommand(() -> {
      driveSubsystem.run(driverJoystick.getRawAxis(Constants.AXIS_RIGHT_X), driverJoystick.getRawAxis(Constants.AXIS_LEFT_Y));
    }
    , driveSubsystem));

  }
  //Refer to https://docs.google.com/document/d/1V3UP8MBADUFDnNZTIlefdBUDyUZ-zYfYCRs3ykREHns/edit?usp=sharing
  private void configureButtonBindings() {

  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;//m_autoCommand;
  }
}
