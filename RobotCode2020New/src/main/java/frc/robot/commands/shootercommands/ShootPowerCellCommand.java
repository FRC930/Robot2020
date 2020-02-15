/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shootercommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.hoppercommands.RunHopperCommand;
import frc.robot.commands.kickercommands.RunKickerCommand;
import frc.robot.commands.towercommands.RunTowerCommand;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.KickerSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.FlywheelAngleSubsystem;
import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.subsystems.TowerSubsystem;
import frc.robot.utilities.ShooterMath;

public class ShootPowerCellCommand extends ParallelRaceGroup {

    public ShootPowerCellCommand(FlywheelSubsystem flywheelSubsystem, TowerSubsystem towerSubsystem,
            HopperSubsystem hopperSubsystem, KickerSubsystem kickerSubsystem, LimelightSubsystem limeLight, FlywheelAngleSubsystem flywheelAngleSubsystem)
    {
        //Run all required commands in order so we can shoot.
        addCommands(new CheckIfShotPossible(limeLight, flywheelAngleSubsystem),
                new SequentialCommandGroup(
                        new ParallelCommandGroup(new RunFlywheelCommand(flywheelSubsystem,
                                                        ShooterMath.getInstance(limeLight.getHorizontalOffset(), limeLight.getDistance()).getVelocity()),
                                                 new RunHopperCommand(hopperSubsystem)),
                        new ParallelCommandGroup(new RunTowerCommand(towerSubsystem), 
                                                 new RunKickerCommand(kickerSubsystem))));
    }
}
