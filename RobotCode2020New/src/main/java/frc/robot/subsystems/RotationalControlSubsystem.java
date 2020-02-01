/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.logging.*;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import java.util.logging.*;


public class RotationalControlSubsystem extends SubsystemBase {

    //-------- CONSTANTS --------\\

    // Creates an instance of the logger class
    private static final Logger logger = Logger.getLogger(ColorSensorSubsystem.class.getName());
    private CANSparkMax Spark;
    
    //-------- CONSTRUCTOR --------\\

    public RotationalControlSubsystem(){
        Spark = new CANSparkMax(0, MotorType.kBrushless);
    }

    //-------- METHODS --------\\

    public double getMotorSpeed(){
        logger.entering(getClass().getName(), "getMotorController");
        logger.exiting(getClass().getName(), "getMotorController");
        return Spark.get();
    }

    @Override
    public void periodic() {
        logger.entering(getClass().getName(), "periodic");
        logger.exiting(getClass().getName(), "periodic");
    }
}