/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.hoppercommands;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.HopperSubsystem;
import frc.robot.Constants;

//-------- COMMAND CLASS --------\\

public class RunHopperCommand extends CommandBase {

    // -------- DECLARATIONS --------\\
    private HopperSubsystem m_HopperSubsystem;

    // -------- CONSTRUCTOR --------\\

    public RunHopperCommand(HopperSubsystem HopperSubsystem) {
        m_HopperSubsystem = HopperSubsystem;
        addRequirements(m_HopperSubsystem);
    }

    // -------- METHODS --------\\

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_HopperSubsystem.setSpeed(Constants.HOPPER_SHOOTING_SPEED);
    }
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
} // end of class RunHopperCommand