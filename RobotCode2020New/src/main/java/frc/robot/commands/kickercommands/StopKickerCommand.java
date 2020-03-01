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

//-------- COMMAND CLASS --------\\

public class StopKickerCommand extends CommandBase {

    //-------- DECLARATIONS --------\\

    private KickerSubsystem kickerSubsystem;

    //-------- CONSTRUCTOR --------\\

    public StopKickerCommand(KickerSubsystem kSubsystem) {
        kickerSubsystem = kSubsystem;
        addRequirements(kSubsystem);
    }

    //-------- METHODS --------\\

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        kickerSubsystem.stopMotor();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
} // end of class StopKickerCommand