/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//-------- IMPORTS --------\\

package frc.robot.commands.hoppercommands;

import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.HopperSubsystem;
import frc.robot.utilities.ShuffleboardUtility;
import frc.robot.Constants;

//-------- COMMAND CLASS --------\\
 
public class RunHopperCommand extends CommandBase {

    //-------- DECLARATIONS --------\\
    private HopperSubsystem m_HopperSubsystem;
    private ShuffleboardUtility shuffleboardUtility;
    private JoystickButton zr;
    private double counter;

    private final int COUNTER_LIMIT = 1;
    
    //-------- CONSTRUCTOR --------\\

    public RunHopperCommand(HopperSubsystem HopperSubsystem,JoystickButton ZR){
        m_HopperSubsystem = HopperSubsystem;
        shuffleboardUtility = ShuffleboardUtility.getInstance();
        zr = ZR;
        addRequirements(m_HopperSubsystem);
    }

    //-------- METHODS --------\\

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        //m_HopperSubsystem.setSpeed(Constants.HOPPER_SHOOTING_SPEED);
        counter = 0;
        //System.out.println("hopper init xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {    
        counter++;

        SmartDashboard.putNumber("hopper counter execute", counter);

        if(counter > COUNTER_LIMIT) {
            m_HopperSubsystem.setSpeed(Constants.HOPPER_SHOOTING_SPEED);
        } else {
            m_HopperSubsystem.setSpeed(0.0);
        }
        shuffleboardUtility.setHopperSpeed(m_HopperSubsystem.getSpeed());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return !zr.get();
    }
} // end of class RunHopperCommand