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

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.RemoteFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//-------- SUBSYSTEM CLASS --------\\

public class TurretSubsystem extends SubsystemBase {

    //-------- CONSTANTS --------\\

    private final int ENCODER_ROTATION_LIMIT = 15000;

    //-------- DECLARATIONS --------\\

    private Logger logger = Logger.getLogger(TurretSubsystem.class.getName());
    // The motor controller that will control the turret
    private TalonSRX turretMotor;
    
    //-------- CONSTRUCTOR --------\\
    
    public TurretSubsystem() {
        this.turretMotor = new TalonSRX(Constants.TURRET_ID);
        this.turretMotor.configSelectedFeedbackSensor(FeedbackDevice.PulseWidthEncodedPosition);
        this.turretMotor.setSelectedSensorPosition(0);
        this.logger.log(Level.INFO, "Starting TurretSubsystem");
    }

    //-------- METHODS --------\\

    public void setSpeed(double speed) {
        //speed = -speed;
        // TODO: Figure out the position needed for 380° of rotation
        /*if (speed < 0) {
            if (this.turretMotor.getSelectedSensorPosition() > ENCODER_ROTATION_LIMIT) {   
                speed = 0;
            }
        } else if (speed > 0) {
            if (this.turretMotor.getSelectedSensorPosition() < -ENCODER_ROTATION_LIMIT) { 
                speed = 0;
            }
        }*/

        /*if(Math.abs(this.turretMotor.getSelectedSensorPosition()) > ENCODER_ROTATION_LIMIT) {
            speed = 0;
        }*/

        SmartDashboard.putNumber("Turret Encoder value", getEncoderPosition());

        this.turretMotor.set(ControlMode.PercentOutput, speed);
        this.logger.log(Level.INFO, "Set speed to " + speed);
    }

    public double getSpeed() {
        return turretMotor.getMotorOutputPercent();
    }

    public int getEncoderPosition() {
        return this.turretMotor.getSelectedSensorPosition();
    }
    
} // end of class TurretSubsystem
