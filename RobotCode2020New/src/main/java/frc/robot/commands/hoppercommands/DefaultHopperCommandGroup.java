/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.HopperSubsystem;

//-------- COMMANDGROUP CLASS --------\\
/**
 * There are two constructors for teleop and auton. The first is for auton and does not require a value to end the command group.
 * The second takes a Joystick Button to stop the command group.
 */
public class DefaultHopperCommandGroup extends SequentialCommandGroup {
    //-------- CONSTRUCTORS --------\\
    
    public DefaultHopperCommandGroup(HopperSubsystem hSubsystem) {
        //Run all required commands in order so we can shoot.
        addCommands(
            // new SetHopperCommand(hSubsystem, Constants.HOPPER_DEFAULT_SPEED, false),
            // new WaitCommand(2),
            // new SetHopperCommand(hSubsystem, Constants.HOPPER_REVERSE_SPEED, true),
            // new WaitCommand(1)
        );
    } // End of Auton Constructor
} // End of Class
