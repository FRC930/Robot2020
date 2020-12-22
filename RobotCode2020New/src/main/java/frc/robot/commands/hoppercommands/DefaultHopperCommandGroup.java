/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\
package frc.robot.commands.hoppercommands;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.HopperSubsystem;

//-------- COMMANDGROUP CLASS --------\\
public class DefaultHopperCommandGroup extends SequentialCommandGroup {
    //-------- CONSTRUCTORS --------\\
    
    public DefaultHopperCommandGroup(HopperSubsystem hSubsystem) {
        addCommands(
            new PrintCommand("STARTING DEFAULT HOPPER"),
            new SetHopperCommand(hSubsystem, Constants.HOPPER_DEFAULT_SPEED, false),
            new WaitCommand(1.25),
            new SetHopperCommand(hSubsystem, Constants.HOPPER_REVERSE_SPEED, true),
            new WaitCommand(0.5)
        );
    } // End of Constructor
} // End of Class
