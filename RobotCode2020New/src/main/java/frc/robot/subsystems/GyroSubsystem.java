/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.geometry.Pose2d;

import java.util.logging.*;

import frc.robot.Constants;

//-------- SUBSYSTEM CLASS --------\\

public class GyroSubsystem extends SubsystemBase {

    //-------- CONSTANTS --------\\

    private static final Logger logger = Logger.getLogger(GyroSubsystem.class.getName());

    //-------- DECLARATIONS --------\\
    
    //The intake talon motor controller, has the gyro attached to it
    private TalonSRX gyroTalon;
    
    //The gyro, used for autonomous
    private PigeonIMU gyro;

    //Values, used to store the yaw, pitch, and roll (the robot's rotation)
    private double yawPitchRollValues[] = new double[3]; 

    public DifferentialDriveOdometry driveOdometry;
    
    //-------- CONSTRUCTOR --------\\

    public GyroSubsystem() {
        gyroTalon = new TalonSRX(Constants.INTAKE_ID);
        gyro = new PigeonIMU(gyroTalon);
        driveOdometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
        zeroHeading();
    }

    // updates the yaw, pitch, and roll values in the array
    public void updateYawPitchRoll() {
        gyro.getYawPitchRoll(yawPitchRollValues);
        logger.log(Level.INFO, yawPitchRollValues.toString());
    }

    public double getHeading() {
        gyro.getYawPitchRoll(yawPitchRollValues);
        return Math.IEEEremainder(yawPitchRollValues[0], 360);
    }

    //Returns the yaw rotation of the robot
    public double getYaw(){
        gyro.getYawPitchRoll(yawPitchRollValues);
        return Math.IEEEremainder(yawPitchRollValues[0], 360);
    }

    public Pose2d getPose() {
        return driveOdometry.getPoseMeters();
    }

    //Resets the yaw rotation
    public void zeroHeading(){
        gyro.setYaw(0.0);
        gyro.setFusedHeading(0.0);
    }

    // @Override
    // public void periodic() {
    //     //TODO: Move this to a default command that uses both drive and gyro subsystem (Most likely DriveCommand)
    //   //driveOdometry.update((Rotation2d.fromDegrees(getHeading())), left1.getRPMLeft(left1),

      
    // }
} // end of subsystem
