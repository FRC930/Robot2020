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
import frc.robot.utilities.ShuffleboardUtility;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Constants;

//-------- COMMAND CLASS --------\\

public class RunIntakeMotorsCommand extends CommandBase {

  //-------- CONSTANTS --------\\

  private final Logger logger = Logger.getLogger(getClass().toString());

  //-------- DECLARATIONS --------\\

  private final IntakeMotorSubsystem intakeMotors;
  private ShuffleboardUtility shuffleboardUtility = ShuffleboardUtility.getInstance();

  //-------- CONSTRUCTOR --------\\

  public RunIntakeMotorsCommand(IntakeMotorSubsystem iMotors) {
    intakeMotors = iMotors;

    addRequirements(iMotors);  // Use addRequirements() here to declare subsystem dependencies.
  }

  //-------- COMMANDBASE METHODS --------\\

  @Override   // Called when the command is initially scheduled.
  public void initialize() {
     intakeMotors.setMotorSpeed(Constants.INTAKE_SPEED);

     logger.log(Constants.LOG_LEVEL_FINE, "Running the intake wheels (command)..."); 
  }
  
  @Override   // Returns true when the command should end.
  public boolean isFinished() {
    return true;
  }

} //End of class RunIntakeMotorsCommand
