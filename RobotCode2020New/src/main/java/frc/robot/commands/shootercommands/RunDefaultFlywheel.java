/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shootercommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FlywheelSubsystem;

public class RunDefaultFlywheel extends CommandBase {

    private FlywheelSubsystem m_FlywheelSubsystem;
    private double m_Speed;

    public RunDefaultFlywheel(FlywheelSubsystem flywheelSubsystem, double speed) {
        m_FlywheelSubsystem = flywheelSubsystem;
        addRequirements(m_FlywheelSubsystem);

        m_Speed = speed;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        // if (m_FlywheelSubsystem.getPercentOutput() > m_Speed) {
        //     //m_ShooterSubsystem.stop();
        // } else {
        //     m_FlywheelSubsystem.setSpeed(m_Speed);
        // }

        //Set flywheel to default speed.
        m_FlywheelSubsystem.setSpeed(m_Speed);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

}
