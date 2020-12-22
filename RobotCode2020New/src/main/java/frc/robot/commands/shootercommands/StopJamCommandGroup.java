/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//-------- IMPORTS --------\\
package frc.robot.commands.shootercommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.hoppercommands.StopHopperStateCommand;
import frc.robot.commands.kickercommands.StopKickerCommand;
import frc.robot.commands.towercommands.ReverseTowerCommand;
import frc.robot.subsystems.KickerSubsystem;
import frc.robot.subsystems.TowerSubsystem;
//-------- COMMANDGROUP CLASS --------\\
/**
 * There are two constructors for teleop and auton. The first is for auton and does not require a value to end the command group.
 * The second takes a Joystick Button to stop the command group.
 */
public class StopJamCommandGroup extends ParallelRaceGroup {
    //-------- CONSTRUCTORS --------\\
    
    public StopJamCommandGroup(
        TowerSubsystem towerSubsystem, 
        KickerSubsystem kSubsystem) 
    {
        //Run all required commands in order so we can shoot.
        addCommands(//new CheckIfShotPossibleCommand(limeLight, flywheelPistonSubsystem),
            new SequentialCommandGroup(
                // new RampShooterCommand(flywheelSubsystem),
                // new RunFlywheelCommand(flywheelSubsystem, Constants.FLYWHEEL_SPEED),
                    //ShooterMath.getInstance(limeLight.getHorizontalOffset(), 
                    //limeLight.getDistance()).getVelocity()), 
                new ParallelCommandGroup(new StopHopperStateCommand(), new ReverseTowerCommand(towerSubsystem), new StopKickerCommand(kSubsystem))
            )
        );
    } // End of Constructor
} // End of Class
