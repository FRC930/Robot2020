package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

/**
 * TurretSpeedCommand is a temporary speed controller
 */
public class TurretSpeedCommand extends CommandBase {
    private TurretSubsystem turret;

    public TurretSpeedCommand(TurretSubsystem turret) {
        this.turret = turret;
    }

    public void setSpeed(double speed) {
        if (Math.abs(speed) > 1) {
            this.turret.setSpeed(speed / Math.abs(speed));
        }
    }
}