/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.LEDSubsystem;

/**
 * An example command that uses an example subsystem.
 */
public class LEDIdleFlash extends CommandBase {
    private LEDSubsystem m_ledSubsystem;

    private int flashType;

    public LEDIdleFlash(LEDSubsystem ledSubsystem, int flashType)
    {
        m_ledSubsystem = ledSubsystem;
        addRequirements(m_ledSubsystem);
        
        this.flashType = flashType;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() 
    {
        switch(flashType){
            case 0:
                m_ledSubsystem.setLEDs(255, 85, 0);
                SmartDashboard.putNumber("ledSet", 0);
            break;
            case 1:
                m_ledSubsystem.setLEDs(0, 0, 0);
                SmartDashboard.putNumber("ledSet", 1);
            break;
            case 2:
                m_ledSubsystem.setLEDs(225,225,225);
                SmartDashboard.putNumber("ledSet", 2);
            break;
            case 3:
                m_ledSubsystem.setLEDs(0,70,170);
                SmartDashboard.putNumber("ledSet", 3);
            break;
            case 4:
                m_ledSubsystem.setLEDs(225,225,0);
                SmartDashboard.putNumber("ledSet", 4);
            break;
            case 5:
                m_ledSubsystem.setLEDs(200,0,0);
                SmartDashboard.putNumber("ledSet", 5);
            break;
            case 6:
                m_ledSubsystem.setLEDs(0,225,0);
                SmartDashboard.putNumber("ledSet", 6);
            break;
            case 7:
                m_ledSubsystem.setLEDs(130,0,230);
                SmartDashboard.putNumber("ledSet", 7);
            break;
            case 8:
                m_ledSubsystem.setLEDs(225,90,160);
                SmartDashboard.putNumber("ledSet", 8);
            break;
            case 9:
                m_ledSubsystem.setLEDs(0,225,255);
                SmartDashboard.putNumber("ledSet", 9);
            break;
            case 10:
                m_ledSubsystem.setLEDs(5,200,90);
                SmartDashboard.putNumber("ledSet", 10);
            break;
            default:
                m_ledSubsystem.setLEDs(0, 0, 0);
            break;
        }
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() 
    {

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) 
    {

    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
}
