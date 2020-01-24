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
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/// TODO: make this a command structure

public class ColorSensor2 extends SubsystemBase {

  //-------- CONSTANTS --------\\

   //--Ports

  //-------- DECLARATIONS --------\\

  // Creates the port value for the color sensor on the roboRIO
  private final I2C.Port i2cPort = I2C.Port.kOnboard;

  // Creates the I2C color sensor
  private ColorSensorV3 Sensor = new ColorSensorV3(i2cPort);

  //-------- CONSTRUCTOR --------\\

  // Adds colors to a list of colors in the ColorMatch class
  public ColorSensor2(){

    }
  //-------- METHODS --------\\    

  public Color getSensorColor(){
    return Sensor.getColor();
  }

  @Override
  public void periodic() {
  }
}