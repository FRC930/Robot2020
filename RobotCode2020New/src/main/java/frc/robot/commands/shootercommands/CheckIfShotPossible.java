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
        this.type = math.calculateShotType(limeLight.getHorizontalOffset(), limeLight.getDistance());
        if (this.type != ShotType.BEST) {
            return false;
        } else {
            return true;
        }
    }
}