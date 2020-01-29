package frc.robot.commands.turretcommads;

import java.util.logging.Level;
import java.util.logging.Logger;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.VisionTracking;

public class AutoAimTurretCommand extends PIDCommand {
    public AutoAimTurretCommand(VisionTracking limeLight, TurretSubsystem turret) {
        super(new PIDController(0.0001, 0, 0), () -> {
            // TODO: Check for valid targets
            if (true) {
                return limeLight.getHorizontalOffset();
            }
            return 0;
        }, 0, (double output) -> {
            // Sets up a lambda (DoubleConsumer) to get the output from the PID controller
            if (output > 1) {
                output = 1;
            } else if (output < -1) {
                output = -1;
            }

            // Make sure that the turret does not turn past ~300° in either direction
            // Internal units. 3570 == ~300°
            if (output < 0) {
                if (turret.getEncoderPosition() > 3570) {
                    output = 0;
                }
            } else if (output > 0) {
                if (turret.getEncoderPosition() < -3570) {
                    output = 0;
                }
            }

            turret.setSpeed(output);
        }, turret, limeLight);

        this.getController().enableContinuousInput(-27, 27);

        this.getController().setTolerance(1);

        addRequirements(limeLight, turret);
    }
}