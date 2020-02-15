/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.turretcommads;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.TurretSubsystem;

//-------- COMMAND CLASS --------\\

public class JoystickTurretCommand extends CommandBase {

    //-------- DECLARATIONS --------\\

    private TurretSubsystem turretSubsystem;
    private Joystick coDriver;
    private int coDriverAxis;

    //-------- CONSTRUCTOR --------\\

    public JoystickTurretCommand(TurretSubsystem turretSubsystem, Joystick coDriver, int coDriverAxis) {
        this.turretSubsystem = turretSubsystem;
        this.coDriver = coDriver;
        this.coDriverAxis = coDriverAxis;

        addRequirements(turretSubsystem);
    }

    //-------- METHODS --------\\

    @Override
    public void execute() {

        double speed = Math.pow(coDriver.getRawAxis(coDriverAxis), 3);

        if (speed < 0) {
            if (turretSubsystem.getEncoderPosition() > 1500) 
                speed = 0;     
        } else if (speed > 0) {
            if (turretSubsystem.getEncoderPosition() < -1500) 
                speed = 0;      
        }

        turretSubsystem.setSpeed(speed);
    } // end of class execute()

    @Override
    public boolean isFinished() {
        return false;
    } 

} // end of class JoystickTurretCommand