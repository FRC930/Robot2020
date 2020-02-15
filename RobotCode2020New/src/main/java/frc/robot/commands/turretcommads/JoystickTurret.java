package frc.robot.commands.turretcommads;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

/**
 * TurretSpeedCommand is a temporary speed controller
 */
public class JoystickTurret extends CommandBase {
    private TurretSubsystem turret;
    private Joystick driver;

    public JoystickTurret(TurretSubsystem turret, Joystick driver) {
        this.turret = turret;
        this.driver = driver;

        addRequirements(turret);
    }

    @Override
    public void execute() {
        double speed = Math.pow(this.driver.getRawAxis(0), 3);
       
        turret.setSpeed(speed);
        System.out.println("running manual turret");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}