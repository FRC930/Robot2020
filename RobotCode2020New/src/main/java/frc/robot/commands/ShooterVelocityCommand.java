package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.utilities.ShooterMath;

/**
 * <h3>ShooterVelocity</h3>
 * <p>
 * The shooter velocity command will set the speed of the shooter based on the
 * calculations returned from the math class
 * </p>
 */
public class ShooterVelocityCommand extends CommandBase {

    private ShooterSubsystem m_ShooterSubsystem;
    private ShooterMath math = new ShooterMath();

    public ShooterVelocityCommand(ShooterSubsystem shooterSubsystem, boolean solenoidStatus) {
        m_ShooterSubsystem = shooterSubsystem;
        addRequirements(m_ShooterSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // ShooterSubsystem get angle
        // Limelight get distance
        // TODO: Get the distance and angle
        this.math.setPosition(/* ShooterSubsystem angle */ m_ShooterSubsystem.getAngle() ? 45 : 60,
                /* Limelight distance */ 0);
        CommandScheduler.getInstance().schedule(new RunShooterCommand(m_ShooterSubsystem, this.math.getVelocity()));
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
