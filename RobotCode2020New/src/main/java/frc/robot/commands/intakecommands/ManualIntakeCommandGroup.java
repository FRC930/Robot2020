/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//-------- IMPORTS --------\\
package frc.robot.commands.intakecommands;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.hoppercommands.RunHopperCommand;
import frc.robot.commands.hoppercommands.StopHopperStateCommand;
import frc.robot.commands.kickercommands.RunKickerCommand;
import frc.robot.commands.kickercommands.StopKickerCommand;
import frc.robot.commands.towercommands.ReverseTowerCommand;
import frc.robot.commands.towercommands.RunTowerCommand;
import frc.robot.commands.shootercommands.flywheelcommands.RampShooterCommand;
import frc.robot.commands.shootercommands.flywheelcommands.RunFlywheelCommand;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeMotorSubsystem;
import frc.robot.subsystems.IntakePistonSubsystem;
import frc.robot.subsystems.KickerSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.subsystems.TowerSubsystem;
import frc.robot.subsystems.TurretSubsystem;
import frc.robot.triggers.TriggerCommand;
import frc.robot.subsystems.FlywheelPistonSubsystem;
//-------- COMMANDGROUP CLASS --------\\
/**
 * There are two constructors for teleop and auton. The first is for auton and does not require a value to end the command group.
 * The second takes a Joystick Button to stop the command group.
 */
public class ManualIntakeCommandGroup extends ParallelRaceGroup {
    //-------- CONSTRUCTORS --------\\
    
    public ManualIntakeCommandGroup(
        IntakeMotorSubsystem intakeMotorSubsystem,
        IntakePistonSubsystem intakePistonSubsystem,
        Joystick stick,
        int axis
        ) 
    {
        //Run all required commands in order so we can shoot.
        addCommands(//new CheckIfShotPossibleCommand(limeLight, flywheelPistonSubsystem),
            new ParallelCommandGroup(new ManualIntakeCommand(intakeMotorSubsystem, stick, axis), new DeployIntakeCommand(intakePistonSubsystem, intakeMotorSubsystem))
        );
    } // End of Constructor
} // End of Class
