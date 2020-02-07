/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.colorwheelcommands.rotationalcontrolcommands;

import java.util.logging.*;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.colorwheelcommands.GetColorCommand;

/**
 * An example command that uses an example subsystem.
 */
public class RotationalControlTrackerCommand extends CommandBase {

  //-------- CONSTANTS --------\\

  //-------- DECLARATIONS --------\\

    // Counts how many times individual colors have passed
  private int colorCounter;
  private GetColorCommand getColorCommand;
  // Creates an instance of the logger class
  private Logger logger = Logger.getLogger(RotationalControlTrackerCommand.class.getName());

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public RotationalControlTrackerCommand() {
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    colorCounter = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    logger.entering(this.getClass().getName(), "execute");
    getColorCommand.rotationalTrackerCounter(colorCounter);
    logger.exiting(this.getClass().getName(), "execute");
  } // End of execute

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    logger.entering(this.getClass().getName(), "isFinished");
    logger.exiting(this.getClass().getName(), "isFinished");
    // Stops the code when the color has changed 24 times (3 rotations)
    return colorCounter > 24;
  }
} // End of class