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

public class HopperSubsystem extends SubsystemBase {

    //-------- CONSTANTS --------\\

    private final Logger logger = Logger.getLogger(CompresserSubsystem.class.getName());

    //-------- DECLARATIONS --------\\

    // VictorSPX is a motor controller that rotates the hopper, containing the power cells. Works like a nerf hellraiser loader  
    private VictorSPX hopperMotor;

    //-------- CONSTRUCTOR --------\\

    public HopperSubsystem() {
        hopperMotor = new VictorSPX(Constants.HOPPER_ID);
    }

    //-------- METHODS --------\\

    //sets the speed to the Hoppermotor
    public void setSpeed(double speed) {
        //Logs the setSpeed method as INFO returning, "setSpeed()"
        logger.entering(getClass().getName(), "setSpeed()");

        hopperMotor.set(ControlMode.PercentOutput, speed);

        logger.log(Constants.LOG_LEVEL_INFO, "Set shooter speed to " + hopperMotor.getMotorOutputPercent());
        logger.exiting(getClass().getName(), "setSpeed()");
    }

    // returns the speed
    public double getSpeed() {
        //Logs the getSpeed method as INFO returning, "getSpeed()"
        logger.entering(getClass().getName(), "getSpeed()");
        logger.log(Constants.LOG_LEVEL_INFO, "Get shooter speed to " + hopperMotor.getMotorOutputPercent());
        logger.exiting(getClass().getName(), "getSpeed()");
        return hopperMotor.getMotorOutputPercent();
    }
} // end of class HopperSubsystem