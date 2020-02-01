/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.logging.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class RotationalControlSubsystem extends SubsystemBase {

    //-------- CONSTANTS --------\\

    private VictorSPX victor;

    //--- Ports
    private final int COLOR_WHEEL_PORT = 4;
    //-------- DECLARATIONS --------\\

    // Creates an instance of the logger class
    private Logger logger = Logger.getLogger(RotationalControlSubsystem.class.getName());

    //-------- CONSTRUCTOR --------\\

    public RotationalControlSubsystem(){
        victor = new VictorSPX(COLOR_WHEEL_PORT);
    }

    //-------- METHODS --------\\

    // Returns the speed of the motor controller
    public double getMotorSpeed(){
        logger.entering(this.getClass().getName(), "getMotorSpeed");
        logger.exiting(this.getClass().getName(), "getMotorSpeed");
        return victor.getMotorOutputPercent();
    }

    // Sets the motor speed to a value between -1.0 and 1.0
    public void setMotorSpeed(double speed){
        logger.entering(this.getClass().getName(), "setMotorSpeed");
        victor.set(ControlMode.PercentOutput, speed);
        // Logs the speed from the motor controller
        logger.log(Level.FINE, "VictorSPX speed: " + getMotorSpeed());
        logger.exiting(this.getClass().getName(), "setMotorSpeed");
    }
} // End of class