package frc.robot.commands.shootercommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class RunShooterVelocityCommand extends CommandBase {

    private ShooterSubsystem m_ShooterSubsystem;
    private double m_Speed;

    public RunShooterVelocityCommand(ShooterSubsystem shooterSubsystem, double speed) {
        m_ShooterSubsystem = shooterSubsystem;
        addRequirements(m_ShooterSubsystem);

        m_Speed = speed;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_ShooterSubsystem.setVelocity(m_Speed);
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
}
