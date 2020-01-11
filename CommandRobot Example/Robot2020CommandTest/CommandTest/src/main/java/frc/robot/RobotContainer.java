/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;

import frc.robot.commands.*;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj2.command.Command;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  
  //Subsystems
  private final HatchIntakeSubsystem hatchIntakeSubsystem;
  private final ExampleSubsystem m_exampleSubsystem;

  //Commands
  private final GrabHatchCommand grabHatch;
  private final DropHatchCommand dropHatch;
  private final ExampleCommand m_autoCommand;

  private Joystick driver;
  private JoystickButton ayyButton;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings

    hatchIntakeSubsystem = new HatchIntakeSubsystem();
    m_exampleSubsystem = new ExampleSubsystem();

    grabHatch = new GrabHatchCommand(hatchIntakeSubsystem);
    dropHatch = new DropHatchCommand(hatchIntakeSubsystem);
    m_autoCommand = new ExampleCommand(m_exampleSubsystem);

    driver = new Joystick(0);
    ayyButton = new JoystickButton(driver, 1);

    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    ayyButton.whenPressed(grabHatch).whenReleased(dropHatch);

    //ayyButton.toggleWhenPressed(grabHatch);
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
