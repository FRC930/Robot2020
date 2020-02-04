package frc.robot.commands.turretcommads;

import java.util.logging.Level;
import java.util.logging.Logger;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.LimelightSubsystem.LimelightPipelines;
import frc.robot.subsystems.LimelightSubsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoAimTurretCommand extends PIDCommand {

    private final Logger logger = Logger.getLogger(AutoAimTurretCommand.class.getName());
    
    public AutoAimTurretCommand(LimelightSubsystem limeLight, TurretSubsystem turret) {
        // TODO: Make comment explaining the constructor
        super(new PIDController(0.019, 0.006, 0.0011), () -> {
            // double horizOff = limeLight.getHorizontalOffset();
            // if (limeLight.getValidTargets() && horizOff < 12 && horizOff > -12) {
            //     limeLight.setPipeline(LimelightPipelines.MID_PIPELINE);
            //     SmartDashboard.putString("Pipeline", "Close pipeline");
            // } else {
            //     limeLight.setPipeline(LimelightPipelines.CLOSE_PIPELINE);
            //     SmartDashboard.putString("Pipeline", "Far pipeline");
            // }

            SmartDashboard.putNumber("horiz off", limeLight.getHorizontalOffset());
            if (limeLight.getValidTargets()) {
                return limeLight.getHorizontalOffset();
            }
            return 0.0;
        }, 0.0, (double output) -> {
            // Sets up a lambda (DoubleConsumer) to get the output from the PID controller
            if (output > 1) {
                output = 1;
            } else if (output < -1) {
                output = -1;
            }

            SmartDashboard.putNumber("controller", output);
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
        }, turret, limeLight); // End of super constructor

        logger.entering(this.getClass().getName(), "AutoAimTurretCommand");
        logger.log(Level.INFO, "horizontal offset: " + limeLight.getHorizontalOffset());
        logger.log(Level.INFO, "encoder position: " + turret.getEncoderPosition());

        this.getController().enableContinuousInput(-27, 27);

        this.getController().setTolerance(2);

        addRequirements(limeLight, turret);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
} // End AutoAimTurretCommand class