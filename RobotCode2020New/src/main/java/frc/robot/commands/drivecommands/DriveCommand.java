/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivecommands;

import frc.robot.Constants;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.GyroSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.geometry.Rotation2d;


public class DriveCommand extends CommandBase {

  //-------- CONSTANTS --------\\

  //-------- DECLARATIONS --------\\
  private final DriveSubsystem driveSubsystem;
  private final Joystick m_driveStick;
  private final GyroSubsystem gyroSubsystem;
  private int driverTurninAxis;
  private int driverThrottleAxis;

  //-------- CONSTRUCTOR --------\\

  public DriveCommand(DriveSubsystem dSubsystem, Joystick driverStick, int turningAxis, int throttleAxis, GyroSubsystem gSubsystem) {
    driveSubsystem = dSubsystem;
    m_driveStick = driverStick;
    gyroSubsystem = gSubsystem;
    setTurningAndThrottleAxis(turningAxis, throttleAxis);
    addRequirements(dSubsystem);  // Use addRequirements() here to declare subsystem dependencies.
  } 
  //-------- COMMANDBASE METHODS --------\\
  @Override   // Called when the command is initially scheduled.
  public void initialize() {
  }

  @Override   // Called every time the scheduler runs while the command is scheduled.
  public void execute() {  
    //System.out.println("COMMAND");
    run(m_driveStick.getRawAxis(driverTurninAxis), m_driveStick.getRawAxis(driverThrottleAxis));
    gyroSubsystem.driveOdometry.update((Rotation2d.fromDegrees(gyroSubsystem.getHeading())), driveSubsystem.getRPMLeft(), driveSubsystem.getRPMRight());
  }
  @Override   // Called once the command ends or is interrupted.
  public void end(boolean interrupted) {
  }


  /*
  //We comment out isFinished so we always have control of the drive and it gives contorl up easier
  @Override   // Returns true when the command should end.
  public boolean isFinished() {
    return false;
  }

  */

  //-------- METHODS --------\\

  public void setTurningAndThrottleAxis(int turningAxis, int throttleAxis){
    driverTurninAxis = turningAxis;
    driverThrottleAxis = throttleAxis;
  }
  private void run(double stickX, double stickY) {
        
    // Cubing values to create smoother function

    stickX = -Math.pow(stickX, 3);
    stickY = Math.pow(stickY, 3);

    // Multiplies for smoothing turning
    stickX *= Constants.DRIVE_TURNING_MULTIPLIER;

    // Dead band after the cube sets to zero if under the dead band
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
