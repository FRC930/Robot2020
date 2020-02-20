/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import java.util.logging.*;

import frc.robot.Constants;

//-------- SUBSYSTEM CLASS --------\\

public class ColorWheelSpinnerSubsystem extends SubsystemBase {

    //-------- CONSTANTS --------\\

    // Creates an instance of the logger class
    // private final Logger logger = // logger.getLogger(ColorWheelSpinnerSubsystem.class.getName());

    //-------- DECLARATIONS --------\\

    private VictorSPX wheelSpinnerMotor;

    //-------- CONSTRUCTOR --------\\

    public ColorWheelSpinnerSubsystem(){
        wheelSpinnerMotor = new VictorSPX(Constants.COLOR_WHEEL_ID);
    }

    //-------- METHODS --------\\

    // Returns the speed of the motor controller
    public double getMotorSpeed(){
        // logger.entering(this.getClass().getName(), "getMotorSpeed");
        // logger.exiting(this.getClass().getName(), "getMotorSpeed");
    
        return wheelSpinnerMotor.getMotorOutputPercent();
    }

    // Sets the motor speed to a value between -1.0 and 1.0
    public void setMotorSpeed(double speed){
        // logger.entering(this.getClass().getName(), "setMotorSpeed");
    
        wheelSpinnerMotor.set(ControlMode.PercentOutput, speed);
  
        // Logs the speed from the motor controller
        // logger.log(Level.FINE, "Motor Speed: " + getMotorSpeed());
        // logger.exiting(this.getClass().getName(), "setMotorSpeed");
    }
    
} // End of class ColorWheelSpinnerSubsystem