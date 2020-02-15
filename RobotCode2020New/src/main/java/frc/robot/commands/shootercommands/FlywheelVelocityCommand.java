package frc.robot.commands.shootercommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import frc.robot.subsystems.FlywheelPistonSubsystem;
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

    private FlywheelPistonSubsystem flywheelPistonSubsystem;
    private FlywheelSubsystem flywheelSubsystem;
    private LimelightSubsystem limelightSubsystem;
    private ShooterMath math = ShooterMath.getInstance();

    public FlywheelVelocityCommand(FlywheelPistonSubsystem flywheelPistonSubsystem, FlywheelSubsystem flywheelSubsystem, boolean solenoidStatus, LimelightSubsystem limelightSubsystem) {
        this.flywheelPistonSubsystem = flywheelPistonSubsystem;
        this.flywheelSubsystem = flywheelSubsystem;
        this.limelightSubsystem = limelightSubsystem;

        addRequirements(flywheelPistonSubsystem, flywheelSubsystem, limelightSubsystem);
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
        this.math.setPosition(flywheelPistonSubsystem.get() ? 31.4 : 39, limelightSubsystem.getDistance());
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
