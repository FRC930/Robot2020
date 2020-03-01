/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.shootercommands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.FlywheelPistonSubsystem;
import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.utilities.ShooterMath;

//-------- COMMANDBASE CLASS --------\\
//The shooter velocity command will set the speed of the shooter based on the calculations returned from the math class

public class FlywheelVelocityCommand extends CommandBase {

    //-------- DECLARATIONS --------\\
    
    // --Subsystems
    private FlywheelPistonSubsystem flywheelPistonSubsystem;
    private LimelightSubsystem limeLightSubsystem;

    // --Utilities
    private ShooterMath shooterMathUtil = ShooterMath.getInstance();

    //-------- CONSTRUCTOR --------\\

    public FlywheelVelocityCommand(FlywheelPistonSubsystem fPistonSubsystem, FlywheelSubsystem fSubsystem, boolean solenoidStatus, LimelightSubsystem lLightSubsystem) {
        flywheelPistonSubsystem = fPistonSubsystem;    
        limeLightSubsystem = lLightSubsystem;

        addRequirements(fPistonSubsystem, fSubsystem, lLightSubsystem);
    } // End of Constructor

    //-------- COMMANDBASE METHODS --------\\

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // FlywheelSubsystem get angle
        // Limelight get distance
        this.shooterMathUtil.setPosition(flywheelPistonSubsystem.get() ? 31.4 : 39, limeLightSubsystem.getDistance());
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }

} // End of Class
