package frc.robot.commands.towercommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TowerSubsystem;

public class StopTowerCommand extends CommandBase {

    private TowerSubsystem m_TowerSubsystem;

    public StopTowerCommand(TowerSubsystem TowerSubsystem) {
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
        m_TowerSubsystem.stopMotor();
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