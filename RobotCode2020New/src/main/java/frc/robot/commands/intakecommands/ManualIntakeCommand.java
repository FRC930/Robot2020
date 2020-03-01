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
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Constants;

//-------- COMMAND CLASS --------\\

public class ManualIntakeCommand extends CommandBase {

  //-------- CONSTANTS --------\\

  private static final Logger logger = Logger.getLogger(DeployIntakeCommand.class.getName());

  //-------- DECLARATIONS --------\\

  private IntakeMotorSubsystem intakeMotorSubsystem;
  private ShuffleboardUtility shuffleboardUtility;

  Joystick stick;
  int axis;
  double stickY;
  double speed;

  //-------- CONSTRUCTOR --------\\

  public ManualIntakeCommand(IntakeMotorSubsystem iMotorSubsystem, Joystick stick, int axis) {
    intakeMotorSubsystem = iMotorSubsystem;
    this.stick = stick;
    this.axis = axis;
    stickY = 0.0;
    speed = 0;
    addRequirements(iMotorSubsystem);  // Use addRequirements() here to declare subsystem dependencies.
  }

  //-------- COMMANDBASE METHODS --------\\

  @Override   // Called when the command is initially scheduled.
  public void initialize() {
    shuffleboardUtility = ShuffleboardUtility.getInstance();
    logger.log(Constants.LOG_LEVEL_FINE, "Deploying the intake (subsystem)..."); 
  }

  @Override   // Called every time the scheduler runs while the command is scheduled.
  public void execute() {

    stickY = stick.getRawAxis(axis);

    if(Math.abs(stickY) > 0.1) {
        speed = -Math.pow(stick.getRawAxis(axis), 3) * 0.6;;
    } else {
        speed = 0;
    }

    intakeMotorSubsystem.setMotorSpeed(speed);
    // logger.log(Constants.LOG_LEVEL_FINE, "Intake piston state: " + intakePistonSubsystem.getIntakePistonState());
    // shuffleboardUtility.setIntakeIndicator(intakePistonSubsystem.getIntakePistonState());
  } 
  
  @Override   // Returns true when the command should end.
  public boolean isFinished() {
    return true;
  }

} //End of class DeployIntakeCommand
