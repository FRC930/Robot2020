/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.colorwheelcommands.rotationalcontrolcommands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.ColorWheelSpinnerSubsystem;


public class RotationalControlCommandGroup extends ParallelRaceGroup{
    
    //-------- CONSTRUCTOR --------\\

    public RotationalControlCommandGroup(ColorWheelSpinnerSubsystem colorWheelSpinner, JoystickButton aButton){

        // Telling the command to run these commands in parallel
        addCommands(
            // Tracks how many times the color has changed. 24 color changes = 3 rotations of the color wheel
            new RotationalControlTrackerCommand(),
            // Spins the color wheel with a motor
            new RotationalControlSpinnerCommand(colorWheelSpinner),
            // Stops the tracker and the spinner early when the A button isn't being held
            new RotationalControlSpinnerKillSwitchCommand(aButton));
    }
} // End of class