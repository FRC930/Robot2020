/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019-2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.endgamecommands;

import java.util.logging.Logger;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberArmSubsystem;
import frc.robot.Constants;

//-------- COMMAND CLASS --------\\

public class ClimberArmCommand extends CommandBase 

{
    //-------- CONSTANTS --------\\
    
    private static final Logger logger = Logger.getLogger(ClimberArmCommand.class.getName());

    //-------- DECLARATIONS --------\\
    private Joystick coDriver;
    private ClimberArmSubsystem climberArmSubsystem;    
    private double stickY;
    private double position;
    private int coDriverAxis;
    private double speed;
    //-------- CONSTRUCTOR --------\\

    public ClimberArmCommand (ClimberArmSubsystem climberArmSubsystem, Joystick coDriver, int coDriverAxis){
        this.climberArmSubsystem = climberArmSubsystem;
        addRequirements(climberArmSubsystem);
       
        this.coDriver = coDriver;
        this.coDriverAxis = coDriverAxis;
        stickY = 0;
    }


    //-------- METHODS --------\\
    
    @Override
    public void initialize() {   
        speed = 0;
    }

    // Runs everytime condisions are met(RB button is being held).
    @Override
    public void execute() {  

        stickY = coDriver.getRawAxis(coDriverAxis);
        position = climberArmSubsystem.getRawEncoderPosition();

        // if the coDriver pushes joystick up it will set the speed to the Climber Arm
        if(Math.abs(stickY) > 0.1 && position > Constants.CLIMBER_LIMIT) {  //&& position <= 0) {
            speed = -Math.pow(stickY, 3) * 0.75;
            //System.out.println("Speed " + speed);
            logger.log(Constants.LOG_LEVEL_INFO, "Absolute Stick axis value: " + stickY + " > " + 0.1);
        } else {
            speed = 0;
            logger.log(Constants.LOG_LEVEL_INFO, "Stick value too low to run");
        }

        this.climberArmSubsystem.setSpeed(speed);
        SmartDashboard.putNumber("co driver axis", stickY);
        SmartDashboard.putNumber("joystick ClimberArm speed", speed);
        SmartDashboard.putNumber("encoder position", position);
    }
    
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns false because we need to keep this command running
    @Override
    public boolean isFinished() {
        return false;
    }
} // end of class ClimberArmCommand