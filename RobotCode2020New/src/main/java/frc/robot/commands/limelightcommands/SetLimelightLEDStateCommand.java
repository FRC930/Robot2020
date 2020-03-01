/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.limelightcommands;

import java.util.logging.Level;
import java.util.logging.Logger;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.LimelightSubsystem;

//-------- COMMAND CLASS --------\\

public class SetLimelightLEDStateCommand extends CommandBase {


    //You must include logger as a constant variable, and you must have logging in your files
    private static final Logger logger = Logger.getLogger(SetLimelightLEDStateCommand.class.getName());

    private LimelightSubsystem limelightSubsystem;    

    private int state;
    
    // Constructor
    public SetLimelightLEDStateCommand(LimelightSubsystem limelightSubsystem, int state){
        this.limelightSubsystem = limelightSubsystem;
        this.state = state;
        addRequirements(limelightSubsystem);
    }

    //-------- METHODS --------\\
    
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {   
        logger.log(Level.INFO, "LED State = " + state);
        limelightSubsystem.setLightMode(state);
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
        return false;
    }
} // end of class SetLimelightLEDStateCommand

