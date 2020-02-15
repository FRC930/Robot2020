/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.utilities.TalonFXSpeedController;
import java.util.logging.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;

import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {

  // -------- CONSTANTS --------\\

  // -------- DECLARATIONS --------\\
  private TalonFXSpeedController right1;
  private TalonFXSpeedController right2;
  private TalonFXSpeedController left1;
  private TalonFXSpeedController left2;
  private DifferentialDrive drive;
  private Logger logger;
  // -------- CONSTRUCTOR --------\\

  public DriveSubsystem() {
    logger = Logger.getLogger(DriveSubsystem.class.getName());
    logger.setLevel(Level.WARNING); 
    setDriveMotors();
  }

  // -------- METHODS --------\\
  private void setDriveMotors() {

    right1 = new TalonFXSpeedController(Constants.DRIVE_RIGHT_FRONT_ID);
    right2 = new TalonFXSpeedController(Constants.DRIVE_RIGHT_BACK_ID);
    left1 = new TalonFXSpeedController(Constants.DRIVE_LEFT_FRONT_ID);
    left2 = new TalonFXSpeedController(Constants.DRIVE_LEFT_BACK_ID);

    // Mirror primary motor controllers on each side
    left2.follow(left1);
    // left3.follow(left1);
    right2.follow(right1);

    //Sets the ramp rate of the robot, this will need to be configued
    left1.configOpenloopRamp(Constants.MOTOR_RAMP_RATE);
    right1.configOpenloopRamp(Constants.MOTOR_RAMP_RATE);

    //Sets up the differntial drive
    drive = new DifferentialDrive(right1, left1);
  }

  // Given Arcade value arguments and sends to motor controllers
  public void runAt(double leftSpeed, double rightSpeed) {
    logger.entering(this.getClass().getName(), "runAt()");
    
    logger.log(Level.WARNING, "runing " + "left encoder " + left1.getRPMLeft(left1) + "right encoder " + right1.getRPMRight(right1));

    System.out.println();
    left1.set(leftSpeed);
    right1.set(rightSpeed);
    logger.exiting(this.getClass().getName(), "runAt()");
  }

  // Returns left speed
  public double getLeftSpeed() {
    return left1.getMotorOutputPercent();
  }

  // Returns right speed
  public double getRightSpeed() {
    return right1.getMotorOutputPercent();
  }
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(left1.getRPMLeft(left1), right1.getRPMRight(right1));
  }
  public void resetOdometry(Pose2d pose) {
    //driveOdometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    logger.entering(this.getClass().getName(), "tankDriveVolts()");
    
    logger.log(Level.WARNING, "MOVING: " + leftVolts + " " + rightVolts);

    System.out.println();
    right1.setVoltage(rightVolts);
    left1.setVoltage(-leftVolts);
    logger.exiting(this.getClass().getName(), "tankDriveVolts()");
    
  }

  public double getAverageEncoderDistance() {
    return (left1.getRPMLeft(left1)
        + right1.getRPMRight(right1)) / 2.0;
  }

  public double getLeftEncoder() {
   return left1.getRPMLeft(left1);
  }
  public double getRightEncoder() {
   return right1.getRPMLeft(right1);
  }
  public void setMaxOutput(double maxOutput) {
    drive.setMaxOutput(maxOutput);
  }

  @Override
  public void periodic() {
    //System.out.println(yaw);
    logger.entering(this.getClass().getName(), "periodic()");
    // This method will be called once per scheduler run
    //driveOdometry.update((Rotation2d.fromDegrees(getHeading())), left1.getRPMLeft(left1),
    logger.exiting(this.getClass().getName(), "periodic()");
    //System.out.println("RIGHT: " + right1.getRPMRight(right1));
    
  }

} // end of the class DriveSubsystem