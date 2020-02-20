/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.colorwheelcommands.positionalcontrolcommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorSensorSubsystem;
import frc.robot.commands.colorwheelcommands.GetColorCommand;
import java.util.logging.*;

public class PositionalControlTrackerCommand extends CommandBase {

    //-------- CONSTANTS --------\\

    // Creates an instance of the logger class
    private final Logger logger = Logger.getLogger(PositionalControlTrackerCommand.class.getName());

    //-------- DECLARATIONS --------\\

    private GetColorCommand getColorCommand;
    private ColorSensorSubsystem colorSensorSubsystem;

    //-------- CONSTRUCTOR --------\\

    public PositionalControlTrackerCommand(ColorSensorSubsystem colorSensorSubsystem) {
        this.colorSensorSubsystem = colorSensorSubsystem;
        addRequirements(colorSensorSubsystem);
    }

    //-------- METHODS --------\\

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        logger.entering(this.getClass().getName(), "execute");
        logger.exiting(this.getClass().getName(), "execute");
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}