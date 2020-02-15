/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.shootercommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FlywheelSubsystem;

public class RunFlywheelCommand extends CommandBase {

  private FlywheelSubsystem m_FlywheelSubsystem;
  private double m_Speed;

  public RunFlywheelCommand(FlywheelSubsystem flywheelSubsystem, double speed) {
    m_FlywheelSubsystem = flywheelSubsystem;
    addRequirements(m_FlywheelSubsystem);
    m_Speed = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //Run the flywheel at a certain speed
    m_FlywheelSubsystem.setSpeed(m_Speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isAtSpeed();
  }

  private boolean isAtSpeed() {
    return this.m_FlywheelSubsystem.getSpeed() < (this.m_Speed * 5880) + 50 || this.m_FlywheelSubsystem.getSpeed() > (this.m_Speed * 5880) - 50;
  }

} // end of command class RunFlywheelCommand

