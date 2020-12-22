/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.endgamecommands;

import frc.robot.Constants;
import frc.robot.commands.shootercommands.flywheelcommands.KillFlywheelCommand;
import frc.robot.commands.turretcommads.SetTurretPositionCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.subsystems.TurretSubsystem;

//import java.util.logging.Logger;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

//-------- COMMANDGROUP CLASS --------\\

public class EndgameCommandGroup extends SequentialCommandGroup {

    //-------- CONSTANTS --------\\
    
    //private static final Logger logger = Logger.getLogger(ClimberArmCommand.class.getName());

    //-------- CONSTRUCTOR --------\\
    
    public EndgameCommandGroup(
        DriveSubsystem driveSubsystem,
        FlywheelSubsystem flywheelSubsystem,
        TurretSubsystem turretSubsystem
        )   
    {   
        
        addCommands(
            new ToggleShiftCommand(driveSubsystem),
            new KillFlywheelCommand(flywheelSubsystem),
            new SetTurretPositionCommand(turretSubsystem, Constants.TURRET_RIGHT_POSITION)
        );
       
    } // end of the constructor EndgameCommandGroup

    @Override
    public void end(boolean interrupted) {
      
    }

} // end of class EndgameCommandGroup
