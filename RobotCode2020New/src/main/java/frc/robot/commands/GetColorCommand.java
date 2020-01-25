/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.logging.*;
import frc.robot.Constants;
import frc.robot.subsystems.ColorSensor2;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.ColorShim;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class GetColorCommand extends CommandBase {

  //-------- CONSTANTS --------\\

  // Sets the values of each color to its corresponding values
  private final Color kBlueTarget = new ColorShim(0.143, 0.427, 0.429);
  private final Color kGreenTarget = new ColorShim(0.197, 0.561, 0.240);
  private final Color kRedTarget = new ColorShim(0.561, 0.232, 0.114);
  private final Color kYellowTarget = new ColorShim(0.361, 0.524, 0.113);
  // Creates an instance of the logger class
  private static final Logger logger = Logger.getLogger(GetColorCommand.class.getName());

  //-------- DECLARATIONS --------\\
  
  // Creates a Color object
  private Color color;
  // Counts how many times individual colors have passed
  private int colorCounter;
  // Creates an instance of the ColorSensor2 subsystem
  private final ColorSensor2 m_subsystem;
  // Tracks the last color seen by the color sensor
  private String lastColor;
  // Creates an object that adds colors for us to scan
  ColorMatch colorMatch = new ColorMatch();
  // Creates an object that compares the color sensor values to the ColorMatch values
  ColorMatchResult match;
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public GetColorCommand(ColorSensor2 subsystem) {
    m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    logger.entering(getClass().getName(), "initialize");
    // Sets the importance of the logger from the Constants class
    logger.setLevel(Constants.LOG_LEVEL);
    colorCounter = 0;
    lastColor = "";
    colorMatch.addColorMatch(kYellowTarget);
    colorMatch.addColorMatch(kBlueTarget);
    colorMatch.addColorMatch(kGreenTarget);
    colorMatch.addColorMatch(kRedTarget);
    logger.exiting(getClass().getName(), "initialize");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    logger.entering(getClass().getName(), "execute");
    // This method will be called once per scheduler run

      // Returns the color values from the sensors
      color = m_subsystem.getSensorColor();
      // Sets the name of the color to the current color being seen
      String currentColor = getNearestColor(color);
      // When red is being seen, make sure that the next color doesn't output yellow
      if (lastColor.equals("Red") && currentColor.equals("Yellow")){
        currentColor = "Green";
      }
      // When the previous color is not equal to the current color, output the new/current color
      if (!currentColor.equals(lastColor)){
          logger.log(Level.INFO, "Matching color:" + currentColor);
          lastColor = currentColor;
          // Counts how many times the color has changed
          colorCounter++;
      }
    logger.exiting(getClass().getName(), "execute");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Logging stuff
    logger.entering(getClass().getName(), "end");
    logger.exiting(getClass().getName(), "end");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    logger.entering(getClass().getName(), "isFinished");
    logger.exiting(getClass().getName(), "isFinished");
    // Stops the code when the color has changed 24 times
    //return colorCounter >= 24;
    return false;
  }

  private String getNearestColor(Color c) {
    logger.entering(getClass().getName(), "getNearestColor");
    // Matches the color sensor RGB values to the closest true color inside of the ColorMatch class
    // Creates a ColorMatch object that finds and manages distance/difference between colors
    match = colorMatch.matchClosestColor(c);
    String colorOutput;
    // Checks the values and matches it to one of the colors below
    if (match.color == kBlueTarget) {
      colorOutput = "Blue";
    } else if (match.color == kRedTarget) {
      colorOutput = "Red";
    } else if (match.color == kGreenTarget) {
      colorOutput = "Green";
    } else if (match.color == kYellowTarget) {
      colorOutput = "Yellow";
    } else {
      colorOutput = "Unknown";
    }
    logger.exiting(getClass().getName(), "getNearestColor");
    // Returns the name of the color being seen by the sensor
    return colorOutput;
  }
}