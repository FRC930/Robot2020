/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.ColorSensorV3;

import java.util.logging.*;

//-------- SUBSYSTEM CLASS --------\\

public class ColorSensorSubsystem extends SubsystemBase {

  //-------- CONSTANTS --------\\

  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  // Creates an instance of the logger class
  private final Logger logger = Logger.getLogger(ColorSensorSubsystem.class.getName());

  //-------- DECLARATIONS --------\\

  // Creates the I2C color sensor
  private ColorSensorV3 Sensor;

  //-------- CONSTRUCTOR --------\\

  public ColorSensorSubsystem() {
    Sensor = new ColorSensorV3(i2cPort);
  }
  
  //-------- METHODS --------\\    

  // Returns the color sensor
  public Color getSensorColor(){
    logger.entering(this.getClass().getName(), "getSensorColor");
    logger.exiting(this.getClass().getName(), "getSensorColor");
    return Sensor.getColor();
  }
  
} // End of class