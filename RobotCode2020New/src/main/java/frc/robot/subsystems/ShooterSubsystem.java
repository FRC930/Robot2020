/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//-------- SUBSYSTEM CLASS --------\\

public class ShooterSubsystem extends SubsystemBase {

    //-------- CONSTANTS --------\\

    //PID Derivitive Gain
    private final double PID_D = 0.004;
    //PID Proportional Gain
    private final double PID_P = 0.0004;
    //PID Feed-Forward Gain
    private final double PID_FF = 0.0002;

    private final int LEAD_MOTOR_ID = 7;
    private final int SLAVE_MOTOR_ID = 9;

    //-------- DECLARATIONS --------\\

    private final Logger logger = Logger.getLogger(ShooterSubsystem.class.getName());

    // motor controllers for the 775 motors on the shooter
    private final CANSparkMax motorLead;
    private final CANSparkMax motor2;

    private CANPIDController pidcontroller;
    
    //TODO: Uncomment once this is added
    // solenoid dedicated to moving the turret up and down to have a close and far range
    //private final Solenoid solenoid;

    //-------- CONSTRUCTOR --------\\
    
    public ShooterSubsystem() {
        this.motorLead = new CANSparkMax(LEAD_MOTOR_ID, MotorType.kBrushless);
        this.motor2 = new CANSparkMax(SLAVE_MOTOR_ID, MotorType.kBrushless);
        
        this.pidcontroller = motorLead.getPIDController();
        //this.pidcontroller.setFF(PID_FF);
        this.pidcontroller.setOutputRange(0, 1);
        this.pidcontroller.setP(PID_P);
        //this.pidcontroller.setD(PID_D);
        motor2.follow(motorLead, true);
        
        //solenoid = new Solenoid(0);
        
        logger.setLevel(Level.INFO); 
    }

    //-------- METHODS --------\\

    public void setSpeed(double speed) {
        logger.entering(this.getClass().getName(), "setSpeed()");

        if(speed <= 1.0 && speed >= 0.0) {
            // Set the speed in percent output * the max RPM of the NEO.
            this.pidcontroller.setReference(speed * 5880, ControlType.kVelocity);
            //motorLead.set(speed);
        }

        logger.log(Level.FINE, "Set shooter speed to " + speed);
        logger.exiting(getClass().getName(), "setSpeed()");
    } // end of method setSpeed()

    public void stop() {
        logger.entering(getClass().getName(), "stop()");
        motorLead.set(0.0);
        logger.exiting(getClass().getName(), "stop()");
    }

    //TODO: Uncomment solenoid code
    public void angleChange(boolean solenoidStatus) {
        logger.entering(getClass().getName(), "angleChange()");
        //solenoid.set(true);
        logger.exiting(getClass().getName(), "angleChange()");
    }

    //TODO: Uncomment solenoid code
    public boolean getAngle() {
        logger.entering(getClass().getName(), "getAngle()");
        logger.exiting(getClass().getName(), "getAngle()");
        //return solenoid.get();
        return true;
    }

    public double getSpeed()
    {
        return motorLead.getEncoder().getVelocity();
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("LeftRPM", motorLead.getEncoder().getVelocity());
        SmartDashboard.putNumber("RightRPM", motor2.getEncoder().getVelocity());
    }
} // end of class ShooterSubsystem