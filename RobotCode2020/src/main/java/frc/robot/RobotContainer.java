/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj.Joystick;

import frc.robot.commands.DriveCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.AutonomousCommand;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Drive;

import java.util.List;
import frc.robot.commands.Autonomous;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final Drive m_drive;
  private final DriveCommand driveCommand;
  private final Autonomous autoCommand;
  private Joystick driverJoystick;
  private Joystick coDriverJoystick;
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    m_drive = new Drive();
    autoCommand = new Autonomous(m_drive);
    driverJoystick = new Joystick(Constants.DRIVER_CONTROLLER_ID);
    coDriverJoystick = new Joystick(Constants.CODRIVER_CONTROLLER_ID);
    driveCommand = new DriveCommand(m_drive,driverJoystick);
    // Configure the button bindings
    configureButtonBindings();
  }

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
      
    //CommandScheduler.getInstance().setDefaultCommand(m_drive, new RunCommand(() -> {
    //   m_drive.run(driverJoystick.getRawAxis(Constants.AXIS_RIGHT_X), driverJoystick.getRawAxis(Constants.AXIS_LEFT_Y));
    //}
    // , m_drive));
    CommandScheduler.getInstance().setDefaultCommand(m_drive,driveCommand);
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
