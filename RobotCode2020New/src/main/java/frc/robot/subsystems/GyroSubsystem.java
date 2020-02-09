package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class GyroSubsystem extends SubsystemBase {

    //The intake talon motor controller, has the gyro attached to it
    private TalonSRX gyroTalon;
    
    //The gyro, used for autonomous
    private PigeonIMU gyro;

    //Values, used to store the yaw, pitch, and roll (the robot's rotation)
    private double yawPitchRollValues[] = new double[3]; 
    
    //-------- CONSTRUCTOR --------\\

    public GyroSubsystem() {
        gyroTalon = new TalonSRX(Constants.INTAKE_ID);
        gyro = new PigeonIMU(gyroTalon);
    }

    // updates the yaw, pitch, and roll values in the array
    public void updateYawPitchRoll() {
        gyro.getYawPitchRoll(yawPitchRollValues);
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

    //Resets the yaw rotation
    public void zeroHeading(){
        gyro.setYaw(0.0);
        gyro.setFusedHeading(0.0);
    }
} // end of subsystem