/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.hoppercommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.utilities.ShuffleboardUtility;

//-------- COMMAND CLASS --------\\

public class ReverseHopperCommand extends CommandBase {

    //-------- DECLARATIONS --------\\

    private HopperSubsystem m_HopperSubsystem;
    private ShuffleboardUtility shuffleboardUtility;
    private JoystickButton home;
    
    //-------- CONSTRUCTOR --------\\

    public ReverseHopperCommand(HopperSubsystem hopperSubsystem, JoystickButton home) {
        m_HopperSubsystem = hopperSubsystem;
        shuffleboardUtility = ShuffleboardUtility.getInstance();
        this.home = home;
    }   

    //-------- METHODS --------\\

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {   
        m_HopperSubsystem.setSpeed(-0.35);
        shuffleboardUtility.setHopperSpeed(m_HopperSubsystem.getSpeed());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return !home.get();
    }
} // end of class ReverseHopperCommand