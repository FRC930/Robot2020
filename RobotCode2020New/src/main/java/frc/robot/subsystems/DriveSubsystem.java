/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.*;
import frc.robot.Constants;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;

public class DriveSubsystem extends SubsystemBase {

    //-------- CONSTANTS --------\\

    //-------- DECLARATIONS --------\\
    //private Encoder m_rightEncoder;
    //private Encoder m_leftEncoder;
    //private TalonSRX gyroTalon;
    private PigeonIMU m_gyro;
    private double values[] = new double[3]; //put to other declerations
    private DifferentialDriveOdometry m_odometry;
    private TalonFX right1;
    private TalonFX right2;
    private TalonFX left1;
    private TalonFX left2;
    //private CANSparkMax left1;
    //private CANSparkMax left2;
    //private CANSparkMax left3;
    private DifferentialDrive m_drive;
    //private CANSparkMax right1;
    //private CANSparkMax right2;
    //private CANSparkMax right3;

    //-------- CONSTRUCTOR --------\\

    public DriveSubsystem() {
      setTalon(new TalonSRX(1));
      setMotorsAndSensors();
      // m_leftEncoder.setDistancePerPulse(0.1016*Math.PI/500);
      // m_rightEncoder.setDistancePerPulse(0.1016*Math.PI/500);
      // m_rightEncoder.reset();
      // m_leftEncoder.reset();
      // m_rightEncoder.setReverseDirection(false);
      // m_leftEncoder.setReverseDirection(false);  
    }

    public DriveSubsystem(TalonFX Left3,TalonFX left4, TalonFX right1, TalonFX right2, Encoder m_RightEncoder, Encoder m_LeftEncoder, TalonSRX Gyrotalon, PigeonIMU m_Gyro, DifferentialDriveOdometry m_Odometry) {
      setTalon(Gyrotalon);
      //setMotorsAndSensors(Left1,Left2,Right1,Right2,m_RightEncoder,m_LeftEncoder,m_Gyro,m_Odometry);
      // m_leftEncoder.setDistancePerPulse(0.1016*Math.PI/500);
      // m_rightEncoder.setDistancePerPulse(0.1016*Math.PI/500);
      // m_rightEncoder.reset();
      // m_leftEncoder.reset();
      // m_rightEncoder.setReverseDirection(false);
      // m_leftEncoder.setReverseDirection(false);  
    }

    //-------- METHODS --------\\
    private void setTalon(TalonSRX GyroTalon){
      //gyroTalon = GyroTalon;
    }

    private void setMotorsAndSensors() {
      setMotorsAndSensors(new TalonFX(3),new TalonFX(4),new TalonFX(1),new TalonFX(2), 
            new Encoder(0,1), 
            new Encoder(2,3)); 
            //new PigeonIMU(gyroTalon), 
            //new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading())));
    }
    public void setMotorsAndSensors(TalonFX Left3,TalonFX Left4, TalonFX Right1, TalonFX Right2, Encoder m_RightEncoder, Encoder m_LeftEncoder /*PigeonIMU m_Gyro, DifferentialDriveOdometry m_Odometry */) {
        // Gives each Spark Max their proper values
        left1 = Left3;
        left2 = Left4;
        //left3 = Left3;

        right1 = Right1;
        right2 = Right2;
        //right3 = Right3;
        // m_rightEncoder = m_RightEncoder;
        // m_leftEncoder = m_LeftEncoder;
        // m_gyro = m_Gyro;
        //m_odometry = m_Odometry;


        // Mirror primary motor controllers on each side
        left2.follow(left1);
        //left3.follow(left1);
        right2.follow(right1); 
        //right3.follow(right1);

        //left1.setOpenLoopRampRate(0.5);
        //right1.setOpenLoopRampRate(0.5);

        //m_drive = new DifferentialDrive(right1, left1);
    }

    // Given Arcade value arguments and sends to motor controllers
    public void runAt(double leftSpeed, double rightSpeed) {
        left1.set(TalonFXControlMode.PercentOutput,leftSpeed);
        right1.set(TalonFXControlMode.PercentOutput,rightSpeed);
    }
    // Returns left speed
    // public double getLeftSpeed() {
    //     return left1.get();
    // }

    // Returns right speed
    // public double getRightSpeed() {
    //     return right1.get();
    // }

    public Pose2d getPose() {
      return m_odometry.getPoseMeters();
    }
    // public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    //   return new DifferentialDriveWheelSpeeds(m_leftEncoder.getRate(), m_rightEncoder.getRate());
    // }
    // public double getHeading() {
    //   m_gyro.getYawPitchRoll(values);
    //   return Math.IEEEremainder(values[0], 360) * (false ? -1.0 : 1.0);
    // }
    // public void resetOdometry(Pose2d pose) {
    //   resetEncoders();
    //   m_odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
    // }
    public void arcadeDrive(double fwd, double rot) {
      m_drive.arcadeDrive(fwd, rot);
    }
    // public void resetEncoders() {
    //   m_leftEncoder.reset();
    //   m_rightEncoder.reset();
    // }
    public void tankDriveVolts(double leftVolts, double rightVolts) {
      System.out.println("MOVING");
      //right1.set(TalonFXControlMode., demand);
      //left1.setVoltage(-rightVolts);
    }
    // public double getAverageEncoderDistance() {
    //   return (m_leftEncoder.getDistance() + m_rightEncoder.getDistance()) / 2.0;
    // }
    // public Encoder getLeftEncoder() {
    //   return m_leftEncoder;
    // }
    // public Encoder getRightEncoder() {
    //   return m_rightEncoder;
    // }
    // public void setMaxOutput(double maxOutput) {
    //   m_drive.setMaxOutput(maxOutput);
    // }
    
    @Override
    public void periodic() {
    // This method will be called once per scheduler run
    }
}