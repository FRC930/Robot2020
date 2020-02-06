/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.commands.shootercommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.TowerSubsystem;
import frc.robot.Constants;

 
public class HopperTurretCommand extends CommandBase {

    private TowerSubsystem m_TowerSubsystem;
    private HopperSubsystem m_HopperSubsystem;
    public HopperTurretCommand(HopperSubsystem HopperSubsystem, TowerSubsystem TowerSubsystem){
        m_HopperSubsystem = HopperSubsystem;
        addRequirements(m_HopperSubsystem);

        m_TowerSubsystem = TowerSubsystem;
        addRequirements(m_TowerSubsystem);

    }
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_HopperSubsystem.setSpeed(Constants.HOPPER_SHOOTING_SPEED);
        m_TowerSubsystem.setSpeed(Constants.TOWER_SPEED);
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
}