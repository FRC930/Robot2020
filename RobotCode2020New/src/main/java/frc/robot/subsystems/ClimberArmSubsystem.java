/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.subsystems;

import frc.robot.Constants;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//--------COMMANDS--------\\



//--------SUBSYSTEM CLASS--------\\

public class ClimberArmSubsystem extends SubsystemBase {

    //--------CONSTANTS--------\\
    private static final Logger logger = Logger.getLogger(ClimberArmSubsystem.class.getName());

    //-------- DECLARATIONS --------\\
    
    // VictorSPX is a motor controller that makes the arm go up and down for when we climb for endgame
    private VictorSPX ClimberArmMotor;

    public void setSpeed(double speed){
        logger.entering(this.getClass().getName(), "Extend");
        logger.log(Constants.LOG_LEVEL_INFO, "motorSpeed: " + speed);

        ClimberArmMotor.set(ControlMode.PercentOutput, speed);

        logger.exiting(this.getClass().getName(), "Extend");  
    }

    public double getSpeed(){ 
        logger.entering(this.getClass().getName(),"getSpeed");
        logger.log(Constants.LOG_LEVEL_INFO, "motorSpeed: " + ClimberArmMotor.getMotorOutputPercent());
        logger.exiting(this.getClass().getName(),"getSpeed");
        
        return ClimberArmMotor.getMotorOutputPercent();
    }
    
    public void stopMotor() {
        ClimberArmMotor.set(ControlMode.PercentOutput, 0.0);
    }
}       // End of ClimberArm Subsystem