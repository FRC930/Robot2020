/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.shootercommands;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.hoppercommands.RunHopperCommand;
import frc.robot.commands.kickercommands.RunKickerCommand;
import frc.robot.commands.towercommands.RunTowerCommand;
import frc.robot.commands.shootercommands.flywheelcommands.RunFlywheelCommand;

import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.KickerSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.subsystems.TowerSubsystem;

import frc.robot.utilities.ShooterMath;

import frc.robot.subsystems.FlywheelPistonSubsystem;

//-------- COMMANDGROUP CLASS --------\\

public class ShootPowerCellCommandGroup extends ParallelRaceGroup {
    //-------- CONSTRUCTOR --------\\
    public ShootPowerCellCommandGroup(
        FlywheelSubsystem flywheelSubsystem, 
        TowerSubsystem towerSubsystem, 
        HopperSubsystem hopperSubsystem, 
        KickerSubsystem kickerSubsystem, 
        LimelightSubsystem limeLight, 
        FlywheelPistonSubsystem flywheelPistonSubsystem,
        JoystickButton ZR)   
    {
        //Run all required commands in order so we can shoot.
        addCommands(//new CheckIfShotPossibleCommand(limeLight, flywheelPistonSubsystem),
            new SequentialCommandGroup(
                new RunFlywheelCommand(flywheelSubsystem, Constants.FLYWHEEL_SPEED),
                    //ShooterMath.getInstance(limeLight.getHorizontalOffset(), 
                    //limeLight.getDistance()).getVelocity()), 
                new ParallelCommandGroup(new RunHopperCommand(hopperSubsystem, ZR), new RunTowerCommand(towerSubsystem), new RunKickerCommand(kickerSubsystem))
            )
        );
        // addCommands(new CheckIfShotPossibleCommand(limeLight, flywheelPistonSubsystem),
        //      new ParallelCommandGroup(
                
        //         new SequentialCommandGroup(new WaitCommand(0.5), new RunKickerCommand(kickerSubsystem)),//new WaitCommand(0.5), new RunKickerCommand(kickerSubsystem)),
        //              //ShooterMath.getInstance(limeLight.getHorizontalOffset(), 
        //              //limeLight.getDistance()).getVelocity()), 
        //         new ParallelCommandGroup(new RunHopperCommand(hopperSubsystem, ZR), new RunTowerCommand(towerSubsystem), new RunFlywheelCommand(flywheelSubsystem, 0.7))
        //      )
        //  );
    } // end of the constructor ShootPowerCellCommandGroup

}   // end of class ShootPowerCellCommandGroup
