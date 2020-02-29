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
import frc.robot.utilities.ShuffleboardUtility;
import frc.robot.Constants;

//-------- COMMAND CLASS --------\\

public class DefaultHopperCommand extends CommandBase {

    //-------- DECLARATIONS --------\\

    private HopperSubsystem m_HopperSubsystem;
    private ShuffleboardUtility shuffleboardUtility;
    private KillHopperStateCommand m_KillHopperStateCommand;

    //private 
    //-------- CONSTRUCTOR --------\\

    public DefaultHopperCommand(HopperSubsystem HopperSubsystem, KillHopperStateCommand killHopperStateCommand) {
        m_HopperSubsystem = HopperSubsystem;
        shuffleboardUtility = ShuffleboardUtility.getInstance();
        m_KillHopperStateCommand = killHopperStateCommand;
        addRequirements(m_HopperSubsystem);
    }

    //-------- METHODS --------\\    

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {     
        if(m_KillHopperStateCommand.getState()) {
            m_HopperSubsystem.setSpeed(0.0);
        } else {
            m_HopperSubsystem.setSpeed(Constants.HOPPER_DEFAULT_SPEED); 
        }
        shuffleboardUtility.setHopperSpeed(m_HopperSubsystem.getSpeed());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
} //end of class DefaultHopperCommand