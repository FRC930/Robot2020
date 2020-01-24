/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.logging.*;
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
  private final Color kBlueTarget = new ColorShim(0.143, 0.427, 0.429);
  private final Color kGreenTarget = new ColorShim(0.197, 0.561, 0.240);
  private final Color kRedTarget = new ColorShim(0.561, 0.232, 0.114);
  private final Color kYellowTarget = new ColorShim(0.361, 0.524, 0.113);
  private static final Logger logger = Logger.getLogger(GetColorCommand.class.getName());

  //-------- DECLARATIONS --------\\
  // Sets 
  private int frame;
  private Color color;
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ColorSensor2 m_subsystem;
  private String lastColor = "";
  ColorMatch colorMatch = new ColorMatch();
  ColorMatchResult match;


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
    frame = 0;
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
      String colorString = getNearestColor(color);
      // Outputs the color returned from the sensor
      if (!colorString.equals(lastColor)){
        logger.log(Level.INFO, "Matching color:" + colorString);
        lastColor = colorString;
      }
      //System.out.println("matching color:" + getNearestColor(color));
      frame++;
    logger.exiting(getClass().getName(), "execute");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    logger.entering(getClass().getName(), "end");
    logger.exiting(getClass().getName(), "end");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    logger.entering(getClass().getName(), "isFinished");
    logger.exiting(getClass().getName(), "isFinished");
    return frame >= 1000;
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
    return colorOutput;
  }
}