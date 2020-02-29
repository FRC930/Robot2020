/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.colorwheelcommands.rotationalcontrolcommands;

import java.util.logging.*;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ColorSensorSubsystem;

/**
 * An example command that uses an example subsystem.
 */
public class RotationalControlTrackerCommand extends CommandBase {

  //-------- CONSTANTS --------\\

  // Creates an instance of the ColorSensorSubsystem subsystem
  private final ColorSensorSubsystem colorSensorSubsystem;

  // Direction wheel spins
  private final boolean CLOCKWISE = true;
  private final boolean COUNTER_CLOCKWISE = false;
  // Sets the values of each color to its corresponding values
  private final Color kBlueTarget = new Color(0.143, 0.427, 0.429);
  private final Color kGreenTarget = new Color(0.197, 0.561, 0.240);
  private final Color kRedTarget = new Color(0.561, 0.232, 0.114);
  private final Color kYellowTarget = new Color(0.361, 0.524, 0.113);

  //-------- DECLARATIONS --------\\

  // Tracks the last color seen by the color sensor
  private String lastColor;
  // Creates an object that adds colors for us to scan
  ColorMatch colorMatch = new ColorMatch();
  // Counts how many times individual colors have passed
  private int colorCounter;
  // true is clockwise, false is counter-clockwise
  private boolean direction;
  
  // Creates an instance of the logger class
  private static final Logger logger = Logger.getLogger(RotationalControlTrackerCommand.class.getName());

  public RotationalControlTrackerCommand(ColorSensorSubsystem colorSensorSubsystem) {
    this.colorSensorSubsystem = colorSensorSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(colorSensorSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Color wheel spins in the direction below
    direction = COUNTER_CLOCKWISE;
    lastColor = "";
    colorCounter = 0;
    colorMatch.addColorMatch(kYellowTarget);
    colorMatch.addColorMatch(kBlueTarget);
    colorMatch.addColorMatch(kGreenTarget);
    colorMatch.addColorMatch(kRedTarget);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    logger.entering(RotationalControlTrackerCommand.class.getName(), "execute");
    // Returns the color values from the sensors
    final Color color = colorSensorSubsystem.getSensorColor();
    String nearestColor = getNearestColor(color);

    // When red is being seen and is being spun counter-clockwise, make sure that the next color doesn't output yellow
    if (lastColor.equals("Red") && nearestColor.equals("Yellow") && direction == COUNTER_CLOCKWISE){
      //Overrides what is seen from the sensor
      logger.log(Constants.LOG_LEVEL_FINE, "discarding yellow (counter-clockwise)");
      return;
    }

    // When blue is being seen and is being spun counter-clockwise, make sure that the next color doesn't output green
    if (lastColor.equals("Blue") && nearestColor.equals("Green") && direction == COUNTER_CLOCKWISE){
      //Overrides what is seen from the sensor
      logger.log(Constants.LOG_LEVEL_FINE, "discarding green (counter-clockwise)");
      return;
    }

    // When green is being seen and is being spun clockwise, make sure that the next color doesn't output yellow
    if (lastColor.equals("Green") && nearestColor.equals("Yellow") && direction == CLOCKWISE){
      //Overrides what is seen from the sensor
      logger.log(Constants.LOG_LEVEL_FINE, "discarding yellow (clockwise)");
      return;
    }

    // When yellow is being seen and is being spun clockwise, make sure that the next color doesn't output green
    if (lastColor.equals("Yellow") && nearestColor.equals("Green") && direction == CLOCKWISE){
      //Overrides what is seen from the sensor
      logger.log(Constants.LOG_LEVEL_FINE, "discarding green (clockwise)");
      return;
    }

    // When the previous color is not equal to the current color, output the new/current color
    if (!nearestColor.equals(lastColor)){
      // Logs the current color being seen
      logger.log(Constants.LOG_LEVEL_FINE, "Matching color:" + nearestColor);
      lastColor = nearestColor;
      // Counts how many times the color has changed
      colorCounter++;
    }
    logger.exiting(RotationalControlTrackerCommand.class.getName(), "execute");
  } // End of execute

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    logger.entering(RotationalControlTrackerCommand.class.getName(), "isFinished");
    logger.exiting(RotationalControlTrackerCommand.class.getName(), "isFinished");
    // Stops the code when the color has changed 24 times (3 rotations)
    return colorCounter > 24;
  }

  public String getNearestColor(Color c) {
    logger.entering(RotationalControlTrackerCommand.class.getName(), "getNearestColor");
    // Matches the color sensor RGB values to the closest true color inside of the ColorMatch class
    // Creates a ColorMatch object that finds and manages distance/difference between colors
    ColorMatchResult match = colorMatch.matchClosestColor(c);
    String colorOutput;
    // Checks the values and matches it to one of the colors below
    if (match.color == kBlueTarget) {
      colorOutput = "Blue";
    }
    
    else if (match.color == kRedTarget) {
      colorOutput = "Red";
    }
    
    else if (match.color == kGreenTarget) {
      colorOutput = "Green";
    }
    
    else if (match.color == kYellowTarget) {
      colorOutput = "Yellow";
    }

    else {
      colorOutput = "Unknown";
    }
    logger.exiting(RotationalControlTrackerCommand.class.getName(), "getNearestColor");
    // Returns the name of the color being seen by the sensor
    return colorOutput;
  }
} // End of class