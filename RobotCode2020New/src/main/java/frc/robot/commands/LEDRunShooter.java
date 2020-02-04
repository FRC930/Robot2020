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
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.LEDSubsystem;

/**
 * An example command that uses an example subsystem.
 */
public class LEDRunShooter extends ParallelCommandGroup {
    private LEDSubsystem m_ledSubsystem;

    public LEDRunShooter(LEDSubsystem ledSubsystem)
    {
        /*
        m_ledSubsystem = ledSubsystem;
        addRequirements(m_ledSubsystem);
        addCommands(
            new LEDIdleFlash(m_ledSubsystem, 4),
            new WaitCommand(.2),
            new LEDIdleFlash(m_ledSubsystem, 3),
            new WaitCommand(.2)
        );      
        m_ledSubsystem = ledSubsystem;
        addRequirements(m_ledSubsystem);
        addCommands(
            new LEDIdleFlash(m_ledSubsystem,4),
            new  WaitCommand(.2),
            new LEDIdleFlash(m_ledSubsystem,1),
            new  WaitCommand(.2)
        );
        m_ledSubsystem = ledSubsystem;
        addRequirements(m_ledSubsystem);
        addCommands(
            new LEDIdleFlash(m_ledSubsystem,3),
            new  WaitCommand(.2),
            new LEDIdleFlash(m_ledSubsystem,5),
            new  WaitCommand(.2)
        );
        m_ledSubsystem = ledSubsystem;
        addRequirements(m_ledSubsystem);
        addCommands(
            new LEDIdleFlash(m_ledSubsystem,5),
            new  WaitCommand(.2),
            new LEDIdleFlash(m_ledSubsystem,4),
            new  WaitCommand(.2),
            new LEDIdleFlash(m_ledSubsystem,6),
            new  WaitCommand(.2)
        );
        m_ledSubsystem = ledSubsystem;
        addRequirements(m_ledSubsystem);
        addCommands(
            new LEDIdleFlash(m_ledSubsystem,5),
            new  WaitCommand(.2),
            new LEDIdleFlash(m_ledSubsystem,1),
            new  WaitCommand(.2)
        );
        */
        parallel(new LEDIdleFlash(m_ledSubsystem, 1), new );
    }

    @Override
    public boolean isFinished()
    {
        return false;
    }
}
