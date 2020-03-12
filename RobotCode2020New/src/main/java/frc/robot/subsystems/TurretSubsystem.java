/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.subsystems;

import frc.robot.Constants;
//import frc.robot.utilities.ShuffleboardUtility;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.FeedbackDevice;
//import com.ctre.phoenix.motorcontrol.RemoteFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//-------- SUBSYSTEM CLASS --------\\

public class TurretSubsystem extends SubsystemBase {

    // -------- CONSTANTS --------\\

    private static final Logger logger = Logger.getLogger(TurretSubsystem.class.getName());
    // constant used in the conversion from encoder units to degrees
    private final double DEGREE_CONVERSION_NUMBER = .0013889;

    // -------- DECLARATIONS --------\\

    // The motor controller that will control the turret
    private TalonSRX turretMotor;
    private DutyCycleEncoder encoder;
    //private ShuffleboardUtility shuffleboardUtility;
    private double encoderPosition;

    // -------- CONSTRUCTOR --------\\

    public TurretSubsystem() {
        this.turretMotor = new TalonSRX(Constants.TURRET_ID);
        this.encoder = new DutyCycleEncoder(Constants.ENCODER_PORT_ID);
        //shuffleboardUtility = ShuffleboardUtility.getInstance();
        logger.log(Constants.LOG_LEVEL_INFO, "Starting TurretSubsystem");
    }

    // -------- METHODS --------\\

    public void setSpeed(double speed) {

        encoderPosition = encoder.get();

        SmartDashboard.putNumber("Turret speed unclamped", speed);
        speed = clamp(speed);
        if (encoderPosition > Constants.UPPER_LIMIT) {
            if (speed < 0) {
                speed = 0;
            }
        } else if (encoderPosition < Constants.LOWER_LIMIT) {
            if (speed > 0) {
                speed = 0;
            }
        }

        this.turretMotor.set(ControlMode.PercentOutput, speed);
        
        logger.log(Constants.LOG_LEVEL_INFO, "Set speed to " + getSpeed());
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
        // return this.turretMotor.getSelectedSensorPosition();
        return unitsToDegrees(this.encoder.get());
    }

    public double getRawEncoderPosition() {
        return this.encoder.get();
    }

    private double clamp(double speed) {
        if (speed > Constants.TURRET_MAX_SPEED) {
            speed = Constants.TURRET_MAX_SPEED;
        } else if (speed < -Constants.TURRET_MAX_SPEED) {
            speed = -Constants.TURRET_MAX_SPEED;
        }
        return speed;
    }
    // @Override
    // public void periodic() {
    //     // TODO Auto-generated method stub
    //     System.out.println(getRawEncoderPosition());
    //     super.periodic();
    // }

    // @Override
    // public void periodic() {
    //     // shuffleboardUtility.setTurretSpeed(getSpeed());
    //     // shuffleboardUtility.setTurretEncoderPosition(getEncoderPosition());
    // }
} // end of class TurretSubsystem
