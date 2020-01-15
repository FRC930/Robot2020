/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Constants;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class DriveCommand extends CommandBase {

  //-------- CONSTANTS --------\\

  //-------- DECLARATIONS --------\\

  private final DriveSubsystem driveSubsystem;

  //-------- CONSTRUCTOR --------\\

  public DriveCommand(DriveSubsystem dSubsystem) {
    driveSubsystem = dSubsystem;

    addRequirements(dSubsystem);  // Use addRequirements() here to declare subsystem dependencies.
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

  /*
  @Override   // Returns true when the command should end.
  public boolean isFinished() {
    return false;
  }
  */

  //-------- METHODS --------\\
  private void run(double stickX, double stickY) {
        
    // Cubing values to create smoother function
    stickX = -Math.pow(stickX, 3);
    stickY = Math.pow(stickY, 3);
    stickX *= Constants.DRIVE_TURNING_MULTIPLIER;

    // Joystick deadband
    if (Math.abs(stickX) < Constants.DRIVE_DEADBAND_JOYSTICK) {
      stickX = 0;
    }
    if (Math.abs(stickY) < Constants.DRIVE_DEADBAND_JOYSTICK) {
      stickY = 0;
    }

    // Arcade drive
    driveSubsystem.runAt((stickY + stickX), -(stickY - stickX));

  } //End of method run()

} //End of class DriveCommand
