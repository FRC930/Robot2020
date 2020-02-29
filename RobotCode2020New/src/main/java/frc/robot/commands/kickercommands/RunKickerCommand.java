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
 
//-------- COMMAND CLASS --------\\

public class RunKickerCommand extends CommandBase {

    //-------- DECLARATIONS --------\\

    private KickerSubsystem kickerSubsystem;
    
    //-------- CONSTRUCTOR --------\\

    public RunKickerCommand(KickerSubsystem kSubsystem){
        kickerSubsystem = kSubsystem;
        addRequirements(kSubsystem);
    }

    //-------- METHODS --------\\
    
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if(((Math.round((((kickerSubsystem.getEncoder() - Constants.KICKER_ENCODER_OFFSET) / 10) % 2) * 100)) % 2) == 1)
        {
            kickerSubsystem.setSpeed(Constants.KICKER_SPEED);
        } else {
            kickerSubsystem.setSpeed(0.0);
        }
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