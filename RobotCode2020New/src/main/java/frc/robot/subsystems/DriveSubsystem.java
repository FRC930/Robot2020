/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utilities.TalonFXSpeedController;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Pose2d;

//-------- SUBSYSTEM CLASS --------\\

public class DriveSubsystem extends SubsystemBase {

  //-------- CONSTANTS --------\\

  private final int RIGHT_FRONT_MOTOR_ID = 1;
  private final int RIGHT_BACK_MOTOR_ID = 2;
  private final int LEFT_FRONT_MOTOR_ID = 3;
  private final int LEFT_BACK_MOTOR_ID = 4;

  private final double MOTOR_RAMP_RATE = 0.5;

  //-------- DECLARATIONS --------\\

  //Encoders on the drivetrain
  private Encoder rightEncoder;
  private Encoder leftEncoder;

  //The intake talon motor controller, has the gyro attached to it
  private TalonSRX gyroTalon;

  //The gyro, used for autonomous
  private PigeonIMU gyro;

  //Values, used to store the yaw, pitch, and roll (the robot's rotation)
  private double yawPitchRollValues[] = new double[3]; 

  //Odemetry, used to track robot position with gyro
  private DifferentialDriveOdometry driveOdometry;

  //The speed controller, TalonFX
  private TalonFXSpeedController right1;
  private TalonFXSpeedController right2;
  private TalonFXSpeedController left1;
  private TalonFXSpeedController left2;

  //The drive train system
  private DifferentialDrive drive;

  //-------- CONSTRUCTOR --------\\

  public DriveSubsystem() {
    gyroTalon = new TalonSRX(Constants.INTAKE_TALONSRX_ID);
    gyro = new PigeonIMU(gyroTalon);
    driveOdometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
    
    setDriveMotors();
  }

  //-------- METHODS --------\\

  private void setDriveMotors() {

    right1 = new TalonFXSpeedController(RIGHT_FRONT_MOTOR_ID);
    right2 = new TalonFXSpeedController(RIGHT_BACK_MOTOR_ID);
    left1 = new TalonFXSpeedController(LEFT_FRONT_MOTOR_ID);
    left2 = new TalonFXSpeedController(LEFT_BACK_MOTOR_ID);

    // Mirror primary motor controllers on each side
    left2.follow(left1);
    right2.follow(right1);

    //Sets the ramp rate of the robot, this will need to be configued
    left1.configOpenloopRamp(MOTOR_RAMP_RATE);
    right1.configOpenloopRamp(MOTOR_RAMP_RATE);

    //Sets up the differntial drive
    drive = new DifferentialDrive(right1, left1);
  }

  // Given Arcade value arguments and sends to motor controllers
  public void runAt(double leftSpeed, double rightSpeed) {
    //Updates our rotations into the rotations table
    gyro.getYawPitchRoll(yawPitchRollValues);

    left1.set(leftSpeed);
    right1.set(rightSpeed);
  }

  // Returns left speed
  public double getLeftSpeed() {
    return left1.getMotorOutputPercent();
  }

  // Returns right speed
  public double getRightSpeed() {
    return right1.getMotorOutputPercent();
  }

  //Returns the position of the robot, in meters
  public Pose2d getPose() {
    return driveOdometry.getPoseMeters();
  }

  //Returns the speed of the wheels on the differential drive
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds((left1.getRPMLeft(left1) / 60), (right1.getRPMRight(right1) / 60));
  }

  public double getHeading() {
    gyro.getYawPitchRoll(yawPitchRollValues);
    return Math.IEEEremainder(yawPitchRollValues[0], 360);
  }

  //Returns the yaw rotation of the robot
  public double getYaw(){
    gyro.getYawPitchRoll(yawPitchRollValues);
    return yawPitchRollValues[0];
  }

  public void resetOdometry(Pose2d pose) {
    driveOdometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
  }

  //Resets the yaw rotation
  public void zeroHeading(){
    gyro.setYaw(0.0);
    gyro.setFusedHeading(0.0);
  }

  //Sets the voltage on the left and right side of the drivetrain
  public void tankDriveVolts(double leftVolts, double rightVolts) {
    right1.set(-rightVolts);
    left1.set(leftVolts);
  }

  //Returns the average between the left and right encoders on the robot
  public double getAverageEncoderDistance() {
    return (left1.getRPMLeft(left1) + right1.getRPMRight(right1)) / 2.0;
  }

  //Returns the value of the encoder on the left drivetrain side
  public double getLeftEncoder() {
    return left1.getRPMLeft(left1);
  }

  //Returns the value of the encoder on the right drivetrain side
  public double getRightEncoder() {
    return right1.getRPMLeft(right1);
  }

  //Sets the maximum output of driving on the DifferentialDrive
  public void setMaxOutput(double maxOutput) {
    drive.setMaxOutput(maxOutput);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    driveOdometry.update((Rotation2d.fromDegrees(getHeading())), left1.getRPMLeft(left1),
    right1.getRPMRight(right1));
  }

} // end of the class DriveSubsystem