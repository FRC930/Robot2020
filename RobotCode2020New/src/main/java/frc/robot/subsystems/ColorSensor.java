/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.util.ColorShim;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
//import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;

public class ColorSensor extends SubsystemBase {

  //-------- CONSTANTS --------\\

   //--Ports

  //-------- DECLARATIONS --------\\

  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  // Color values
  private final ColorShim kBlueTarget = new ColorShim(0.143, 0.427, 0.429);
  private final ColorShim kGreenTarget = new ColorShim(0.197, 0.561, 0.240);
  private final ColorShim kRedTarget = new ColorShim(0.561, 0.232, 0.114);
  private final ColorShim k930RedTarget = new ColorShim(0.450, 0.232, 0.114);
  private final ColorShim kYellowTarget = new ColorShim(0.361, 0.524, 0.113);

  //private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  //private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  //private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  //private final Color k930RedTarget = ColorMatch.makeColor(0.450, 0.232, 0.114);
  //private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  //private final Color kBlueTarget = new Color(0.143, 0.427, 0.429);
  //private final Color kGreenTarget = new Color(new Color8Bit((int) 0.197 * 255, (int) 0.561 * 255, (int) 0.240 * 255));
  //private final Color kRedTarget = new Color(new Color8Bit((int) 0.561 * 255, (int) 0.232 * 255, (int) 0.114 * 255));
  //private final Color k930RedTarget = new Color(new Color8Bit((int) 0.450 * 255, (int) 0.232 * 255, (int) 0.114 * 255));
  //private final Color kYellowTarget = new Color(new Color8Bit((int) 0.361 * 255, (int) 0.524 * 255, (int) 0.113 * 255));

  // ColorMatch object
  private final ColorMatch colorMatch = new ColorMatch();
  
  private ColorSensorV3 Sensor = new ColorSensorV3(i2cPort);

  private Color color;

  //private double redValue;
  //private double greenValue;
  //private double blueValue;

  //-------- CONSTRUCTOR --------\\
  public ColorSensor(){
    colorMatch.addColorMatch(kYellowTarget);
    colorMatch.addColorMatch(kBlueTarget);
    colorMatch.addColorMatch(kGreenTarget);
    colorMatch.addColorMatch(kRedTarget);
    colorMatch.addColorMatch(k930RedTarget);
  }
  //-------- METHODS --------\\    

  // checks values from color sensor and matches it to the closest color
  private String colorResult(Color c){
    // Matches the sensor values to the color closest to them
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

      /*redValue = color.red;
      greenValue = color.green;
      blueValue = color.blue;

      System.out.println("Red: " + redValue * 255);
      System.out.println("Green: " + greenValue * 255);
      System.out.println("Blue: " + blueValue * 255);

      System.out.println("getRed: " + Sensor.getRed() * 255);
      System.out.println("getGreen: " + Sensor.getGreen() * 255);
      System.out.println("getBlue: " + Sensor.getBlue() * 255);

      System.out.println("Red - RED: " + Color.kRed.red);
      System.out.println("Red - GREEN: " + Color.kRed.green);
      System.out.println("red - BLUE: " + Color.kRed.blue);*/

      // Outputs the color returned from the sensor
      System.out.println("matching color:" + colorResult(color));
    }
  }