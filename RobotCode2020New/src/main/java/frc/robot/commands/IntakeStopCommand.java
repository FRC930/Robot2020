/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Constants;

import frc.robot.subsystems.IntakeMotors;
import frc.robot.subsystems.IntakePistons;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Joystick;


public class IntakeStopCommand extends CommandBase {

  //-------- CONSTANTS --------\\

  //-------- DECLARATIONS --------\\
  private final Joystick coDriver;
  private final IntakePistons intakePistons;
  private final IntakeMotors intakeMotors;

  //-------- CONSTRUCTOR --------\\

  public IntakeStopCommand (IntakePistons iPistons, IntakeMotors iMotors, Joystick iCoDriver) {
    intakePistons = iPistons;
    intakeMotors = iMotors;
    coDriver = iCoDriver;


    addRequirements(iPistons, iMotors);  // Use addRequirements() here to declare subsystem dependencies.
  }

  //-------- COMMANDBASE METHODS --------\\

  
  @Override   // Called when the command is initially scheduled.
  public void initialize() {
  }

  @Override   // Called every time the scheduler runs while the command is scheduled.
  public void execute() {
    System.out.print("command"); 
    if(coDriver.getRawAxis(3) < 0.5){
        intakePistons.setIntakePiston(false);
        intakeMotors.setMotorSpeed(0.0);
    }
    else if (coDriver.getRawAxis(3) > 0.5){
        intakePistons.setIntakePiston(true);
        intakeMotors.setMotorSpeed(0.3);
    }
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
