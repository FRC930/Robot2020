/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.turretcommads;

import java.util.logging.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.TurretSubsystem;

//-------- COMMAND CLASS --------\\

public class JoystickTurretCommand extends CommandBase {

    //-------- DECLARATIONS --------\\

    private static final Logger logger = Logger.getLogger(JoystickTurretCommand.class.getName());

    private TurretSubsystem turretSubsystem;
    private Joystick coDriver;
    private int coDriverAxis;
    private double stickX;

    //-------- CONSTRUCTOR --------\\

    public JoystickTurretCommand(TurretSubsystem turretSubsystem, Joystick coDriver, int coDriverAxis) {
        this.turretSubsystem = turretSubsystem;
        this.coDriver = coDriver;
        this.coDriverAxis = coDriverAxis;
        stickX = 0;

        addRequirements(turretSubsystem);
    }

    //-------- METHODS --------\\

    @Override
    public void execute() {

        double speed;

        stickX = coDriver.getRawAxis(coDriverAxis);
 
        logger.log(Level.INFO, "stickX = "+stickX);
        if(Math.abs(stickX) > Constants.JOYSTICK_TURRET_DEADBAND) {
            logger.log(Level.INFO, "stickX >  Constants.JOYSTICK_TURRET_DEADBAND("+Constants.JOYSTICK_TURRET_DEADBAND+")");
            speed = Math.pow(stickX, 3) * 0.5;
        } else {
            logger.log(Level.INFO, "stickX <  Constants.JOYSTICK_TURRET_DEADBAND("+Constants.JOYSTICK_TURRET_DEADBAND+")");
            speed = 0;
        }

        SmartDashboard.putNumber("co driver axis", stickX);
        SmartDashboard.putNumber("joystick turret speed", speed);

        // we pass in a negative speed to match Kyle's joystick-+
        turretSubsystem.setSpeed(-speed);

        

    } // end of class execute()

    @Override
    public boolean isFinished() {
        return false;
    } 

} // end of class JoystickTurretCommand