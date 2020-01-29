package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

/**
 * TurretSpeedCommand is a temporary speed controller
 */
public class StopTurretCommand extends CommandBase {
    private TurretSubsystem turret;

    public StopTurretCommand(TurretSubsystem turret) {
        this.turret = turret;
    }

    @Override
    public void execute() {
        turret.setSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}