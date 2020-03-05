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

public class StopHopperCommand extends CommandBase {

    // -------- DECLARATIONS --------\\
    HopperSubsystem hopperSubsystem;

    // private
    // -------- CONSTRUCTOR --------\\

    public StopHopperCommand(HopperSubsystem hopperSubsystem) {
        this.hopperSubsystem = hopperSubsystem;
    }

    //-------- METHODS --------\\    

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        //System.out.println("Hopper state: " + state);
        hopperSubsystem.setSpeed(0.0);
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
} //end of class StopHopperStateCommand