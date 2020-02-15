/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.limelightcommands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.LimelightSubsystem.LimelightPipelines;

//-------- COMMAND CLASS --------\\

public class SetPipelineCommand extends CommandBase {

    private LimelightSubsystem m_LimelightSubsystem;

    private double distance;

    private final double DISTANCE_THRESHOLD = 10; //TODO: find this threshold, 10 is a placeholder

    public SetPipelineCommand(LimelightSubsystem limelightSubsystem){

        m_LimelightSubsystem = new LimelightSubsystem();
        addRequirements(m_LimelightSubsystem);

    }

    //-------- METHODS --------\\
    
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        
        distance = m_LimelightSubsystem.getDistance();

        if(distance > DISTANCE_THRESHOLD) {
            m_LimelightSubsystem.setPipeline(LimelightPipelines.ZOOM_2X);
        } else {
            m_LimelightSubsystem.setPipeline(LimelightPipelines.NO_ZOOM);
        }
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
} // end of command
