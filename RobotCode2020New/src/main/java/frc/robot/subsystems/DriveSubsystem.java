/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.*;

import frc.robot.utilities.TalonFXSpeedController;
import java.util.logging.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;

public class DriveSubsystem extends SubsystemBase {

  // -------- CONSTANTS --------\\

  // -------- DECLARATIONS --------\\
  private Encoder rightEncoder;
  private Encoder leftEncoder;
  private TalonSRX gyroTalon;
  private PigeonIMU gyro;
  private double values[] = new double[3]; // put to other declerations
  private DifferentialDriveOdometry driveOdometry;
  private TalonFXSpeedController right1;
  private TalonFXSpeedController right2;
  private TalonFXSpeedController left1;
  private TalonFXSpeedController left2;
  private DifferentialDrive drive;
  private Logger logger;
  // private CANSparkMax left1;
  // private CANSparkMax left2;
  // private CANSparkMax left3;
  // private DifferentialDrive m_drive;
  // private CANSparkMax right1;
  // private CANSparkMax right2;
  // private CANSparkMax right3;

  // -------- CONSTRUCTOR --------\\

  public DriveSubsystem() {
    gyroTalon = new TalonSRX(6);
    gyro = new PigeonIMU(gyroTalon);
    driveOdometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
    logger = Logger.getLogger(DriveSubsystem.class.getName());
    logger.setLevel(Level.WARNING); 
    setDriveMotors();
  }

  // -------- METHODS --------\\
  private void setDriveMotors() {
    right1 = new TalonFXSpeedController(1);
    right2 = new TalonFXSpeedController(2);
    left1 = new TalonFXSpeedController(3);
    left2 = new TalonFXSpeedController(4);
    // Mirror primary motor controllers on each side
    left2.follow(left1);
    // left3.follow(left1);
    right2.follow(right1);
    left1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    right1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    // right3.follow(right1);
    left1.configOpenloopRamp(0.5);
    right1.configOpenloopRamp(0.5);
    left1.resetEncoders(right1, left1);
    drive = new DifferentialDrive(right1, left1);
  }

  // Given Arcade value arguments and sends to motor controllers
  public void runAt(double leftSpeed, double rightSpeed) {
    gyro.getYawPitchRoll(values);
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

  public Pose2d getPose() {
  return driveOdometry.getPoseMeters();
  }
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(left1.getRPMLeft(left1), right1.getRPMRight(right1));
  }

  public double getHeading() {
    gyro.getYawPitchRoll(values);
    return Math.IEEEremainder(values[0], 360);
  }
  public double getYaw(){
    gyro.getYawPitchRoll(values);
    return values[0];
    }
  public void resetOdometry(Pose2d pose) {
    driveOdometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
  }

  public void zeroHeading(){
    gyro.setYaw(0.0);
    gyro.setYawToCompass();
    gyro.setFusedHeading(0.0);
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    //System.out.println("MOVING: " + leftVolts + " " + rightVolts);
    right1.setVoltage(rightVolts);
    left1.setVoltage(-leftVolts);
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

    // This method will be called once per scheduler run
    driveOdometry.update((Rotation2d.fromDegrees(getHeading())), left1.getRPMLeft(left1),
    right1.getRPMRight(right1));
    //System.out.println("RIGHT: " + right1.getRPMRight(right1));
    
  }

} // end of the class DriveSubsystem