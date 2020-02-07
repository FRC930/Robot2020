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
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

//-------- SUBSYSTEM CLASS --------\\

public class TurretSubsystem extends SubsystemBase {

    //-------- CONSTANTS --------\\

    private final int TURRET_MOTOR_ID = 5;
    private final int ENCODER_ROTATION_LIMIT = 1500;

    //-------- DECLARATIONS --------\\

    private Logger logger = Logger.getLogger(TurretSubsystem.class.getName());
    // The current speed of the motor
    private double speed;
    // The motor controller that will control the turret
    private TalonSRX mc;
    
    //-------- CONSTRUCTOR --------\\
    
    public TurretSubsystem() {
        this.speed = 0;
        this.mc = new TalonSRX(TURRET_MOTOR_ID);
        this.mc.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
        this.mc.setSelectedSensorPosition(0);
        this.logger.log(Level.INFO, "Starting TurretSubsystem");
    }

    //-------- METHODS --------\\

    public void setSpeed(double motorSpeed) {
        this.speed = -motorSpeed;
        // TODO: Figure out the position needed for 380° of rotation
        if (speed < 0) {
            if (this.mc.getSelectedSensorPosition() > ENCODER_ROTATION_LIMIT) {   
                speed = 0;
            }
        } else if (speed > 0) {
            if (this.mc.getSelectedSensorPosition() < -ENCODER_ROTATION_LIMIT) { 
                speed = 0;
            }
        }

        this.mc.set(ControlMode.PercentOutput, this.speed);
        this.logger.log(Level.INFO, "Set speed to " + this.speed);
    }

    public double getSpeed() {
        return this.speed;
    }

    public int getEncoderPosition() {
        return this.mc.getSelectedSensorPosition();
    }
    
} // end of class TurretSubsystem
