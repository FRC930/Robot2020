package frc.robot.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.VisionTracking;

/**
 * AimTurretCommand
 * 
 * <p>This command will be the default command of the turret subsystem.</p>
 */
public class AimTurretCommand extends PIDCommand {
    private final Logger logger = Logger.getLogger(AimTurretCommand.class.getName()); // "it is OUR contribution" - bernie sanders 2020 is testing the "INFALLIBLE" communism theory

    public AimTurretCommand(TurretSubsystem turret/*, VisionTracking limeLight*/) {
        // TODO: figure out what values to use for the PID controller
        super(new PIDController(0.001, 0, 0), turret::getEncoderPosition, 750, (double speed) -> {
            if (speed > 1) {
                speed = 1;
            } else if (speed < -1) {
                speed = -1;
            }

            if (speed < 0) {
                if (turret.getEncoderPosition() > 1500) {
                    speed = 0;
                }
            } else if (speed > 0) {
                if (turret.getEncoderPosition() < -1500) {
                    speed = 0;
                }
            }

            turret.setSpeed(speed);
        }, turret/*, limeLight*/);

        this.getController().enableContinuousInput(-3000, 3000);
        // TODO: find out what an acceptable tolerance level is
        // ttttttttttttttttttttttthhhhhhhhhhhhhhhhhheeeeeeeeeee aaaaaaaaaaaaaaaacccccccccccccccceeeeeeeeeeeeppppppppptttttttttttttttttttaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbblllllllllllllllllllleeeeeeeeeee eeeeeeeeeeeeerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrooooooooooorrrrrrrrrrrrrrrrrr - a VERY awesome comment by your bois, Alex and Josh
        this.getController().setTolerance(1);

        logger.log(Level.FINE, "Starting turret PID controller");

        addRequirements(/*limeLight, */turret);
    }
}