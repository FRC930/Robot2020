package frc.robot.commands.shootercommands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.LimelightSubsystem;

import frc.robot.utilities.DeadbandMath;
import frc.robot.utilities.DeadbandMath.DeadbandZone;
import frc.robot.utilities.DeadbandMath.ShotChance;
import frc.robot.utilities.ShooterMath;
import frc.robot.utilities.ShooterMath.ShotOutcome;

import frc.robot.subsystems.FlywheelPistonSubsystem;

/**
 * CheckIfShotPossible
 */
public class CheckIfShotPossible extends CommandBase{

    private DeadbandMath deadbandMath = DeadbandMath.getInstance();
    private ShooterMath shooterMath = ShooterMath.getInstance();

    private LimelightSubsystem limeLight;
    private FlywheelPistonSubsystem flywheelPistonSubsystem;

    private ShotOutcome shotOutcome;
    private DeadbandZone deadbandZone;
    private ShotChance shotChance;

    public CheckIfShotPossible(LimelightSubsystem limeLight, FlywheelPistonSubsystem flywheelPistonSubsystem) {
        this.limeLight = limeLight;
        this.flywheelPistonSubsystem = flywheelPistonSubsystem;
        shotOutcome = null;
        deadbandZone = null;
        shotChance = null;
    }
    
    @Override
    public boolean isFinished() {

        //Set the shot type to the shooter.
        shooterMath.setPosition(flywheelPistonSubsystem.get() ? 31.4 : 39, limeLight.getDistance()); 
        this.shotOutcome = shooterMath.getPossibleShot();
        
        deadbandMath.setPosition(limeLight.getHorizontalOffset(), limeLight.getDistance());
        this.deadbandZone = deadbandMath.getDeadbandZone();
        this.shotChance = deadbandMath.getShotChance();

        // Return true if the shot types is best. We are not shooting at maybe or lower.
        if (this.shotOutcome == ShotOutcome.INNER && this.deadbandZone == DeadbandZone.GREEN && this.shotChance == ShotChance.HIGH) {
            return true;
        } else {
            return false;
        }
    }
}