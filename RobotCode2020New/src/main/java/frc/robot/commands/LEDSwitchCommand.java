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
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.subsystems.LEDSubsystem;

/**
 * An example command that uses an example subsystem.
 */
public class LEDSwitchCommand extends SequentialCommandGroup {
    private LEDSubsystem m_ledSubsystem;
    private AddressableLED m_leds;
    private AddressableLEDBuffer m_ledsBuffer;

    public LEDSwitchCommand(LEDSubsystem ledSubsystem, LEDDone isDone)
    {
        m_ledSubsystem = ledSubsystem;
        addRequirements(m_ledSubsystem);

        addCommands(new DoneCommand(isDone, true),
                    new LEDIdleFlash(m_ledSubsystem, 0),
                    new WaitCommand(1),
                    new LEDIdleFlash(m_ledSubsystem, 1),
                    new WaitCommand(1),
                    new DoneCommand(isDone, false));
    }
}
