/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.utilities;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import java.util.logging.*;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;

/*
* TalonFXSpeedController is made to object cast a TalonFX motor to a SpeedController for the DifferentialDrive() used for autonomous.
*
* extends to TalonFX class, implements SpeedController interface.
*/
public class TalonFXSpeedController extends TalonFX implements SpeedController {
    // private Logger logger;
    private SmartDashboard smartDashboard;

    /*
     * constructor.
     */
    public TalonFXSpeedController(int deviceNumber) {
        super(deviceNumber);
        //System.out.println("constructor");
        // logger = // logger.getLogger(TalonFXSpeedController.class.getName());
        //// logger.setLevel(Level.OFF);

    }

    /*
     * disable() sets the motor output to neutral.
     * 
     * super goes to BaseMotorController class above TalonFX and BaseTalon.
     */
    public void disable() {
        // System.out.println("void disable");
        super.neutralOutput();
    }

    /*
     * Gets the output percentage of the motor controller.
     * 
     * super goes to BaseMotorController class above TalonFX and BaseTalon.
     */
    @Override
    public double get() {
        // System.out.println("double get "+super.getMotorOutputPercent());
        return super.getMotorOutputPercent();
    }

    /*
     * Sets speed by percent from -1 to 1. alegator
     */
    public void set(double speed) { // that ----------------.
        // System.out.println("void set");
        // logger.entering(this.getClass().getName(), "set()");
        super.set(TalonFXControlMode.PercentOutput, speed);
        // logger.exiting(this.getClass().getName(), "set()");// |
    } // |
      // |
      // to
    /*
     * // | Stops motor by setting speed to 0.0. // |
     */ // |

    public void stopMotor() { // |
        // System.out.println("void stopmotor");
        // this.set(0.0); //this -----------------------------'
        // System.out.flush();
        // throw new RuntimeException("thingy");
    }

    // public void set(TalonFXControlMode mode, double speed) { //that
    // ----------------.
    // System.out.println("void set22");
    // super.set(mode, speed); // |
    // }

    // public void setVolts(double speed){
    // this.set(TalonFXControlMode.Current, speed);
    // }
    /*
     * Since pidWrite is never called within the DifferentialDrive() class, we do
     * not need to fill in anything.
     * 
     * pidWrite is located within SpeedController and PIDOutput class.
     */
    public void pidWrite(double output) {
        //System.out.println("pidwrite");
    }

    public double getRPMLeft(TalonFXSpeedController left1) {
        double rotationML;
        rotationML = -left1.getSelectedSensorPosition() * ((1 / 2048) * 0.152 * Math.PI);
        //smartDashboard.get
        return rotationML;
    }

    public double getRPMRight(TalonFXSpeedController right1) {
        double rotationMR;
        rotationMR = right1.getSelectedSensorPosition() * ((1 / 2048) * 0.152 * Math.PI);
        return rotationMR;
    }

    public void resetEncoders(TalonFXSpeedController right1, TalonFXSpeedController left1) {
        // logger.entering(getClass().getName(), "resetEncoders()");
        right1.setSelectedSensorPosition(0);
        left1.setSelectedSensorPosition(0);
        // logger.exiting(this.getClass().getName(), "resetEncoders()");
    }

}