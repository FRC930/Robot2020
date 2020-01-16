/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.ColorSensorV3;

public class ColorSensor extends SubsystemBase {

  //-------- CONSTANTS --------\\

   //--Ports

  //-------- DECLARATIONS --------\\

  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  
  private ColorSensorV3 Sensor = new ColorSensorV3(i2cPort);

  private Color color;

  private double redValue;
  private double greenValue;
  private double blueValue;

  //-------- METHODS --------\\
   

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
      color = Sensor.getColor();

      redValue = color.red;
      greenValue = color.green;
      blueValue = color.blue;

      System.out.println("Red: " + redValue);
      System.out.println("Green: " + greenValue);
      System.out.println("Blue: " + blueValue);
    }
  }