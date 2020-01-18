/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.ColorShim;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;

/// TODO: make this a command structure
/// TODO: use CMYK colors instead of RGB

public class ColorSensor extends SubsystemBase {

  //-------- CONSTANTS --------\\

   //--Ports

  //-------- DECLARATIONS --------\\

  // Creates the port value for the color sensor on the roboRIO
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  // Creates color objects with their color values (RGB from 0 to 1 / 255)
  private final ColorShim kBlueTarget = new ColorShim(0.143, 0.427, 0.429);
  private final ColorShim kGreenTarget = new ColorShim(0.197, 0.561, 0.240);
  private final ColorShim kRedTarget = new ColorShim(0.561, 0.232, 0.114);
  private final ColorShim k930RedTarget = new ColorShim(0.450, 0.232, 0.114);
  private final ColorShim kYellowTarget = new ColorShim(0.361, 0.524, 0.113);

  // Creates a ColorMatch object that finds and manages distance/difference between colors
  private final ColorMatch colorMatch = new ColorMatch();
  
  // Creates the I2C color sensor
  private ColorSensorV3 Sensor = new ColorSensorV3(i2cPort);

  // Creates a color object
  private Color color;

  //-------- CONSTRUCTOR --------\\

  // Adds colors to a list of colors in the ColorMatch class
  public ColorSensor(){
    colorMatch.addColorMatch(kYellowTarget);
    colorMatch.addColorMatch(kBlueTarget);
    colorMatch.addColorMatch(kGreenTarget);
    colorMatch.addColorMatch(kRedTarget);
    colorMatch.addColorMatch(k930RedTarget);
  }
  //-------- METHODS --------\\    

  // Checks values from color sensor and matches it to the closest color
  private String colorResult(Color c){
    // Matches the color sensor RGB values to the closest true color inside of the ColorMatch class
    ColorMatchResult match = colorMatch.matchClosestColor(c);

    // Checks the values and matches it to one of the colors below
    if (match.color == kBlueTarget) {
      return "Blue";
    } else if (match.color == kRedTarget) {
      return "Red";
    } else if (match.color == k930RedTarget) {
      return "930Red";
    } else if (match.color == kGreenTarget) {
      return "Green";
    } else if (match.color == kYellowTarget) {
      return "Yellow";
    } else {
      return "Unknown";
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
      // Returns the color values from the sensors
      color = Sensor.getColor();

      // Outputs the color returned from the sensor
      System.out.println("matching color:" + colorResult(color));
    }
  }