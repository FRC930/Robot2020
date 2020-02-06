package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

/**
 * TurretSpeedCommand is a temporary speed controller
 */
public class TurretSpeedCommand extends CommandBase {
    private TurretSubsystem turret;
    private double speed;

    public TurretSpeedCommand(TurretSubsystem turret, double speed) {
        this.turret = turret;
        this.speed = speed;
    }

    @Override
    public void execute() {
        turret.setSpeed(this.speed);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}