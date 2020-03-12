/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.kickercommands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.KickerSubsystem;
import frc.robot.Constants;

//import java.util.logging.Logger;


 
//-------- COMMAND CLASS --------\\

public class RunKickerCommand extends CommandBase {

    //-------- DECLARATIONS --------\\

    private KickerSubsystem kickerSubsystem;
    //private static final Logger logger = Logger.getLogger(RunKickerCommand.class.getName());
    
    //-------- CONSTRUCTOR --------\\

    public RunKickerCommand(KickerSubsystem kSubsystem){
        kickerSubsystem = kSubsystem;
        addRequirements(kSubsystem);
    }

    //-------- METHODS --------\\
    
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        kickerSubsystem.setSpeed(Constants.KICKER_SPEED);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // logger.log(Constants.LOG_LEVEL_FINE, "Math.round((((" + kickerSubsystem.getEncoder() + " - " + Constants.KICKER_ENCODER_OFFSET +"-" + "/ 10) % 2) * 100)) % 2) == 1");
        // if(((Math.round((((kickerSubsystem.getEncoder() - Constants.KICKER_ENCODER_OFFSET) / 10) % 2) * 100)) % 2) == 1)
        // {
        //     logger.log(Constants.LOG_LEVEL_FINE, "kickers running");
        //     kickerSubsystem.setSpeed(Constants.KICKER_SPEED);
        // } else {
        //     kickerSubsystem.setSpeed(0.0);
        //     logger.log(Constants.LOG_LEVEL_FINE, "kickers not running");
        // }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
} // end of class RunKickerCommand