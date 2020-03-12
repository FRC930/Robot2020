/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.intakecommands.intakemotorcommands;

import java.util.logging.Logger;

import frc.robot.subsystems.IntakeMotorSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Constants;

//-------- COMMAND CLASS --------\\

public class StopIntakeMotorsCommand extends CommandBase {

  //-------- CONSTANTS --------\\

  private static final Logger logger = Logger.getLogger(StopIntakeMotorsCommand.class.getName());

  //-------- DECLARATIONS --------\\

  private final IntakeMotorSubsystem intakeMotors;

  //-------- CONSTRUCTOR --------\\

  public StopIntakeMotorsCommand(IntakeMotorSubsystem iMotors) {
    intakeMotors = iMotors;
    logger.log(Constants.LOG_LEVEL_FINE, "Initializing the StopIntakeMotorsCommand...");
    addRequirements(iMotors);  // Use addRequirements() here to declare subsystem dependencies.
  }

  //-------- COMMANDBASE METHODS --------\\

  @Override   // Called when the command is initially scheduled.
  public void initialize() {
     intakeMotors.setMotorSpeed(0.0);
     logger.log(Constants.LOG_LEVEL_FINE, "Stopping the intake wheels (command)..."); 
  }

  @Override   // Returns true when the command should end.
  public boolean isFinished() {
    return true;
  }

} //End of class StopIntakeMotorsCommand
