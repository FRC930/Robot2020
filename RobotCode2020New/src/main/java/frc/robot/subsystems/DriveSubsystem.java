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
//import frc.robot.utilities.ShuffleboardUtility;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

//-------- SUBSYSTEM CLASS --------\\

public class DriveSubsystem extends SubsystemBase {

  // -------- CONSTANTS --------\\
  private final double DRIVE_GEAR_RATIO = 9.88;
  private static final Logger logger = Logger.getLogger(DriveSubsystem.class.getName());

  // -------- DECLARATIONS --------\\
  private WPI_TalonFX right1;
  private WPI_TalonFX right2;
  private WPI_TalonFX left1;
  private WPI_TalonFX left2;
  //private ShuffleboardUtility shuffleboardUtility;

  // The intake talon motor controller, has the gyro attached to it
  private TalonSRX gyroTalon;

  // The gyro, used for autonomous
  private PigeonIMU gyro;

  private Solenoid shifter;
  private Solenoid endgameClamp;

  // Values, used to store the yaw, pitch, and roll (the robot's rotation)
  private double yawPitchRollValues[] = new double[3];

  // The odometry of the differential drive
  private DifferentialDriveOdometry driveOdometry;

  // The differential drive object itself
  private DifferentialDrive differentialDrive;

  // -------- CONSTRUCTOR --------\\

  public DriveSubsystem() {
    setDriveMotors();
  }

  // -------- METHODS --------\\

  private void setDriveMotors() {

    // instantiates the drive motors
    right1 = new WPI_TalonFX(Constants.DRIVE_RIGHT_FRONT_ID);
    right2 = new WPI_TalonFX(Constants.DRIVE_RIGHT_BACK_ID);
    left1 = new WPI_TalonFX(Constants.DRIVE_LEFT_FRONT_ID);
    left2 = new WPI_TalonFX(Constants.DRIVE_LEFT_BACK_ID);

    // the talon that controls intake, used to get the piston
    // TODO: Change this because IntakeSubsystem already instantiates this!
    gyroTalon = new TalonSRX(Constants.INTAKE_ID);

    // the gyro attached to the talon, used to track position and rotation
    // TODO: Change this because GyroSubsystem already instantiates this!
    gyro = new PigeonIMU(gyroTalon);
   
    shifter = new Solenoid(Constants.SHIFTER_SOLENOID_ID);

    //shuffleboardUtility = ShuffleboardUtility.getInstance();

    driveOdometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));

    // Inverts the direction of the drive motors
    left1.setInverted(true);
    left2.setInverted(true);
    right1.setInverted(false);
    right2.setInverted(false);

    // Resets drive motor encoders to 0
    left1.getSensorCollection().setIntegratedSensorPosition(0.0, 100);
    right1.getSensorCollection().setIntegratedSensorPosition(0.0, 100);

    left1.setSensorPhase(true);
    left2.setSensorPhase(true);
    right1.setSensorPhase(false);
    right2.setSensorPhase(false);

    // Mirror primary motor controllers on each side
    left2.follow(left1);
    right2.follow(right1);

    // Sets the ramp rate of the robot, this will need to be configued
    left1.configOpenloopRamp(Constants.MOTOR_RAMP_RATE);
    right1.configOpenloopRamp(Constants.MOTOR_RAMP_RATE);
    // Sets up the differntial drive
    // drive = new DifferentialDrive(right1, left1);
    shifter.set(true);
    endgameClamp.set(true);
  }

  public void setShifterState(boolean state) {
    logger.log(Constants.LOG_LEVEL_FINE, "New shifter state: " + state);
    shifter.set(state);
  }

  public void setEndgameClampState(boolean state) {
    logger.log(Constants.LOG_LEVEL_FINE, "Endgame clamp state: " + state);
    endgameClamp.set(state);
  }

  /**
   * Sets the left and right drivetrain motors to the speeds passed through the
   * parameters
   * 
   * @param leftSpeed  The speed of the left drivetrain motors
   * @param rightSpeed The speed of the right drivetrain motors
   */
  public void runAt(double leftSpeed, double rightSpeed) {
    logger.entering(DriveSubsystem.class.getName(), "runAt()");

    //TODO: We should be seeing this on the Shuffleboard
    logger.log(Constants.LOG_LEVEL_FINE, "New left speed: " + leftSpeed + "|| New right speed: " + rightSpeed);
    logger.log(Constants.LOG_LEVEL_FINE, "running " + "left encoder " + getLeftWheelRotations() + " | right encoder " + getRightWheelRotations());

    if(!shifter.get()) {
      leftSpeed *= 0.3;
      rightSpeed *= 0.3;
    }
    
    left1.set(leftSpeed);
    right1.set(rightSpeed);

    logger.exiting(DriveSubsystem.class.getName(), "runAt()");
  } // end of method runAt()

  /**
     * Returns the speed of the left drivetrain motors
     * 
     * @return The left drivetrain motor speed, ranging from -1 to 1
     */
  public double getLeftSpeed() {
    return left1.getMotorOutputPercent();
  }

  /**
     * Returns the speed of the right drivetrain motors
     * 
     * @return The right drivetrain motor speed, ranging from -1 to 1
     */
  public double getRightSpeed() {
    return right1.getMotorOutputPercent();
  }

  /**
     * Returns the gyro's yaw, in degrees
     * 
     * @return The gyro's yaw value, in degrees
     */
  public double getHeading() {
    gyro.getYawPitchRoll(yawPitchRollValues);
    return Math.IEEEremainder(yawPitchRollValues[0], 360);
  }

  /**
     * Returns the number of rotations from the left side of the drivetrain
     * 
     * @return A double, the # of rotations from the left drivetrain
     */
  public double getLeftWheelRotations() {
    return left1.getSelectedSensorPosition() * ((1.0 / 2048.0) * 0.152 * Math.PI) / DRIVE_GEAR_RATIO;
  }

  /**
     * Returns the number of rotations from the right side of the drivetrain
     * 
     * @return A double, the # of rotations from the right drivetrain
     */
  public double getRightWheelRotations() {
      return right1.getSelectedSensorPosition() * ((1.0 / 2048.0) * 0.152 * Math.PI) / DRIVE_GEAR_RATIO;
  }

  /**
     * Returns the speeds in the differential drive
     * 
     * @return A DifferentialDriveWheelSpeeds object, has the rotations of the left and right wheels
     */
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(getLeftWheelRotations(), getRightWheelRotations());
  }

  /**
     * Resets the gyro
     */
  public void resetOdometry(Pose2d pose) {
    driveOdometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
    logger.log(Constants.LOG_LEVEL_FINE, "Odometer reset");
  }

  /**
     * Runs the drivetrain motors, but by sending the voltage amount instead  
     */
  public void tankDriveVolts(double leftVolts, double rightVolts) {
    logger.entering(DriveSubsystem.class.getName(), "tankDriveVolts()");

    logger.log(Constants.LOG_LEVEL_FINE, "Drivetrain Moving: " + leftVolts + " " + rightVolts);

    //TODO: Change for Prac Robot
    // right1.setVoltage(rightVolts);
    // left1.setVoltage(-leftVolts);

    //TODO: Change for Comp Robot
    right1.setVoltage(rightVolts);
    left1.setVoltage(leftVolts);

    logger.exiting(DriveSubsystem.class.getName(), "tankDriveVolts()");
  } // end of method tankDriveVolts()

  //-- TODO: Update getLeftEncoder() and getRightEncoder()

  /**
     * Gets the left drivetrain encoder value
     * 
     * @return A double, of the getLeftWheelRotations()
     */
  public double getLeftEncoder() {
    return getLeftWheelRotations();
  }

  /**
     * Gets the right drivetrain encoder value
     * 
     * @return A double, of the getRightWheelRotations()
     */
  public double getRightEncoder() {
    return getRightWheelRotations();
  }

  /**
     * Returns the position from the odometry
     * 
     * @return A Pose2d object, from the drive odometry
     */
  public Pose2d getPose() {
    return driveOdometry.getPoseMeters();
  }

  /**
     * Sets the max output the differential drive can give
     * 
     * @param maxOutput A double, specifying the maxiumum power that can be given by the differential drive
     */
  public void setMaxOutput(double maxOutput) {
    differentialDrive.setMaxOutput(maxOutput);
    logger.log(Constants.LOG_LEVEL_FINE, "New max output: " + maxOutput);
  }

  @Override
  public void periodic() {
    //logger.entering(DriveSubsystem.class.getName(), "periodic()");

    driveOdometry.update(Rotation2d.fromDegrees(getHeading()), getLeftWheelRotations(), getRightWheelRotations());
    //shuffleboardUtility.setGyroYaw(getHeading());

    //logger.log(Constants.LOG_LEVEL_FINE, "Rotations: " + Rotation2d.fromDegrees(getHeading()) + "|| Left wheel rotations: " + getLeftWheelRotations() + "|| Right wheel rotations " + getRightWheelRotations());
    //logger.exiting(DriveSubsystem.class.getName(), "periodic()");  
  }

} // end of the class DriveSubsystem