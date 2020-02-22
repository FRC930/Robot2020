/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.logging.*;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.PigeonIMU;

import frc.robot.Constants;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

//-------- SUBSYSTEM CLASS --------\\

public class DriveSubsystem extends SubsystemBase {

  // -------- CONSTANTS --------\\

  // private final Logger logger = // logger.getLogger(DriveSubsystem.class.getName());

  // -------- DECLARATIONS --------\\

  private WPI_TalonFX right1;
  private WPI_TalonFX right2;
  private WPI_TalonFX left1;
  private WPI_TalonFX left2;
  //The intake talon motor controller, has the gyro attached to it
  private TalonSRX gyroTalon;
    
  //The gyro, used for autonomous
  private PigeonIMU gyro;

  //Values, used to store the yaw, pitch, and roll (the robot's rotation)
  private double yawPitchRollValues[] = new double[3]; 

  private DifferentialDriveOdometry driveOdometry;
  //private DifferentialDrive drive;
  
  // -------- CONSTRUCTOR --------\\

  public DriveSubsystem() {
    //// logger.setLevel(Level.OFF); 
    setDriveMotors();
  }

  // -------- METHODS --------\\

  private void setDriveMotors() {

    right1 = new WPI_TalonFX(Constants.DRIVE_RIGHT_FRONT_ID);
    right2 = new WPI_TalonFX(Constants.DRIVE_RIGHT_BACK_ID);
    left1 = new WPI_TalonFX(Constants.DRIVE_LEFT_FRONT_ID);
    left2 = new WPI_TalonFX(Constants.DRIVE_LEFT_BACK_ID);
    gyroTalon = new TalonSRX(Constants.INTAKE_ID);
    gyro = new PigeonIMU(gyroTalon);
    driveOdometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
    left1.setInverted(true);
    left2.setInverted(true);
    right1.setInverted(false);
    right2.setInverted(false);

    left1.getSensorCollection().setIntegratedSensorPosition(0.0, 100);
    right1.getSensorCollection().setIntegratedSensorPosition(0.0, 100);

    left1.setSensorPhase(true);
    left2.setSensorPhase(true);
    right1.setSensorPhase(false);
    right2.setSensorPhase(false);
    // Mirror primary motor controllers on each side
    left2.follow(left1);
    right2.follow(right1);

    //Sets the ramp rate of the robot, this will need to be configued
    left1.configOpenloopRamp(Constants.MOTOR_RAMP_RATE);
    right1.configOpenloopRamp(Constants.MOTOR_RAMP_RATE);
    //Sets up the differntial drive
    //drive = new DifferentialDrive(right1, left1);
  }

  // Given Arcade value arguments and sends to motor controllers
  public void runAt(double leftSpeed, double rightSpeed) {
    // logger.entering(this.getClass().getName(), "runAt()");
    // logger.log(Level.OFF, "runing " + "left encoder " + left1.getRPMLeft(left1) + "right encoder " + right1.getRPMRight(right1));

    left1.set(leftSpeed);
    right1.set(rightSpeed);

    // logger.exiting(this.getClass().getName(), "runAt()");
  }

  // Returns left speed
  public double getLeftSpeed() {
    return left1.getMotorOutputPercent();
  }

  // Returns right speed
  public double getRightSpeed() {
    return right1.getMotorOutputPercent();
  }

  public double getHeading() {
    gyro.getYawPitchRoll(yawPitchRollValues);
    return Math.IEEEremainder(yawPitchRollValues[0], 360);
  }

  public double getRPMLeft() {
    double rotationML;
    rotationML = left1.getSelectedSensorPosition() * ((1.0 / 2048.0) * 0.152 * Math.PI) / 12.0;
    return rotationML;
}

public double getRPMRight() {
    double rotationMR;
    rotationMR = right1.getSelectedSensorPosition() * ((1.0 / 2048.0) * 0.152 * Math.PI) / 12.0;
    return rotationMR;
}

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(getRPMLeft(), getRPMRight());
  }
  public void resetOdometry(Pose2d pose) {
    System.out.println("BOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOB resetOdometry got called");
    //driveOdometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    // logger.entering(this.getClass().getName(), "tankDriveVolts()");
    // logger.log(Level.OFF, "MOVING: " + leftVolts + " " + rightVolts);

    //TODO: Change for Prac Robot
    //right1.setVoltage(rightVolts);
    //left1.setVoltage(-leftVolts);

    //TODO: Change for Comp Robot
    right1.setVoltage(rightVolts);
    left1.setVoltage(leftVolts);

    // logger.exiting(this.getClass().getName(), "tankDriveVolts()");
    
  }

  public double getAverageEncoderDistance() {
    return (getRPMLeft()
        + getRPMRight()) / 2.0;
  }

  public double getLeftEncoder() {
    return getRPMLeft();
  }

  public double getRightEncoder() {
    return getRPMLeft();
  }

  

public Pose2d getPose() {
  return driveOdometry.getPoseMeters();
}

  // public void setMaxOutput(double maxOutput) {
  //   drive.setMaxOutput(maxOutput);
  // }

  @Override
  public void periodic() {
    driveOdometry.update(Rotation2d.fromDegrees(getHeading()), getRPMLeft(), getRPMRight());

    //System.out.println(driveOdometry.getPoseMeters().getTranslation().getX());

    SmartDashboard.putNumber("Odometry X", driveOdometry.getPoseMeters().getTranslation().getX());
    SmartDashboard.putNumber("Odometry Y", driveOdometry.getPoseMeters().getTranslation().getY());
    SmartDashboard.putNumber("Heading", getHeading());
    SmartDashboard.putNumber("RPM Left", getRPMLeft());
    SmartDashboard.putNumber("RPM Right", getRPMRight());

    SmartDashboard.putNumber("Left selected sensor pos", left1.getSelectedSensorPosition());
    SmartDashboard.putNumber("Right selected sensor pos", right1.getSelectedSensorPosition());
    // logger.entering(this.getClass().getName(), "periodic()");
    // This method will be called once per scheduler run
    //driveOdometry.update((Rotation2d.fromDegrees(getHeading())), left1.getRPMLeft(left1),
    // logger.exiting(this.getClass().getName(), "periodic()");  
  }

} // end of the class DriveSubsystem