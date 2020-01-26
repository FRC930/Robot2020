package frc.robot.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.VisionTracking;

/**
 * <h3>AimTurretCommand</h3>
 * 
 * <p>This command will be the default command of the turret subsystem.</p>
 * <p>It is a PID command that will use the LimeLight to align itself to the turret</p>
 */
public class AimTurretCommand extends PIDCommand {
    /**
     * Use this to log messages
     */
    private final Logger logger = Logger.getLogger(AimTurretCommand.class.getName()); // "it is OUR contribution" - bernie sanders 2020 is testing the "INFALLIBLE" communism theory

    /**
     * This constructor needs the turret that it will be using and the LimeLight to get the values from
     * @param turret the turret subsystem that controls the turret
     * @param limelight the reference to the VisionTracking class that is used to get values from the LimeLight
     */
    public AimTurretCommand(TurretSubsystem turret/*, VisionTracking limeLight*/) {
        // Call the super constructor to initialize the PID controller
        // TODO: figure out what values to use for the PID controller
        super(new PIDController(0.001, 0, 0), turret::getEncoderPosition, 750, (double speed) -> {
            // Sets up a lambda (DoubleConsumer) to get the output from the PID controller
            if (speed > 1) {
                speed = 1;
            } else if (speed < -1) {
                speed = -1;
            }

            // Make sure that the turret does not turn past ~300° in either direction
            // Internal units. 3570 == ~300°
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

        // Enable the PID Controller to always be tuning to the value
        this.getController().enableContinuousInput(-3000, 3000);
        // TODO: find out what an acceptable tolerance level is
        // ttttttttttttttttttttttthhhhhhhhhhhhhhhhhheeeeeeeeeee aaaaaaaaaaaaaaaacccccccccccccccceeeeeeeeeeeeppppppppptttttttttttttttttttaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbblllllllllllllllllllleeeeeeeeeee eeeeeeeeeeeeerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrooooooooooorrrrrrrrrrrrrrrrrr - a VERY awesome comment by your bois, Alex and Josh
        this.getController().setTolerance(1);

        logger.log(Level.FINE, "Starting turret PID controller");

        // Add the needed subsystems to the class requirements
        addRequirements(/*limeLight, */turret);
    }
}