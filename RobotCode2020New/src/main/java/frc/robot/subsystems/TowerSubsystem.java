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

public class TowerSubsystem extends SubsystemBase {
  
    //-------- CONSTANTS --------\\

    private static final int TOWER_MOTOR_PORT = 4;

    //-------- DECLARATIONS --------\\

    private Logger logger = Logger.getLogger(this.getClass().getName());

    // VictorSPX is a motor controller that makes the conveor belt  
    //Take's the power cell up to the shooter
    private VictorSPX TowerMotor;

    //TODO: Remove this and use the getSpeed method from VictorSPX class
    private double speed;   

    //-------- CONSTRUCTOR --------\\

    public TowerSubsystem() {
        TowerMotor = new VictorSPX(TOWER_MOTOR_PORT);  
        logger.setLevel(Level.FINE);
    }   
    
    //-------- METHODS --------\\

    //sets the speed to the tower motor 
    public void setSpeed(double speed){
        logger.entering(this.getClass().getName(), "setSpeed");
        logger.log(Level.INFO, "motorSpeed: "+ speed );

        this.speed = speed;

        TowerMotor.set(ControlMode.PercentOutput, speed);
        logger.exiting (this.getClass().getName(), "setSpeed");
    }

    // returns the speed from the tower motor
    public double getSpeed(){ 
        logger.entering(this.getClass().getName(),"getSpeed");
        logger.log(Level.INFO, "motorSpeed: " + speed);
        logger.exiting (this.getClass().getName(),"getSpeed");
        
        return this.speed;
    }

    //stops the motor
    public void stopMotor() {
        this.speed = 0;
        TowerMotor.set(ControlMode.PercentOutput, 0);
    }

} //end of class TowerSubsystem