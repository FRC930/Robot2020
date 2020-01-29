/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class DoneCommand extends CommandBase {
    private LEDDone isDone;
    private boolean reset;

    public DoneCommand(LEDDone isDone, boolean reset)
    {
        this.isDone = isDone;
        this.reset = reset;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize()
    {

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() 
    {
        if(reset)
        {
            isDone.isDone = false;
        }
        else
        {
            isDone.isDone = true;
        }
        System.out.println("Executed:" + isDone.isDone);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) 
    {
        
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        System.out.println("Finished:" + isDone.isDone);
        return true;
    }
}
