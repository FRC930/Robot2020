/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.subsystems;

import frc.robot.Constants;

import java.util.logging.Level;
import java.util.logging.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

//-------- SUBSYSTEM CLASS --------\\

public class IntakeMotorSubsystem extends SubsystemBase {

    //-------- DECLARATIONS --------\\

    private final Logger logger = Logger.getLogger(IntakeMotorSubsystem.class.getName());
    private TalonSRX intakeMotorController; 
    private final int  INTAKE_TALONSRX_ID = 6;
    //-------- CONSTRUCTOR --------\\

    public IntakeMotorSubsystem() {
        intakeMotorController = new TalonSRX(Constants.INTAKE_ID);
    }

    //-------- METHODS --------\\
    
    //sets motor speed 
    public void setMotorSpeed(double speed) {
        intakeMotorController.set(ControlMode.PercentOutput, speed);
        logger.log(Level.FINE,"sets motor speed");
    }

    //returns the motor speed
    public double getMotorSpeed() {
        return intakeMotorController.getMotorOutputPercent();  
    }

} // end of class IntakeMotorSubsystem
