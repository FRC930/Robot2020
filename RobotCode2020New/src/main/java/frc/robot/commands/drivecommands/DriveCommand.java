
//-------- IMPORTS --------\\

package frc.robot.commands.drivecommands;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Joystick;

//-------- COMMAND CLASS --------\\

public class DriveCommand extends CommandBase {

  //-------- CONSTANTS --------\\

  private final double DRIVE_DEADBAND_JOYSTICK = 0.000125;
  private final double DRIVE_TURNING_MULTIPLIER = 0.73;

  //-------- DECLARATIONS --------\\

  private final DriveSubsystem driveSubsystem;
  private final Joystick driverController;

  private int driverTurningAxis;
  private int driverThrottleAxis;

  //-------- CONSTRUCTOR --------\\

  public DriveCommand(DriveSubsystem dSubsystem, Joystick driverStick, int turningAxis, int throttleAxis) {
    driveSubsystem = dSubsystem;
    driverController = driverStick;

    setTurningAndThrottleAxis(turningAxis, throttleAxis);

    addRequirements(dSubsystem);  // Use addRequirements() here to declare subsystem dependencies.
  } // end of constructor DriveCommand()

  //-------- COMMANDBASE METHODS --------\\

  @Override   // Called when the command is initially scheduled.
  public void initialize() {
  }

  @Override   // Called every time the scheduler runs while the command is scheduled.
  public void execute() {  
    run(driverController.getRawAxis(driverTurningAxis), driverController.getRawAxis(driverThrottleAxis));
  }

  @Override   // Called once the command ends or is interrupted.
  public void end(boolean interrupted) {
  }

  //-------- METHODS --------\\

  public void setTurningAndThrottleAxis(int turningAxis, int throttleAxis) {
    driverTurningAxis = turningAxis;
    driverThrottleAxis = throttleAxis;
  }

  private void run(double stickX, double stickY) {
        
    // Cubing values to create smoother function
    stickX = -Math.pow(stickX, 3);
    stickY = Math.pow(stickY, 3);

    // Multiplies for smoothing turning
    stickX *= DRIVE_TURNING_MULTIPLIER;

    // Dead band after the cube sets to zero if under the dead band
    if (Math.abs(stickX) < DRIVE_DEADBAND_JOYSTICK) {
      stickX = 0;
    }
    if (Math.abs(stickY) < DRIVE_DEADBAND_JOYSTICK) {
      stickY = 0;
    }
    // Arcade drive
    driveSubsystem.runAt((stickY + stickX), -(stickY - stickX));
  } //End of method run()

} //End of class DriveCommand
