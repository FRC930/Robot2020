/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.utilities;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import edu.wpi.first.wpilibj.SpeedController;

import java.util.logging.*;

/*
* TalonFXSpeedController is made to object cast a TalonFX motor to a SpeedController for the DifferentialDrive() used for autonomous.
*
* extends to TalonFX class, implements SpeedController interface.
*/
public class TalonFXSpeedController extends TalonFX implements SpeedController {

    private final Logger logger = Logger.getLogger(TalonFXSpeedController.class.getName());

    public TalonFXSpeedController(int deviceNumber) {
        super(deviceNumber);
        logger.setLevel(Constants.LOG_LEVEL_FINE);
    }

    /*
     * disable() sets the motor output to neutral.
     * 
     * super goes to BaseMotorController class above TalonFX and BaseTalon.
     */
    public void disable() {
     
        super.neutralOutput();
    }

    /*
     * Gets the output percentage of the motor controller.
     * 
     * super goes to BaseMotorController class above TalonFX and BaseTalon.
     */
    @Override
    public double get() {
      
        return super.getMotorOutputPercent();
    }

    /*
     * Sets speed by percent from -1 to 1. alegator
     */
    public void set(double speed) { // that ----------------.
  
        logger.entering(this.getClass().getName(), "set()");
        super.set(TalonFXControlMode.PercentOutput, speed);
        logger.exiting(this.getClass().getName(), "set()");// |
    } // |
      // |
      // to
    /*
     * // | Stops motor by setting speed to 0.0. // |
     */ // |

    public void stopMotor() { // |
      
        // this.set(0.0); //this -----------------------------'

        // throw new RuntimeException("thingy");
    }

    public void pidWrite(double output) {
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
        logger.entering(getClass().getName(), "resetEncoders()");
        right1.setSelectedSensorPosition(0);
        left1.setSelectedSensorPosition(0);
        logger.exiting(this.getClass().getName(), "resetEncoders()");
    }

}