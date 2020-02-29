/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.shootercommands.flywheelcommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.utilities.JamState;
import frc.robot.Constants;

//-------- COMMAND CLASS --------\\

public class DefaultFlywheelCommand extends CommandBase {

    //-------- DECLARATIONS --------\\

    private FlywheelSubsystem m_FlywheelSubsystem;
    private JamState state = JamState.getInstance();

    //-------- CONSTRUCTOR --------\\

    public DefaultFlywheelCommand(FlywheelSubsystem flywheelSubsystem) {
        m_FlywheelSubsystem = flywheelSubsystem;
        addRequirements(m_FlywheelSubsystem);
    }

    //-------- COMMANDBASE METHODS --------\\

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_FlywheelSubsystem.setSpeed(Constants.FLYWHEEL_SPEED);     //Set flywheel to default speed.
    }

    @Override
    public void execute() {
      if (state.getState()) {
        m_FlywheelSubsystem.setSpeed(0.0);
      } else {
        m_FlywheelSubsystem.setSpeed(Constants.FLYWHEEL_SPEED);
      }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }

} // end of class RunDefaultFlywheelCommand
