/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.intakecommands;

import java.util.logging.Logger;

import frc.robot.subsystems.IntakeMotorSubsystem;
import frc.robot.subsystems.IntakePistonSubsystem;
import frc.robot.utilities.ShuffleboardUtility;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Constants;

//-------- COMMAND CLASS --------\\

public class ReturnIntakeCommand extends CommandBase {

  //-------- CONSTANTS --------\\

  private static final Logger logger = Logger.getLogger(ReturnIntakeCommand.class.getName());

  //-------- DECLARATIONS --------\\

  private IntakePistonSubsystem intakePistonsSubsystem;
  private IntakeMotorSubsystem intakeMotorsSubsystem;
  private ShuffleboardUtility shuffleboardUtility;

  //-------- CONSTRUCTOR --------\\

  public ReturnIntakeCommand(IntakePistonSubsystem iPistonsSubsystem, IntakeMotorSubsystem iMotorSubsystem) {
    intakePistonsSubsystem = iPistonsSubsystem;
    intakeMotorsSubsystem = iMotorSubsystem;

    addRequirements(iPistonsSubsystem, iMotorSubsystem);  // Use addRequirements() here to declare subsystem dependencies.
  }

  //-------- COMMANDBASE METHODS --------\\

  @Override   // Called when the command is initially scheduled.
  public void initialize() {
    intakePistonsSubsystem.setIntakePistonState(Constants.INTAKE_PISTONS_UP);
    intakeMotorsSubsystem.setMotorSpeed(0.0);
    shuffleboardUtility = ShuffleboardUtility.getInstance();
    logger.log(Constants.LOG_LEVEL_FINE, "Returning the intake (command)...");
  }

  @Override   // Called every time the scheduler runs while the command is scheduled.
  public void execute() {
    logger.log(Constants.LOG_LEVEL_FINE, "Intake piston state: " + intakePistonsSubsystem.getIntakePistonState());
    shuffleboardUtility.putIntakeIndicator(intakePistonsSubsystem.getIntakePistonState());
  }

  @Override   // Returns true when the command should end.
  public boolean isFinished() {
    return true;
  }
  
} //End of class ReturnIntakeCommand
