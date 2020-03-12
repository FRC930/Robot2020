/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.endgamecommands;

//import java.util.logging.Logger;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DriveSubsystem;

//-------- COMMAND CLASS --------\\

public class ClimbCommand extends CommandBase {


    //You must include logger as a constant variable, and you must have logging in your files
    //private static final Logger logger = Logger.getLogger(ClimbCommand.class.getName());

    private DriveSubsystem driveSubsystem;    
    private Joystick driver;
    private int climbAxis;

    private double climbSpeed;
    
    public ClimbCommand(DriveSubsystem driveSubsystem, Joystick driver, int climbAxis){
        this.driveSubsystem = driveSubsystem;
        this.driver = driver;
        this.climbAxis = climbAxis;
        this.climbSpeed = 0;
        addRequirements(driveSubsystem);
    }

    //-------- METHODS --------\\
    
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {   
        if(Math.abs(driver.getRawAxis(climbAxis)) > 0.1) {
            climbSpeed = Math.pow(driver.getRawAxis(climbAxis), 3);
        } else {
            climbSpeed = 0;
        }

        driveSubsystem.runAt(climbSpeed, -climbSpeed);
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
} // end of class ClimbCommand 

