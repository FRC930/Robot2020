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

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//-------- SUBSYSTEM CLASS --------\\

public class TurretSubsystem extends SubsystemBase {

    //-------- DECLARATIONS --------\\

    private Logger logger = Logger.getLogger(TurretSubsystem.class.getName());

    // The motor controller that will control the turret
    private TalonSRX turretMotor;
    private DutyCycleEncoder encoder;

    // constant used in the conversion from encoder units to degrees
    private final double DEGREE_CONVERSION_NUMBER = .0013889;

    private double encoderPosition;
    
    //-------- CONSTRUCTOR --------\\
    
    public TurretSubsystem() {
        this.turretMotor = new TalonSRX(Constants.TURRET_ID);
        //this.turretEncoder = this.turretMotor.getEncoder();
        this.encoder = new DutyCycleEncoder(Constants.ENCODER_PORT_ID);
        //this.encoder.reset();
        //this.turretMotor.configSelectedFeedbackSensor(FeedbackDevice.PulseWidthEncodedPosition);
        //this.turretMotor.setSelectedSensorPosition(0);
        this.logger.log(Level.INFO, "Starting TurretSubsystem");
    }

    //-------- METHODS --------\\

    public void setSpeed(double speed) {

        // if(Math.abs(getEncoderPosition()) > Constants.ENCODER_ROTATION_LIMIT) {
        //     speed = 0;
        // }
        
        encoderPosition = encoder.get();

        if(encoderPosition > Constants.UPPER_LIMIT) {
            if(speed > 0) {
                speed = 0;
            } else if(speed < -0.5) {
                speed = -0.5;
            }
        } else if(encoderPosition < Constants.LOWER_LIMIT) {
            if(speed < 0) {
                speed = 0;
            } else if(speed > 0.5) {
                speed = 0.5;
            }
        } else {
            if(speed > 0.5) {
                speed = 0.5;
            } else if(speed < -0.5) {
                speed = -0.5;
            }
        }   

        SmartDashboard.putNumber("Turret Encoder value", encoderPosition);

        this.turretMotor.set(ControlMode.PercentOutput, speed);
        this.logger.log(Level.INFO, "Set speed to " + getSpeed());
    }

    public double getSpeed() {
        return turretMotor.getMotorOutputPercent();
    }

    // converts encoder units to degrees
    public double unitsToDegrees(double units) {
        return this.encoder.get() / DEGREE_CONVERSION_NUMBER;
    }

    // returns the current encoder position in degrees
    public double getEncoderPosition() {
        //return this.turretMotor.getSelectedSensorPosition();
        return unitsToDegrees(this.encoder.get());
    }
    
} // end of class TurretSubsystem
