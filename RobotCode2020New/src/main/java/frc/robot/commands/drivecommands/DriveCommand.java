/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivecommands;

import frc.robot.Constants;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Joystick;

import java.util.logging.*;

public class DriveCommand extends CommandBase {

  //-------- CONSTANTS --------\\

  //-------- DECLARATIONS --------\\

  private final DriveSubsystem driveSubsystem;
  private final Joystick m_driveStick;
  private int driverTurninAxis;
  private int driverThrottleAxis;

  private static final Logger logger = Logger.getLogger(DriveCommand.class.getName());

  //-------- CONSTRUCTOR --------\\

  public DriveCommand(DriveSubsystem dSubsystem, Joystick driverStick, int turningAxis, int throttleAxis) {
    driveSubsystem = dSubsystem;
    m_driveStick = driverStick;
    setTurningAndThrottleAxis(turningAxis, throttleAxis);
    addRequirements(dSubsystem);  // Use addRequirements() here to declare subsystem dependencies.
  } 
  //-------- COMMANDBASE METHODS --------\\
  @Override   // Called when the command is initially scheduled.
  public void initialize() {
  }

  @Override   // Called every time the scheduler runs while the command is scheduled.
  public void execute() {  
    // Sends our two driver axis to our run method see below
    run(m_driveStick.getRawAxis(driverTurninAxis), m_driveStick.getRawAxis(driverThrottleAxis));
  }
  @Override   // Called once the command ends or is interrupted.
  public void end(boolean interrupted) {
  }


  //-------- METHODS --------\\
  
  //sets up our two driver axis for driving
  public void setTurningAndThrottleAxis(int turningAxis, int throttleAxis){
    driverTurninAxis = turningAxis;
    driverThrottleAxis = throttleAxis;
  }

  // this is our run method it contains all our logic for driving 
  private void run(double stickX, double stickY) {
        
    // Cubing values to create smoother function
    //stickX = -Math.pow(stickX, 3);
    stickY = -Math.pow(stickY, 3);

    // Multiplies for smoothing turning
    stickX *= -Constants.DRIVE_TURNING_MULTIPLIER;

    // Dead band after the cube sets to zero if under the dead band
    if (Math.abs(stickX) < Constants.DRIVE_DEADBAND_JOYSTICK) {
      logger.log(Level.INFO, "stickX < DRIVE_DEADBAND_JOYSTICK");
      stickX = 0;
    }
    if (Math.abs(stickY) < Constants.DRIVE_DEADBAND_JOYSTICK) {
      logger.log(Level.INFO, "stickY < DRIVE_DEADBAND_JOYSTICK");
      stickY = 0;
    }
    // sends the values of our sticks (with some math for turning) to our drive train motors
    driveSubsystem.runAt((stickY - stickX), (stickY + stickX));
  } //End of method run()
} //End of class DriveCommand