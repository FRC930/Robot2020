/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.controlcommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

import static frc.robot.triggers.ManualModeTrigger.IN_MANUAL_MODE;

//-------- COMMAND CLASS --------\\

public class ToggleManualCommand extends CommandBase {

    //-------- DECLARATIONS --------\\
    
    //-------- CONSTRUCTOR --------\\

    public ToggleManualCommand() {
    }

    //-------- METHODS --------\\
    
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        SmartDashboard.putBoolean("Safety", IN_MANUAL_MODE);
        IN_MANUAL_MODE = !IN_MANUAL_MODE;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {  
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
} // end of class ToggleManualCommand