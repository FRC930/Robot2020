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

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class VisionTracking {

  //-------- CONSTANTS --------\\

   //--Ports

   //--Deadbands
   private final double TRIGGER_PRESSED_THRESHOLD = 0.4;

  //-------- DECLARATIONS --------\\


  //-------- SUBSYSTEMS --------\\

    //  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  //-------- COMMANDS --------\\

    //  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  //-------- CONSTRUCTOR ---------\\

  public VisionTracking() {

  }
  //-------- METHODS --------\\
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
