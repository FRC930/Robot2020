/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.logging.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ColorWheelSpinnerSubsystem extends SubsystemBase {

    //-------- CONSTANTS --------\\

    //private TalonSRX talon;
    private CANSparkMax spark1;

    //--- Ports
    private final int COLOR_WHEEL_PORT = 7;
    //-------- DECLARATIONS --------\\

    // Creates an instance of the logger class
    private Logger logger = Logger.getLogger(ColorWheelSpinnerSubsystem.class.getName());

    //-------- CONSTRUCTOR --------\\

    public ColorWheelSpinnerSubsystem(){
        //talon = new TalonSRX(COLOR_WHEEL_PORT);
        spark1 = new CANSparkMax(COLOR_WHEEL_PORT, MotorType.kBrushless);
    }

    //-------- METHODS --------\\

    // Returns the speed of the motor controller
    public double getMotorSpeed(){
        logger.entering(this.getClass().getName(), "getMotorSpeed");
        logger.exiting(this.getClass().getName(), "getMotorSpeed");
        //return talon.getMotorOutputPercent();
        return spark1.get();
    }

    // Sets the motor speed to a value between -1.0 and 1.0
    public void setMotorSpeed(double speed){
        logger.entering(this.getClass().getName(), "setMotorSpeed");
        //talon.set(ControlMode.PercentOutput, speed);
        spark1.set(speed);
        //spark2.set(-speed);
        // Logs the speed from the motor controller
        logger.log(Level.FINE, "Motor Speed: " + getMotorSpeed());
        logger.exiting(this.getClass().getName(), "setMotorSpeed");
    }
} // End of class