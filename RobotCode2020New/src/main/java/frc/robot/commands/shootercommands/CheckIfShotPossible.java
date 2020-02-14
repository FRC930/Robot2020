package frc.robot.commands.shootercommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.utilities.DeadbandMath;
import frc.robot.utilities.DeadbandMath.DeadbandZone;
import frc.robot.utilities.DeadbandMath.ShotChance;
import frc.robot.utilities.ShooterMath;
import frc.robot.utilities.ShooterMath.ShotType;

/**
 * CheckIfShotPossible
 */
public class CheckIfShotPossible extends CommandBase{
    private DeadbandMath deadbandMath = DeadbandMath.getInstance();
    private ShooterMath shooterMath = new ShooterMath();
    private LimelightSubsystem limeLight;
    private ShotType shotType;
    private DeadbandZone deadbandZone;
    private ShotChance shotChance;

    public CheckIfShotPossible(LimelightSubsystem limeLight) {
        this.limeLight = limeLight;
        shotType = null;
        deadbandZone = null;
        shotChance = null;
    }
    
    @Override
    public boolean isFinished() {

        //Set the shot type to the shooter.
        this.shotType = shooterMath.(limeLight.getHorizontalOffset(), limeLight.getDistance());

        
        deadbandMath.setPosition(limeLight.getHorizontalOffset(), limeLight.getDistance());
        this.deadbandZone = deadbandMath.getDeadbandZone();
        this.shotChance = deadbandMath.getShotChance();

        // Return true if the shot type is best. We are not shooting at maybe or lower.
        if (this.type != ShotType.BEST) {
            return false;
        } else {
            return true;
        }
    }
}