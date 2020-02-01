/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intakecommands;

import frc.robot.Constants;

import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class IntakeCommand extends CommandBase {

  //-------- CONSTANTS --------\\

  //-------- DECLARATIONS --------\\

  private final IntakeSubsystem intakeSubsystem;

  //-------- CONSTRUCTOR --------\\

  public IntakeCommand(IntakeSubsystem iSubsystem) {
    intakeSubsystem = iSubsystem;

    addRequirements(iSubsystem);  // Use addRequirements() here to declare subsystem dependencies.
  }

  //-------- COMMANDBASE METHODS --------\\

  
  @Override   // Called when the command is initially scheduled.
  public void initialize() {
  }

  @Override   // Called every time the scheduler runs while the command is scheduled.
  public void execute() {  
  }

  
  @Override   // Called once the command ends or is interrupted.
  public void end(boolean interrupted) {
  }

  
  @Override   // Returns true when the command should end.
  public boolean isFinished() {
    return true;
  }
  

  //-------- METHODS --------\\

} //End of class IntakeCommand
