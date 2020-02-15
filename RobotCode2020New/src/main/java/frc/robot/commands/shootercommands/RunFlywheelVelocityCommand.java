package frc.robot.commands.shootercommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FlywheelSubsystem;

public class RunFlywheelVelocityCommand extends CommandBase {

    private FlywheelSubsystem m_FlywheelSubsystem;
    private double m_Speed;

    public RunFlywheelVelocityCommand(FlywheelSubsystem flywheelSubsystem, double speed) {
        m_FlywheelSubsystem = flywheelSubsystem;
        addRequirements(m_FlywheelSubsystem);

        m_Speed = speed;
    }

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
}
