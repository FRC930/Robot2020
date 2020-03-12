/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//TODO: Uncomment PID stuff and configure it

//-------- IMPORTS --------\\

package frc.robot.subsystems;

import frc.robot.Constants;
//import frc.robot.utilities.ShuffleboardUtility;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.logging.Logger;

//import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//-------- SUBSYSTEM CLASS --------\\

public class FlywheelSubsystem extends SubsystemBase {

    // -------- CONSTANTS --------\\

    // PID Derivitive Gain
    //private final double PID_D = 0.02;
    // PID Proportional Gain
    //private final double PID_P = 0.0001;
    // PID Feed-Forward Gain
    //private final double PID_FF = 0.00025;

    private final double SLOPE = 0.06;
    private final double Y_INTERCEPT = -0.05;

    // -------- DECLARATIONS --------\\

    private static final Logger logger = Logger.getLogger(FlywheelSubsystem.class.getName());
    //private ShuffleboardUtility shuffleboardUtility;

    // motor controllers for the NEO motors on the shooter
    private final CANSparkMax motorLead;
    private final CANSparkMax motor2;

    //private CANPIDController pidcontroller;

    // -------- CONSTRUCTOR --------\\

    public FlywheelSubsystem() {

        // Motor declaration
        this.motorLead = new CANSparkMax(Constants.SHOOTER_LEAD_ID, MotorType.kBrushless);
        this.motor2 = new CANSparkMax(Constants.SHOOTER_SLAVE_ID, MotorType.kBrushless);

        //TODO: Config PID
        // Setting our PID values
        // this.pidcontroller = motorLead.getPIDController();
        // this.pidcontroller.setFF(PID_FF);
        // this.pidcontroller.setOutputRange(0, 1);
        // this.pidcontroller.setP(PID_P);
        // this.pidcontroller.setD(PID_D);

        // Follow lead reverse speed
        motor2.follow(motorLead, true);

        //shuffleboardUtility = ShuffleboardUtility.getInstance();
    }

    // -------- METHODS --------\\

    public void setSpeed(double speed) {
        logger.entering(FlywheelSubsystem.class.getName(), "setSpeed()");

        // Set PID to speed up flywheel
        // this.pidcontroller.setReference(speed * 5880, ControlType.kVelocity);
        //motorLead.set(-speed * 5880 * PID_FF);
        motorLead.set(-speed);

        logger.log(Constants.LOG_LEVEL_FINE, "Set shooter speed to " + speed);
        logger.exiting(FlywheelSubsystem.class.getName(), "setSpeed()");
    } // end of method setSpeed()

    public void setVelocity(double metersPerSecond) {
        this.setSpeed((this.SLOPE * metersPerSecond) - this.Y_INTERCEPT);
    }

    public void stop() {
        logger.entering(FlywheelSubsystem.class.getName(), "stop()");

        // Set motors to stop without PID to allow them to coast down
        motorLead.set(0.0);

        logger.exiting(FlywheelSubsystem.class.getName(), "stop()");
    }

    public double getSpeed() {
        return motorLead.getEncoder().getVelocity();
    }

    public double getPercentOutput() {
        return (motorLead.getEncoder().getVelocity() / 5880);
    }

    public boolean isShooterUpToSpeed(){
        if (getPercentOutput() >= Constants.FLYWHEEL_TELEOP_SPEED){
            return true;
        }
        else{
            return false;
        }
    }

    public double getVoltage() {
        return motorLead.getBusVoltage();
    }

} // end of class ShooterSubsystem