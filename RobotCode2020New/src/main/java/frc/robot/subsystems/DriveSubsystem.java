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
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveSubsystem extends SubsystemBase {

    //-------- CONSTANTS --------\\

    //-------- DECLARATIONS --------\\
    private final Encoder m_rightEncoder = new Encoder(0,1);
    private final Encoder m_leftEncoder = new Encoder(2,3);
    private final TalonSRX gyroTalon = new TalonSRX(1);
    private final PigeonIMU m_gyro = new PigeonIMU(gyroTalon);
    private double values[] = new double[3];
    private final DifferentialDriveOdometry m_odometry;
    
    private CANSparkMax left1;
    private CANSparkMax left2;
    private CANSparkMax left3;
    private DifferentialDrive m_drive;
    private CANSparkMax right1;
    private CANSparkMax right2;
    private CANSparkMax right3;

    //-------- CONSTRUCTOR --------\\

    public DriveSubsystem() {
      m_leftEncoder.setDistancePerPulse(0.1016*Math.PI/500);
      m_rightEncoder.setDistancePerPulse(0.1016*Math.PI/500);
      m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
      m_rightEncoder.reset();
      m_leftEncoder.reset();
      m_rightEncoder.setReverseDirection(false);
      m_leftEncoder.setReverseDirection(false);
      setMotorControllers();
        
    }

    //-------- METHODS --------\\

    public void setMotorControllers() {
        setMotorControllers(new CANSparkMax(1, MotorType.kBrushless),
            new CANSparkMax(2, MotorType.kBrushless),
            new CANSparkMax(3, MotorType.kBrushless),
            new CANSparkMax(4, MotorType.kBrushless),
            new CANSparkMax(5, MotorType.kBrushless),
            new CANSparkMax(6, MotorType.kBrushless));
    }

    public void setMotorControllers(CANSparkMax Left1, CANSparkMax Left2, CANSparkMax Left3, CANSparkMax Right1,
            CANSparkMax Right2, CANSparkMax Right3) {
        // Gives each Spark Max their proper values
        left1 = Left1;
        left2 = Left2;
        left3 = Left3;

        right1 = Right1;
        right2 = Right2;
        right3 = Right3;

        // Mirror primary motor controllers on each side
        left2.follow(left1);
        left3.follow(left1);
        right2.follow(right1);
        right3.follow(right1);

        left1.setOpenLoopRampRate(0.5);
        right1.setOpenLoopRampRate(0.5);

        m_drive = new DifferentialDrive(right1, left1);
    }

    // Given Arcade value arguments and sends to motor controllers
    public void runAt(double leftSpeed, double rightSpeed) {
        left1.set(leftSpeed);
        right1.set(rightSpeed);
    }

    public void run(double stickX, double stickY) {

        // Cubing values to create smoother function
        stickX = -Math.pow(stickX, 3);
        stickY = Math.pow(stickY, 3);
        stickX *= Constants.DRIVE_TURNING_MULTIPLIER;
        // Joystick deadband
        if (Math.abs(stickX) < Constants.DRIVE_DEADBAND_JOYSTICK) {
            stickX = 0;
        }
        if (Math.abs(stickY) < Constants.DRIVE_DEADBAND_JOYSTICK) {
            stickY = 0;
        }

        // Arcade drive
        runAt((stickY + stickX), -(stickY - stickX));

      } //End of method run()
      
      public 

      }
    // Returns left speed
    public double getLeftSpeed() {
        return left1.get();
    }

    // Returns right speed
    public double getRightSpeed() {
        return right1.get();
    }

    public Pose2d getPose() {
      return m_odometry.getPoseMeters();
    }
    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
      return new DifferentialDriveWheelSpeeds(m_leftEncoder.getRate(), m_rightEncoder.getRate());
    }
    public double getHeading() {
      m_gyro.getYawPitchRoll(values);
      return Math.IEEEremainder(values[0], 360) * (false ? -1.0 : 1.0);
    }
    public void resetOdometry(Pose2d pose) {
      resetEncoders();
      m_odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
    }
    public void arcadeDrive(double fwd, double rot) {
      m_drive.arcadeDrive(fwd, rot);
    }
    public void resetEncoders() {
      m_leftEncoder.reset();
      m_rightEncoder.reset();
    }
    public void tankDriveVolts(double leftVolts, double rightVolts) {
      System.out.println("MOVING");
      right1.setVoltage(leftVolts);
      left1.setVoltage(-rightVolts);
    }
    public double getAverageEncoderDistance() {
      return (m_leftEncoder.getDistance() + m_rightEncoder.getDistance()) / 2.0;
    }
    public Encoder getLeftEncoder() {
      return m_leftEncoder;
    }
    public Encoder getRightEncoder() {
      return m_rightEncoder;
    }
    public void setMaxOutput(double maxOutput) {
      m_drive.setMaxOutput(maxOutput);
    }
    
    @Override
    public void periodic() {
    // This method will be called once per scheduler run
    }
}