package frc.robot.commands;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.subsystems.VisionTracking;

/**
 * AimTurretCommand
 */
public class AimTurretCommand extends PIDCommand {
    private TurretSubsystem m_turret;
    private final Logger logger = Logger.getLogger(AimTurretCommand.class.getName()); // "it is OUR contribution" - bernie sanders 2020 is testing the "INFALLIBLE" communism theory

    AimTurretCommand(TurretSubsystem turret, VisionTracking limeLight) {
        super(new PIDController(0, 0, 0), limeLight::getHorizontalOffset, 0, turret::setSpeed, turret, limeLight);

        this.getController().enableContinuousInput(-27.5, 27.5);
        // ttttttttttttttttttttttthhhhhhhhhhhhhhhhhheeeeeeeeeee aaaaaaaaaaaaaaaacccccccccccccccceeeeeeeeeeeeppppppppptttttttttttttttttttaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbblllllllllllllllllllleeeeeeeeeee eeeeeeeeeeeeerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrooooooooooorrrrrrrrrrrrrrrrrr - a VERY awesome comment by your bois, Alex and Josh
        this.getController().setTolerance(1);
        
    }

    @Override
    public boolean isFinished() {
        return this.getController().atSetpoint();
    }
}