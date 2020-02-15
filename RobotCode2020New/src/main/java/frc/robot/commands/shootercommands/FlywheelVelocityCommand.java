package frc.robot.commands.shootercommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.FlywheelAngleSubsystem;
import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.utilities.ShooterMath;

/**
 * <h3>ShooterVelocity</h3>
 * <p>
 * The shooter velocity command will set the speed of the shooter based on the
 * calculations returned from the math class
 * </p>
 */
public class FlywheelVelocityCommand extends CommandBase {

    private FlywheelAngleSubsystem m_FlywheelAngleSubsystem;
    private FlywheelSubsystem m_FlywheelSubsystem;
    private LimelightSubsystem m_LimelightSubsystem;
    private ShooterMath math = ShooterMath.getInstance();

    public FlywheelVelocityCommand(FlywheelAngleSubsystem flywheelAngleSubsystem, FlywheelSubsystem flywheelSubsystem,
            boolean solenoidStatus, LimelightSubsystem limelightSubsystem)
    {
        m_FlywheelAngleSubsystem = flywheelAngleSubsystem;
        m_FlywheelSubsystem = flywheelSubsystem;
        m_LimelightSubsystem = limelightSubsystem;
        
        addRequirements(m_FlywheelAngleSubsystem, m_FlywheelSubsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // FlywheelSubsystem get angle
        // Limelight get distance
        this.math.setPosition(m_FlywheelAngleSubsystem.get() ? 31.4 : 39, m_LimelightSubsystem.getDistance());
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
