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
import frc.robot.commands.shootercommands.flywheelcommands.RampShooterCommand;
import frc.robot.commands.shootercommands.flywheelcommands.RunFlywheelCommand;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.KickerSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.subsystems.TowerSubsystem;
import frc.robot.triggers.TriggerCommand;

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
                new TriggerCommand(ZR),
                new RampShooterCommand(flywheelSubsystem),
                new RunFlywheelCommand(flywheelSubsystem, Constants.FLYWHEEL_SPEED),
                    //ShooterMath.getInstance(limeLight.getHorizontalOffset(), 
                    //limeLight.getDistance()).getVelocity()), 
                new ParallelCommandGroup(new RunHopperCommand(hopperSubsystem), new RunTowerCommand(towerSubsystem), new RunKickerCommand(kickerSubsystem))
            )
        );
    } // end of the constructor ShootPowerCellCommandGroup
    

    @Override
    public void end(boolean interrupted) {
        System.out.println("### PARALLEL RACE GROUP ENDING ###");
    }

} // end of class ShootPowerCellCommandGroup
