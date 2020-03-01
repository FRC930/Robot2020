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

public class TowerSubsystem extends SubsystemBase {
  
    //-------- CONSTANTS --------\\

    private static final Logger logger = Logger.getLogger(TowerSubsystem.class.getName());

    //-------- DECLARATIONS --------\\

    // VictorSPX is a motor controller that makes the conveor belt  Take's the power cell up to the shooter
    private VictorSPX towerMotor;

    //-------- CONSTRUCTOR --------\\

    public TowerSubsystem() {
        towerMotor = new VictorSPX(Constants.TOWER_ID);  
    }   
    
    //-------- METHODS --------\\

    //sets the speed to the tower motor 
    public void setSpeed(double speed){
        logger.entering(TowerSubsystem.class.getName(), "setSpeed");
        logger.log(Constants.LOG_LEVEL_INFO, "motorSpeed: " + -speed);

        towerMotor.set(ControlMode.PercentOutput, -speed);

        logger.exiting(TowerSubsystem.class.getName(), "setSpeed");
    }
        
    // returns the speed from the tower motor
    public double getSpeed(){ 
        logger.entering(TowerSubsystem.class.getName(),"getSpeed");
        logger.log(Constants.LOG_LEVEL_INFO, "motorSpeed: " + towerMotor.getMotorOutputPercent());
        logger.exiting(TowerSubsystem.class.getName(),"getSpeed");
        
        return towerMotor.getMotorOutputPercent();
    }

    //stops the motor
    public void stopMotor() {
        towerMotor.set(ControlMode.PercentOutput, 0.0);
    }

} //end of class TowerSubsystem