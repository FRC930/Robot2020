/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
<<<<<<< HEAD
=======
import edu.wpi.first.wpilibj2.command.CommandScheduler;
>>>>>>> e1a890593abd3910dd01b6d89637fa5cc595ecb6
import frc.robot.subsystems.LEDSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.CommandScheduler;


public class RunShooterCommand extends CommandBase {

  private ShooterSubsystem m_ShooterSubsystem;
  private double m_Speed;

  public RunShooterCommand(ShooterSubsystem shooterSubsystem, double speed) 
  {
    m_ShooterSubsystem = shooterSubsystem;
    addRequirements(m_ShooterSubsystem);

    m_Speed = speed;
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
<<<<<<< HEAD
      
=======

>>>>>>> e1a890593abd3910dd01b6d89637fa5cc595ecb6
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_ShooterSubsystem.setSpeed(m_Speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
