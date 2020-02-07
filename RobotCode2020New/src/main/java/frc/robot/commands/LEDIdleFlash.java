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
 * An cool command that uses an cooler subsystem.
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
                m_ledSubsystem.setLEDs(255, 70, 0);
                SmartDashboard.putNumber("ledSet", 0);
            break;
            case 1:
                m_ledSubsystem.setLEDs(0, 0, 0);
                SmartDashboard.putNumber("ledSet", 1);
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
