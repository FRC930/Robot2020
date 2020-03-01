/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.hoppercommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.drivecommands.StopDriveCommand;
import frc.robot.commands.hoppercommands.StopHopperStateCommand;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.utilities.JamState;
import frc.robot.utilities.ShuffleboardUtility;
import frc.robot.Constants;

//-------- COMMAND CLASS --------\\

public class DefaultHopperCommand extends CommandBase {

    //-------- DECLARATIONS --------\\

    private HopperSubsystem m_HopperSubsystem;
    private JamState hopperState;
    private ShuffleboardUtility shuffleboardUtility;
    private StopHopperStateCommand m_StopHopperStateCommand;

    //private 
    //-------- CONSTRUCTOR --------\\

    public DefaultHopperCommand(HopperSubsystem HopperSubsystem, StopHopperStateCommand stopHopperStateCommand) {
        m_HopperSubsystem = HopperSubsystem;
        this.hopperState = JamState.getInstance();
        shuffleboardUtility = ShuffleboardUtility.getInstance();
        m_StopHopperStateCommand = stopHopperStateCommand;
        addRequirements(m_HopperSubsystem);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {     
        if(this.hopperState.getState()) {
            m_HopperSubsystem.setSpeed(0.0);
        } else {
            m_HopperSubsystem.setSpeed(Constants.HOPPER_DEFAULT_SPEED); 
        }
        shuffleboardUtility.setHopperSpeed(m_HopperSubsystem.getSpeed());
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
} //end of class DefaultHopperCommand