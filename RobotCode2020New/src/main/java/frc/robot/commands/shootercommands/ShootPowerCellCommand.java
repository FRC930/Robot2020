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
import frc.robot.Constants;
import frc.robot.commands.hoppercommands.SetHopperCommand;
import frc.robot.commands.kickercommands.RunKickerCommand;
import frc.robot.commands.shootercommands.flywheelcommands.RunFlywheelCommand;
import frc.robot.commands.towercommands.RunTowerCommand;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.KickerSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.FlywheelPistonSubsystem;
import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.subsystems.TowerSubsystem;
import frc.robot.utilities.ShooterMath;

public class ShootPowerCellCommand extends ParallelRaceGroup {

    public ShootPowerCellCommand(FlywheelSubsystem fSubsystem, TowerSubsystem tSubsystem, HopperSubsystem hSubsystem, 
        KickerSubsystem kSubsystem, LimelightSubsystem lLightSubsystem, FlywheelPistonSubsystem fAngleSubsystem)
    {
        //Run all required commands in order so we can shoot.
        addCommands(new CheckIfShotPossible(lLightSubsystem, fAngleSubsystem),
                new SequentialCommandGroup(
                        new ParallelCommandGroup(new RunFlywheelCommand(fSubsystem,
                                                        ShooterMath.getInstance(lLightSubsystem.getHorizontalOffset(), lLightSubsystem.getDistance()).getVelocity()),
                                                                new SetHopperCommand(hSubsystem,Constants.HOPPER_SHOOTING_SPEED,false),
                        new ParallelCommandGroup(new RunTowerCommand(tSubsystem), 
                                                 new RunKickerCommand(kSubsystem)))));
    } // End of Constructor
} // End of CLass
