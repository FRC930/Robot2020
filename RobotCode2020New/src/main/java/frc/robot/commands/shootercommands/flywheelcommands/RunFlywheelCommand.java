/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.shootercommands.flywheelcommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.utilities.ShuffleboardUtility;

//-------- COMMANDBASE CLASS --------\\

public class RunFlywheelCommand extends CommandBase {

  // -------- DECLARATIONS --------\\

  private FlywheelSubsystem m_FlywheelSubsystem;
  private ShuffleboardUtility shuffleboardUtility;
  private double m_Speed;

  // -------- CONSTRUCTOR --------\\

  public RunFlywheelCommand(FlywheelSubsystem flywheelSubsystem, double speed) {
    m_FlywheelSubsystem = flywheelSubsystem;
    m_Speed = speed;
    shuffleboardUtility = ShuffleboardUtility.getInstance();
    addRequirements(m_FlywheelSubsystem);
  }

  // --------- COMMANDBASE METHODS ----------\\

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_FlywheelSubsystem.setSpeed(m_Speed);  //Run the flywheel at a certain speed
    shuffleboardUtility = ShuffleboardUtility.getInstance();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // Sets the flywheel state into ShuffleboardUtility
    shuffleboardUtility.setShootIndicator(m_FlywheelSubsystem.isFlywheelActive());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

} // end of command class RunFlywheelCommand
