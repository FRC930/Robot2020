/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.intakecommands.intakepistoncommands;

import java.util.logging.Level;
import java.util.logging.Logger;

import frc.robot.subsystems.IntakePistonSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Constants;

//-------- COMMAND CLASS --------\\

public class ExtendIntakePistonCommand extends CommandBase {

  //-------- CONSTANTS --------\\

  // private final Logger logger = // logger.getLogger(ExtendIntakePistonCommand.class.getName());

  //-------- DECLARATIONS --------\\

  private final IntakePistonSubsystem intakePistonsSubsystem;

  //-------- CONSTRUCTOR --------\\

  public ExtendIntakePistonCommand(IntakePistonSubsystem iPistons) {
    intakePistonsSubsystem = iPistons;

    // logger.log(Level.FINE, "Initializing the ExtendIntakePistonCommand...");

    addRequirements(iPistons);  // Use addRequirements() here to declare subsystem dependencies.
  }

  //-------- COMMANDBASE METHODS --------\\

  @Override   // Called when the command is initially scheduled.
  public void initialize() {
    intakePistonsSubsystem.setIntakePistonState(Constants.INTAKE_PISTONS_DOWN);

    // logger.log(Level.FINE, "Extending the intake piston (command)..."); 
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

} //End of class ExtendIntakePistonCommand
