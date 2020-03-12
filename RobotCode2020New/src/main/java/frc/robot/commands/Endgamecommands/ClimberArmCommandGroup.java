/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.endgamecommands;

import frc.robot.commands.endgamecommands.ClimberArmCommand;
import frc.robot.subsystems.ClimberArmSubsystem;
import frc.robot.triggers.TriggerCommand;

//import java.util.logging.Logger;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;

//-------- COMMANDGROUP CLASS --------\\

public class ClimberArmCommandGroup extends ParallelRaceGroup {

    //-------- CONSTANTS --------\\
    
    //private static final Logger logger = Logger.getLogger(ClimberArmCommand.class.getName());

    //-------- CONSTRUCTOR --------\\
    
    public ClimberArmCommandGroup(
        ClimberArmSubsystem climberArmSubsystem,
         Joystick coDriver,
         int coDriverAxis,
        JoystickButton RB)   
    {
        // adding commands to use for our climber arm
        
        addCommands(
            new TriggerCommand(RB),
            new ClimberArmCommand(climberArmSubsystem, coDriver, coDriverAxis)
        );
       
    } // end of the constructor ClimberArmCommandGroup
    

    @Override
    public void end(boolean interrupted) {
      
    }

} // end of class ClimberArmCommandGroup
