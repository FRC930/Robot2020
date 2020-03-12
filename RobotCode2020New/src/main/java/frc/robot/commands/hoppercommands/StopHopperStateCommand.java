/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.hoppercommands;

import edu.wpi.first.wpilibj2.command.CommandBase;

//-------- COMMAND CLASS --------\\

public class StopHopperStateCommand extends CommandBase {

    // -------- DECLARATIONS --------\\
    private boolean state;

    // private
    // -------- CONSTRUCTOR --------\\

    public StopHopperStateCommand() {
        state = false;
    }

    //-------- METHODS --------\\    

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        //System.out.println("Hopper state: " + state);
        state = !state;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {     

    }

    public boolean getState()
    {
        return state;
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
} //end of class StopHopperStateCommand