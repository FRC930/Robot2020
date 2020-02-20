/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.colorwheelcommands.rotationalcontrolcommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;

import java.util.logging.*;

public class RotationalControlSpinnerKillSwitchCommand extends CommandBase{

    //-------- CONSTANTS --------\\
    private Logger logger = Logger.getLogger(RotationalControlSpinnerKillSwitchCommand.class.getName());
    //-------- DECLARATIONS --------\\
    // Refers to the instance of the button in the RobotContainer
    JoystickButton aButton;

    //-------- CONSTRUCTOR --------\\

    public RotationalControlSpinnerKillSwitchCommand(JoystickButton aButton) {
        // Makes a reference of the A button from the RobotContainer
        this.aButton = aButton;
    }
    
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

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
        // Kills the color wheel spinner early when the A button is let go
        logger.entering(this.getClass().getName(), "isFinished");
        // Logs when the kill switch activates
        logger.log(Constants.LOG_LEVEL_FINE, "Kill Switch!");
        logger.exiting(this.getClass().getName(), "isFinished");
        return !aButton.get();
    }
} // End of class