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

public class RunFlywheelAutoCommand extends CommandBase {

    //-------- DECLARATIONS --------\\

    private FlywheelSubsystem m_FlywheelSubsystem;

    private double speed;

    //-------- CONSTRUCTOR --------\\

    public RunFlywheelAutoCommand(FlywheelSubsystem flywheelSubsystem, double speed) {
        m_FlywheelSubsystem = flywheelSubsystem;
        this.speed = speed;
        addRequirements(m_FlywheelSubsystem);
    }

    //-------- COMMANDBASE METHODS --------\\

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_FlywheelSubsystem.setSpeed(speed);     //Set flywheel to default speed.
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
        return true;
    }

} // end of class RunDefaultFlywheelCommand
