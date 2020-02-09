/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.towercommands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.TowerSubsystem;
import frc.robot.Constants;

//-------- COMMAND CLASS --------\\

public class RunTowerCommand extends CommandBase {

    //-------- DECLARATIONS --------\\

    private TowerSubsystem m_TowerSubsystem;
    
    //-------- CONSTRUCTOR --------\\

    public RunTowerCommand(TowerSubsystem TowerSubsystem){
        m_TowerSubsystem = TowerSubsystem;
        addRequirements(m_TowerSubsystem);
    }

    //-------- METHODS --------\\
    
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_TowerSubsystem.setSpeed(Constants.TOWER_SPEED);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {  
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_TowerSubsystem.setSpeed(0.0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
} // end of class RunTowerCommand