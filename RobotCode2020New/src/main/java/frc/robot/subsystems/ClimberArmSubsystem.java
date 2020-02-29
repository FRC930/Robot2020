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

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//--------COMMANDS--------\\



//--------SUBSYSTEM CLASS--------\\

public class ClimberArmSubsystem extends SubsystemBase {

    //--------CONSTANTS--------\\
    private static final Logger logger = Logger.getLogger(ClimberArmSubsystem.class.getName());

    //-------- DECLARATIONS --------\\
    private DutyCycleEncoder encoder;
    private double encoderPostition;
    
    // VictorSPX is a motor controller that makes the arm go up and down for when we climb for endgame
    private VictorSPX ClimberArmMotor;

    // -------- CONSTRUCTOR --------\\

    public ClimberArmSubsystem() {
        ClimberArmMotor = new VictorSPX(Constants.CLIMBER_ARM_ID);
        this.encoder = new DutyCycleEncoder(Constants.CLIMBER_ENCODER_PORT_ID);
        this.encoder.reset();
    }

    // -------- METHODS --------\\

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
    public double getRawEncoderPosition(){
        return this.encoder.get();
    }
}       // End of ClimberArm Subsystem