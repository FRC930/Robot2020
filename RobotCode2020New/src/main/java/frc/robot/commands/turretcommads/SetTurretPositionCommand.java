/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.turretcommads;

import java.util.logging.*;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Constants;
import frc.robot.subsystems.TurretSubsystem;

//-------- COMMAND CLASS --------\\

public class SetTurretPositionCommand extends CommandBase {

    //You must include logger as a constant variable, and you must have logging in your files
    private static final Logger logger = Logger.getLogger(SetTurretPositionCommand.class.getName());

    private double turretPosition;
    private double targetPosition;
    private double speed;

    private TurretSubsystem turretSubsystem;    
    
    public SetTurretPositionCommand(TurretSubsystem turretSubsystem, double targetPosition){
        this.turretSubsystem = turretSubsystem;
        this.targetPosition = targetPosition;
        addRequirements(turretSubsystem);
    }

    //-------- METHODS --------\\
    
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {   
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {  
        turretPosition = turretSubsystem.getRawEncoderPosition();

        logger.log(Level.INFO, "targetPosition = " + targetPosition);
        logger.log(Level.INFO, "turretPosition = " + turretPosition);

        if(Math.abs(turretPosition - targetPosition) > Constants.TURRET_DEADBAND){
            logger.log(Level.INFO, "| turretPosition - targetPosition | >  Constants.TURRET_DEADBAND("+Constants.TURRET_DEADBAND+")");
            if(turretPosition < targetPosition) {
                logger.log(Level.INFO, "seting 'speed' to -Constants.TURRET_TURNING_SPEED("+-Constants.TURRET_TURNING_SPEED+")");
                speed = -Constants.TURRET_TURNING_SPEED;
            } else if(turretPosition > targetPosition) {
                logger.log(Level.INFO, "seting 'speed' to Constants.TURRET_TURNING_SPEED("+Constants.TURRET_TURNING_SPEED+")");
                speed = Constants.TURRET_TURNING_SPEED;
            }  
        } else {
            logger.log(Level.INFO, "| turretPosition - targetPosition | <  Constants.TURRET_DEADBAND("+Constants.TURRET_DEADBAND+")");
            speed = 0;
        }
        
        turretSubsystem.setSpeed(speed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return speed == 0;
    }
} // end of class TurretBackCommand 

