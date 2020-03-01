/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.colorwheelcommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ColorWheelSpinnerSubsystem;
import java.util.logging.*;

public class ColorWheelSpinnerCommand extends CommandBase {

    //-------- CONSTANTS --------\\
    
    // Creates an instance of the RotationalControlSubsystem subsystem
    private final ColorWheelSpinnerSubsystem m_subsystem;
    // Creates an instance of the logger class
    private static final Logger logger = Logger.getLogger(ColorWheelSpinnerCommand.class.getName());

    //-------- DECLARATIONS --------\\

    private final double SPEED = 0.5;

    public ColorWheelSpinnerCommand(ColorWheelSpinnerSubsystem subsystem) {
        m_subsystem = subsystem;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        logger.entering(ColorWheelSpinnerCommand.class.getName(), "initialize");
        // Sets the motor speed to the constant SPEED
        m_subsystem.setMotorSpeed(SPEED);
        logger.exiting(ColorWheelSpinnerCommand.class.getName(), "initialize");
    }

    @Override
    public void execute() {
        logger.entering(ColorWheelSpinnerCommand.class.getName(), "execute");
        // Gets the SparkMax values from the RotationalControlSubsystem subsystem        
        logger.log(Constants.LOG_LEVEL_FINE, "(spinning)");
        logger.exiting(ColorWheelSpinnerCommand.class.getName(), "execute");
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        // Logging stuff
        logger.entering(ColorWheelSpinnerCommand.class.getName(), "end");
        // Sets the motor speed to 0
        m_subsystem.setMotorSpeed(0.0);
        logger.exiting(ColorWheelSpinnerCommand.class.getName(), "end");
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        logger.entering(ColorWheelSpinnerCommand.class.getName(), "isFinished");
        logger.exiting(ColorWheelSpinnerCommand.class.getName(), "isFinished");
        return false;
    }
} // End of class