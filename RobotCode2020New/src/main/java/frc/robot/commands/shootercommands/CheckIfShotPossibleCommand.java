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
import frc.robot.utilities.ShooterMath.ShotOutcome;
//import frc.robot.utilities.ShuffleboardUtility;

import frc.robot.Constants;

//-------- COMMANDBASE CLASS --------\\

public class CheckIfShotPossibleCommand extends CommandBase {

    //-------- DECLARATIONS --------\\

    // --Utilities (Singletons)
    private DeadbandMath deadbandMathUtil = DeadbandMath.getInstance();
    private ShooterMath shooterMathUtil = ShooterMath.getInstance();
    //private ShuffleboardUtility shuffleboardUtil = ShuffleboardUtility.getInstance();

    // --Subsystems
    private LimelightSubsystem limeLightSubsystem;
    private FlywheelPistonSubsystem flywheelPistonSubsystem;

    // --Other
    private ShotOutcome shotOutcome;
    private DeadbandZone deadbandZone;
    private ShotChance shotChance;

    //-------- CONSTRUCTOR --------\\

    public CheckIfShotPossibleCommand(LimelightSubsystem lLightSubsystem, FlywheelPistonSubsystem fPistonSubsystem) {
        limeLightSubsystem = lLightSubsystem;
        flywheelPistonSubsystem = fPistonSubsystem;

        shotOutcome = ShotOutcome.NONE;
        deadbandZone = DeadbandZone.RED;
        shotChance = ShotChance.MISS;
    } // End of Constructor
    
    //-------- COMMANDBASE METHODS --------\\

    @Override
    public boolean isFinished() {

        //Set the shot type to the shooter.
        shooterMathUtil.setPosition((flywheelPistonSubsystem.get() ? Constants.FLYWHEEL_LOWER_ANGLE : Constants.FLYWHEEL_UPPER_ANGLE), limeLightSubsystem.getDistance()); 
        shotOutcome = shooterMathUtil.getPossibleShot();
        
        deadbandMathUtil.setPosition(limeLightSubsystem.getHorizontalOffset(), limeLightSubsystem.getDistance());
        deadbandZone = deadbandMathUtil.getDeadbandZone();
        shotChance = deadbandMathUtil.getShotChance();
        
        // Return true if the shot types is best. We are not shooting at maybe or lower.
        if (shotOutcome == ShotOutcome.INNER && deadbandZone == DeadbandZone.GREEN && shotChance == ShotChance.HIGH) {
            return true;
        } else {
            return false;
        }
    } // End of isFinished() method
    
} // End of Class