/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.shootercommands;

import frc.robot.commands.kickercommands.StopKickerCommand;
import frc.robot.commands.towercommands.StopTowerCommand;

import frc.robot.subsystems.KickerSubsystem;
import frc.robot.subsystems.TowerSubsystem;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

//-------- COMMANDGROUP CLASS --------\\

public class StopTowerKickerCommandGroup extends ParallelCommandGroup {

    //-------- CONSTRUCTOR --------\\

    public StopTowerKickerCommandGroup(TowerSubsystem tSubsystem, KickerSubsystem kSubsystem) {

        //Stop the tower and kicker, the other subsystems will run at a default speed.
        addCommands(
            new ParallelCommandGroup(
                new StopTowerCommand(tSubsystem), 
                new StopKickerCommand(kSubsystem)
            )
        );
    } // End of Constructor
} // End of Class
