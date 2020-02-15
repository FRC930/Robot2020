/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.shootercommands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.ShooterPistonSubsystem;
import frc.robot.subsystems.ShooterPistonSubsystem.SolenoidValues;

//-------- COMMAND CLASS --------\\

public class ExtendShooterPistonCommand extends CommandBase {

  //-------- DECLARATIONS --------\\

  private ShooterPistonSubsystem shooterPistonSubsystem;
    
  //-------- CONSTRUCTOR --------\\
  
  public ExtendShooterPistonCommand(ShooterPistonSubsystem m_shooterPistonSubsystem){
    shooterPistonSubsystem = m_shooterPistonSubsystem;
    addRequirements(shooterPistonSubsystem);
  }

  //-------- COMMANDBASE METHODS --------\\

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooterPistonSubsystem.set(SolenoidValues.EXTEND);
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
     return true;
   }

} // end of class ExtendShooterPistonCommand