/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.colorwheelcommands.positionalcontrolcommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorSensorSubsystem;
import java.util.logging.*;

public class PositionalControlTrackerCommand extends CommandBase {

    // -------- CONSTANTS --------\\
    // Creates an instance of the ColorSensorSubsystem subsystem
    private final ColorSensorSubsystem m_subsystem;
    // Creates an instance of the logger class
    private Logger logger = Logger.getLogger(PositionalControlTrackerCommand.class.getName());
    // -------- DECLARATIONS --------\\

    // -------- CONSTRUCTOR --------\\
    public PositionalControlTrackerCommand(ColorSensorSubsystem subsystem) {
        m_subsystem = subsystem;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }

    // -------- METHODS --------\\

    @Override
    public void initialize() {
        logger.entering(this.getClass().getName(), "initialize");
        logger.exiting(this.getClass().getName(), "initialize");
    }

    @Override
    public void execute() {
        logger.entering(this.getClass().getName(), "execute");
        logger.exiting(this.getClass().getName(), "execute");
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        logger.entering(this.getClass().getName(), "end");
        logger.exiting(this.getClass().getName(), "end");
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        logger.entering(this.getClass().getName(), "isFinished");
        logger.exiting(this.getClass().getName(), "isFinished");
        return false;
    }
} // End of class