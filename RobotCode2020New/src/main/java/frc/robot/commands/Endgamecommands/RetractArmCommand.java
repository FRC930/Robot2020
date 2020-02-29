/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.Endgamecommands;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ClimberArmSubsystem;

//-------- COMMAND CLASS --------\\

public class RetractArmCommand extends CommandBase {

    //-------- CONSTANTS --------\\

    //You must include logger as a constant variable, and you must have logging in your files
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    //-------- DECLARATIONS --------\\

    private ClimberArmSubsystem climberArmSubsystem;    
    
    //-------- CONSTRUCTOR --------\\

    public RetractArmCommand(ClimberArmSubsystem climberArmSubsystem){
        this.climberArmSubsystem = climberArmSubsystem;
        addRequirements(climberArmSubsystem);
    }

    //-------- METHODS --------\\
    
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {   
        climberArmSubsystem.setSpeed(Constants.CLIMBER_RETRACT_SPEED);  
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
} // end of class ClimberArmCommand 

