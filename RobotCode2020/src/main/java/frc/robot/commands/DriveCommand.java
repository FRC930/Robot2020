/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Constants;

import frc.robot.subsystems.Drive;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Joystick;


public class DriveCommand extends CommandBase {

  //-------- CONSTANTS --------\\

  //-------- DECLARATIONS --------\\

  private final Drive driveSubsystem;
  private final Joystick m_driveStick;
  private double prevX;
  private double prevY;
  //-------- CONSTRUCTOR --------\\

  public DriveCommand(Drive dSubsystem,Joystick driverStick) {
    driveSubsystem = dSubsystem;
    m_driveStick = driverStick;
    addRequirements(dSubsystem);  // Use addRequirements() here to declare subsystem dependencies.
  } 

  //-------- COMMANDBASE METHODS --------\\

  
  @Override   // Called when the command is initially scheduled.
  public void initialize() {
    prevX = 0.0;
    prevY = 0.0;
  }

  @Override   // Called every time the scheduler runs while the command is scheduled.
  public void execute() {  

    run(m_driveStick.getRawAxis(Constants.AXIS_RIGHT_X), m_driveStick.getRawAxis(Constants.AXIS_LEFT_Y));
  }

  
  @Override   // Called once the command ends or is interrupted.
  public void end(boolean interrupted) {
  }
  //We comment out isFinished so we always have control of the drive and it gives contorl up easier
  
  @Override   // Returns true when the command should end.
  public boolean isFinished() {
    return false;
  }
  
  

  //-------- METHODS --------\\
  private double inter(double v1,double v2, double t){
    return(1-t) * v1 + t * v2;
  }
  private void run(double stickX, double stickY) {
    System.out.println("RUN");
    // Cubing values to create smoother function
    //stickX = -Math.pow(stickX, 3);
    //stickY = Math.pow(stickY, 3);
    
    stickX = inter(prevX, stickX, 0.1);
    stickY = inter(prevY, stickY, 0.1);
    stickX *= Constants.DRIVE_TURNING_MULTIPLIER;
    // Joystick deadband
    if (Math.abs(stickX) < Constants.DRIVE_DEADBAND_JOYSTICK) {
      System.out.println("Dead band");
      stickX = 0;
    }
    if (Math.abs(stickY) < Constants.DRIVE_DEADBAND_JOYSTICK) {
      System.out.println("Dead band");
      stickY = 0;
    }

    // Arcade drive
    driveSubsystem.runAt((stickY + stickX), -(stickY - stickX));
    prevX = stickX;
    prevY = stickY;

  } //End of method run()

} //End of class DriveCommand