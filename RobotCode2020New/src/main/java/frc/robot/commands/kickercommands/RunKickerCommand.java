/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.kickercommands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.KickerSubsystem;
import frc.robot.utilities.JamState;
import frc.robot.Constants;

import java.util.logging.Logger;


 
//-------- COMMAND CLASS --------\\

public class RunKickerCommand extends CommandBase {

    // -------- DECLARATIONS --------\\

    private KickerSubsystem kickerSubsystem;
    private JamState state = JamState.getInstance();
    private boolean kickerFlag = false;

    // -------- CONSTRUCTOR --------\\
    private static final Logger logger = Logger.getLogger(RunKickerCommand.class.getName());
    
    //-------- CONSTRUCTOR --------\\

    public RunKickerCommand(KickerSubsystem kSubsystem) {
        kickerSubsystem = kSubsystem;
        addRequirements(kSubsystem);
    }

    @Override
    public void initialize() {
        kickerSubsystem.setSpeed(0.0);
        kickerFlag = false;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // if (state.getState()) {
        //     kickerSubsystem.setSpeed(0.0);
        // } else {
            //If we havent started kicker...
            if(kickerFlag == false) {
                double encoderValue = kickerSubsystem.getEncoder();

                //Calculate if we are in position
                boolean value = calculateStart(encoderValue);
                if (true == value) {
                    //Start kicker once
                    kickerFlag = true;
                    kickerSubsystem.setSpeed(Constants.KICKER_SPEED);
                }
            }
        //}
    }

    public boolean calculateStart(double encoderValue)
    {
        int value = (int)((encoderValue - Math.floor(encoderValue)) * 100);

        //Math for finding the location of where kicker should start
        if (((Math.round((value / 10) % 2))) == 0) {
            return true;
        } else {
            return false;
        }
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
} // end of class RunKickerCommand