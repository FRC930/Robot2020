/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Constants;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionTracking;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Joystick;


public class RotateCommand extends CommandBase {

  //-------- CONSTANTS --------\\

  //-------- DECLARATIONS --------\\

  private final DriveSubsystem driveSubsystem;
  private final Joystick m_driveStick;

  //-------- CONSTRUCTOR --------\\

  public RotateCommand(VisionTracking dSubsystem) {
    
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
  private double lerp(double v0,double v1, double t){
    return (1 - t) * v0 + t * v1;
  }

  private void run(double stickX, double stickY) {

    //-----------------------------------------------------------------CUBING
    

    // Cubing values to create smoother function
  
    stickX = -Math.pow(stickX, 3);
    stickY = Math.pow(stickY, 3);
    stickX *= Constants.DRIVE_TURNING_MULTIPLIER;

    if (Math.abs(stickX) < Constants.DRIVE_DEADBAND_JOYSTICK) {
      stickX = 0;
    }

    if (Math.abs(stickY) < Constants.DRIVE_DEADBAND_JOYSTICK) {
      stickY = 0;
    }

    // Arcade drive
    driveSubsystem.runAt((stickY + stickX), -(stickY - stickX));
    
    //-----------------------------------------------------------------CUBING

    /*
    // Joystick deadband
    if (Math.abs(stickX) < Constants.DRIVE_DEADBAND_JOYSTICK) {
      //System.out.println("Dead band");
      stickX = 0;
    }
    if (Math.abs(stickY) < Constants.DRIVE_DEADBAND_JOYSTICK) {
     // System.out.println("Dead band");
      stickY = 0;
    }
    
    //stickX = -lerp(prevX, stickX, 1);
    stickX = -Math.pow(stickX, 3);
    stickX *= Constants.DRIVE_TURNING_MULTIPLIER;
    stickY = lerp(prevY, stickY, 0.07);
    prevX = stickX;
    prevY = stickY;
    driveSubsystem.runAt((stickY + stickX), -(stickY - stickX));
    */
    
    //stickX *= Constants.DRIVE_TURNING_MULTIPLIER;
    
    /*
    if (Math.abs(stickX) < Constants.DRIVE_DEADBAND_JOYSTICK) {
      //System.out.println("Dead band");
      stickX = 0;
    }
    if (Math.abs(stickY) < Constants.DRIVE_DEADBAND_JOYSTICK) {
     // System.out.println("Dead band");
      stickY = 0;
    }
    */
     
    


  } //End of method run()

} //End of class RotateCommand