/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.shootercommands.pistoncommands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.FlywheelPistonSubsystem;
import frc.robot.subsystems.FlywheelPistonSubsystem.SolenoidValues;

public class ExtendFlywheelPistonCommand extends CommandBase {

  private FlywheelPistonSubsystem flywheelAngleSubsystem;

  public ExtendFlywheelPistonCommand(FlywheelPistonSubsystem flywheelAngleSubsystem) {
    this.flywheelAngleSubsystem = flywheelAngleSubsystem;
    addRequirements(flywheelAngleSubsystem);
  }
    // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    flywheelAngleSubsystem.set(SolenoidValues.EXTEND);
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

} // end of class ExtendFlywheelPistonCommand