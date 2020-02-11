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
        addRequirements(kickerSubsystem);
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
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        //kickerSubsystem.setSpeed(0.0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
} // end of class RunKickerCommand