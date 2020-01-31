package frc.robot.commands.turretcommads;

import java.util.logging.Level;
import java.util.logging.Logger;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoAimTurretCommand extends PIDCommand {

    private final Logger logger = Logger.getLogger(AutoAimTurretCommand.class.getName());
    
    public AutoAimTurretCommand(LimelightSubsystem limeLight, TurretSubsystem turret) {
        super(new PIDController(0.01, 0, 0.001), () -> {

            // TODO: Check for valid targets
            if (true) {
                return limeLight.getHorizontalOffset();
            }
            return 0;
        }, 0, (double output) -> {

            SmartDashboard.putNumber("controller output", output);

            // Sets up a lambda (DoubleConsumer) to get the output from the PID controller
            if (output > 1) {
                output = 1;
            } else if (output < -1) {
                output = -1;
            }

            // Make sure that the turret does not turn past ~300° in either direction
            // Internal units. 3570 == ~300°

            SmartDashboard.putNumber("Encoder Position", turret.getEncoderPosition());

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

        logger.entering(getClass().getName(), "AutoAimTurretCommand");
        logger.log(Level.INFO, "horizontal offset: " + limeLight.getHorizontalOffset());
        logger.log(Level.INFO, "encoder position: " + turret.getEncoderPosition());

        this.getController().enableContinuousInput(-27, 27);

        this.getController().setTolerance(2);

        logger.setLevel(Level.INFO);

        addRequirements(limeLight, turret);
    }
}