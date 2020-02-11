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
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.TowerSubsystem;
import frc.robot.utilities.ShooterMath;

public class ShootPowerCellCommand extends ParallelRaceGroup {

    public ShootPowerCellCommand(ShooterSubsystem shooterSubsystem, TowerSubsystem towerSubsystem,
            HopperSubsystem hopperSubsystem, KickerSubsystem kickerSubsystem, LimelightSubsystem limeLight) {
        addRequirements(shooterSubsystem, towerSubsystem, hopperSubsystem, limeLight);

        addCommands(new CheckIfShotPossible(limeLight),
                new SequentialCommandGroup(
                        new ParallelCommandGroup(new RunShooterCommand(shooterSubsystem,
                                                        new ShooterMath(limeLight.getHorizontalOffset(), limeLight.getDistance()).getVelocity()),
                                                 new RunHopperCommand(hopperSubsystem)),
                        new ParallelCommandGroup(new RunTowerCommand(towerSubsystem), 
                                                 new RunKickerCommand(kickerSubsystem))));
    }
}
