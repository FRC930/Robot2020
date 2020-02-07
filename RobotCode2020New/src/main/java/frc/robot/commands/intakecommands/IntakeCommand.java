/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intakecommands;

import frc.robot.Constants;

import java.util.logging.Level;
import java.util.logging.Logger;

import frc.robot.subsystems.IntakeMotorSubsystem;
import frc.robot.subsystems.IntakePistonSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Joystick;
import static frc.robot.subsystems.IntakePistonSubsystem.INTAKE_PISTONS_DOWN;


public class IntakeCommand extends CommandBase {
  
  private final Logger logger = Logger.getLogger (IntakeCommand.class.getName());

  //-------- CONSTANTS --------\\

  //-------- DECLARATIONS --------\\

  private final IntakePistonSubsystem intakePistons;
  private final IntakeMotorSubsystem intakeMotors;

  //-------- CONSTRUCTOR --------\\

  public IntakeCommand(IntakePistonSubsystem iPistons, IntakeMotorSubsystem iMotors) {
    intakePistons = iPistons;
    intakeMotors = iMotors;
    logger.log(Level.FINE,"starting intake command");


    addRequirements(iPistons, iMotors);  // Use addRequirements() here to declare subsystem dependencies.
  }

  //-------- COMMANDBASE METHODS --------\\

  
  @Override   // Called when the command is initially scheduled.
  public void initialize() {
     //System.out.println("intakeCommandExecute" + coDriver.getRawAxis(3));TODO: init
     intakePistons.setIntakePiston(INTAKE_PISTONS_DOWN);
     intakeMotors.setMotorSpeed(0.8);
     logger.log(Level.FINE,"starting intake"); 
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
