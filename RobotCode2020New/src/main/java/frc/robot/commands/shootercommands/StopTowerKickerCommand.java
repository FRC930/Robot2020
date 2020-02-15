/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shootercommands;

import frc.robot.commands.kickercommands.StopKickerCommand;
import frc.robot.commands.towercommands.StopTowerCommand;
import frc.robot.subsystems.KickerSubsystem;
import frc.robot.subsystems.TowerSubsystem;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

/**
 * An example command that uses an example subsystem.
 */
public class StopTowerKickerCommand extends ParallelCommandGroup{

    public StopTowerKickerCommand(TowerSubsystem towerSubsystem, KickerSubsystem kickerSubsystem) 
    {
        //addRequirements(towerSubsystem, kickerSubsystem);

        //Stop the tower and kicker, the other subsystems will run at a default speed.
        addCommands(new ParallelCommandGroup(new StopTowerCommand(towerSubsystem), 
                                             new StopKickerCommand(kickerSubsystem)));
    }
}
