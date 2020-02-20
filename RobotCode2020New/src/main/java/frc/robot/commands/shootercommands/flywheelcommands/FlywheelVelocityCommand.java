/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.shootercommands.flywheelcommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FlywheelSubsystem;

//-------- COMMANDBASE CLASS --------\\

public class FlywheelVelocityCommand extends CommandBase {

    //-------- DECLARATIONS --------\\

    private FlywheelSubsystem m_FlywheelSubsystem;
    private double m_Speed;

    //-------- CONSTRUCTOR --------\\

    public FlywheelVelocityCommand(FlywheelSubsystem flywheelSubsystem, double speed) {
        m_FlywheelSubsystem = flywheelSubsystem;
        addRequirements(m_FlywheelSubsystem);
        m_Speed = speed;
    }

    //--------- COMMANDBASE METHODS ----------\\

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // Set velocity in m/s of ball to shoot from shooter.
        m_FlywheelSubsystem.setVelocity(m_Speed);
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
} // end of class FlywheelVelocityCommand
