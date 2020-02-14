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
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        double speed = Math.pow(this.driver.getRawAxis(0), 3);
        /*if (speed < 0) {
            if (turret.getEncoderPosition() > 1500) {
                speed = 0;
            }
        } else if (speed > 0) {
            if (turret.getEncoderPosition() < -1500) {
                speed = 0;
            }
        }*/
        turret.setSpeed(speed);
        System.out.println("running manual turret");
        //SmartDashboard.putNumber("TurretRotation", turret.getEncoderPosition());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}