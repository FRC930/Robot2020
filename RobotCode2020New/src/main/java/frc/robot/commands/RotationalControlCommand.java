/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.RotationalControlSubsystem;
import com.revrobotics.CANSparkMax;
import java.util.logging.*;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;

public class RotationalControlCommand extends CommandBase {

    //-------- CONSTANTS --------\\
    // Creates an instance of the RotationalControlSubsystem subsystem
    private final RotationalControlSubsystem m_subsystem;
    // Creates an instance of the logger class
    private static final Logger logger = Logger.getLogger(GetColorCommand.class.getName());

    //-------- DECLARATIONS --------\\


    public RotationalControlCommand(RotationalControlSubsystem subsystem) {
        m_subsystem = subsystem;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }


    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        logger.entering(getClass().getName(), "initialize");
        logger.exiting(getClass().getName(), "initialize");
    }

    @Override
    public void execute() {
        logger.entering(getClass().getName(), "execute");
        // Gets the SparkMax values from the RotationalControlSubsystem subsystem
        final double spark = m_subsystem.getMotorSpeed();
        logger.log(Level.INFO, "(spinning)");
        logger.exiting(getClass().getName(), "execute");
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        // Logging stuff
        logger.entering(getClass().getName(), "end");
        logger.exiting(getClass().getName(), "end");
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        logger.entering(getClass().getName(), "isFinished");
        logger.exiting(getClass().getName(), "isFinished");
        return false;
    }
}