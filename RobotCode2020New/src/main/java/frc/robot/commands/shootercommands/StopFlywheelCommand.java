/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.shootercommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

//-------- COMMAND CLASS --------\\

public class StopFlywheelCommand extends CommandBase {

    //-------- CONSTANTS --------\\

    //-------- DECLARATIONS --------\\

    private ShooterSubsystem m_ShooterSubsystem;

    //-------- CONSTRUCTOR --------\\

    public StopFlywheelCommand(ShooterSubsystem shooterSubsystem) {
        m_ShooterSubsystem = shooterSubsystem;
        addRequirements(m_ShooterSubsystem);
    }

    //-------- METHODS --------\\

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_ShooterSubsystem.setSpeed(0.0);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
} // end of class StopFlywheelCommand
