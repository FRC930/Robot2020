/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.colorwheelcommands;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.subsystems.ColorSensorSubsystem;
import frc.robot.subsystems.RotationalControlSubsystem;

public class ColorWheelCommandGroup extends ParallelRaceGroup{
    
    //-------- CONSTRUCTOR --------\\

    public ColorWheelCommandGroup(ColorSensorSubsystem colorSensor, RotationalControlSubsystem rotationalControl){

        // Telling the scheduler that this command will use the ColorSensorSubsystem and RotationalControlSubsystem
        addRequirements(colorSensor);
        addRequirements(rotationalControl);
        // Telling the command to run these commands in parallel
        addCommands(
            new RotationalControlCommand(rotationalControl),
            new GetColorCommand(colorSensor));
    }
} // End of class