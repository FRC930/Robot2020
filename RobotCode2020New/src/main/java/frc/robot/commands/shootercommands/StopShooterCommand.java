/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.shootercommands;

import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

//-------- COMMAND CLASS --------\\

public class StopShooterCommand extends CommandBase {

    //-------- DECLARATIONS --------\\

    private ShooterSubsystem m_ShooterSubsystem;

    //-------- CONSTRUCTOR --------\\

    public StopShooterCommand(ShooterSubsystem shooterSubsystem) {
        m_ShooterSubsystem = shooterSubsystem;
        addRequirements(m_ShooterSubsystem);
    }

    //-------- COMMANDBASE METHODS --------\\
    
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_ShooterSubsystem.stop();
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

} // end of command class StopShooterCommand
