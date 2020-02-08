/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.subsystems;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

//-------- SUBSYSTEM CLASS --------\\

public class HopperSubsystem extends SubsystemBase {

    //-------- CONSTANTS --------\\

    private static final int HOPPER_MOTOR_PORT = 3;

    //-------- DECLARATIONS --------\\

    // VictorSPX is a motor controller that rotates the hopper, containing the power cells. Works like a nerf hellraiser loader  
    private VictorSPX HopperMotor;
    
    private double speed;    //TODO: Remove this speed variable and use the .getSpeed method in the VictorSPX

    private final Logger logger = Logger.getLogger(CompresserSubsystem.class.getName());

    //-------- CONSTRUCTOR --------\\

    public HopperSubsystem() {
        HopperMotor = new VictorSPX(HOPPER_MOTOR_PORT);
    }

    //-------- METHODS --------\\

    //sets the speed to the Hoppermotor
    public void setSpeed(double speed) {
        //Logs the setSpeed method as INFO returning, "setSpeed()"
        logger.entering(getClass().getName(), "setSpeed()");

        
        HopperMotor.set(ControlMode.PercentOutput, speed);

        logger.log(Level.INFO, "Set shooter speed to " + HopperMotor.getMotorOutputPercent());
        logger.exiting(getClass().getName(), "setSpeed()");
    }

    // returns the speed
    public double getSpeed() {
        //Logs the getSpeed method as INFO returning, "getSpeed()"
        logger.entering(getClass().getName(), "getSpeed()");
        logger.log(Level.INFO, "Get shooter speed to " + HopperMotor.getMotorOutputPercent());
        logger.exiting(getClass().getName(), "getSpeed()");
        return HopperMotor.getMotorOutputPercent();
    }
} // end of class HopperSubsystem