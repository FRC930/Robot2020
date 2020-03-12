/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.hoppercommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopperSubsystem;

//-------- COMMAND CLASS --------\\

public class SetHopperCommand extends CommandBase {

    //-------- DECLARATIONS --------\\

    private HopperSubsystem m_HopperSubsystem;
    private double speed;
    private boolean isInverted;

    //private 
    //-------- CONSTRUCTOR --------\\

    public SetHopperCommand(HopperSubsystem HopperSubsystem, double speed, boolean isInverted) {
        m_HopperSubsystem = HopperSubsystem;
        this.speed = speed;
        this.isInverted = isInverted;
        addRequirements(m_HopperSubsystem);
    }

    //-------- METHODS --------\\    

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        if(isInverted){
            m_HopperSubsystem.setSpeed(-speed);
        }
        else{
            m_HopperSubsystem.setSpeed(speed);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }
} //end of class DefaultHopperCommand