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

import java.util.logging.Logger;

import frc.robot.Constants;

//-------- SUBSYSTEM CLASS --------\\

/**
 * <h3>ColorWheelSpinnerSubsystem</h3>
 * This subsystem controls the motor that spins the color wheel
 */
public class ColorWheelSpinnerSubsystem extends SubsystemBase {

    // -------- CONSTANTS --------\\

    /**
     * The logger that we will use to output information to the user
     */
    private static final Logger logger = Logger.getLogger(ColorWheelSpinnerSubsystem.class.getName());

    // -------- DECLARATIONS --------\\

    /**
     * The VictorSPX that will control the color wheel
     */
    private VictorSPX wheelSpinnerMotor;

    // -------- CONSTRUCTOR --------\\

    /**
     * This is the default constructor that will initialize
     * {@link #wheelSpinnerMotor} to the correct motor.
     */
    public ColorWheelSpinnerSubsystem() {
        wheelSpinnerMotor = new VictorSPX(Constants.COLOR_WHEEL_ID);
    }

    // -------- METHODS --------\\

    /**
     * <h3>getMotorSpeed</h3>
     * 
     * This method will return the current speed of the motor.
     * 
     * @return the speed of the motor
     */
    public double getMotorSpeed() {

        logger.entering(ColorWheelSpinnerSubsystem.class.getName(), "getMotorSpeed()");
        logger.exiting(ColorWheelSpinnerSubsystem.class.getName(), "getMotorSpeed()");

        return wheelSpinnerMotor.getMotorOutputPercent();
    }

    /**
     * <h3>setMotorSpeed</h3>
     * 
     * This method will set the speed of the wheel spinner. 
     * The speed is between -1.0 and 1.0
     * 
     * @param speed is the motor speed between -1.0 and 1.0
     */
    public void setMotorSpeed(double speed) {

        logger.entering(ColorWheelSpinnerSubsystem.class.getName(), "setMotorSpeed()");

        // Bounds check the speed
        if (speed > 1.0) {
            speed = 1.0;
        } else if (speed < -1.0) {
            speed = -1.0;
        }

        // Set the spinner motor to the speed
        wheelSpinnerMotor.set(ControlMode.PercentOutput, speed);

        // Logs the speed from the motor controller
        logger.log(Constants.LOG_LEVEL_FINE, "Motor Speed: " + getMotorSpeed());
        logger.exiting(ColorWheelSpinnerSubsystem.class.getName(), "setMotorSpeed()");
    }

} // End of class ColorWheelSpinnerSubsystem