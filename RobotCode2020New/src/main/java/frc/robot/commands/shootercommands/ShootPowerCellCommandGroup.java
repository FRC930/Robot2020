/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//-------- IMPORTS --------\\
package frc.robot.commands.shootercommands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.hoppercommands.SetHopperCommand;
import frc.robot.commands.kickercommands.RunKickerCommand;
import frc.robot.commands.towercommands.RunTowerCommand;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.KickerSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.subsystems.TowerSubsystem;
import frc.robot.triggers.TriggerCommand;
import frc.robot.subsystems.FlywheelPistonSubsystem;
//-------- COMMANDGROUP CLASS --------\\
/**
 * There are two constructors for teleop and auton. The first is for auton and does not require a value to end the command group.
 * The second takes a Joystick Button to stop the command group.
 */
public class ShootPowerCellCommandGroup extends ParallelRaceGroup {
    //-------- CONSTRUCTORS --------\\
    
    public ShootPowerCellCommandGroup( 
        TowerSubsystem tSubsystem, 
        HopperSubsystem hSubsystem, 
        KickerSubsystem kSubsystem) 
    {
        //Run all required commands in order so we can shoot.
        addCommands(//new CheckIfShotPossibleCommand(limeLight, flywheelPistonSubsystem),
            new SequentialCommandGroup( 
                new ParallelCommandGroup(new SetHopperCommand(hSubsystem,Constants.HOPPER_SHOOTING_SPEED,false), new RunTowerCommand(tSubsystem), new RunKickerCommand(kSubsystem))
            )
        );
    } // End of Auton Constructor
    
    public ShootPowerCellCommandGroup(
        FlywheelSubsystem fSubsystem, 
        TowerSubsystem tSubsystem, 
        HopperSubsystem hSubsystem, 
        KickerSubsystem kSubsystem, 
        LimelightSubsystem lLightSubsystem, 
        FlywheelPistonSubsystem fPistonSubsystem,
        JoystickButton ZR)   
    {
        //Run all required commands in order so we can shoot.
        addCommands(//new CheckIfShotPossibleCommand(limeLight, flywheelPistonSubsystem),
            new TriggerCommand(ZR),
            new SequentialCommandGroup(
                // new RampShooterCommand(flywheelSubsystem),
                // new RunFlywheelCommand(flywheelSubsystem, Constants.FLYWHEEL_SPEED),
                    //ShooterMath.getInstance(limeLight.getHorizontalOffset(), 
                    //limeLight.getDistance()).getVelocity()), 
                new ParallelCommandGroup(new SetHopperCommand(hSubsystem,Constants.HOPPER_SHOOTING_SPEED,false), new RunTowerCommand(tSubsystem), new RunKickerCommand(kSubsystem))
            )
        );
    } // End of Teleop Constructor

} // End of Class
