/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.colorwheelcommands.positionalcontrolcommands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.commands.colorwheelcommands.ColorWheelSpinnerCommand;
import frc.robot.subsystems.ColorWheelSpinnerSubsystem;
import frc.robot.subsystems.ColorSensorSubsystem;

public class PositionalControlCommandGroup extends ParallelRaceGroup{
    
    //-------- CONSTRUCTOR --------\\

    public PositionalControlCommandGroup(ColorSensorSubsystem colorSensorSubsystem, ColorWheelSpinnerSubsystem colorWheelSpinner){

        // Telling the command to run these commands in parallel
        addCommands(
            // Tracks how many times the color has changed. 24 color changes = 3 rotations of the color wheel
            new PositionalControlTrackerCommand(colorSensorSubsystem),
            // Spins the color wheel with a motor
            new ColorWheelSpinnerCommand(colorWheelSpinner));
    }
} // End of class