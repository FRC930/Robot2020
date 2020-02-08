/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intakecommands;

import java.util.logging.Level;
import java.util.logging.Logger;

import frc.robot.subsystems.IntakeMotorSubsystem;
import frc.robot.subsystems.IntakePistonSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.subsystems.IntakePistonSubsystem.*;


public class IntakeStopCommand extends CommandBase {

  private final Logger logger = Logger.getLogger(IntakeStopCommand.class.getName());
  //-------- CONSTANTS --------\\

  //-------- DECLARATIONS --------\\
  private final IntakePistonSubsystem intakePistonsSubsystem;
  private final IntakeMotorSubsystem intakeMotorsSubsystem;

  //-------- CONSTRUCTOR --------\\

  public IntakeStopCommand (final IntakePistonSubsystem iPistonsSubsystem, final IntakeMotorSubsystem iMotorSubsystem) {
    intakePistonsSubsystem = iPistonsSubsystem;
    intakeMotorsSubsystem = iMotorSubsystem;
    logger.log(Level.FINE,"starting intake stop command");

    addRequirements(iPistonsSubsystem, iMotorSubsystem);  // Use addRequirements() here to declare subsystem dependencies.
  }

  //-------- COMMANDBASE METHODS --------\\

  
  @Override   // Called when the command is initially scheduled.
  public void initialize() {
    intakePistonsSubsystem.setIntakePiston(INTAKE_PISTONS_UP);
    intakeMotorsSubsystem.setMotorSpeed(0.0);
    logger.log(Level.FINE,"stoping intake");
  }

  @Override   // Called every time the scheduler runs while the command is scheduled.
  public void execute() {
       
}
  
  @Override   // Called once the command ends or is interrupted.
  public void end(final boolean interrupted) {
  }

  
  @Override   // Returns true when the command should end.
  public boolean isFinished() {
    return true;
  }
  

  //-------- METHODS --------\\
} //End of class IntakeCommand
