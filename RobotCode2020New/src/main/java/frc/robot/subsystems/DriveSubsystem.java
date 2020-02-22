/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.logging.*;

import frc.robot.utilities.TalonFXSpeedController;
import frc.robot.Constants;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

//-------- SUBSYSTEM CLASS --------\\

public class DriveSubsystem extends SubsystemBase {

  // -------- CONSTANTS --------\\

  private final Logger logger = Logger.getLogger(DriveSubsystem.class.getName());

  // -------- DECLARATIONS --------\\

  private TalonFXSpeedController right1;
  private TalonFXSpeedController right2;
  private TalonFXSpeedController left1;
  private TalonFXSpeedController left2;
  private DifferentialDrive differentialDrive;
  
  // -------- CONSTRUCTOR --------\\

  public DriveSubsystem() {
    logger.setLevel(Constants.LOG_LEVEL_FINE); 
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
    right2.follow(right1);

    //Sets the ramp rate of the robot, this will need to be configued
    left1.configOpenloopRamp(Constants.MOTOR_RAMP_RATE);
    right1.configOpenloopRamp(Constants.MOTOR_RAMP_RATE);

    //Sets up the differntial drive
    differentialDrive = new DifferentialDrive(right1, left1);
  }

  public double getRPMLeft(){
    double rotationML;
    rotationML = -left1.getSelectedSensorPosition() * ((1 / 2048) * 0.152 * Math.PI);
    //smartDashboard.get
    return rotationML;
  }
  public double getRPMRight(){
    double rotationML;
    rotationML = right1.getSelectedSensorPosition() * ((1 / 2048) * 0.152 * Math.PI);
    //smartDashboard.get
    return rotationML;
  }

  // Given Arcade value arguments and sends to motor controllers
  public void runAt(double leftSpeed, double rightSpeed) {
    logger.entering(this.getClass().getName(), "runAt()");
    logger.log(Constants.LOG_LEVEL_FINE, "running " + "left encoder " + getRPMLeft() + " | right encoder " + getRPMRight());

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
    return new DifferentialDriveWheelSpeeds(getRPMLeft(), getRPMRight());
  }

  public void resetOdometry(Pose2d pose) {
    //driveOdometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    logger.entering(this.getClass().getName(), "tankDriveVolts()");
    logger.log(Constants.LOG_LEVEL_FINE, "MOVING: " + leftVolts + " " + rightVolts);

    //TODO: Change for Prac Robot
    right1.setVoltage(rightVolts);
    left1.setVoltage(-leftVolts);

    //TODO: Change for Comp Robot
    //right1.setVoltage(-rightVolts);
    //left1.setVoltage(leftVolts);

    logger.exiting(this.getClass().getName(), "tankDriveVolts()");
  }

  public double getLeftEncoder() {
    return getRPMLeft();
  }

  public double getRightEncoder() {
    return getRPMLeft();
  }

  public double getAverageEncoderDistance() {
    return (getRPMLeft()
        + getRPMRight()) / 2.0;
  }

  
  

  // public void setMaxOutput(double maxOutput) {
  //   drive.setMaxOutput(maxOutput);
  // }
  public void setMaxOutput(double maxOutput) {
    differentialDrive.setMaxOutput(maxOutput);
  }

  @Override
  public void periodic() {
    logger.entering(this.getClass().getName(), "periodic()");
    // This method will be called once per scheduler run
    //driveOdometry.update((Rotation2d.fromDegrees(getHeading())), left1.getRPMLeft(left1),
    logger.exiting(this.getClass().getName(), "periodic()");  
  }

} // end of the class DriveSubsystem