/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.*;

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

  private final int RIGHT_ENCODER_CHANNEL_A = 0;
  private final int RIGHT_ENCODER_CHANNEL_B = 1;
  private final int LEFT_ENCODER_CHANNEL_A = 2;
  private final int LEFT_ENCODER_CHANNEL_B = 3;

  private final int GRYO_TALON_PORT = 1;

  //in meters
  private final double WHEEL_DIAMETER = 0.1016; 
  //in ticks
  private final int ENCODER_TICKS_PER_ROTATION = 500;  

  //in seconds
  private final double RAMPRATE_TIME = 0.5;

  //-------- DECLARATIONS --------\\

  //Encoders on the left and right side of the drivetrain
  //TODO: May be subject to change because the TalonFX has their own controllers
  private Encoder rightEncoder;
  private Encoder leftEncoder;

  //Talon that the pigeon IMU gyro is hooked up to (same talon that controls intake motors)
  private TalonSRX gyroTalon;

  //The robot gyro, used to measure the yaw of the robot
  private PigeonIMU gyro;

  //A double array used to store the values of the gyro's yaw, pitch, and roll (the 3 rotational axes of the devil)
  private double yawPitchRoll[];

  //Used to determine the robot's change in position 
  private DifferentialDriveOdometry odometry;

  //Our motor controllers on the robot 
  //TODO: Change for Falcon FXs
  private CANSparkMax left1;
  private CANSparkMax left2;
  private CANSparkMax left3;
  private CANSparkMax right1;
  private CANSparkMax right2;
  private CANSparkMax right3;

  //Our drivetrain type
  private DifferentialDrive m_drive;

  //-------- CONSTRUCTOR --------\\

  //Initialize our subsystem
  public DriveSubsystem() {

    //Init encoders
    initializeEncoders();

    //Set up talon and the gyro attached via ribbon cable
    gyroTalon = new TalonSRX(GRYO_TALON_PORT);
    gyro = new PigeonIMU(gyroTalon);

    //Instantiate array for yawPitchRoll
    yawPitchRoll = new double[3];

    //Sets up initial robot position
    odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));

    setMotorControllers();
  } //end of constructor DriveSubsystem()

  //-------- INITIALIZE METHODS --------\\

  //This method will initialize any encoder logic before its use
  public void initializeEncoders() {

    //Instantiates our encoders with their proper ports
    rightEncoder = new Encoder(RIGHT_ENCODER_CHANNEL_A, RIGHT_ENCODER_CHANNEL_B);
    leftEncoder = new Encoder(LEFT_ENCODER_CHANNEL_A, LEFT_ENCODER_CHANNEL_B);

    //Sets up a ratio between robot's movement and the encoder's rotation (aka, rotations to meters)
    leftEncoder.setDistancePerPulse(WHEEL_DIAMETER * Math.PI / ENCODER_TICKS_PER_ROTATION);
    rightEncoder.setDistancePerPulse(WHEEL_DIAMETER * Math.PI / ENCODER_TICKS_PER_ROTATION);

    //Resets encoders to 0
    rightEncoder.reset();
    leftEncoder.reset();

    //Puts encoder values to opposite of their placement on the robot
    rightEncoder.setReverseDirection(false);
    leftEncoder.setReverseDirection(false);
  } //End of method initializeEncoders()

  public void setMotorControllers() {
    //TODO: Change this when converting to Falcon FX
    setMotorControllers(new CANSparkMax(1, MotorType.kBrushless),
        new CANSparkMax(2, MotorType.kBrushless),
        new CANSparkMax(3, MotorType.kBrushless),
        new CANSparkMax(4, MotorType.kBrushless),
        new CANSparkMax(5, MotorType.kBrushless),
        new CANSparkMax(6, MotorType.kBrushless));
  } //end of default method setMotorControllers()

  //TODO: Change parameters when converting to Falcon FX
  public void setMotorControllers(CANSparkMax Left1, CANSparkMax Left2, CANSparkMax Left3, CANSparkMax Right1, CANSparkMax Right2, CANSparkMax Right3) {
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

    //Allows the motors to automatically be ramped up to speed when controlled
    //TODO: change to new method for Falcon FX
    left1.setOpenLoopRampRate(RAMPRATE_TIME);
    right1.setOpenLoopRampRate(RAMPRATE_TIME);

    //Sets up drive train type
    m_drive = new DifferentialDrive(right1, left1);

  } //end of overloaded method setMotorControllers()

  //-------- GETTERS --------\\

  // Returns left speed
  public double getLeftSpeed() {
    return left1.get();
  }

  // Returns right speed
  public double getRightSpeed() {
    return right1.get();
  }

  //Returns the left encoder
  public Encoder getLeftEncoder() {
    return leftEncoder;
  }

  //Returns the average distance with the two encoders
  public double getAverageEncoderDistance() {
    return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2.0;
  }

  //Returns the right encoder
  public Encoder getRightEncoder() {
    return rightEncoder;
  }

  // gets the current position of the robot, based off initial position
  public Pose2d getPose() {
    return odometry.getPoseMeters();
  }

  // gets the speeds at which the encoders run at
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(leftEncoder.getRate(), rightEncoder.getRate());
  }

  // gets the yaw rotation of the robot
  public double getHeading() {
    gyro.getYawPitchRoll(yawPitchRoll);
    return Math.IEEEremainder(yawPitchRoll[0], 360);  //If our gyro is rotated backwards, we must negate this value
  }

  //-------- METHODS --------\\

  // Given Arcade value arguments and sends to motor controllers
  public void runAt(double leftSpeed, double rightSpeed) {
      left1.set(leftSpeed);
      right1.set(rightSpeed);
  }

  //Resets the encoders
  public void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  //Resets the odemetry (basically, sets position back to 0)
  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
  }

  //This drives the the robot by manually setting volts to the motors 
  public void tankDriveVolts(double leftVolts, double rightVolts) {
    right1.setVoltage(leftVolts);
    left1.setVoltage(-rightVolts);
  }
 
  /* TODO: Determine if this is neccessary also
  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }
  */

  /* TODO: Determine if this is even neccessary in the code
  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
  }
  */

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
} //end of the class DriveSubsystem