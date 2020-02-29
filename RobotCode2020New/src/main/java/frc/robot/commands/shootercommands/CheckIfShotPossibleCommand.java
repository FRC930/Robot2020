/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.shootercommands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.FlywheelPistonSubsystem;

import frc.robot.utilities.DeadbandMath;
import frc.robot.utilities.DeadbandMath.DeadbandZone;
import frc.robot.utilities.DeadbandMath.ShotChance;
import frc.robot.utilities.ShooterMath;
import frc.robot.utilities.ShuffleboardUtility;
import frc.robot.utilities.ShooterMath.ShotOutcome;

import frc.robot.Constants;

//-------- COMMANDBASE CLASS --------\\

public class CheckIfShotPossibleCommand extends CommandBase {

    //-------- DECLARATIONS --------\\

    // --Utilities (Singletons)
    private DeadbandMath deadbandMath = DeadbandMath.getInstance();
    private ShooterMath shooterMath = ShooterMath.getInstance();
    private ShuffleboardUtility shuffleboardUtility = ShuffleboardUtility.getInstance();

    // --Subsystems
    private LimelightSubsystem limeLight;
    private FlywheelPistonSubsystem flywheelPistonSubsystem;

    // --Other
    private ShotOutcome shotOutcome;
    private DeadbandZone deadbandZone;
    private ShotChance shotChance;

    //-------- CONSTRUCTOR --------\\

    public CheckIfShotPossibleCommand(LimelightSubsystem limeLight, FlywheelPistonSubsystem flywheelPistonSubsystem) {
        this.limeLight = limeLight;
        this.flywheelPistonSubsystem = flywheelPistonSubsystem;

        shotOutcome = null;
        deadbandZone = null;
        shotChance = null;
    }
    
    //-------- COMMANDBASE METHODS --------\\

    @Override
    public boolean isFinished() {

        //Set the shot type to the shooter.
        shooterMath.setPosition((flywheelPistonSubsystem.get() ? Constants.FLYWHEEL_LOWER_ANGLE : Constants.FLYWHEEL_UPPER_ANGLE), limeLight.getDistance()); 
        this.shotOutcome = shooterMath.getPossibleShot();
        shuffleboardUtility.setShotType(ShooterMath.getShotType(shotOutcome));
        
        deadbandMath.setPosition(limeLight.getHorizontalOffset(), limeLight.getDistance());
        this.deadbandZone = deadbandMath.getDeadbandZone();
        this.shotChance = deadbandMath.getShotChance();
        
        // Return true if the shot types is best. We are not shooting at maybe or lower.
        if (this.shotOutcome == ShotOutcome.INNER && this.deadbandZone == DeadbandZone.GREEN && this.shotChance == ShotChance.HIGH) {
            return true;
        } else {
            return false;
        }
    } // end of method isFinished()
    
} //end of command class CheckIfShotPossibleCommand