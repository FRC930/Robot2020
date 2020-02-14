package frc.robot.commands.shootercommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.utilities.DeadbandMath;
import frc.robot.utilities.DeadbandMath.ShotType;

/**
 * CheckIfShotPossible
 */
public class CheckIfShotPossible extends CommandBase{
    private DeadbandMath math = new DeadbandMath();
    private LimelightSubsystem limeLight;
    private ShotType type;

    public CheckIfShotPossible(LimelightSubsystem limeLight) {
        this.limeLight = limeLight;
    }
    
    @Override
    public boolean isFinished() {

        //Set the shot type to the shooter.
        this.type = math.calculateShotType(limeLight.getHorizontalOffset(), limeLight.getDistance());

        // Return true if the shot type is best. We are not shooting at maybe or lower.
        if (this.type != ShotType.BEST) {
            return false;
        } else {
            return true;
        }
    }
}