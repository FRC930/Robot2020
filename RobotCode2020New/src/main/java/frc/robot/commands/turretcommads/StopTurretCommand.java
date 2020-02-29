/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.turretcommads;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.TurretSubsystem;

//-------- COMMAND CLASS --------\\

public class StopTurretCommand extends CommandBase {

    //-------- DECLARATIONS --------\\

    private TurretSubsystem turretSubsystem;

    //-------- CONSTRUCTOR --------\\

    public StopTurretCommand(TurretSubsystem turretSubsystem) {
        this.turretSubsystem = turretSubsystem;
        addRequirements(turretSubsystem);
    }

    //-------- METHODS --------\\

    @Override
    public void execute() {
        turretSubsystem.setSpeed(0.0);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
} // end of class StopTurretCommand