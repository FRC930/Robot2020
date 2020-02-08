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
//import java.util.logging.*;

public class PositionalControlTrackerCommand extends CommandBase {

    //-------- CONSTANTS --------\\

    //-------- DECLARATIONS --------\\

    private GetColorCommand getColorCommand;
    private final ColorSensorSubsystem m_subsystem;

    //-------- CONSTRUCTOR --------\\

    public PositionalControlTrackerCommand(ColorSensorSubsystem subsystem) {
        m_subsystem = subsystem;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }

    //-------- METHODS --------\\

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        getColorCommand.rotationalTrackerCounter();
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
