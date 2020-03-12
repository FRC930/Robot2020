/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.colorwheelcommands.positionalcontrolcommands;

//import frc.robot.utilities.ShuffleboardUtility;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ColorSensorSubsystem;
import java.util.logging.*;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;

public class PositionalControlTrackerCommand extends CommandBase {

    //-------- CONSTANTS --------\\

    // Direction wheel spins
    private final boolean CLOCKWISE = true;
    private final boolean COUNTER_CLOCKWISE = false;

    // Sets the values of each color to its corresponding values
    private final Color kBlueTarget = new Color(0.143, 0.427, 0.429);
    private final Color kGreenTarget = new Color(0.197, 0.561, 0.240);
    private final Color kRedTarget = new Color(0.561, 0.232, 0.114);
    private final Color kYellowTarget = new Color(0.361, 0.524, 0.113);

    // Creates an instance of the logger class
    private static final Logger logger = Logger.getLogger(PositionalControlTrackerCommand.class.getName());

    //-------- DECLARATIONS --------\\

    // Tracks the last color seen by the color sensor
    private String lastColor;
    // Creates an object that adds colors for us to scan
    private ColorMatch colorMatch;
    // The color that the field sensor reads
    private String fieldColor;
    // true is clockwise, false is counter-clockwise
    private boolean direction;
    // Creates an instance of the ColorSensorSubsystem subsystem
    private ColorSensorSubsystem colorSensorSubsystem;

    //private ShuffleboardUtility shuffleboardUtility;
    private String fmsColor;

    //-------- CONSTRUCTOR --------\\

    public PositionalControlTrackerCommand(ColorSensorSubsystem colorSensorSubsystem) {
        colorMatch = new ColorMatch();
        this.colorSensorSubsystem = colorSensorSubsystem;
        addRequirements(colorSensorSubsystem);
    }

    //-------- METHODS --------\\

    @Override
    public void initialize() {
      // Color wheel spins in the direction below
      direction = COUNTER_CLOCKWISE;
      fieldColor = "";
      lastColor = "";
      colorMatch.addColorMatch(kYellowTarget);
      colorMatch.addColorMatch(kBlueTarget);
      colorMatch.addColorMatch(kGreenTarget);
      colorMatch.addColorMatch(kRedTarget);
    }

    /**
     * What the execute is doing is it's matching the color of the wheel received from Shuffleboard to the correct color under the field's sensor.
     * Our robot's color sensor will see one color, but the field's sensor will see another color. For example, when our robot sees the
     * color red, the field sensor will see the color blue. Here is a link to the image of the sensor on the color wheel
     * https://drive.google.com/file/d/1TQU4u39DIl0iIE7XdlLHuZEuskAtRAx3/view
     */
    @Override
    public void execute() {
      logger.entering(PositionalControlTrackerCommand.class.getName(), "execute");
      // Returns the color values from the sensors
      final Color color = colorSensorSubsystem.getSensorColor();
      // The color that we are currently seen
      String nearestColor = getNearestColor(color);
      // The color the field color sensor sees
      fieldColor = "Unknown";
  
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
      }

      // If the robot color sensor sees Red, then the field sensor sees Blue
      if (nearestColor.equals("Red")){
        fieldColor = "Blue";
      }
  
      // If the robot color sensor sees Green, then the field sensor sees Yellow
      else if (nearestColor.equals("Green")){
        fieldColor = "Yellow";
      }
  
      // If the robot color sensor sees Blue, then the field sensor sees Red
      else if (nearestColor.equals("Blue")){
        fieldColor = "Red";
      }
  
      // If the robot color sensor sees Yellow, then the field sensor sees Green
      else if (nearestColor.equals("Yellow")){
        fieldColor = "Green";
      }
      
      // If the robot color sensor doesn't see a color, then output unknown
      else {
        fieldColor = "Unknown";
      }
      logger.exiting(PositionalControlTrackerCommand.class.getName(), "execute");
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      // TODO: Will finish when the shuffleboard color and the field color match
      return (fieldColor == fmsColor);
    }

    public String getNearestColor(Color c) {
      logger.entering(PositionalControlTrackerCommand.class.getName(), "getNearestColor");
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
      logger.exiting(PositionalControlTrackerCommand.class.getName(), "getNearestColor");
      // Returns the name of the color being seen by the sensor
      return colorOutput;
    }
}