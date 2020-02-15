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

import frc.robot.Constants;

//-------- COMMANDBASE CLASS --------\\
//The shooter velocity command will set the speed of the shooter based on the calculations returned from the math class

public class FlywheelVelocityCommand extends CommandBase {

    //-------- DECLARATIONS --------\\
    
    // --Subsystems
    private FlywheelPistonSubsystem flywheelPistonSubsystem;
    private FlywheelSubsystem flywheelSubsystem;
    private LimelightSubsystem limelightSubsystem;

    // --Utilities
    private ShooterMath math = ShooterMath.getInstance();

    //-------- CONSTRUCTOR --------\\

    public FlywheelVelocityCommand(FlywheelPistonSubsystem flywheelPistonSubsystem, FlywheelSubsystem flywheelSubsystem, boolean solenoidStatus, LimelightSubsystem limelightSubsystem) {
        this.flywheelPistonSubsystem = flywheelPistonSubsystem;
        this.flywheelSubsystem = flywheelSubsystem;
        this.limelightSubsystem = limelightSubsystem;

        addRequirements(flywheelPistonSubsystem, flywheelSubsystem, limelightSubsystem);
    }

    //-------- COMMANDBASE METHODS --------\\

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        this.math.setPosition((flywheelPistonSubsystem.get() ? Constants.FLYWHEEL_LOWER_ANGLE : Constants.FLYWHEEL_UPPER_ANGLE), limelightSubsystem.getDistance());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
}
