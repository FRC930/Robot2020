/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.subsystems;

import frc.robot.Constants;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//-------- SUBSYSTEM CLASS --------\\

public class KickerSubsystem extends SubsystemBase {
  
    //-------- CONSTANTS --------\\

    private static final Logger logger = Logger.getLogger(KickerSubsystem.class.getName());

    //-------- DECLARATIONS --------\\

    // VictorSPX is a motor controller that makes the conveor belt  Take's the power cell up to the shooter
    private VictorSPX kickerMotor;  

    //-------- CONSTRUCTOR --------\\

    public KickerSubsystem() {
        kickerMotor = new VictorSPX(Constants.KICKER_ID);  
        kickerMotor.setInverted(true);
    }   
    
    //-------- METHODS --------\\

    //sets the speed to the tower motor 
    public void setSpeed(double speed) {
        logger.entering(this.getClass().getName(), "setSpeed()");
        logger.log(Constants.LOG_LEVEL_INFO, "motorSpeed: " + speed);

        kickerMotor.set(ControlMode.PercentOutput, speed);

        logger.exiting (this.getClass().getName(), "setSpeed()");
    }

    // returns the speed from the tower motor
    public double getSpeed() { 
        logger.entering(this.getClass().getName(), "getSpeed()");
        logger.log(Constants.LOG_LEVEL_INFO, "motorSpeed: " + kickerMotor.getMotorOutputPercent());
        logger.exiting (this.getClass().getName(), "getSpeed()");
        
        return kickerMotor.getMotorOutputPercent();
    }

    //stops the motor
    public void stopMotor() {
        kickerMotor.set(ControlMode.PercentOutput, 0.0);
    }

} //end of class KickerSubsystem