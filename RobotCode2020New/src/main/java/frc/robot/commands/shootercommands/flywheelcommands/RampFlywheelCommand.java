/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.shootercommands.flywheelcommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FlywheelSubsystem;

//-------- COMMAND CLASS --------\\

public class RampFlywheelCommand extends CommandBase {

    // -------- DECLARATIONS --------\\

    private FlywheelSubsystem m_FlywheelSubsystem;

    // -------- CONSTRUCTOR --------\\

    public RampFlywheelCommand(FlywheelSubsystem flywheelSubsystem) {
        m_FlywheelSubsystem = flywheelSubsystem;
        addRequirements(m_FlywheelSubsystem);
    }

    //-------- COMMANDBASE METHODS --------\\

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_FlywheelSubsystem.setSpeed(1.0);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        // boolean vreturn = false;
        // System.out.println(m_FlywheelSubsystem.getPercentOutput());
        // if(m_FlywheelSubsystem.getPercentOutput() >= 0.5)
        // {
        //     //m_FlywheelSubsystem.setSpeed(Constants.FLYWHEEL_SPEED);
        //     vreturn = true;
        // }
        System.out.println(m_FlywheelSubsystem.getPercentOutput());
        return m_FlywheelSubsystem.getPercentOutput() >= 0.6;
    }

} // end of class RunDefaultFlywheelCommand
