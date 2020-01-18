/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.ColorSensorV3;


public class ColorSensor extends SubsystemBase {
 private ColorSensorV3 ColorSensorData;

  //-------- CONSTANTS --------\\

   //--Ports

  //-------- DECLARATIONS --------\\

  public ColorSensor() {
  
  ColorSensorData = new ColorSensorV3(3);
  }
  //-------- METHODS --------\\
   

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    ColorSensorData.getColor();
    }
  }